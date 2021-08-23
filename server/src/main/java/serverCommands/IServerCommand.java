package serverCommands;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;

public interface IServerCommand {
    CommandData execute();
}
