package com.bignerdranch.android.shareddata.commands.mapFeatures;

public interface IRoute {
    String getStartCity();
    void setStartCity(String startCity);
    String getEndCity();
    void setEndCity(String endCity);
    int getPointValue();
}
