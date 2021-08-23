package presenter;

import com.bignerdranch.android.shareddata.commands.clientModels.EndGameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.EndGamePlayer;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import cs340.tickettoride.EndGameActivity;

public class EndGamePresenter implements IEndScreenPresenter, Observer {
    private EndGameModel endGameModel;
    private Vector<EndGamePlayer> endGamePlayers;
    private EndGameActivity view;

    public EndGamePresenter(EndGameActivity view_i) {
        endGameModel = EndGameModel.getInstance();
        endGameModel.addObserver(this);
        view = view_i;
    }

    @Override
    public void update(Observable o, Object arg) {
        endGamePlayers = endGameModel.getEndGameInfo();
        setPlayerInfos();
    }

    @Override
    public void setPlayerInfos() {

        view.runOnUiThread(new Runnable(){
            public void run() {
                view.addPlayerStatusToTable(endGamePlayers);
            }
        });

    }

    @Override
    public void playAgain() {

    }
}
