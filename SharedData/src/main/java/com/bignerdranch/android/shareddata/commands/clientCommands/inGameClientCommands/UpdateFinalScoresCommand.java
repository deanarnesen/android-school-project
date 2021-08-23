package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.EndGameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.EndGamePlayer;

import java.util.Vector;

public class UpdateFinalScoresCommand implements IClientCommand {
    private Vector<EndGamePlayer> players;

    public UpdateFinalScoresCommand(Vector<EndGamePlayer> players) {
        this.players = players;
    }

    @Override
    public void execute() {
        EndGameModel.getInstance().setEndGameInfo(players);
    }
}
