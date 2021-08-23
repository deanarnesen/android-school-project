package com.bignerdranch.android.shareddata.commands.commandSupport;

public class Pair {
    private String userName;
    private int value;

    public Pair() {
    }

    public Pair(String userName, int value) {

        this.userName = userName;
        this.value = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
