package com.bignerdranch.android.shareddata.commands.serverModels;

import java.io.Serializable;

public class LoginRequestModel implements Serializable {
    String username;
    String password;

    public LoginRequestModel() {
    }

    public LoginRequestModel(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
