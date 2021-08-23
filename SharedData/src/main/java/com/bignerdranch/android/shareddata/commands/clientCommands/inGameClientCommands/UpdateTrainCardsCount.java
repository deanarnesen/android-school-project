package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;

public class UpdateTrainCardsCount implements IClientCommand {
    private Pair information;

    public UpdateTrainCardsCount(Pair information) {
        this.information = information;
    }

    @Override
    public void execute() {
        GameModel.getInstance().updatePlayerTrainCardCount(information);
    }
}
