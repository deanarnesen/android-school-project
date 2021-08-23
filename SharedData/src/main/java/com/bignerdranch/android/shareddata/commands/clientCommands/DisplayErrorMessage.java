package com.bignerdranch.android.shareddata.commands.clientCommands;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;

import java.util.Objects;

public class DisplayErrorMessage implements IClientCommand {
    private String errorMessage;

    public DisplayErrorMessage(CommandData cD) {
        this.errorMessage = (String) cD.getCommandData();
    }

    @Override
    public void execute() {
        ClientModelRoot.getInstance().setCurrentError(errorMessage);
        //TODO call presenter error message function
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisplayErrorMessage that = (DisplayErrorMessage) o;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(errorMessage);
    }
}
