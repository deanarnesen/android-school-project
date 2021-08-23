package com.bignerdranch.android.shareddata.commands.clientModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatStorage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;

import java.util.Objects;
import java.util.Observable;
import java.util.Vector;

public class GameLobbyModel extends Observable {
    private Vector<Player> currentPlayers;
    private Vector<ChatMessage> allMessages;
    private Vector<LobbyColor> colors;
    private ChatStorage chatLog;
    private GameListing game;

    private static final GameLobbyModel myInstance = new GameLobbyModel();

    private class LobbyColor{
        Color color;
        Boolean taken;
        public LobbyColor(Color color){
            taken = false;
            this.color = color;
        }
    }

    private GameLobbyModel(){
        currentPlayers = new Vector<>();
        allMessages = new Vector<>();
        colors = new Vector<>();
        chatLog = ChatStorage.getInstance();
        //todo initialize each color
    }

    public static GameLobbyModel getInstance(){
        return myInstance;
    }

//    public void addMessage(ChatMessage newMessage){
//        if(newMessage != null){
//            if(newMessage.isValidMessage()){
//                allMessages.add(newMessage);
//                setChanged();
//                notifyObservers(UpdateType.CHAT);
//            }
//        }
//    }

    public void addMessage(){
        setChanged();
        notifyObservers(UpdateType.CHAT);
    }


    public void addPlayerToLobby(Player newPlayer){
        if(newPlayer != null){
            boolean found = false;
            for(Player p : this.currentPlayers){
                if(newPlayer.getName().equals(p.getName())){
                    found = true;
                }
            }
            if(!found){
                currentPlayers.add(newPlayer);
                setChanged();
                notifyObservers(UpdateType.PLAYER_ADDED);
            }
        }
    }

    public void removePlayerToLobby(Player playerToRemove){
        if(playerToRemove != null){
            int i = 0;
            for(Player player : currentPlayers){
                if(player.equals(playerToRemove)){

                    break;
                }
                i++;
            }
            currentPlayers.remove(i);
            setChanged();
            notifyObservers(UpdateType.PLAYER_REMOVED);
        }
    }

    public void takeColor(Color color){
        //todo manage the color list //done on server side
        //notifyObservers(UpdateType.PLAYER);
    }

    public Vector<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public Vector<ChatMessage> getAllMessages() {
        return chatLog.getLog();
    }

    public void addInitialPlayers(Vector<Player> initialPlayers){
        this.currentPlayers = new Vector<>(initialPlayers);
        setChanged();
        notifyObservers(UpdateType.PLAYER_ADDED);
    }

    public GameListing getGame() {
        return game;
    }

    public void setGame(GameListing game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameLobbyModel that = (GameLobbyModel) o;
        return Objects.equals(currentPlayers, that.currentPlayers) &&
                Objects.equals(allMessages, that.allMessages) &&
                Objects.equals(colors, that.colors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(currentPlayers, allMessages, colors);
    }

    @Override
    public String toString() {
        return "GameLobbyModel{" +
                "currentPlayers=" + currentPlayers +
                ", allMessages=" + allMessages +
                ", colors=" + colors +
                '}';
    }
}
