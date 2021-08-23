package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

public class EndGamePlayer {
    String username;
    int pointsFromDest;
    int pointsLostFromDest;
    int finalScore;

    public int getRouteBonus() {
        return routeBonus;
    }

    public void setRouteBonus(int routeBonus) {
        this.routeBonus = routeBonus;
    }

    int routeBonus;

    Boolean winner;

    public EndGamePlayer(String username) {
        this.username = username;
        winner = false; //guilty until proven innocent
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPointsFromDest() {
        return pointsFromDest;
    }

    public void setPointsFromDest(int pointsFromDest) {
        this.pointsFromDest = pointsFromDest;
    }

    public int getPointsLostFromDest() {
        return pointsLostFromDest;
    }

    public void setPointsLostFromDest(int pointsLostFromDest) {
        this.pointsLostFromDest = pointsLostFromDest;
    }

    public int getFinalScore() {
        return finalScore + routeBonus;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }
}
