package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import com.bignerdranch.android.shareddata.commands.mapFeatures.IRoute;

import java.util.Vector;

public class ClientGamePlayer {
    private String username;
    private Color color;
    private Vector<IRoute> playerRoutes;
    private int numTrainCards = 4;
    private int numDestCards = 0;
    private int numTrainCars = 45;
    private int score = 0;
    private boolean turnState = false;

    public ClientGamePlayer(Player player) {
        setUsername(player.getName());
        setColor(player.getColor());
        playerRoutes = new Vector<>();
    }

    public ClientGamePlayer(String username, Vector<IRoute> playerRoutes, int numTrainCards,
                            int numDestCards, int numTrainCars, int score, boolean turnState) {
        setUsername(username);
        setPlayerRoutes(playerRoutes);
        setNumTrainCards(numTrainCards);
        setNumDestCards(numDestCards);
        setNumTrainCars(numTrainCars);
        setScore(score);
        setTurnState(turnState);
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        if(username != null){
            this.username = username;
        }
    }

    public Vector<IRoute> getPlayerRoutes() {
        return playerRoutes;
    }
    public void setPlayerRoutes(Vector<IRoute> playerRoutes) {
        if(playerRoutes != null){
            this.playerRoutes = playerRoutes;
        }
    }

    public int getNumTrainCards() {
        return numTrainCards;
    }
    public void setNumTrainCards(int numTrainCards) {
        if(numTrainCards >= 0){
            this.numTrainCards = numTrainCards;
        }
    }

    public int getNumDestCards() {
        return numDestCards;
    }
    public void setNumDestCards(int numDestCards) {
        if(numDestCards >= 0) {
            this.numDestCards = numDestCards;
        }
    }

    public int getNumTrainCars() {
        return numTrainCars;
    }
    public void setNumTrainCars(int numTrainCars) {
        if(numTrainCars >= 0){
            this.numTrainCars = numTrainCars;
        }
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        if(score >= 0){
            this.score = score;
        }
    }

    public int getFinalScore(){
        return score; //todo return actual final score
    }

    public boolean isTurnState() {
        return turnState;
    }
    public void setTurnState(boolean turnState) {
        this.turnState = turnState;
    }
}
