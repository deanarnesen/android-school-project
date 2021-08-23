package presenter;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.JoinGameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import cs340.tickettoride.ListGameActivity;
import service.GameListService;

public class GameListPresenter implements IGameViewPresenter, Observer, IPresenter {
    private ListGameActivity view;
    private JoinGameLobbyModel model;
    private ArrayList<GameListing> currentGames;
    private ClientModelRoot modelRoot;
    private GameListService service;
    boolean beenToLobby;

    public GameListPresenter(ListGameActivity view){
        this.view = view;
        model = JoinGameLobbyModel.getInstance();
        model.addObserver(this);
        modelRoot = ClientModelRoot.getInstance();
        modelRoot.addObserver(this);
        //testlist was called here
        currentGames = new ArrayList<>();
        currentGames.addAll(model.getCurrentGames());
        view.setList(currentGames);
        service = new GameListService();
        beenToLobby = false;
    }

    /*update game list by calling the appropriate view function*/
    @Override
    public void update(Observable o, Object arg) {
        if(arg == UpdateType.SCREEN_STATE){
            PlayerGameState state = modelRoot.getCurrentScreen();
            if(state == PlayerGameState.GAME_LOBBY){
                if(!beenToLobby){
                    view.goToGameLobby();
                    beenToLobby = true;
                }

            }
        }
        else if(arg == UpdateType.DISPLAY_ERROR){
            view.errorMessage(modelRoot.getCurrentError());
        }
        else if(arg == UpdateType.GAME_ADDED){
            //only update view if view is active
            currentGames.add(model.getCurrentGames().lastElement());
            if(modelRoot.getCurrentScreen() == PlayerGameState.JOIN_GAME_LOBBY){
                view.updateList(currentGames);
            }
        }else if (arg == UpdateType.INCREASED_PLAYER_COUNT){

            ArrayList<GameListing> listGames = new ArrayList<>(model.getCurrentGames());
            view.updateList(listGames);

        }else if (arg == UpdateType.DECREMENT_PLAYER_COUNT){
            ArrayList<GameListing> listGames = new ArrayList<>(model.getCurrentGames());
            view.updateList(listGames);

        }


    }


    @Override
    public void displayError(String error) {
        //display a toast on the game view screen //todo
    }

    private void testList(){
        Vector<GameListing> listOfGameListings = new Vector<>();
        GameListing test = new GameListing();
        test.setGameName("THIS IS A TEST");
        test.setNumPlayers(100);
        test.setMaxNumOfPlayers(200);
        test.setStarted(false);
        listOfGameListings.add(test);
        GameListing test2 = new GameListing();

        test2.setGameName("LAMANITES");
        test2.setNumPlayers(50);
        test2.setMaxNumOfPlayers(100);
        test2.setStarted(false);
        listOfGameListings.add(test2);
        model.addInitialGames(listOfGameListings);
    }

    public void joinGame(GameListing gameListing){
        modelRoot.setCurrentScreen(PlayerGameState.JOIN_GAME_LOBBY);
        service.joinGame(gameListing);
    }

    public void setScreenCreateGame(){
        modelRoot.setCurrentScreen(PlayerGameState.CREATE_GAME);
    }
}