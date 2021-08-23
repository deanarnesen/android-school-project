package com.bignerdranch.android.shareddata.commands.serverModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;

public class UpdateDestCardRequest
{
    private GameListing game;
    private String username;

    public UpdateDestCardRequest(GameListing game, String username)
    {
        this.game = game;
        this.username = username;
    }

    public GameListing getGame()
    {
        return game;
    }

    public void setGame(GameListing game)
    {
        this.game = game;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
