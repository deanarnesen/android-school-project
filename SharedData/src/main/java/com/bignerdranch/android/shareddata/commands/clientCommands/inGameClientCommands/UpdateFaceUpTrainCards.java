package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

import java.util.Vector;

public class UpdateFaceUpTrainCards implements IClientCommand {
    private Vector<TrainCarCard> faceUpCards;

    public UpdateFaceUpTrainCards(Vector<TrainCarCard> faceUpCards) {
        this.faceUpCards = faceUpCards;
    }

    @Override
    public void execute() {
        GameModel.getInstance().updateFaceUpCards(faceUpCards);
    }
}
