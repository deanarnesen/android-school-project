package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

public class ServerPair {
    private String userName;
    private String gameName;
    private Integer value;

    public ServerPair(String userName, String gameName, Integer value) {
        this.userName = userName;
        this.gameName = gameName;
        this.value = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
