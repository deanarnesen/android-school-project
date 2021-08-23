package com.bignerdranch.android.shareddata.commands;

public interface IClientCommand {
    void execute();
    boolean equals(Object o);
}
