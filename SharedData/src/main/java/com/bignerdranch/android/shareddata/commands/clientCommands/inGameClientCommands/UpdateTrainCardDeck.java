package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

public class UpdateTrainCardDeck implements IClientCommand {
    private int count;

    public UpdateTrainCardDeck(int count) {
        this.count = count;
    }

    @Override
    public void execute() {
        GameModel.getInstance().updateTrainCardDeck(count);
    }
}
