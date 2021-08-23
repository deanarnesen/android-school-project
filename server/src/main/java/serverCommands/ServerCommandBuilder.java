package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.CardsChosen;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerPair;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.serverModels.AddDestCardRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.CreateGameRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.DrawDestCardRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.DrawTrainRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.JoinGameRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.LastRoundRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.LeaveGameRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.LoginRequestModel;
import com.bignerdranch.android.shareddata.commands.serverModels.PassTurnRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.RegisterRequestModel;
import com.bignerdranch.android.shareddata.commands.serverModels.StartGameRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.UpdateDestCardRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.UpdateDestDeckRequest;
import com.google.gson.Gson;

import ServerCentral.ActiveGameModel;
import ServerCentral.ActiveGamePersistanceManager;
import ServerCentral.StaticData;


public class ServerCommandBuilder {
    public static IServerCommand buildCommand(CommandData commandInfo, String userName) {
        Gson gson = new Gson();
        switch (commandInfo.getCommandType()) {
            case CREATE_GAME_COMMAND:
                CreateGameRequest create = gson.fromJson(commandInfo.getCommandData(), CreateGameRequest.class);
                return new CreateGameCommand(create);
            case DISTRIBUTE_CHAT_MESSAGE:
                ChatMessage chatMessage = gson.fromJson(commandInfo.getCommandData(), ChatMessage.class);
                return new DistributeChatMessage(chatMessage);
            case GET_COMMAND_LIST:
                String username = gson.fromJson(commandInfo.getCommandData(), String.class);
                return new GetCommandList(username);
            case JOIN_GAME_COMMAND:
                JoinGameRequest gameRequest = gson.fromJson(commandInfo.getCommandData(), JoinGameRequest.class);
                return new JoinGameCommand(gameRequest);
            case LEAVE_GAME:
                LeaveGameRequest leaveGameRequest = gson.fromJson(commandInfo.getCommandData(), LeaveGameRequest.class);
                return new LeaveGameCommand(leaveGameRequest);
            case LOGIN_COMMAND:
                LoginRequestModel loginRequest = gson.fromJson(commandInfo.getCommandData(), LoginRequestModel.class);
                return new LoginCommand(loginRequest);
            case REGISTER_COMMAND:
                RegisterRequestModel registerRequest = gson.fromJson(commandInfo.getCommandData(), RegisterRequestModel.class);
                return new RegisterCommand(registerRequest);
            case START_GAME_COMMAND:
                StartGameRequest startRequest = gson.fromJson(commandInfo.getCommandData(), StartGameRequest.class);
                return new StartGameCommand(startRequest);
            case UPDATE_DEST_CARD_COUNT:
                UpdateDestCardRequest updateDestCard = gson.fromJson(commandInfo.getCommandData(), UpdateDestCardRequest.class);
                addCommand(commandInfo, updateDestCard.getGame().getGameName());
                return new UpdateDestCardCommand(updateDestCard);
            case UPDATE_DEST_DECK_COUNT:
                UpdateDestDeckRequest updateDestDeck = gson.fromJson(commandInfo.getCommandData(), UpdateDestDeckRequest.class);
                addCommand(commandInfo, updateDestDeck.getGame().getGameName());
                return new UpdateDestDeckCommand(updateDestDeck);
            case ADD_DESTINATION_CARD:
                AddDestCardRequest addRequest = gson.fromJson(commandInfo.getCommandData(), AddDestCardRequest.class);
                addCommand(commandInfo, addRequest.getGameName());
                return new AddDestCardCommand(addRequest);
            case DRAW_DESTINATION_CARD:
                DrawDestCardRequest drawRequest = gson.fromJson(commandInfo.getCommandData(), DrawDestCardRequest.class);
                addCommand(commandInfo, drawRequest.getGameName());
                return new DrawDestCardCommand(drawRequest);
            case RETURN_CHOSEN:
                CardsChosen cardsChosen = gson.fromJson(commandInfo.getCommandData(), CardsChosen.class);
                addCommand(commandInfo, cardsChosen.getGameName());
                return new ReturnPlayerChosenCards(cardsChosen);
            case DRAW_TRAIN_CARD:
                DrawTrainRequest trainRequest = gson.fromJson(commandInfo.getCommandData(), DrawTrainRequest.class);
                addCommand(commandInfo, trainRequest.getGameName());
                return new DrawTrainCarCard(trainRequest);
            case CLAIM_ROUTE:
                ServerPair claim_request = gson.fromJson(commandInfo.getCommandData(), ServerPair.class);
                addCommand(commandInfo, claim_request.getGameName());
                return new ClaimRouteCommand(claim_request);
            case PASS_TURN:
                PassTurnRequest request = gson.fromJson(commandInfo.getCommandData(), PassTurnRequest.class);
                addCommand(commandInfo, request.getGameName());
                return new PassTurnCommand(request);
            case LAST_ROUND:
                LastRoundRequest reqLastRound = gson.fromJson(commandInfo.getCommandData(), LastRoundRequest.class);
                addCommand(commandInfo, reqLastRound.getGameName());
                return new LastRoundCommand(reqLastRound);
            case MOVE_TO_END_GAME_SCREEN:
                LastRoundRequest endGameRequest = gson.fromJson(commandInfo.getCommandData(), LastRoundRequest.class);
                return new EndGameScreenCommand(endGameRequest);
            case DISTRIBUTE_FINAL_SCORE:
                String gameName = gson.fromJson(commandInfo.getCommandData(), String.class);
                return new GetFinalScoresCommand(gameName);
            default:
                return null;
        }
    }

    private static void addCommand(CommandData commandInfo, String gameName){
        ActiveGamePersistanceManager persistanceManager = ActiveGamePersistanceManager.GetInstance();
        StaticData data = StaticData.getInstance();
        if(data != null && !data.isLoadingData()){
            persistanceManager.AddCommand(commandInfo, gameName);
        }
    }
}
