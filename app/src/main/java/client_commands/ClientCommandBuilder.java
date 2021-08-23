package client_commands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.bignerdranch.android.shareddata.commands.clientCommands.DistributeMessage;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.AddDestinationCard;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.AddRoute;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.AddTrainCard;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.DrawDestinationCard;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.GoToEndScreen;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.LastRoundCheck;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateDestCardCount;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateDestDeckCount;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateFaceUpTrainCards;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateRoutes;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateScore;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateTrainCardDeck;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateTrainCardsCount;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateTrainCars;
import com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands.UpdateTurn;
import com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands.AddCurrentLobbyMembers;
import com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands.AddGameListing;
import com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands.AddInitialGames;
import com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands.AddPlayerToLobby;
import com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands.DecrementGamePlayerCount;
import com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands.EnterGameCommand;
import com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands.IncrementGamePlayerCount;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.CardsForPlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.EndGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.JoinGameResponse;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Vector;

public class ClientCommandBuilder {
    public static IClientCommand buildCommand(CommandData command){
        CommandType type = command.getCommandType();
        String pulledData = command.getCommandData();
        Gson gson = new Gson();
        switch(type) {
            case ADD_INITIAL_GAMES:
                Type listType = new TypeToken<HashMap<String, GameListing>>() {}.getType();
                HashMap<String, GameListing> gameListings = gson.fromJson(pulledData, listType);
                return new AddInitialGames(gameListings);
            case DISPLAY_ERROR_MESSAGE:
                ClientModelRoot modelRoot = ClientModelRoot.getInstance();
                PlayerGameState state = modelRoot.getCurrentScreen();
                if (state == PlayerGameState.LOGIN) {
                    modelRoot.setCurrentScreen(PlayerGameState.LOGIN);
                }
                /*else   //many if not all of our error messages get thrown as exceptions
                {        //error message "Can't create handler inside thread that has not called
                         //Looper,prepare()
                    modelRoot.setCurrentError(pulledData);
                }*/
            case ADD_CURRENT_LOBBY_MEMBERS:
                //when joining a game, all players get this command on the server end, but it seems like
                //only the person that is most recently joining executes his/her command properly
                JoinGameResponse responseFromJoin = gson.fromJson(pulledData, JoinGameResponse.class);
                return new AddCurrentLobbyMembers(responseFromJoin);
            case ADD_GAME_LISTING:
                GameListing gameListing = gson.fromJson(pulledData, GameListing.class);
                return new AddGameListing(gameListing);
            case ADD_PLAYER_TO_LOBBY:
                Player playerToAdd = gson.fromJson(pulledData, Player.class);
                return new AddPlayerToLobby(playerToAdd);
            case INCREMENT_GAME_PLAYER_COUNT:
                GameListing game = gson.fromJson(pulledData, GameListing.class);
                return new IncrementGamePlayerCount(game);
            case DECREMENT__GAME_PLAYER_COUNT:
                GameListing gameLeft = gson.fromJson(pulledData, GameListing.class);
                return new DecrementGamePlayerCount(gameLeft);
            case LEAVE_GAME:
                GameListing game2 = gson.fromJson(pulledData, GameListing.class);
                return new DecrementGamePlayerCount(game2);
            case DISTRIBUTE_CHAT_MESSAGE:
                ChatMessage message = gson.fromJson(pulledData, ChatMessage.class);
                return new DistributeMessage(message);
            case REMOVE_GAME_LISTING:
                return null; //todo
            case ENTER_GAME:
                return new EnterGameCommand();
            case ADD_DESTINATION_CARD:
                DestinationCard destCard = gson.fromJson(pulledData, DestinationCard.class);
                return new AddDestinationCard(destCard);
            case ADD_TRAIN_CARD:
                TrainCarCard trainCard = gson.fromJson(pulledData, TrainCarCard.class);
                return new AddTrainCard(trainCard);
            case UPDATE_DEST_CARD_COUNT:
                Pair destCountPair = gson.fromJson(pulledData, Pair.class);
                return new UpdateDestCardCount(destCountPair);
            case DRAW_DESTINATION_CARD:
                CardsForPlayer incomingCards = gson.fromJson(pulledData, CardsForPlayer.class);
                return new DrawDestinationCard(incomingCards);
            case UPDATE_FACE_UP_TRAIN_CARDS:
                listType = new TypeToken<Vector<TrainCarCard>>() {}.getType();
                Vector<TrainCarCard> trainCards = gson.fromJson(pulledData, listType);
                return new UpdateFaceUpTrainCards(trainCards);
            case UPDATE_SCORE:
                Pair scorePair = gson.fromJson(pulledData, Pair.class);
                return new UpdateScore(scorePair);
            case UPDATE_TRAIN_CARDS_COUNT:
                Pair trainCardsCountPair = gson.fromJson(pulledData, Pair.class);
                return new UpdateTrainCardsCount(trainCardsCountPair);
            case UPDATE_TRAIN_CARS:
                Pair carsPair = gson.fromJson(pulledData, Pair.class);
                return new UpdateTrainCars(carsPair);
            case UPDATE_TURN:
                String newActive = gson.fromJson(pulledData, String.class);
                return new UpdateTurn(newActive);
            case UPDATE_DEST_DECK_COUNT:
                int destCount = gson.fromJson(pulledData, int.class);
                return new UpdateDestDeckCount(destCount);
            case UPDATE_TRAIN_DECK_COUNT:
                int trainCardCount = gson.fromJson(pulledData, int.class);
                return new UpdateTrainCardDeck(trainCardCount);
            case SET_ROUTES:
                listType = new TypeToken<Vector<Route>>() {}.getType();
                Vector<Route> routes = gson.fromJson(pulledData, listType);
                return new UpdateRoutes(routes);
            case ADD_ROUTE:
                Pair routePair = gson.fromJson(pulledData, Pair.class);
                return new AddRoute(routePair);
            case LAST_ROUND:
                return new LastRoundCheck();
            case MOVE_TO_END_GAME_SCREEN:
                listType = new TypeToken<Vector<EndGamePlayer>>(){}.getType();
                Vector<EndGamePlayer> players = gson.fromJson(pulledData, listType);
                return new GoToEndScreen(players);
            default:
                System.out.print("type not found");
                return null;
        }
    }
}
