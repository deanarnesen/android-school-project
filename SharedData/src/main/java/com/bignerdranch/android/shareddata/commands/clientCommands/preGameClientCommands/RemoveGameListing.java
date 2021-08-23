package com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.JoinGameLobbyModel;

import java.util.Objects;

public class RemoveGameListing implements IClientCommand
{
    private GameListing gameToRemove;

    public RemoveGameListing(GameListing temp){
        gameToRemove = temp;
    }

    @Override
    public void execute(){
        JoinGameLobbyModel.getInstance().removeGame(gameToRemove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveGameListing that = (RemoveGameListing) o;
        return Objects.equals(gameToRemove, that.gameToRemove);
    }

    @Override
    public int hashCode() {

        return Objects.hash(gameToRemove);
    }
}
