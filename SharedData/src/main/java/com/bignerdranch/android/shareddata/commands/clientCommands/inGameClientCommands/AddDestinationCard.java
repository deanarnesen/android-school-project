package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

public class AddDestinationCard implements IClientCommand {
    private DestinationCard cardToAdd;

    public AddDestinationCard(DestinationCard cardToAdd) {
        this.cardToAdd = cardToAdd;
    }

    @Override
    public void execute() {
        GameModel.getInstance().addDestinationCard(cardToAdd);
    }
}
