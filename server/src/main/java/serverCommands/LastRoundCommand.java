package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LastRoundRequest;
import com.google.gson.Gson;

import ServerCentral.ActiveGameModel;
import ServerCentral.StaticData;

public class LastRoundCommand implements IServerCommand
{
    private LastRoundRequest req;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public LastRoundCommand(LastRoundRequest request)
    {
        req = request;
    }

    public CommandData execute()
    {
        String input = "Just to avoid null pointer exception that probably won't happen";
        CommandData response = new CommandData(CommandType.LAST_ROUND, input);
        GameListing game = savedData.getSpecificGameByName(req.getGameName());
        ActiveGameModel activeGame = savedData.getListActiveGames().get(req.getGameName());
        if(!activeGame.isLastRound()){
            activeGame.lastRound();
            savedData.addGameCommand(response, game);
        }
        return response;
    }
}
