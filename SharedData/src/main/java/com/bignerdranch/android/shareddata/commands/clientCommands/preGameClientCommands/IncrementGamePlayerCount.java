package com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.JoinGameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;

import java.util.Objects;

public class IncrementGamePlayerCount implements IClientCommand {
    private GameListing gamePlayerJoined;

    public IncrementGamePlayerCount(GameListing temp){
        gamePlayerJoined = temp;
    }

    @Override
    public void execute(){
        JoinGameLobbyModel.getInstance().increasePlayerCount(gamePlayerJoined);
        //ClientModelRoot.getInstance().setCurrentScreen(PlayerGameState.JOIN_GAME_LOBBY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncrementGamePlayerCount that = (IncrementGamePlayerCount) o;
        return Objects.equals(gamePlayerJoined, that.gamePlayerJoined);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gamePlayerJoined);
    }
}
