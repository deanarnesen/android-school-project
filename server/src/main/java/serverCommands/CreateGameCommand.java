package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.CreateGameRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.JoinGameRequest;
import com.google.gson.Gson;

import java.util.HashMap;

import ServerCentral.StaticData;

public class CreateGameCommand implements IServerCommand {
    CreateGameRequest gameToCreate;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public CreateGameCommand(CreateGameRequest game){
        gameToCreate = game;
    }
    public CommandData execute(){

        CommandData response = new CommandData();
        int maxNumOfPlayers = gameToCreate.getMaxPlayers();
        String nameOfTheGame = gameToCreate.getGameName();
        GameListing newGame = new GameListing();
        boolean found = false;


        if(savedData.getListOFGames().containsKey(nameOfTheGame))
        {
           found = true;
        }

        if(found)
        {
            String errormessage = "Game name already exists";
            response = new CommandData (CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
            savedData.addCommand(response, gameToCreate.getUsername());
        }
        else if(2 > maxNumOfPlayers || 5 < maxNumOfPlayers)
        {
            String errormessage = "2-5 players is a requirement to play";
            response = new CommandData (CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
            savedData.addCommand(response, gameToCreate.getUsername());
        }
        else if(!gameToCreate.isPublic())
        {
            newGame.setPublicGame(false);
            newGame.setMaxNumOfPlayers(maxNumOfPlayers);

            newGame.setGameName(nameOfTheGame);
            newGame.setHost(gameToCreate.getUsername());
            HashMap<String, GameListing> temp = savedData.getListOFGames();//.put(newGame.getGameName(), newGame);
            temp.put(newGame.getGameName(), newGame);
            savedData.setListOFGames(temp);//this and the line above were added with the comment

            String listing = gson.toJson(newGame);
            CommandData updateListing = new CommandData(CommandType.ADD_GAME_LISTING, listing);
            savedData.addAllCommand(updateListing);
            Player player = new Player();
            player.setName(gameToCreate.getUsername());

            JoinGameRequest joinGame = new JoinGameRequest(player, newGame);
            JoinGameCommand join = new JoinGameCommand(joinGame);
            response = join.execute();
        }
        else
        {
            newGame.setPublicGame(true);
            newGame.setMaxNumOfPlayers(maxNumOfPlayers);
            newGame.setGameName(nameOfTheGame);
            newGame.setHost(gameToCreate.getUsername());
            HashMap<String, GameListing> temp = savedData.getListOFGames();//.put(newGame.getGameName(), newGame);
            temp.put(newGame.getGameName(), newGame);
            savedData.setListOFGames(temp);//this and the line above were added with the comment

            String listing = gson.toJson(newGame);
            CommandData updateListing = new CommandData(CommandType.ADD_GAME_LISTING, listing);
            savedData.addAllCommand(updateListing);
            Player player = new Player();
            player.setName(gameToCreate.getUsername());

            JoinGameRequest joinGame = new JoinGameRequest(player, newGame);

            JoinGameCommand join = new JoinGameCommand(joinGame);
            response = join.execute();
        }

        return response;

    }
}
