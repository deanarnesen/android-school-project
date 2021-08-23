package com.bignerdranch.android.shareddata.commands.clientModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;

import java.util.Objects;
import java.util.Observable;
import java.util.Vector;

public class JoinGameLobbyModel extends Observable {
    private Vector<GameListing> currentGames;
    private static JoinGameLobbyModel myInstance = new JoinGameLobbyModel();

    public static JoinGameLobbyModel getInstance(){
        return myInstance;
    }

    private JoinGameLobbyModel(){
        currentGames = new Vector<>();
        //addObserver(GameLobbyPresenter()); //todo remove (presenter attached itself)
    }

    public void addInitialGames(Vector<GameListing> currentGames){
        this.currentGames = currentGames;
    }

    public void addGame(GameListing gameToAdd)
    {
        if(gameToAdd != null){
            currentGames.add(gameToAdd);
            setChanged();
            notifyObservers(UpdateType.GAME_ADDED);
        }
    }

    public void removeGame(GameListing gameToRemove)
    {
        boolean found= false;
        int i = 0;
        for(GameListing game : currentGames){
            if(game.equals(gameToRemove)){
                found = true;
                break;
            }
            i++;
        }
        if(found) {
            currentGames.remove(i);
            setChanged();
            notifyObservers(UpdateType.GAME_REMOVED);
        }
    }

    public void increasePlayerCount(GameListing gameToIncrement){
        boolean found = false;
        for(GameListing game : currentGames){
            if(game.getGameName().equals(gameToIncrement.getGameName())){
                game.setPlayers(gameToIncrement.getPlayers());
                game.setNumPlayers(game.getNumPlayers()+1);
                found = true;
            }
        }
        if(found) {
            setChanged();
            notifyObservers(UpdateType.INCREASED_PLAYER_COUNT);
        }
    }


    public void decreasePlayerCount (GameListing gameToDecrease){
        boolean found = false;
        for(GameListing game : currentGames){
            if(game.getGameName().equals(gameToDecrease.getGameName())){
                game.setPlayers(gameToDecrease.getPlayers());
                game.setNumPlayers(game.getNumPlayers()-1);
                found = true;
            }
        }
        if(found) {
            setChanged();
            notifyObservers(UpdateType.DECREMENT_PLAYER_COUNT);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinGameLobbyModel that = (JoinGameLobbyModel) o;
        return Objects.equals(currentGames, that.currentGames);
    }

    @Override
    public int hashCode() {

        return Objects.hash(currentGames);
    }

    @Override
    public String toString() {
        return "JoinGameLobbyModel{" +
                "currentGames=" + currentGames +
                '}';
    }

    //added by Dean to fetch a game
    public GameListing getGameByName(String name){
        for (GameListing game : currentGames){
            if(game.getGameName().equals(name)){
                return game;
            }
        }
        return null;
    }

    //added by Dean to update a game
    public void updateGame(GameListing updatedGame){
        for(GameListing game : currentGames){
            if(game.equals(updatedGame)){
                game.setGameName(updatedGame.getGameName());
                game.setMaxNumOfPlayers(updatedGame.getMaxNumOfPlayers());
                game.setNumPlayers(game.getNumPlayers());
                game.setStarted(updatedGame.isStarted());
            }
        }
    }

    public Vector<GameListing> getCurrentGames() {
        return currentGames;
    }
}
