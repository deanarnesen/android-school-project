package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.EndGameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.EndGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;

import java.util.Vector;

public class GoToEndScreen implements IClientCommand
{
    private Vector<EndGamePlayer> players;
    public GoToEndScreen(Vector<EndGamePlayer> players){
        this.players = players;
    }
    @Override
    public void execute()
    {
        ClientModelRoot.getInstance().setCurrentScreen(PlayerGameState.END_GAME);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EndGameModel.getInstance().setEndGameInfo(players);
    }
}
