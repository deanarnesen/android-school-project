package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LastRoundRequest;
import com.google.gson.Gson;

import ServerCentral.StaticData;

public class EndGameScreenCommand implements IServerCommand
{
    private LastRoundRequest req;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public EndGameScreenCommand(LastRoundRequest request)
    {
        req = request;
    }

    @Override
    public CommandData execute()
    {
        String input = "Just to avoid null pointer exception that probably won't happen";
        CommandData response = new CommandData(CommandType.MOVE_TO_END_GAME_SCREEN, input);
        GameListing game = savedData.getSpecificGameByName(req.getGameName());
        savedData.addGameCommand(response, game);

        //todo add the data from the dto so the correct information is displayed correctly for each player

        //we can probabaly do some things here to help mop up the game too, like switching the game to an
        //inactive game/deleting it, or trigger something that will move all the players back to the game
        // lobby after a small wait period.

        return response;
    }
}
