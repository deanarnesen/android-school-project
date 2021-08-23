package com.bignerdranch.android.shareddata.commands.serverModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;

public class JoinGameRequest {

    Player player;
    GameListing gameListing;

    public JoinGameRequest(Player player, GameListing gameListing) {
        this.player = player;
        this.gameListing = gameListing;
    }
    public JoinGameRequest() {
    }



    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameListing getGameListing() {
        return gameListing;
    }

    public void setGameListing(GameListing gameListing) {
        this.gameListing = gameListing;
    }
}
