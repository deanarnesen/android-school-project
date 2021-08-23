package com.bignerdranch.android.shareddata.commands.serverModels;

public class DrawDestCardRequest
{
    private String username;
    private String gameName;

    public DrawDestCardRequest(String username, String gameName)
    {
        this.username = username;
        this.gameName = gameName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
