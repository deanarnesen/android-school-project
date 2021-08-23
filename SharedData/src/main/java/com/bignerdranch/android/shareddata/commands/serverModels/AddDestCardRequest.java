package com.bignerdranch.android.shareddata.commands.serverModels;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;

public class AddDestCardRequest
{
    private DestinationCard cardToAdd;
    private String gameName;
    private String username;

    //use the username "deck" if you want to add destination cards back to the deck that the user didn't take
    public AddDestCardRequest(DestinationCard card, String gameName, String username)
    {
        this.cardToAdd = card;
        this.gameName = gameName;
        this.username = username;
    }

    public DestinationCard getCardToAdd()
    {
        return cardToAdd;
    }

    public void setCardToAdd(DestinationCard cardToAdd)
    {
        this.cardToAdd = cardToAdd;
    }

    public String getGameName()
    {
        return gameName;
    }

    public void setGameName(String gameName)
    {
        this.gameName = gameName;
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
