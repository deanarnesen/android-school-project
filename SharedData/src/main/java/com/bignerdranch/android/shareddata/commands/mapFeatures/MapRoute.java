package com.bignerdranch.android.shareddata.commands.mapFeatures;

public class MapRoute implements IRoute{
    String startCity;
    String endCity;
    int pointValue;

    public MapRoute(String startCity, String endCity, int pointValue) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.pointValue = pointValue;
    }

    @Override
    public String getStartCity() {
        return startCity;
    }

    @Override
    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    @Override
    public String getEndCity() {
        return endCity;
    }

    @Override
    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    @Override
    public int getPointValue() {
        return pointValue;
    }
}
