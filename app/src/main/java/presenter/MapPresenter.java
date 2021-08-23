package presenter;


import android.os.AsyncTask;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.clientModels.AllRoutes;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.CardsChosen;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ClientGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.RouteColor;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import cs340.tickettoride.IMapInterface;
import service.ChatService;
import service.EndTurnService;
import service.ServerProxy;

/**
 * @Invariable model != null
 * @Invariable modelRoot != null
 */
public class MapPresenter implements Observer {
    private IMapInterface map;
    private GameModel model;
    private ClientModelRoot modelRoot;
    private int demoNum;
    private PlayerState playerState;
    private AllRoutes allRoutes;


    /**
     * Builds a basic MapPresenter object using the IMapInterface implementation given
     * @param map an implementation of the IMapInterface class
     *
     * @Pre There is a valid GameModel instance.
     *
     * @Post The MapPresenter is created with the implementation of IMapInterface provided
     * @Post the MapPresenter is set to observe the GameModel observable
     * @Post Creates a new MapPresenter object
     */
    public MapPresenter(IMapInterface map){
        this.map = map;
        model = GameModel.getInstance();
        model.addObserver(this);
        modelRoot = ClientModelRoot.getInstance();
        modelRoot.addObserver(this);
        this.demoNum = 0;
        playerState = InactivePlayer.getInstance(); //initially not your turn


    }

    /**
     * A basic default constructor.
     *
     * @Post creates a new MapPresenter object
     */
    public MapPresenter() {
        modelRoot = ClientModelRoot.getInstance();
    }

    /**
     * This update function is called by the Presenters observable object and appropriate changes
     * are made to the view.
     * @param o The observable object that called notified this observer
     * @param arg The type of update that the observable made
     *
     * @Pre o != null
     * @Pre o.type() == GameModel
     * @Pre arg was originally a UpdateType enum object
     *
     * @Post the presenter makes the changes to the view visible for the user.
     */
    @Override
    public void update(Observable o, Object arg) {
        UpdateType type = (UpdateType)arg;
        switch (type){
            case PLAYER_INFO:
                HashMap<String, ClientGamePlayer> opponents = model.getGameMembers();
                for(ClientGamePlayer opp : opponents.values()){
                    updatePublicInfo(opp);
                }
                break;
            case HAND_UPDATE:
                ServerGamePlayer player = model.getClientPlayerInfo();
                map.setPlayerDestCards(player.getDestinationCards());
                map.setPlayerTrainCards(player.getTrainCarCards());
                break;
            case DECK_UPDATE:
                map.updateDestDeck(model.getDestCardDeckCount());
                map.updateTrainDeck(model.getTrainCardDeckCount());
                map.setFaceupTrainCards(model.getFaceUpCards());
                break;
            case CHAT:
                map.updateChat(model.getChatLog());
                break;
            case DRAW_DEST_CARDS:
                map.promptDestCards(model.getCardsShown().getThreeDestCards());
                break;
            case TURN_UPDATE:
                map.setActivePlayer(model.getActivePlayer());
                if(model.getActivePlayer().equals(modelRoot.getClientUserName())){
                    setState(ActivePlayer.getInstance());
                }
                break;
            case LAST_ROUND_NOTIFICATION:
                map.displayLastRoundNotificaiton();
                break;
            case ROUTE_UPDATE:
                for(Route route : model.getAllRoutes().getRoutes()){
                    if(route.isTaken()){
                        map.claimRoute(route.getPlayerTakenBy(), route);
                    }
                }
                break;
            case SCREEN_STATE:
                if(modelRoot.getCurrentScreen() == PlayerGameState.END_GAME){
                    map.toEndScreen();
                }
            default:
                break;
        }
    }

    /**
     * Updates the player info on the screen
     * @param player the player object that you want to update
     *
     * @Pre player != null
     * @Pre player is a member of the game
     * @Pre player has username, numDestCards, numTrainCards, numTrainCars, and score
     *
     * @Post updates the information in the view with the information in player
     */
    private void updatePublicInfo(ClientGamePlayer player){
        map.setDestCardNumber(player.getUsername(), player.getNumDestCards());
        map.setTrainCardNumber(player.getUsername(), player.getNumTrainCards());
        map.setPlayerCarsLeft(player.getUsername(), player.getNumTrainCars());
        map.setPlayerScore(player.getUsername(), player.getScore());
        if (player.isTurnState()){
            map.setActivePlayer(player.getUsername());
        }
    }

    /**
     * This function is used to display routes connected to a city
     * @param sourceCity a city in the map
     * @return an ArrayList of routes
     *
     * @Pre sourceCity is a valid city in the map
     *
     * @Post return an array list of all the routes with the source city as a start or end point
     */
    public ArrayList<Route> getAdjRoutes(String sourceCity){
        return model.getAdjacents(sourceCity);
    }


    /**
     * Sends a chat message to the server to be distributed
     * @param text the text of the message.
     *
     * @Pre text must be a valid string
     *
     * @Post a distribute chat message is sent to the server and each users chat is eventually updated
     */
    public void sendChat(String text) {
        String user = modelRoot.getClientUserName();
        ChatService service = new ChatService();
        ChatMessage message = new ChatMessage(user, text, GameLobbyModel.getInstance().getGame().getGameName());
        service.addChat(message);
    }

    /**
     * Sends the destination cards a user chose and the ones they did not choose back to the server
     * @param cardChosen the cards that the client choose
     * @param rejectedCards the cards that the client rejected
     *
     * @Pre cardChosen != null
     * @Pre cardChosen contains valid destination card objects
     * @Pre rejectedCards != null
     * @Pre rejecteCards contains only valid destination card objects
     *
     * @Post add the chosen destination cards to the users hand
     */
    public void destCardsChosen(Vector<DestinationCard> cardChosen, Vector<DestinationCard> rejectedCards){
        Gson gson = new Gson();
        String selected = gson.toJson(new CardsChosen(cardChosen, rejectedCards));
        CommandData data = new CommandData(CommandType.RETURN_CHOSEN, selected);
        ServerProxy.getInstance().send(data);
    }

    /**
     * Add a route to the users list of routes
     * @param id the id of the route
     *
     * @Pre id = valid route id
     *
     * @Post the route is added to the users list of routes
     */
    public void claimRoute(Integer id){
        //todo comment back in if looking to implement gray route color selection
//        Route route = model.getAllRoutes().getRouteById(id);
//        if(route.getRouteColor() == RouteColor.GRAY){
//            map.grayRouteColorSelector(model.grayColors(route.getId()));
//            return;
//        }
        String response = playerState.claimRoute(id, this, model, GameLobbyModel.getInstance()); //only claims if active player
        map.showToast(response);
    }

    public void claimWithColor(Integer id, RouteColor color){
        String response = playerState.claimRouteWithColor(id, color, this, model, GameLobbyModel.getInstance());
        map.showToast(response);
    }

    /**
     * Triggered by a special button on the GUI //todo
     */
    public void getNewDests(){
        String response = playerState.drawDestCards(this, model.getActivePlayer(), GameLobbyModel.getInstance().getGame().getGameName());
        map.showToast(response);
    }

    /**
     * Triggered by a card selection
     * @param pos position in the card array of selected card
     */
    public void drawTrainCard(int pos){

        //Todo: If position is 6, that means they draw a card from the deck.
        if(pos == 6){
            String response = playerState.drawTrain(-1, this, model);
            map.showToast(response);
        }else {

            String response = playerState.drawTrain(pos, this, model);
            map.showToast(response);
        }
    }

    /**
     * Gets the list of user names
     * @return array of userNames in the game
     *
     * @Pre there are players in the game
     *
     * @Post returns a list of game members usernames
     */
    public ArrayList<String> getUsers(){
        return new ArrayList<>(model.getGameMembers().keySet());
    }

    void setState(PlayerState newState){
        if(newState instanceof InactivePlayer){
            new PassTurn().execute();
        }
        playerState = newState;
    }

    class PassTurn extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void...voids){
            EndTurnService service = new EndTurnService(GameLobbyModel.getInstance().getGame().getGameName());
            service.execute();
            return null;
        }
    }

}