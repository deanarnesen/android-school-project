package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;

public class UpdateTrainCars implements IClientCommand {
    private Pair information;

    public UpdateTrainCars(Pair information) {
        this.information = information;
    }

    @Override
    public void execute() {
        GameModel.getInstance().updateTrainCarPieces(information);
    }
}
