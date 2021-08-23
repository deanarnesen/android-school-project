package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import com.bignerdranch.android.shareddata.commands.mapFeatures.IRoute;

public class Route implements IRoute, java.io.Serializable{
    //todo routeID?
    private String cityEndpoint1;
    private String cityEndpoint2;
    private RouteColor routeColor;
    private int length;
    boolean isTaken;
    private Color playerTakenBy;
    private int id;

    public Route(String city1, String city2, RouteColor color, int length, int id){
        this.cityEndpoint1 = city1;
        this.cityEndpoint2 = city2;
        routeColor = color;
        this.length = length;
        isTaken = false;
        playerTakenBy = null; //todo change when taken.
        this.id = id;
    }

    public Route(){
        //may not need an empty constructor
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public void setPlayerTakenBy(Color playerTakenBy) {
        this.playerTakenBy = playerTakenBy;
    }

    @Override
    public String getStartCity() {
        return cityEndpoint1;
    }

    @Override
    public void setStartCity(String startCity) {
        cityEndpoint1 = startCity;
    }

    @Override
    public String getEndCity() {
        return cityEndpoint2;
    }

    @Override
    public void setEndCity(String endCity) {
        cityEndpoint2 = endCity;
    }

    @Override
    public int getPointValue() {
        switch(length) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
        }
        return 0;
    }

    public Color getPlayerTakenBy() {
        return playerTakenBy;
    }

    public int getId(){
        return id;
    }

    public boolean isTaken(){
        return isTaken;
    }

    public RouteColor getRouteColor(){
        return routeColor;
    }

    public int getLength(){
        return length;
    }

}
