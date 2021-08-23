package presenter;

import com.bignerdranch.android.shareddata.commands.cards.CardType;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

import service.ClaimRouteService;
import service.DrawDestCardService;
import service.DrawTrainCardService;

public class ActivePlayer extends PlayerState{
    /**
     * singleton
     */
    private static ActivePlayer instance = new ActivePlayer();
    public static ActivePlayer getInstance(){
        return instance;
    }

    @Override
    public String drawTrain(int cardPosition, MapPresenter presenter, GameModel gameModel){
        //Deck return
        if(cardPosition == -1){
            presenter.setState(TrainCardDrawnState.getInstance());
            DrawTrainCardService service = new DrawTrainCardService(gameModel.getActivePlayer(), cardPosition, GameLobbyModel.getInstance().getGame().getGameName()); //todo WHAAAAT???
            service.execute();
            return "drawing train card";
        }

        //Face-ups return
        if(gameModel.getFaceUpCards().elementAt(cardPosition).getCardType() == CardType.LOCOMOTIVE){
            presenter.setState(InactivePlayer.getInstance());
            DrawTrainCardService service = new DrawTrainCardService(gameModel.getActivePlayer(), cardPosition, GameLobbyModel.getInstance().getGame().getGameName());
            service.execute();
        }
        else{
            presenter.setState(TrainCardDrawnState.getInstance());
            DrawTrainCardService service = new DrawTrainCardService(gameModel.getActivePlayer(), cardPosition, GameLobbyModel.getInstance().getGame().getGameName());
            service.execute();
        }
        return "drawing train card";
    }

    @Override
    public String drawDestCards(MapPresenter presenter, String username, String gameName){
        new DrawDestCardService().drawCards(username, gameName);
        presenter.setState(InactivePlayer.getInstance());
        return "drawing dest cards";
    }

    @Override
    public String claimRoute(Integer routeID, MapPresenter presenter, GameModel model, GameLobbyModel lobby){
        //todo seperate routes to same city for user
        new ClaimRouteService(lobby.getGame().getGameName(), model.getClientPlayerInfo().getUsername(), routeID).execute();
        presenter.setState(InactivePlayer.getInstance());
        return "claiming route";
    }




}
