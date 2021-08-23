package serverCommands;

import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.serverModels.DrawTrainRequest;

import java.util.Vector;

import ServerCentral.ActiveGameModel;
import ServerCentral.StaticData;

public class DrawTrainCarCard implements IServerCommand{
    private DrawTrainRequest userInfo;
    private StaticData savedData = StaticData.getInstance();

    public DrawTrainCarCard(DrawTrainRequest userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public CommandData execute() {
        ActiveGameModel game = savedData.getListActiveGames().get(userInfo.getGameName());
        TrainCarCard card;
        CommandData response;
        if((int)userInfo.getCardPosition() == -1){
            card = game.drawCard();
        }
        else{
            Vector<TrainCarCard> faceUps = game.getFaceUpTrainCards();
            card = faceUps.elementAt((int)userInfo.getCardPosition());
            game.setFaceupCard((int)userInfo.getCardPosition());
        }
        if(card != null){
            game.addTrainCarCard(userInfo.getUserName(), card);
        }
        return null;
    }
}
