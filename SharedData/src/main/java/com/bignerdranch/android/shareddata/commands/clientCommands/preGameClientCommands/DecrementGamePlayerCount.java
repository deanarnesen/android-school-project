package com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.JoinGameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;

import java.util.Objects;

public class DecrementGamePlayerCount implements IClientCommand {
    private GameListing gamePlayerLeft;

    public DecrementGamePlayerCount(GameListing temp) {
        gamePlayerLeft = temp;
    }

    @Override
    public void execute() {
        JoinGameLobbyModel.getInstance().decreasePlayerCount(gamePlayerLeft);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecrementGamePlayerCount that = (DecrementGamePlayerCount) o;
        return Objects.equals(gamePlayerLeft, that.gamePlayerLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamePlayerLeft);
    }
}