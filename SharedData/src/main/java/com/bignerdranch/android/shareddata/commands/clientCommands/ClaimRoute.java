package com.bignerdranch.android.shareddata.commands.clientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;

public class ClaimRoute implements IClientCommand {
    Player claimer;
    Route route;


    @Override
    public void execute() {
        //todo (not needed phase 2)
    }

    public void setClaimer(Player claimer) {
        this.claimer = claimer;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
