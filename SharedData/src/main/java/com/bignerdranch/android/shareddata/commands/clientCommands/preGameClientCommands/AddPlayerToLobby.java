package com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;

import java.util.Objects;

public class AddPlayerToLobby implements IClientCommand
{
    private Player playerToAdd;

    public AddPlayerToLobby(Player sob){
        playerToAdd = sob;
    }

    @Override
    public void execute(){
        GameLobbyModel.getInstance().addPlayerToLobby(playerToAdd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddPlayerToLobby that = (AddPlayerToLobby) o;
        return Objects.equals(playerToAdd, that.playerToAdd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(playerToAdd);
    }
}
