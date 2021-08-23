package serverCommands;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.serverModels.PassTurnRequest;

import ServerCentral.ActiveGameModel;
import ServerCentral.StaticData;

public class PassTurnCommand implements IServerCommand{
    private PassTurnRequest request;
    private StaticData savedData = StaticData.getInstance();
    public PassTurnCommand(PassTurnRequest request){
        this.request = request;
    }

    public CommandData execute(){
        ActiveGameModel game = savedData.getListActiveGames().get(request.getGameName());
        game.nextTurn();
        return null;
    }
}
