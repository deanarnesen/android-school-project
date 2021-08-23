package com.bignerdranch.android.shareddata.commands.clientCommands;


import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;

import java.util.Objects;
import java.util.Queue;

public class CommandList implements IClientCommand {
    private Queue<CommandData> commandQueue;

    //we're assuming we have the right object here
    @SuppressWarnings("unchecked")
    public CommandList(Queue<CommandData> data){
        commandQueue = data;
    }

    @Override
    public void execute(){
        for(CommandData command : commandQueue){
            String newName = command.getCommandData();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandList that = (CommandList) o;
        return Objects.equals(commandQueue, that.commandQueue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(commandQueue);
    }
}
