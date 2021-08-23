package service;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.JoinGameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.JoinGameRequest;
import com.google.gson.Gson;

public class PlayerListService {
    JoinGameLobbyModel lobbyModel = JoinGameLobbyModel.getInstance();
    public void addPlayer(Player player, GameListing gameListing){
        JoinGameRequest request = new JoinGameRequest(player, gameListing);
        Gson gson = new Gson();
        String input = gson.toJson(request);
        CommandData data = new CommandData(CommandType.ADD_PLAYER_TO_LOBBY, input);
        ServerProxy.getInstance().send(data);
    }
    public void getPlayer(String username){

    }
    public void setPlayerColor(String username, Color color){

    }
    public void removePlayer(String username){
    }
}
