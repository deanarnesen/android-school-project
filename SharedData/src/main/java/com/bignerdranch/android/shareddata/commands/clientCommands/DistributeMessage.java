package com.bignerdranch.android.shareddata.commands.clientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatStorage;

import java.util.Objects;

public class DistributeMessage implements IClientCommand
{
    private ChatMessage messageToAdd;

    public DistributeMessage(ChatMessage message) {
        this.messageToAdd = message;
    }

    @Override
    public void execute() {
        //GameLobbyModel.getInstance().addMessage(messageToAdd);
        ChatStorage.getInstance().addMessage(messageToAdd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributeMessage that = (DistributeMessage) o;
        return Objects.equals(messageToAdd, that.messageToAdd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageToAdd);
    }
}
