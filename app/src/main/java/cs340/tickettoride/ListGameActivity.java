package cs340.tickettoride;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;

import java.util.ArrayList;

import cs340.tickettoride.adapters.AdapterGames;
import presenter.GameListPresenter;

public class ListGameActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewGames;
    AdapterGames mGameAdapter;
    GameListPresenter presenter;
    GameListing selectedGame = null;
    GameLobbyModel model = GameLobbyModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_game);

        presenter = new GameListPresenter(this);


        Button mCreateGame = (Button) findViewById(R.id.lobby_create_game);
        mCreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreateGame();
            }
        });


    }

    public void goToGameLobby(){
        Intent intent2 = new Intent(this, GameLobbyActivity.class);
        if(model.getGame() != null)
            selectedGame = model.getGame();
        intent2.putExtra("Name", selectedGame.getGameName());
        intent2.putExtra("Admin", false);
        this.startActivity(intent2); //this is called like three or four timesi
    }


    /**
     * After pressing the CREATE GAME button, it takes you to the create game window.
     */
    public void goToCreateGame(){
        presenter.setScreenCreateGame();
        Intent intent2 = new Intent(this, CreateGameActivity.class);
        this.startActivity(intent2);
    }



    /**
     * Function that add a new game to the recycler view. It adds it to the list of item first, and
     * then it notifies the Adapter of the changes so they show in the view.
     * @param toAdd
     */
    public void addGame(GameListing toAdd){

    }

    /**
     * Function that removes a game from the recycler vew. It removes of from the list of items,
     * and then it notifies the Adapter of the changes.
     * @param toRemove
     */
    public void removeGame(GameListing toRemove){

    }


    /**
     * Displays a Toast with the error message.
     */
    public void errorMessage(String message){


        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();


    }



    public void setList(ArrayList<GameListing> gameList){
        mRecyclerViewGames = (RecyclerView) findViewById(R.id.game_recycler_view);
        mRecyclerViewGames.setHasFixedSize(true);
        mRecyclerViewGames.setLayoutManager(new LinearLayoutManager(this));
        mGameAdapter = new AdapterGames(gameList, this);
        mRecyclerViewGames.setAdapter(mGameAdapter);
    }

    //makes a bg thread able to update the adapter list
    public void updateList(ArrayList<GameListing> gameList){
        final ArrayList<GameListing> list = gameList;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mGameAdapter.updateList(list);
                mGameAdapter.notifyDataSetChanged();
            }
        });

    }

    public void joinGame(GameListing gameListing){
        selectedGame = gameListing;
        GameLobbyModel lobbyModel = GameLobbyModel.getInstance();
        lobbyModel.setGame(selectedGame);

        new JoinGameTask().execute();
    }

    public void setSelectedGame(GameListing selectedGame) {
        this.selectedGame = selectedGame;
    }

    private class JoinGameTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            presenter.joinGame(selectedGame);
            return null;
        }
    }

}
