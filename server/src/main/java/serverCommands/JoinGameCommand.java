package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.JoinGameResponse;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.JoinGameRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Vector;

import ServerCentral.StaticData;

public class JoinGameCommand implements IServerCommand {

    JoinGameRequest joinGameRequest;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public JoinGameCommand(JoinGameRequest joinGameRequest) {
        this.joinGameRequest = joinGameRequest;
    }

    @Override
    public CommandData execute() {

        CommandData response = new CommandData();

        GameListing game = joinGameRequest.getGameListing();
        Player newcomer = joinGameRequest.getPlayer();
        newcomer = savedData.getPlayerFromList(newcomer.getName());
        String gameRequested = game.getGameName();

        //Check if there is space for one more player.
        if (game.getNumPlayers() == game.getMaxNumOfPlayers()) {
            String errormessage = "The player limit has already been reached";
            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
            savedData.addCommand(response, newcomer.getName());
        } else {
            //check each game for a match
            GameListing curGame = savedData.getSpecificGame(game);
            if (curGame != null) {

                //PRIVATE GAME
                if (!curGame.isPublicGame()) {
                    System.out.println("We are accessing a private game that has not been implemented.\n\n\n");

                }
                //PUBLIC GAME
                else {

                    //user knows which game he/she belongs to
                    newcomer.setGame(gameRequested);
                    curGame.addPlayer(newcomer);
                    HashMap<String, GameListing> temp = savedData.getListOFGames();//.put(curGame.getGameName(), curGame);
                    temp.put(curGame.getGameName(), curGame);
                    savedData.setListOFGames(temp);//this and the line above were added with the comment
                    Vector<Player> currentPlayerInGame = curGame.getPlayers();
                    JoinGameResponse joinResp = new JoinGameResponse(currentPlayerInGame, curGame);
                    String playerVector = gson.toJson(joinResp);


                    //Remove previous info from listOfPlayers
                    savedData.getListOFPeople().remove(newcomer);
                    //UPDATE player info in listOfPeople ( this contains the color and the game it is in )
                    savedData.getListOFPeople().add(curGame.getPlayerByName(newcomer.getName()));

                    response = new CommandData(CommandType.ADD_CURRENT_LOBBY_MEMBERS, playerVector);

                    savedData.addPlayerToGame(newcomer, curGame);
                    //newcomer initializes all lobby members
                    savedData.addGameCommand(response, game);

                    //Update current players list for all other players
                    String playerString = gson.toJson(newcomer);
                    for(Player p : curGame.getPlayers()){
                        if(!p.getName().equals(newcomer.getName())){
                            savedData.addCommand(new CommandData(CommandType.ADD_PLAYER_TO_LOBBY, playerString), p.getName());
                        }
                    }

                    String gameString = gson.toJson(curGame);
                    for(Player p : savedData.getListOFPeople())
                    {
                        if(p.getGame() == null)
                        {
                            CommandData listGamesUpdate = new CommandData(CommandType.INCREMENT_GAME_PLAYER_COUNT, gameString);
                            savedData.addCommand(listGamesUpdate, p.getName());
                        }
                    }
                    //savedData.addAllCommand(newPlayer);

                }

            }

        }

            return null;
    }

    public JoinGameRequest getJoinGameRequest()
    {
        return joinGameRequest;
    }
}