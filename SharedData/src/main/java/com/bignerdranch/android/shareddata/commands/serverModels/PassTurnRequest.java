package com.bignerdranch.android.shareddata.commands.serverModels;

public class PassTurnRequest {

    String gameName;

    public PassTurnRequest(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

}
