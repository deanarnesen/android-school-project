package com.bignerdranch.android.shareddata.commands.clientCommands.inGameClientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;

import java.util.Vector;

public class UpdateRoutes implements IClientCommand {
    private Vector<Route> routes;
    public UpdateRoutes(Vector<Route> routes){
        this.routes = routes;
    }

    @Override
    public void execute() {
        GameModel.getInstance().setAllRoutes(routes);
    }
}
