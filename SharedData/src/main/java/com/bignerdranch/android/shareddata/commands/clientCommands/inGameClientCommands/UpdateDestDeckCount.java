package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

public class UpdateDestDeckCount implements IClientCommand {
    private int destDeckCount;

    public UpdateDestDeckCount(int destDeckCount) {
        this.destDeckCount = destDeckCount;
    }

    @Override
    public void execute() {
        GameModel.getInstance().updateDestDeckCount(destDeckCount);
    }
}
