package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import java.util.Vector;

public class JoinGameResponse
{
    private Vector<Player> playerList;
    private GameListing game;

    public JoinGameResponse(Vector<Player> players, GameListing gameListed)
    {
        playerList = players;
        game = gameListed;
    }

    public Vector<Player> getPlayerList()
    {
        return playerList;
    }

    public void setPlayerList(Vector<Player> playerList)
    {
        this.playerList = playerList;
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
