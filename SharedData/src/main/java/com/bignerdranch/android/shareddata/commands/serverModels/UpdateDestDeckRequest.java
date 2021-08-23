package com.bignerdranch.android.shareddata.commands.serverModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;

public class UpdateDestDeckRequest
{
    private GameListing game;

    public UpdateDestDeckRequest(GameListing game)
    {
        this.game = game;
    }

    public GameListing getGame()
    {
        return game;
    }

    public void setGame(GameListing game)
    {
        this.game = game;
    }
}
