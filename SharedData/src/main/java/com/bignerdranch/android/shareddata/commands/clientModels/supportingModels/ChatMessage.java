package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import java.io.Serializable;

public class ChatMessage implements Serializable {
    private String messageSender;
    private String message;
    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ChatMessage(String messageSender, String message, String name) {
        this.messageSender = messageSender;
        this.message = message;
        this.gameName = name;
    }

    public boolean isValidMessage(){
        if(messageSender != null && message != null){
            return true;
        }
        return false;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.equals(messageSender, that.messageSender) &&
                    Objects.equals(message, that.message);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(messageSender, message);
        }
        return -1;
    }*/

    @Override
    public String toString() {
        return "ChatMessage{" +
                "messageSender='" + messageSender + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
