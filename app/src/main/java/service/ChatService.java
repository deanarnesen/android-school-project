package service;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.google.gson.Gson;

public class ChatService {
    public ChatService(){
    }
    public void addChat(ChatMessage messasge){
        Gson gson = new Gson();
        String input = gson.toJson(messasge);
        CommandData data = new CommandData(CommandType.DISTRIBUTE_CHAT_MESSAGE, input);
        ServerProxy.getInstance().send(data);
    }
}
