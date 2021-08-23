package client_commands;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;

public interface IClientCommand
{
    void execute();
    boolean equals(Object o);
}
