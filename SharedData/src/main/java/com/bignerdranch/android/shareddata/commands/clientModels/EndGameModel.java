package com.bignerdranch.android.shareddata.commands.clientModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.EndGamePlayer;

import java.util.Observable;
import java.util.Vector;

public class EndGameModel extends Observable {
    Vector<EndGamePlayer> endGameInfo;
    private static EndGameModel instance = new EndGameModel();

    public static EndGameModel getInstance(){
        return instance;
    }
    private EndGameModel() {
        endGameInfo  = new Vector<>();
    }

    public Vector<EndGamePlayer> getEndGameInfo() {
        return endGameInfo;
    }

    public void setEndGameInfo(Vector<EndGamePlayer> endGameInfo) {
        this.endGameInfo = endGameInfo;
        setChanged();
        notifyObservers();
    }
    public void refresh(){
        setChanged();
        notifyObservers();
    }
}
