package ServerCentral;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;

import com.google.gson.Gson;

public class Server //implements IServer
{
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();
    private CommandData response;
    public Server() {    }

/*    public CommandData createGame(CreateGameRequest gameCreate)
    {
//        response = new CommandData();
//        int maxNumOfPlayers = gameCreate.getMaxPlayers();
//        String nameOfTheGame = gameCreate.getGameName();
//        GameListing newGame = new GameListing();
//        boolean found = false;
//        HashMap<GameListing, HashMap<Color, Boolean>> gameWithcolors = savedData.getColorsChosen();
//
//        for(GameListing game : savedData.getListOFGames())
//        {
//            if(game.getGameName().equals(nameOfTheGame))
//            {
//                found = true;
//            }
//        }
//
//        if(found)
//        {
//            String errormessage = "Game name already exists";
//            response = new CommandData (CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//            savedData.addCommand(response, gameCreate.getUsername());
//        }
//        else if(2 > maxNumOfPlayers || 5 < maxNumOfPlayers)
//        {
//            String errormessage = "2-5 players is a requirement to play";
//            response = new CommandData (CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//            savedData.addCommand(response, gameCreate.getUsername());
//        }
//        else if(!gameCreate.isPublic())
//        {
//            newGame.setPublicGame(false);
//            //newGame.setPassword(gameCreate.getPassword());
//            newGame.setMaxNumOfPlayers(maxNumOfPlayers);
//            newGame.setNumPlayers(1);
//            newGame.setGameName(nameOfTheGame);
//            newGame.setHost(gameCreate.getUsername());
//            savedData.getListOFGames().add(newGame);
//            gameWithcolors.put(newGame, savedData.getInitializedColorMap());
//            String listing = gson.toJson(newGame);
//            CommandData updateListing = new CommandData(CommandType.ADD_GAME_LISTING, listing);
//            savedData.addAllCommand(updateListing);
//            Player player = new Player();
//            player.setName(gameCreate.getUsername());
//            //savedData.getListOFPeople().add(player);
//
//            Vector<Player> listOfPeople = new Vector<>();
//            //listOfPeople.add(player);
//            //add empty list
//            savedData.getListOfPlayersPerGame().put(newGame, listOfPeople);
//
//            JoinGameRequest joinGame = new JoinGameRequest(player, newGame);
//            joinGame(joinGame);
//        }
//        else
//        {
//            newGame.setPublicGame(true);
//            newGame.setMaxNumOfPlayers(maxNumOfPlayers);
//            newGame.setNumPlayers(1);
//            newGame.setGameName(nameOfTheGame);
//            newGame.setHost(gameCreate.getUsername());
//            savedData.getListOFGames().add(newGame);
//            gameWithcolors.put(newGame, savedData.getInitializedColorMap());
//            String listing = gson.toJson(newGame);
//            CommandData updateListing = new CommandData(CommandType.ADD_GAME_LISTING, listing);
//            savedData.addAllCommand(updateListing);
//            Player player = new Player();
//            player.setName(gameCreate.getUsername());
//            //savedData.getListOFPeople().add(player);
//
//            Vector<Player> listOfPeople = new Vector<>();
//            //listOfPeople.add(player);
//            //add empty list of people, handle in joinGame instead
//            savedData.getListOfPlayersPerGame().put(newGame, listOfPeople);
//
//            JoinGameRequest joinGame = new JoinGameRequest(player, newGame);
//            joinGame(joinGame);
//        }
//
//        return response;
    }*/

/*    public CommandData distributeChat(ChatMessage message)
    {
//        response = new CommandData();
//
//        String messagetosend = message.getMessage();
//        String usernameofsending = message.getMessageSender();
//        String gameofsending = message.getGameName();
//        ChatMessage message1 = new ChatMessage(usernameofsending, messagetosend, gameofsending);
//        String messageConvert = gson.toJson(message1);
//        CommandData dataToSend = new CommandData(CommandType.ADD_CHAT_MESSAGE, messageConvert);
//
//        TreeMap<String, Queue<CommandData>> commandsForPlayers = savedData.getListOfCommandsPerPlayer();
//        HashMap<GameListing, Vector<Player>> mappedGameListing = savedData.getListOfPlayersPerGame();
//        String gameWeAreLookingFor = message.getGameName();
//
//        for(Map.Entry<GameListing, Vector<Player>> entry : mappedGameListing.entrySet())
//        {
//            if(entry.getKey().getGameName().equals(gameWeAreLookingFor))
//            {
//                Vector<Player> peopleInSpecificGame = entry.getValue();
//                for(Player player : peopleInSpecificGame)
//                {
//                    String playerName = player.getName();
//                    if(!playerName.equals(usernameofsending))
//                    {
//                        commandsForPlayers.get(playerName).add(dataToSend);
//                    }//Todo not sure if what i am sending back is correct CommandType wise
//                }
//                break;
//            }
//        }
//        return response;
    }*/

/*    public CommandData getCommandList(String username)
    {
//        boolean found = false;
//        CommandData response = new CommandData();
//        TreeMap<String, Queue<CommandData>> commandList = savedData.getListOfCommandsPerPlayer();
//        for(Map.Entry<String, Queue<CommandData>> entry : commandList.entrySet())
//        {
//            if(entry.getKey().equals(username))
//            {
//                found = true;
//                Queue<CommandData> list = entry.getValue();
//                String listJson = gson.toJson(list);
//                response = new CommandData(CommandType.COMMAND_LIST, listJson);
//                break;
//            }
//            else
//            {
//
//            }
//        }
//
//        return response;
    }*/

/*    public CommandData joinGame(JoinGameRequest gameRequest) //Where do we get the password to check if they entered everything right
    {
//        CommandData response = new CommandData();
//        Vector<Player> oldListOfPlayers = savedData.getListOFPeople();
//        HashMap<GameListing, Vector<Player>> mappedGameListing = savedData.getListOfPlayersPerGame();
//        HashMap<GameListing, Vector<Player>> listOfPlayersPerGame = savedData.getListOfPlayersPerGame();
//        GameListing listing = gameRequest.getGameListing();
//        Player newcomer = gameRequest.getPlayer();
//        String gameRequested = listing.getGameName();
//
//        if(listing.getNumPlayers() == listing.getMaxNumOfPlayers())
//        {
//            String errormessage = "The player limit has already been reached";
//            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//            savedData.addCommand(response, newcomer.getName());
//        }
//        else
//        {
//            //check each game for a match
//            for (Map.Entry<GameListing, Vector<Player>> entry : mappedGameListing.entrySet())
//            {
//                GameListing game = entry.getKey();
//                if (game.getGameName().equals(gameRequested))
//                {
//                    //private game
//                    if (!listing.isPublicGame())
//                    {
//                        if (game.getPassword().equals(listing.getPassword()))
//                        {
//                            newcomer = defaultSetColor(newcomer, listing);
//
//                            newcomer.setGame(gameRequested);
//                            Vector<Player> newListOfPlayers = new Vector<>();
//                            for(Player player1 : oldListOfPlayers)
//                            {
//                                if(!player1.getName().equals(newcomer.getName()))
//                                {
//                                    newListOfPlayers.add(player1);
//                                }
//                                else
//                                {
//                                    newListOfPlayers.add(newcomer);
//                                }
//                            }
//                            //savedData.setListOFPeople(newListOfPlayers);
//
//                            int numOfPlayers = entry.getKey().getNumPlayers();
//
//                            //if not the host
//                            if(!entry.getKey().getHost().equals(newcomer.getName())){
//                                entry.getKey().setNumPlayers(++numOfPlayers);
//                            }
//
//                            response.setMyCommandType(CommandType.ADD_CURRENT_LOBBY_MEMBERS);
//                            String playerList = gson.toJson(entry.getValue());
//                            response = new CommandData(CommandType.ADD_CURRENT_LOBBY_MEMBERS, playerList);
//                            savedData.addCommand(response, newcomer.getName());
//                            //todo update game listing
//                            break;
//                        }
//                        else
//                        {
//                            String errormessage = "Incorrect password";
//                            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//                            savedData.addCommand(response, newcomer.getName());
//                        }
//                        break;
//                    }
//                    //public game
//                    else
//                    {
//                        newcomer = defaultSetColor(newcomer, listing);
//
//                        newcomer.setGame(gameRequested);
//                        Vector<Player> newListOfPlayers = new Vector<>();
//                        for(Player player1 : oldListOfPlayers)
//                        {
//                            if(!player1.getName().equals(newcomer.getName()))
//                            {
//                                newListOfPlayers.add(player1);
//                            }
//                            else
//                            {
//                                newListOfPlayers.add(newcomer);
//                            }
//                        }
//                        savedData.setListOFPeople(newListOfPlayers);
//
//                        int numOfPlayers = entry.getKey().getNumPlayers();
//                        if(!entry.getKey().getHost().equals(newcomer.getName())){
//                            entry.getKey().setNumPlayers(++numOfPlayers);
//                        }
//
//                        //serialize vector of playerse
//
//                        savedData.addPlayerToGame(newcomer, game);
//                        Vector<Player> players = savedData.getListOfPlayersPerGame().get(game);
//                        String playerVector = gson.toJson(players);
//                        response = new CommandData(CommandType.ADD_CURRENT_LOBBY_MEMBERS, playerVector);
//
//                        //newcomer initializes all lobby members
//                        savedData.addGameCommand(response, game);
//
//
//                        String gameString = gson.toJson(game);
//                        CommandData newPlayer = new CommandData(CommandType.INCREMENT_GAME_PLAYER_COUNT, gameString);
//                        savedData.addAllCommand(newPlayer);
//
//                        break;
//                    }
//                }
//                //game listing, vector of players
//                listOfPlayersPerGame.put(entry.getKey(), entry.getValue());
//
//            }
//        }
//
//        return response;
    }*/

/*    public CommandData leaveGame(LeaveGameRequest requestLeave)
    {
//        String gameName = requestLeave.getGameName();
//        String username = requestLeave.getUsername();
//        CommandData response = new CommandData();
//        HashMap<GameListing, Vector<Player>> mappedGameListing = savedData.getListOfPlayersPerGame();
//
//        for (Map.Entry<GameListing, Vector<Player>> entry : mappedGameListing.entrySet())
//        {
//            if(entry.getKey().getGameName().equals(gameName))
//            {
//                Vector<Player> players = entry.getValue();
//                for (Player updatePlayer : players)
//                {
//                    if(updatePlayer.getName().equals(username))
//                    {
//                        GameListing newGameDefinition = entry.getKey();
//                        int newnumber = newGameDefinition.getNumPlayers();
//                        newGameDefinition.setNumPlayers(--newnumber);
//                        updatePlayer.setGame("");
//                        Vector<Player> newListOfPlayers = new Vector<>();
//                        Vector<Player> oldListOfPlayers = entry.getValue();
//                        for(Player player : oldListOfPlayers)
//                        {
//                            if(!player.getName().equals(username))
//                            {
//                                newListOfPlayers.add(player);
//                            }
//                        }
//
//                        mappedGameListing.put(newGameDefinition, newListOfPlayers);
//                        savedData.setListOfPlayersPerGame(mappedGameListing);
//                        break;
//                    }
//                }
//                break;
//            }
//        }
//
//        String farewellMessage = "Thank you for playing our rendition of Ticket to Ride!";
//        response = new CommandData(CommandType.LEAVE_GAME, farewellMessage);
//        return response;
    }*/

//    public CommandData login(LoginRequestModel login)
//    {
//        CommandData response = new CommandData();
//        Boolean found = false;
//        Vector<Player> listofpeople = savedData.getListOFPeople();
//        String username = login.getUsername();
//        String password = login.getPassword();
//
//        for(Player player : listofpeople)
//        {
//            if(player.getName().equals(username) && player.getPassword().equals(password))
//            {
//                found = true;
//                Vector<GameListing> gamesOnServer = savedData.getListOFGames();
//                String gotobed = gson.toJson(gamesOnServer);
//                response = new CommandData(CommandType.ADD_INITIAL_GAMES, gotobed);
//                break;
//            }
//        }
//
//        if(!found)
//        {
//
//            // I think setMyCommandType is redundant if we call new CommandData(CommandType.DISPLAY_ERROR_MESSAGE);
//            response.setMyCommandType(CommandType.DISPLAY_ERROR_MESSAGE);
//            String errormessage = "Username or password is wrong";
//            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//        }
//
//        return response;
//    }
//
//    public CommandData register(RegisterRequestModel register)
//    {
//        CommandData response = new CommandData();
//        Boolean found = false;
//        Vector<Player> listofpeople = savedData.getListOFPeople();
//        String username = register.getUsername();
//        String password = register.getPassword();
//        String confirmPassword = register.getConfirmPassword();
//
//        if(username.length() > 10)
//        {
//            String errormessage = "Usernames may only be a max of 10 characters long";
//            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//        }
//        else if(!password.equals(confirmPassword))
//        {
//            String errormessage = "Password and confirm password fields did not match";
//            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//        }
//        else
//        {
//
//            for(Player player : listofpeople)
//            {
//                if(player.getName().equals(username))
//                {
//                    found = true;
//                    String errormessage = "Username already taken";
//                    response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//                }
//            }
//
//            if(!found)
//            {
//                Player newPlayer = new Player();
//                newPlayer.setName(username);
//                newPlayer.setPassword(password);
//                listofpeople.add(newPlayer);
//                savedData.setListOFPeople(listofpeople);
//
//                Vector<GameListing> gamesOnServer = savedData.getListOFGames();
//                String BNEd = gson.toJson(gamesOnServer);
//                response = new CommandData(CommandType.ADD_INITIAL_GAMES, BNEd);
//
//                savedData.getListOfCommandsPerPlayer().put(newPlayer.getName(), new LinkedList<CommandData>());
//            }
//        }
//
//        return response;
//    }
//
//    public CommandData startGame(StartGameRequest start)
//    {
//        CommandData response = new CommandData();
//        Vector<GameListing> games = savedData.getListOFGames();
//        String requestedGameName = start.getGameName();
//
//        for(GameListing game : games)
//        {
//            if(game.getGameName().equals(requestedGameName))
//            {
//                if(game.getHost().equals(start.getUsername()))
//                {
//                    response.setMyCommandType(CommandType.START_GAME_COMMAND);
//                    String commenceMessage = "Starting game, have a nice trip!";
//                    response = new CommandData(CommandType.START_GAME_COMMAND, commenceMessage);
//                }
//                else
//                {
//                    response.setMyCommandType(CommandType.DISPLAY_ERROR_MESSAGE);
//                    String errormessage = "Only the host may start the game. Current game host: " + game.getHost();
//                    response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
//                    savedData.addCommand(response, start.getUsername());
//                }
//                break;
//            }
//        }
//
//        return response;
//    }
//
//    private Player defaultSetColor(Player playerNeedingColor, GameListing whichGameHeIsIn)
//    {
//        HashMap<GameListing, HashMap<Color, Boolean>> gameForColors = savedData.getColorsChosen();
//        Color color = Color.BLACK;
//        HashMap<Color, Boolean> newListOfColors = new HashMap<>();
//        boolean foundColor = false;
//
//        for (Map.Entry<GameListing, HashMap<Color, Boolean>> entry : gameForColors.entrySet())
//        {
//            if(entry.getKey().getGameName().equals(whichGameHeIsIn.getGameName()))
//            {
//                HashMap<Color, Boolean> colorsChosen = entry.getValue();
//                for(Map.Entry<Color, Boolean> entry2 : colorsChosen.entrySet())
//                {
//                    if(!entry2.getValue() && !foundColor)
//                    {
//                        color = entry2.getKey();
//                        Boolean change = true;
//                        foundColor = true;
//                        newListOfColors.put(entry2.getKey(), change);
//                    }
//                    else
//                    {
//                        newListOfColors.put(entry2.getKey(), entry2.getValue());
//                    }
//                }
//                break;
//            }
//        }
//
//        gameForColors.put(whichGameHeIsIn, newListOfColors);
//        playerNeedingColor.setColor(color);
//        return playerNeedingColor;
//    }
}
