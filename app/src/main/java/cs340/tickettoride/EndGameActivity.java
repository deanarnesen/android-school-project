package cs340.tickettoride;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.EndGamePlayer;

import java.util.Locale;
import java.util.Vector;

import presenter.EndGamePresenter;

public class EndGameActivity extends Activity {

    private GameModel model = GameModel.getInstance();
    private TableLayout myTable;
    private EndGamePresenter mPresenter;
    private TextView winnerLooser;
    private LinearLayout box;


    //dummy list of EndGamePlayers
    private Vector<EndGamePlayer> dummyList = new Vector<>();




    private void fillDummyList(){

        EndGamePlayer one = new EndGamePlayer("Mike");
        one.setFinalScore(100);
        one.setPointsFromDest(30);
        one.setPointsLostFromDest(70);
        one.setWinner(true);
        EndGamePlayer two = new EndGamePlayer("Peter");
        two.setFinalScore(100);
        two.setPointsFromDest(30);
        two.setPointsLostFromDest(70);
        two.setWinner(false);

        dummyList.add(one);

        dummyList.add(two);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        if(model.getWinnerStatus()){
            setContentView(R.layout.activity_end_game);
        }else{
            setContentView(R.layout.activity_end_game_looser);
        }
*/


        setContentView(R.layout.activity_end_game);
        myTable = findViewById(R.id.end_game_score_table);

        mPresenter = new EndGamePresenter(this);

        winnerLooser = findViewById(R.id.end_game_winner_looser);
        box = findViewById(R.id.end_game_box_color);

    }

    /**
     * This function should be called when you want to display the information of a player.
     */
    public void addPlayerStatusToTable(Vector<EndGamePlayer> list){
        TableLayout table = this.findViewById(R.id.end_game_score_table);
        int totalScore = 0;
        for (EndGamePlayer player : list){

//            setColorsWinnerLooser(player);

            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f));
            row.setBackgroundColor(Color.parseColor("#99474747"));

            if (player.getUsername().equals(GameModel.getInstance().getActivePlayer())){
                setColorsWinnerLooser(player);
            }


            //Column 1
            TextView username = new TextView(this);
            username.setText(player.getUsername());
            username.setGravity(Gravity.LEFT);
            username.setLayoutParams(new TableRow.LayoutParams (0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            username.setTextColor(Color.parseColor("#b49db0"));
            row.addView(username);

            //Column 2
            TextView totalDesPoints = new TextView(this);
            //totalDesPoints.setText(Integer.toString(player.getPointsFromDest()));
            totalDesPoints.setText(String.format(Locale.ENGLISH,"%d", player.getPointsFromDest()));
            totalDesPoints.setLayoutParams(new TableRow.LayoutParams (0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            totalDesPoints.setTextColor(Color.parseColor("#b49db0"));
            totalDesPoints.setGravity(Gravity.CENTER_HORIZONTAL);
            row.addView(totalDesPoints);

            //Column 3
            TextView lostPoints = new TextView(this);
            lostPoints.setText(Integer.toString(player.getPointsLostFromDest()));
            lostPoints.setGravity(Gravity.CENTER_HORIZONTAL);
            lostPoints.setLayoutParams(new TableRow.LayoutParams (0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            lostPoints.setTextColor(Color.parseColor("#b49db0"));
            row.addView(lostPoints);

            //Column 4
            TextView longestPaths = new TextView(this);
            longestPaths.setText(Integer.toString(player.getRouteBonus()));
            longestPaths.setGravity(Gravity.CENTER_HORIZONTAL);
            longestPaths.setLayoutParams(new TableRow.LayoutParams (0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            longestPaths.setTextColor(Color.parseColor("#b49db0"));
            row.addView(longestPaths);


            //Column 5
            TextView total = new TextView(this);

            total.setText(Integer.toString(player.getFinalScore()));
            total.setGravity(Gravity.CENTER_HORIZONTAL);
            total.setTextColor(Color.parseColor("#b49db0"));
            total.setLayoutParams(new TableRow.LayoutParams (0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            row.addView(total);

            table.addView(row);



        }

    }


    public void setColorsWinnerLooser(EndGamePlayer player){
        if (player.getWinner()){
            winnerLooser.setText("YOU WIN!!!");
            winnerLooser.setTextColor(Color.parseColor("#ffd31b"));
            box.setBackgroundColor(Color.parseColor("#99a26101"));

        }else{
            winnerLooser.setText("LOSER!!!");
            winnerLooser.setTextColor(Color.parseColor("#ff0c2a"));
            box.setBackgroundColor(Color.parseColor("#99690511"));
        }

    }



    public void play_again(View view){
        Toast.makeText(this, "I should go to GameList Lobby", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

}
