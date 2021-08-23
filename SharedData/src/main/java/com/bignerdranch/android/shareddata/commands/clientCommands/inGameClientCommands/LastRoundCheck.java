package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

public class LastRoundCheck implements IClientCommand
{
    @Override
    public void execute()
    {
        GameModel.getInstance().notifyLastRound();
    }
}
