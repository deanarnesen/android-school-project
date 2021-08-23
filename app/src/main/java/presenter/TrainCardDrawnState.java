package presenter;

import com.bignerdranch.android.shareddata.commands.cards.CardType;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

import service.DrawTrainCardService;


public class TrainCardDrawnState extends PlayerState {
    /**
     * singleton
     */
    private TrainCardDrawnState(){}
    private static TrainCardDrawnState instance = new TrainCardDrawnState();
    public static TrainCardDrawnState getInstance(){
        return instance;
    }

    @Override
    public String drawTrain(int cardPosition, MapPresenter presenter, GameModel gameModel){
        if(cardPosition == -1){
            presenter.setState(InactivePlayer.getInstance());
            DrawTrainCardService service = new DrawTrainCardService(gameModel.getActivePlayer(), cardPosition, GameLobbyModel.getInstance().getGame().getGameName());
            service.execute();
            return "drawing train card";
        }
        else if(GameModel.getInstance().getFaceUpCards().elementAt(cardPosition).getCardType() == CardType.LOCOMOTIVE){
            return "cannot draw locomotive";
        }
        presenter.setState(InactivePlayer.getInstance());
        DrawTrainCardService service = new DrawTrainCardService(gameModel.getActivePlayer(), cardPosition, GameLobbyModel.getInstance().getGame().getGameName());
        service.execute();

        return "drawing train card";
    }
}
