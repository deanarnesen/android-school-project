package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;

public class AddRoute implements IClientCommand {
    Pair data;

    public AddRoute(Pair data) {
        this.data = data;
    }

    @Override
    public void execute() {
        GameModel game = GameModel.getInstance();
        game.addRouteToPlayer(data);
    }
}
