package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

public class AddTrainCard implements IClientCommand {
    private TrainCarCard cardList;

    public AddTrainCard(TrainCarCard cardList) {
        this.cardList = cardList;
    }

    @Override
    public void execute() {
        GameModel.getInstance().addTrainCarCards(cardList);
    }
}
