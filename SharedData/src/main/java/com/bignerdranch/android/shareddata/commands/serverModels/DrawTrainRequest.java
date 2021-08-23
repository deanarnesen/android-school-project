package com.bignerdranch.android.shareddata.commands.serverModels;

public class DrawTrainRequest {
    int cardPosition;
    String gameName;
    String userName;

    public DrawTrainRequest(int cardPosition, String gameName, String userName) {
        this.cardPosition = cardPosition;
        this.gameName = gameName;
        this.userName = userName;
    }

    public int getCardPosition() {
        return cardPosition;
    }

    public String getGameName() {
        return gameName;
    }

    public String getUserName() {
        return userName;
    }
}
