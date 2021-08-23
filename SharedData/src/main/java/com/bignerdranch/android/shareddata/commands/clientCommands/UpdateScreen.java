package com.bignerdranch.android.shareddata.commands.clientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;

public class UpdateScreen implements IClientCommand {
    PlayerGameState clientState;

    public void setClientState(PlayerGameState newState){
        clientState = newState;
    }

    public void execute(){
        //todo
    }
}
