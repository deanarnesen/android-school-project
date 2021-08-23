package com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.JoinGameLobbyModel;

import java.util.Objects;

public class AddGameListing implements IClientCommand
{
    private GameListing gameToAdd;
    private ClientModelRoot modelRoot = ClientModelRoot.getInstance();
    public AddGameListing(GameListing temp) {
        gameToAdd = temp;
    }

    @Override
    public void execute(){

        JoinGameLobbyModel.getInstance().addGame(gameToAdd);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddGameListing that = (AddGameListing) o;
        return Objects.equals(gameToAdd, that.gameToAdd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gameToAdd);
    }
}
