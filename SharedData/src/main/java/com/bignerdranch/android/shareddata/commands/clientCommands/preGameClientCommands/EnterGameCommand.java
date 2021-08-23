package com.bignerdranch.android.shareddata.commands.clientCommands.preGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;

import static com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState.IN_GAME;

public class EnterGameCommand implements IClientCommand {
    @Override
    public void execute() {
        ClientModelRoot.getInstance().setCurrentScreen(IN_GAME);
        try{Thread.sleep(500);}
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
