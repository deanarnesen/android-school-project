package com.bignerdranch.android.shareddata.commands.clientModels;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.PlayerGameState;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;

import java.util.Objects;
import java.util.Observable;

public class ClientModelRoot extends Observable {
    private String clientUserName;
    private PlayerGameState currentScreen = PlayerGameState.LOGIN;
    private String currentError = null;
    private static final ClientModelRoot myInstance = new ClientModelRoot();
    private boolean inGame = false;

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public static ClientModelRoot getInstance(){
        return myInstance;
    }

    private ClientModelRoot(){ }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        if(clientUserName != null){
            this.clientUserName = clientUserName;

        }
    }

    public PlayerGameState getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(PlayerGameState currentScreen) {
        if(currentScreen != null) {
            if(currentScreen.getClass() == PlayerGameState.class &&
                    !this.currentScreen.equals(currentScreen)) {
                this.currentScreen = currentScreen;
                setChanged();
                notifyObservers(UpdateType.SCREEN_STATE);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientModelRoot that = (ClientModelRoot) o;
        return Objects.equals(clientUserName, that.clientUserName) &&
                currentScreen == that.currentScreen;
    }

    @Override
    public int hashCode() {

        return Objects.hash(clientUserName, currentScreen);
    }

    @Override
    public String toString() {
        return "ClientModelRoot{" +
                "clientUserName='" + clientUserName + '\'' +
                ", currentScreen=" + currentScreen +
                '}';
    }

    public String getCurrentError() {
        return currentError;
    }

    public void setCurrentError(String currentError) {
        this.currentError = currentError;
        setChanged();
        notifyObservers(UpdateType.DISPLAY_ERROR);
    }
}
