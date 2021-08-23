package cs340.tickettoride;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;

import java.util.ArrayList;

import cs340.tickettoride.adapters.AdapterMessage;
import cs340.tickettoride.adapters.AdapterPlayers;
import presenter.GameLobbyPresenter;

public class GameLobbyActivity extends AppCompatActivity {

    private GameListing cGameListing;
    private TextView mGameName;
    private EditText messageWritten;
    private ArrayList<Player> players;
    private ArrayList<ChatMessage> chatMessages= new ArrayList<>();
    private GameLobbyPresenter presenter;
    private GameListing selectedGame;

    private RecyclerView mRecyclerViewPlayers;
    private AdapterPlayers mPlayerAdapter;
    private RecyclerView getmRecyclerViewMessages;
    private AdapterMessage mChatMessagesAdapter;

    private String gameName;
    private boolean admin = false;


    private Button mCancelButton;
    private Button mStartButton;
    private Button mSendChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Getting information from the intent.
        Intent intent = getIntent();
        gameName = intent.getStringExtra("Name");
        admin = intent.getBooleanExtra("Admin", false);
        setContentView(R.layout.activity_game_lobby);
        presenter = new GameLobbyPresenter(this);

        mCancelButton = (Button) findViewById(R.id.game_lobby_cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                callCancelTask();
                GameLobbyModel model = GameLobbyModel.getInstance();//
                selectedGame = GameLobbyModel.getInstance().getGame();
                new LeaveGameTask().execute();
                onBackPressed();
            }
        });



        mStartButton = (Button) findViewById(R.id.game_lobby_start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new InitializeGame().execute();
            }
        });
//        if (!admin){
//
//            mStartButton.setVisibility(View.GONE);
//
//        }
        mSendChatButton = (Button) findViewById(R.id.send_message_button);
        mSendChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendChatMessage().execute();
            }
        });


        mSendChatButton.setEnabled(false);
        messageWritten = findViewById(R.id.message_text);
        messageWritten.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(count > 0)
                {
                    mSendChatButton.setEnabled(true);
                }
                else
                {
                    mSendChatButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

    }

    public String getGameName(){
        return gameName;
    }

    public void initilizeLists(ArrayList<Player> players, ArrayList<ChatMessage> messages){
        this.players = players;
        this.chatMessages = messages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages){
        this.chatMessages = chatMessages;
    }

    public void setChat(ArrayList<ChatMessage> messages)
    {
        this.chatMessages = messages;

        getmRecyclerViewMessages = (RecyclerView) findViewById(R.id.game_lobby_chat_list);

        getmRecyclerViewMessages.setHasFixedSize(true);
        getmRecyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));

        mChatMessagesAdapter = new AdapterMessage(chatMessages, this);
        getmRecyclerViewMessages.setAdapter(mChatMessagesAdapter);
    }

    /**
     * Fills the array of players with the arrayList passed
     * @param players
     */
    public void setPlayers(ArrayList<Player> players){

        this.players = players;

        mRecyclerViewPlayers = (RecyclerView) findViewById(R.id.game_lobby_player);

        mRecyclerViewPlayers.setHasFixedSize(true);
        mRecyclerViewPlayers.setLayoutManager(new LinearLayoutManager(this));

        mPlayerAdapter = new AdapterPlayers(players, this);
        mRecyclerViewPlayers.setAdapter(mPlayerAdapter);
    }

    public void moveToMap(){
        //CREATE AN ACTIVITY
        Intent intent = new Intent(this, MapGameActivity.class); //this is always called even if it is an error
        this.startActivity(intent);
        this.finish();
    }


    public void updatePlayers(ArrayList<Player> players){
        this.players = players;
        if(mPlayerAdapter != null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPlayerAdapter.notifyDataSetChanged();
                }
            });
        }
        else{
            System.out.println("adapter not set yet");
        }
    }

    public void updateMessages(ArrayList<ChatMessage> messages)
    {
        this.chatMessages = messages;
        if(mChatMessagesAdapter != null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageWritten.getText().clear();
                    mChatMessagesAdapter.notifyDataSetChanged();
                }
            });
        }
        else{
            System.out.println("adapter not set yet");
        }
    }

    public void errorMessage(String message){
        //Todo: Change the "Loging In" by the message givne.
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


    private class LeaveGameTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids){
            presenter.leaveGame(selectedGame);
            return null;
        }
    }


    /**
     * This will be called once the user hits the start button.6
     */
    private class InitializeGame extends AsyncTask<GameLobbyActivity, String, Void> {
        @Override
        protected Void doInBackground(GameLobbyActivity... voids) {
            presenter.startButton();
            return null;
        }
    }

    private class SendChatMessage extends AsyncTask<GameLobbyActivity, String, Void> {
        @Override
        protected Void doInBackground(GameLobbyActivity... voids) {
            presenter.sendChat(messageWritten.getText().toString());
            return null;
        }
    }
}
