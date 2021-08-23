package service;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LeaveGameRequest;
import com.google.gson.Gson;

public class GameLobbyService {

    public GameLobbyService (){

    }


    /**
     * Not an admin leaving a game
     * @param gameListing
     */
    public void leaveGame(GameListing gameListing){
        String user = ClientModelRoot.getInstance().getClientUserName();
        Player player = new Player();
        player.setName(user);
        LeaveGameRequest request = new LeaveGameRequest(player.getName(), gameListing.getGameName());
        Gson gson = new Gson();
        String input = gson.toJson(request);
        CommandData data = new CommandData(CommandType.LEAVE_GAME, input);
        ServerProxy.getInstance().send(data);
    }





}
