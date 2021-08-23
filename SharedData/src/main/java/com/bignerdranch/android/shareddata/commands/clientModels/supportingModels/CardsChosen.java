package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;

import java.util.Vector;

public class CardsChosen {
    private Vector<DestinationCard> chosen = new Vector<>();
    private Vector<DestinationCard> notChosen = new Vector<>();
    private String username;
    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public CardsChosen(Vector<DestinationCard> chosen, Vector<DestinationCard> notChosen) {
        this.chosen = chosen;
        this.notChosen = notChosen;
        this.username = GameModel.getInstance().getClientPlayerInfo().getUsername();
        this.gameName = GameLobbyModel.getInstance().getGame().getGameName();
    }

    public Vector<DestinationCard> getChosen() {
        return chosen;
    }

    public void setChosen(Vector<DestinationCard> chosen) {
        this.chosen = chosen;
    }

    public Vector<DestinationCard> getNotChosen() {
        return notChosen;
    }

    public void setNotChosen(Vector<DestinationCard> notChosen) {
        this.notChosen = notChosen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
