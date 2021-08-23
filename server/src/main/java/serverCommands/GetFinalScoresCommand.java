package serverCommands;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;

import ServerCentral.ActiveGameModel;
import ServerCentral.StaticData;

public class GetFinalScoresCommand implements IServerCommand {
    String gameName;

    public GetFinalScoresCommand(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public CommandData execute() {
        ActiveGameModel game = StaticData.getInstance().getListActiveGames().get(gameName);
        game.distributeFinalScore();
        return null;
    }
}
