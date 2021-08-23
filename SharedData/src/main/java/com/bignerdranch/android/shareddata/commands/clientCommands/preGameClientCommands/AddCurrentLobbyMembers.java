package com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.JoinGameResponse;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;

import java.util.Objects;
import java.util.Vector;

public class AddCurrentLobbyMembers implements IClientCommand {
    private JoinGameResponse response;

    public AddCurrentLobbyMembers(JoinGameResponse response) {
        this.response = response;
    }

    @Override
    public void execute() {
        if(response.getPlayerList() != null){
            GameLobbyModel.getInstance().addInitialPlayers(response.getPlayerList());
            GameLobbyModel.getInstance().setGame(response.getGame());
            ClientModelRoot.getInstance().setCurrentScreen(PlayerGameState.GAME_LOBBY);
        }
        //TODO goto single game lobby screen
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddCurrentLobbyMembers that = (AddCurrentLobbyMembers) o;
        return Objects.equals(response.getPlayerList(), that.response.getPlayerList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(response.getPlayerList());
    }
}
