package service;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.JoinGameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.CreateGameRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.JoinGameRequest;
import com.google.gson.Gson;


public class GameListService {
    JoinGameLobbyModel gameScreenModel = JoinGameLobbyModel.getInstance();
    public void addGame(GameListing gameListing){
        Gson gson = new Gson();
        String input = gson.toJson(gameListing);
        CommandData data = new CommandData(CommandType.ADD_GAME_LISTING, input);
        ServerProxy.getInstance().send(data);
    }
    public void removeGame(GameListing gameListing){
        Gson gson = new Gson();
        String input = gson.toJson(gameListing);
        CommandData data = new CommandData(CommandType.REMOVE_GAME_LISTING, input);
        ServerProxy.getInstance().send(data);
    }

    public void joinGame(GameListing gameListing){
        String user = ClientModelRoot.getInstance().getClientUserName();
        Player player = new Player();
        player.setName(user);
        player.setColor(null);
        player.setGame(null);
        player.setPassword(null);
        JoinGameRequest request = new JoinGameRequest(player, gameListing);
        Gson gson = new Gson();
        String input = gson.toJson(request);
        CommandData data = new CommandData(CommandType.JOIN_GAME_COMMAND, input);
        ServerProxy.getInstance().send(data);
    }


    public void createGame(CreateGameRequest request){
        Gson gson = new Gson();
        String input = gson.toJson(request);
        CommandData data = new CommandData(CommandType.CREATE_GAME_COMMAND, input);
        ServerProxy.getInstance().send(data);
    }
}
