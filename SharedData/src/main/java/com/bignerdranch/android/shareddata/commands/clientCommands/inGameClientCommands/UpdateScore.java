package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;

public class UpdateScore implements IClientCommand {
    private Pair inormation;

    public UpdateScore(Pair inormation) {
        this.inormation = inormation;
    }

    @Override
    public void execute() {
        GameModel.getInstance().updatePlayerScore(inormation);
    }
}
