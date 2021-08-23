package com.bignerdranch.android.shareddata.commands.serverModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;

public class LeaveGameRequest
{
    String username;
    String gameName;

    GameListing game = null;

    public LeaveGameRequest() {
    }

    /*public LeaveGameRequest(String username, GameListing game){
        this.game = game;
        this.username = username;
    }*/

    public LeaveGameRequest(String username, String gameName) {

        this.username = username;
        this.gameName = gameName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameName() {
        return gameName;
    }

    public void setPassword(String gameName) {
        this.gameName = gameName;
    }

    public void setGameListing(GameListing game){
        this.game = game;
    }
    public GameListing getGameListing (){
        return game;
    }

}
