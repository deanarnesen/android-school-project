package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.CardsForPlayer;

public class DrawDestinationCard implements IClientCommand
{
    private CardsForPlayer threeDestCards;

    public DrawDestinationCard(CardsForPlayer incomingCards)
    {
        threeDestCards = incomingCards;
    }

    @Override
    public void execute()
    {
        GameModel.getInstance().drawDestCard(threeDestCards);
    }
}
