package presenter;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;

public interface IGameLobbyPresenter {
    void setPlayerColor(Color color);
    void sendChat(String text);
    void startButton();
    void changeGameName(String text);
}
