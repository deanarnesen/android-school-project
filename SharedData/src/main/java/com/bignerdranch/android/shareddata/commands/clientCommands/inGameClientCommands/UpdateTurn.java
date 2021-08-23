package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

public class UpdateTurn implements IClientCommand {
    private String newActive;

    public UpdateTurn(String newActive) {
        this.newActive = newActive;
    }

    @Override
    public void execute() {
        GameModel.getInstance().setActivePlayer(newActive);
    }
}
