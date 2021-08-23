package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.JoinGameResponse;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LeaveGameRequest;
import com.google.gson.Gson;

import ServerCentral.StaticData;

//todo: WE NEED TO TEST THIS LATER----------------------------------> LEVI and RYAN
public class LeaveGameCommand implements IServerCommand {
    LeaveGameRequest leaveRequest;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();
    //GameListing game = null;

    /*public LeaveGameCommand() {
        leaveRequest = new LeaveGameRequest();
    }*/


    public LeaveGameCommand(LeaveGameRequest model) {

        leaveRequest = model;
    }

    public CommandData execute() {
        String gameName = leaveRequest.getGameName();
        String username = leaveRequest.getUsername();
        CommandData response = new CommandData();

        GameListing game = savedData.getListOFGames().get(gameName);
        game.removePlayerByName(username);
        savedData.getListOFGames().put(gameName, game);
        savedData.removePlayerFromGame(username);

        String gameString = gson.toJson(game);
        for(Player p : savedData.getListOFPeople())
        {
            if(p.getGame() == null)
            {
                CommandData listOfGameUpdate = new CommandData(CommandType.DECREMENT__GAME_PLAYER_COUNT, gameString);
                savedData.addCommand(listOfGameUpdate, p.getName());
            }
            else if (p.getGame().equals(gameName))
            {
                JoinGameResponse joinResp = new JoinGameResponse(game.getPlayers(), game);
                String data = gson.toJson(joinResp);
                CommandData playerListUpdate = new CommandData(CommandType.ADD_CURRENT_LOBBY_MEMBERS, data);
                savedData.addGameCommand(playerListUpdate, game);
            }
        }

        String farewellMessage = "Thank you for playing our rendition of Ticket to Ride!";
        response = new CommandData(CommandType.LEAVE_GAME, farewellMessage);
        return response;
    }

    public LeaveGameRequest getLeaveRequest()
    {
        return leaveRequest;
    }
}
