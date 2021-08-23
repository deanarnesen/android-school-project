package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.UpdateDestCardRequest;
import com.google.gson.Gson;

import ServerCentral.ActiveGameModel;
import ServerCentral.StaticData;

public class UpdateDestCardCommand implements IServerCommand
{
    private UpdateDestCardRequest req;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public UpdateDestCardCommand(UpdateDestCardRequest req)
    {
        this.req = req;
    }

    public CommandData execute()
    {
        ActiveGameModel game = savedData.getListActiveGames().get(req.getGame().getGameName());
        ServerGamePlayer playerWeAreLookingFor = new ServerGamePlayer();

        for(ServerGamePlayer player : game.getPlayersInGame())
        {
            if(player.getUsername().equals(req.getUsername()))
            {
                playerWeAreLookingFor = player;
            }
        }

        int increase = playerWeAreLookingFor.getDestinationCards().size();
        String input = gson.toJson(increase);

        CommandData response = new CommandData(CommandType.UPDATE_DEST_CARD_COUNT, input);
        savedData.addCommandForSpecificPlayers(req.getGame(), response);

        return response;
    }
}
