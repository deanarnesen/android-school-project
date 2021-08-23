package cs340.tickettoride;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import presenter.CreateGamePresenter;

public class CreateGameActivity extends AppCompatActivity {

    private EditText mGameName;
    private EditText mNumPlayers;
    private Button mCreateButton;
    private Button mCancelButton;
    CreateGamePresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        presenter = new CreateGamePresenter(this);

        mGameName = (EditText) findViewById(R.id.create_game_name);
        mNumPlayers = (EditText) findViewById(R.id.create_game_numberpLayers);
        mCreateButton = (Button) findViewById(R.id.create_game_button);
        mCancelButton = (Button) findViewById(R.id.create_game_cancel_button);

        mCreateButton.setEnabled(false);

        mGameName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!mGameName.getText().toString().isEmpty() &&
                        isNumeric(mNumPlayers.getText().toString())){
                    mCreateButton.setEnabled(true);
                }
                else{
                    mCreateButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNumPlayers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!mGameName.getText().toString().isEmpty() &&
                        isNumeric(mNumPlayers.getText().toString())){
                    mCreateButton.setEnabled(true);
                }
                else{
                    mCreateButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateTask task = new CreateTask();
                task.execute();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }


    public void goToGameLobby(){
        //CREATE AN ACTIVITY
        Intent intent2 = new Intent(this, GameLobbyActivity.class);
        intent2.putExtra("Name", mGameName.getText().toString());
        intent2.putExtra("Admin", true);
        this.startActivity(intent2);
        this.finish();
    }



    private class CreateTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            presenter.createGame(mGameName.getText().toString(), Integer.parseInt(mNumPlayers.getText().toString()));
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
