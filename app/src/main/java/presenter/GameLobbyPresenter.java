package presenter;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.StartGameRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import cs340.tickettoride.GameLobbyActivity;
import service.ChatService;
import service.GameLobbyService;
import service.ServerProxy;

public class GameLobbyPresenter implements IGameLobbyPresenter, Observer, IPresenter {
    private GameLobbyActivity view;
    private PlayerCard playerCard;
    private GameLobbyModel lobbyModel;
    private ClientModelRoot modelRoot;
    private ChatService chatService;
    private ArrayList<ChatMessage> messages;
    private ArrayList<Player> players;
    private ArrayList<Color> colors;
    private boolean inGame = false;

    @Override
    public void displayError(String error) {
        //display error //todo
    }

    private class PlayerCard{
        Color playerColor;

        public Color getPlayerColor() {
            return playerColor;
        }

        public void setPlayerColor(Color playerColor) {
            this.playerColor = playerColor;
        }
    }


    public void setView (GameLobbyActivity view_in){
        this.view = view_in;
    }


    public GameLobbyPresenter(GameLobbyActivity view){
        this.view = view;
        this.playerCard = new PlayerCard();
        lobbyModel = GameLobbyModel.getInstance();
        lobbyModel.addObserver(this);
        modelRoot = ClientModelRoot.getInstance();
        modelRoot.addObserver(this);
        messages = new ArrayList<>(lobbyModel.getAllMessages());
        players = new ArrayList<>(lobbyModel.getCurrentPlayers());
        initializeGameLobby();
        initializeGameChat();
    }

    /*update available colors, game name, and chat contents*/
    @Override
    public void update(Observable o, Object arg) {
         if(arg == UpdateType.CHAT){
             messages.clear();
             messages.addAll(lobbyModel.getAllMessages());
             view.updateMessages(messages);
         }
         if(arg == UpdateType.PLAYER_ADDED){
            players.clear();
            players.addAll(lobbyModel.getCurrentPlayers());
            view.updatePlayers(players);
         }
         if(arg == UpdateType.SCREEN_STATE){
             players.clear();
             players.addAll(lobbyModel.getCurrentPlayers());
             view.updatePlayers(players);
             if(modelRoot.getCurrentScreen() == PlayerGameState.IN_GAME){
                 startGame();
             }
         }
         if(arg == UpdateType.DISPLAY_ERROR){
            view.errorMessage(modelRoot.getCurrentError());
         }
    }

    @Override
    public void setPlayerColor(Color color) {

    }

    @Override
    public void sendChat(String text) {
        String user = modelRoot.getClientUserName();
        String game = lobbyModel.getGame().getGameName();//this causes an error for ONE of the clients
        ChatService service = new ChatService();
        ChatMessage message = new ChatMessage(user, text, game);
        service.addChat(message);
    }

    @Override
    public void startButton() {
        String userName = modelRoot.getClientUserName();
        String gameName = lobbyModel.getGame().getGameName();
        StartGameRequest request = new StartGameRequest(userName,gameName);
        String data = new Gson().toJson(request);
        GameModel demo = GameModel.getInstance();
        demo.updateCurrentPlayers();

        ServerProxy.getInstance().send(new CommandData(CommandType.START_GAME_COMMAND, data));
    }

    public void startGame(){
        if(!ClientModelRoot.getInstance().isInGame()){
            view.moveToMap();
            ClientModelRoot.getInstance().setInGame(true);
        }

    }

    @Override
    public void changeGameName(String text) {

    }

    public void initializeGameChat()
    {
        view.setChat(messages);
    }

    public void initializeGameLobby(){
        view.setPlayers(players);
    }

    public String cancelOutOfGame()
    {
        return null;
    }

    public void leaveGame(GameListing gameListing){

        GameLobbyService service = new GameLobbyService();
        service.leaveGame(gameListing);
    }
}
