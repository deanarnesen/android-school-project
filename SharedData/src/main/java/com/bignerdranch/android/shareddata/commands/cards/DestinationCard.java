package com.bignerdranch.android.shareddata.commands.cards;

import java.util.Objects;

public class DestinationCard implements IDestinationCards, java.io.Serializable {
    String startCity;
    String endCity;
    int pointValue;

    public DestinationCard(String startCity, String endCity, int pointValue) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationCard that = (DestinationCard) o;
        return pointValue == that.pointValue &&
                Objects.equals(startCity, that.startCity) &&
                Objects.equals(endCity, that.endCity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(startCity, endCity, pointValue);
    }
}
