package presenter;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;
import com.bignerdranch.android.shareddata.commands.serverModels.CreateGameRequest;

import java.util.Observable;
import java.util.Observer;

import cs340.tickettoride.CreateGameActivity;
import service.GameListService;


public class CreateGamePresenter implements ICreateGamePresenter, IPresenter, Observer {
    private GameListing gameListing;
    private CreateGameRequest request;
    private GameListService listService;
    private CreateGameActivity view;
    private ClientModelRoot modelRoot;
    boolean beenToLobby;


    public CreateGamePresenter(CreateGameActivity view){
        request = new CreateGameRequest();
        this.view = view;
        modelRoot = ClientModelRoot.getInstance();
        modelRoot.addObserver(this);
        gameListing = new GameListing();
        listService = new GameListService();
        beenToLobby = false;

    }
    @Override
    public void setGameName(String name) {
        request.setGameName(name);
    }

    /* 2, 3, 4, or 5 only, will call error msg if not*/
    @Override
    public void setMaxPlayers(int number) {
        request.setMaxPlayers(number);
    }

    /* will change to gameListing lobby screen if valid, error msg otherwise */
    @Override
    public void confirmCreation() {
        listService.addGame(gameListing);
    }

    @Override
    public void displayError(String error) {
        //todo
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg == UpdateType.SCREEN_STATE){
            if(!beenToLobby){
                view.goToGameLobby();
                beenToLobby = true;
            }

        }
        if(arg == UpdateType.DISPLAY_ERROR){

        }
    }



    public void createGame(String gameName, int maxNumb){
        request.setUsername(modelRoot.getClientUserName());
        setGameName(gameName);
        setMaxPlayers(maxNumb);
        listService.createGame(request);
    }

    /* @Override
    public void setPrivacy(Boolean isPrivate) {

    }*/

    /*
    @Override
    public void setPassword(String password) {

    }*/
}
