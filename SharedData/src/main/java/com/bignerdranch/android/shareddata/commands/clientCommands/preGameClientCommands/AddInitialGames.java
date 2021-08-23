package com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.JoinGameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;

import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

public class AddInitialGames implements IClientCommand {
    private Vector<GameListing> initialGames;

    public AddInitialGames(HashMap<String, GameListing> temp){
        Vector<GameListing> games = new Vector<>();
        games.addAll(temp.values());
        this.initialGames = games;
    }
    @Override
    public void execute() {
        JoinGameLobbyModel.getInstance().addInitialGames(initialGames);
        ClientModelRoot.getInstance().setCurrentScreen(PlayerGameState.JOIN_GAME_LOBBY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddInitialGames that = (AddInitialGames) o;
        return Objects.equals(initialGames, that.initialGames);
    }

    @Override
    public int hashCode() {

        return Objects.hash(initialGames);
    }
}
