package com.bignerdranch.android.shareddata.commands.commandSupport;

import java.io.Serializable;

public interface ICommandData extends Serializable {
    CommandType getCommandType();
    Object getCommandData();
}
