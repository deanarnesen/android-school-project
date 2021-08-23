package serverCommands;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.CardsForPlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;
import com.bignerdranch.android.shareddata.commands.serverModels.StartGameRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import ServerCentral.ActiveGameModel;
import ServerCentral.StaticData;

public class StartGameCommand implements IServerCommand {
    private StartGameRequest startGameRequest;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public StartGameCommand(StartGameRequest request) {
        startGameRequest = request;
    }

    public CommandData execute(){

        CommandData response = new CommandData();
        HashMap<String, GameListing> games = savedData.getListOFGames();
        String requestedGameName = startGameRequest.getGameName();

        for(Map.Entry<String, GameListing> entry : games.entrySet())
        {
            GameListing game = entry.getValue();
            if(game.getGameName().equals(requestedGameName))
            {
                //prevents non-hosts from starting game
                if(game.getHost().equals(startGameRequest.getUsername()))
                {
                    String noDataForEnterGame = "";
                    //sends an "EnterGameCommand" (sharedData/clientCommands/pregameclientcommands) to each player in this game
                    //host will get one too, but it will be blocked in the model function that updates the current screen (ClientRootModel)
                    savedData.addGameCommand(new CommandData(CommandType.ENTER_GAME, noDataForEnterGame), game);

                    savedData.setGameAsStarted(game);
                    response.setMyCommandType(CommandType.START_GAME_COMMAND);
                    String commenceMessage = "Starting game, have a nice trip!";
                    response = new CommandData(CommandType.START_GAME_COMMAND, commenceMessage);

                    StaticData.getInstance().addActiveGame(new ActiveGameModel(game));
                    ActiveGameModel activeGameModel = savedData.getListActiveGames().get(game.getGameName());
                    activeGameModel.setActivePlayer(game.getHost());
                    Gson gson = new Gson();

                    //set active player for all players
                    GameListing gameListing = new GameListing();
                    gameListing.setGameName(game.getGameName());
                    String data = gson.toJson(startGameRequest.getUsername());
                    savedData.addGameCommand(new CommandData(CommandType.UPDATE_TURN, data), gameListing);

                    //Send the information about one player to all other players
                    for(ServerGamePlayer client : activeGameModel.getPlayersInGame()){
                        String commandData;
                        for(ServerGamePlayer gameMember : activeGameModel.getPlayersInGame()){
                            //If the same player also send more detailed information
                            if(client.getUsername().equals(gameMember.getUsername())){
                                //Send players cards
                                for(TrainCarCard card : client.getTrainCarCards()){
                                    String cardString = gson.toJson(card);
                                    savedData.addCommand(new CommandData(CommandType.ADD_TRAIN_CARD, cardString), client.getUsername());
                                }
                            }
                            Pair dataForCommand;
                            //Update the number of destination cards
                            dataForCommand = new Pair(gameMember.getUsername(), gameMember.getDestinationCards().size());
                            commandData = gson.toJson(dataForCommand);
                            savedData.addCommand(new CommandData(CommandType.UPDATE_DEST_CARD_COUNT,
                                    commandData), client.getUsername());

                            //Update the number of train cards
                            dataForCommand = new Pair(gameMember.getUsername(), gameMember.getTrainCarCards().size());
                            commandData = gson.toJson(dataForCommand);
                            savedData.addCommand(new CommandData(CommandType.UPDATE_TRAIN_CARDS_COUNT,
                                    commandData), client.getUsername());

                            //update player score
                            dataForCommand = new Pair(gameMember.getUsername(), gameMember.getScore());
                            commandData = gson.toJson(dataForCommand);
                            savedData.addCommand(new CommandData(CommandType.UPDATE_SCORE,
                                    commandData), client.getUsername());

                            //update the number of train cars
                            dataForCommand = new Pair(gameMember.getUsername(), gameMember.getNumTrainCars());
                            commandData = gson.toJson(dataForCommand);
                            savedData.addCommand(new CommandData(CommandType.UPDATE_TRAIN_CARS,
                                    commandData), client.getUsername());

                            //TODO implement player turn update
                            //update player turn
                            //dataForCommand = new Pair(gameMember.getUsername(), gameMember.isTurnState());
                            //commandData = gson.toJson(dataForCommand);
                            //savedData.addCommand(new CommandData(CommandType.UPDATE_TURN,
                            //        commandData), client.getUsername());


                        }
                        //Update face up card
                        Vector<TrainCarCard> fuCards = activeGameModel.getFaceUpTrainCards();
                        commandData = gson.toJson(fuCards);
                        savedData.addCommand(new CommandData(CommandType.UPDATE_FACE_UP_TRAIN_CARDS,
                                commandData), client.getUsername());

                        //Update number of cards in decks
                        commandData = gson.toJson(activeGameModel.getTrainCardDrawDeck().deckSize());
                        savedData.addCommand(new CommandData(CommandType.UPDATE_TRAIN_DECK_COUNT,
                                commandData),client.getUsername());
                        commandData = gson.toJson(activeGameModel.getDestinationCardDrawDeck().deckSize());
                        savedData.addCommand(new CommandData(CommandType.UPDATE_DEST_DECK_COUNT,
                                commandData),client.getUsername());

                        Vector<Route> routes = activeGameModel.getAllRoutes();
                        commandData = gson.toJson(routes);
                        savedData.addCommand(new CommandData(CommandType.SET_ROUTES,
                                commandData), client.getUsername());

                        //Send three initial cards
                        Vector<DestinationCard> cardsToSend = new Vector<>();
                        for(int i = 0; i < 3; i++){
                            cardsToSend.add(activeGameModel.getDestinationCardDrawDeck().drawCard());
                        }
                        CardsForPlayer cardsForPlayer = new CardsForPlayer(cardsToSend, client.getUsername());
                        savedData.addCommand(new CommandData(CommandType.DRAW_DESTINATION_CARD,
                                gson.toJson(cardsForPlayer)), client.getUsername());
                        //prompt the first turn?
                    }

                }
                else
                {
                    String errormessage = "Only the host may start the game. Current game host: " + game.getHost();
                    response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
                    savedData.addCommand(response, startGameRequest.getUsername());
                }
                break;
            }
        }

        return response;

    }
}
