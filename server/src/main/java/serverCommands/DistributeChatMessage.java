package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.google.gson.Gson;

import ServerCentral.StaticData;

public class DistributeChatMessage implements IServerCommand {
    ChatMessage messageToAdd;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public DistributeChatMessage(ChatMessage message) {
        this.messageToAdd = message;
    }


    public CommandData execute(){

        CommandData response;

        response = new CommandData();

        String messagetosend = messageToAdd.getMessageSender() + ": " + messageToAdd.getMessage();
        String usernameofsending = messageToAdd.getMessageSender();
        String gameofsending = messageToAdd.getGameName();
        ChatMessage message1 = new ChatMessage(usernameofsending, messagetosend, gameofsending);
        String messageConvert = gson.toJson(message1);
        CommandData dataToSend = new CommandData(CommandType.DISTRIBUTE_CHAT_MESSAGE, messageConvert);

        GameListing game = savedData.getSpecificGameByName(gameofsending);
        savedData.addCommandForSpecificPlayers(game, dataToSend);
        return response;
    }
}
