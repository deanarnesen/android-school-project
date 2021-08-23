package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;

public class UpdateDestCardCount implements IClientCommand {
    private Pair information;

    public UpdateDestCardCount(Pair information) {
        this.information = information;
    }

    @Override
    public void execute() {
        GameModel.getInstance().updatePlayerDestCardCount(information);
    }
}
