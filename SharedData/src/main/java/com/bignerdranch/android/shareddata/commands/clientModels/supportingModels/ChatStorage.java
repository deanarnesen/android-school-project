package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

import java.util.Vector;

public class ChatStorage {
    public static ChatStorage instance = new ChatStorage();
    private Vector<ChatMessage> log = new Vector<>();

    private ChatStorage(){

    }

    public static ChatStorage getInstance(){
        return instance;
    }

    public void addMessage(ChatMessage message){
        log.add(message);
        if(ClientModelRoot.getInstance().getCurrentScreen() == PlayerGameState.GAME_LOBBY){
            GameLobbyModel.getInstance().addMessage();
        }
        else {
            GameModel.getInstance().addMessage();
        }
    }

    public Vector<ChatMessage> getLog(){
        return log;
    }
}
