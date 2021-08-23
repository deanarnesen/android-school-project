package presenter;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ClientGamePlayer;

public interface IEndScreenPresenter {
    // Use the model to set the end game information
    void setPlayerInfos();
    // Send the user back to the joinGameLobby
    void playAgain();
}
