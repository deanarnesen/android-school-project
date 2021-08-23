package com.bignerdranch.android.shareddata.commands.serverModels;

public class LastRoundRequest
{
    private String gameName;

    public LastRoundRequest(String name)
    {
        gameName = name;
    }

    public String getGameName()
    {
        return gameName;
    }

    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }
}
