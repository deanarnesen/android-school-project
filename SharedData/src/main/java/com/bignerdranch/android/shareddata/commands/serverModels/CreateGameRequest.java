package com.bignerdranch.android.shareddata.commands.serverModels;

public class CreateGameRequest {
    String username;
    String gameName;
    int maxPlayers;
    boolean isPublic;

    public CreateGameRequest() {
        isPublic = true;
    }

    public CreateGameRequest(String username, String gameName, int maxPlayers) {
        this.username = username;
        this.gameName = gameName;
        this.maxPlayers = maxPlayers;
        isPublic = true;
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

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isPublic()
    {
        return isPublic;
    }

    public void setPublic(boolean aPublic)
    {
        isPublic = aPublic;
    }
}
