package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;

import java.util.ArrayList;
import java.util.Vector;

public class CardsForPlayer
{
    private Vector<DestinationCard> destinationCards = new Vector<>();
    private String username = new String();

    public CardsForPlayer(Vector<DestinationCard> incomingCards, String forUser)
    {
        destinationCards = incomingCards;
        username = forUser;
    }

    public Vector<DestinationCard> getThreeDestCards()
    {
        return destinationCards;
    }

    public void setThreeDestCards(Vector<DestinationCard> threeDestCards)
    {
        this.destinationCards = threeDestCards;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
