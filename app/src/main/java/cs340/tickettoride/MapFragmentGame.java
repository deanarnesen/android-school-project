package cs340.tickettoride;


import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shareddata.commands.cards.CardType;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;

import java.util.ArrayList;
import java.util.Vector;

import cs340.tickettoride.betweenFragments.CommunBetweenFragm;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragmentGame extends Fragment {

    CommunBetweenFragm communication = CommunBetweenFragm.getInstance();
    ArrayList<TableLayout> listTables = new ArrayList<>();
    ArrayList<String> usernames;
    ArrayList<String> avaliableColors = new ArrayList<>();
    private LinearLayout mLinearLayout;
    ImageView mView1;
    ImageView mView2;
    ImageView mView3;
    ImageView mView4;
    ImageView mView5;
    String previousUsername;


    public MapFragmentGame() {
        // Required empty public constructor
    }

    public void updateTrainCardsfrag(){

        Vector<TrainCarCard> list = communication.getTrainCarCards();
        int i =0;
        for(TrainCarCard card : list){
            matchCardToTrainImage(card.getCardType(), i);
            i++;
        }

    }


    public void setActivePlayerfrag(String username){
        if(previousUsername!=null) {
            TableLayout table = getTable(previousUsername);
            TableRow row = (TableRow) table.getChildAt(0);
            row.setBackgroundColor(Color.parseColor("#99474747"));
        }


        TableLayout table = getTable(username);
        TableRow row = (TableRow) table.getChildAt(0);
        row.setBackgroundColor(Color.parseColor("#14a518"));
        previousUsername= username;

    }
    public void setTrainCardNumberfrag(String username, int value){
        TableLayout table = getTable(username);
        TableRow row = (TableRow) table.getChildAt(4);
        TextView vi = (TextView) row.getChildAt(1);
        vi.setText(Integer.toString(value));
    }

    public void updateTrainDeckfrag(int cards){
        TextView textv = getActivity().findViewById(R.id.stats_train_cards);
        textv.setText(Integer.toString(cards));
    }

    public void updateDestDeckfrag(int cardsRemaining){
        TextView textv = getActivity().findViewById(R.id.stats_dest_cards);
        textv.setText(Integer.toString(cardsRemaining));
    }


    public void setDestCardNumberfrag(String username, int cards) {
        TableLayout table = getTable(username);
        TableRow row = (TableRow) table.getChildAt(3);
        TextView vi = (TextView) row.getChildAt(1);
        vi.setText(Integer.toString(cards));
    }

    private void matchCardToTrainImage(CardType type, int i){

        Drawable image;
        switch (type){
            case BLACK:
                image = getActivity().getResources().getDrawable(R.drawable.black_train);
                break;
            case BLUE:
                image = getActivity().getResources().getDrawable(R.drawable.blue_train);
                break;
            case RED:
                image = getActivity().getResources().getDrawable(R.drawable.red_train);
                break;
            case GREEN:
                image = getActivity().getResources().getDrawable(R.drawable.green_train);
                break;
            case YELLOW:
                image = getActivity().getResources().getDrawable(R.drawable.yellow_train);
                break;
            case PURPLE:
                image = getActivity().getResources().getDrawable(R.drawable.purple_train);
                break;
            case WHITE:
                image = getActivity().getResources().getDrawable(R.drawable.white_train);
                break;
            case ORANGE:
                image = getActivity().getResources().getDrawable(R.drawable.brown_train);
                break;
            case LOCOMOTIVE:
                image = getActivity().getResources().getDrawable(R.drawable.wild_train0);
                break;
                default:
                    image = getActivity().getResources().getDrawable(R.drawable.calgary_phoenix);
                    break;
        }

        switch(i){
            case 0:
                mView1.setImageDrawable(image);
                break;
            case 1:
                mView2.setImageDrawable(image);
                break;
            case 2:
                mView3.setImageDrawable(image);
                break;
            case 3:
                mView4.setImageDrawable(image);
                break;
            case 4:
                mView5.setImageDrawable(image);
                break;
            default:
                mView1.setImageDrawable(image);
                break;




        }

    }


    public void setPlayerScoreFrag(String name, int score){
        TableLayout table = getTable(name);
        TableRow row = (TableRow) table.getChildAt(1);
        TextView vi = (TextView) row.getChildAt(1);
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        vi.setText(Integer.toString(score));
     }


    private TableLayout getTable(String username){
        for(TableLayout tab: listTables){
            TableRow row = (TableRow) tab.getChildAt(0);
            TextView vi = (TextView) row.getChildAt(0);
            if(vi.getText().toString().equals(username)){
                return tab;
            }
        }

        return null;
    }

    public void setPlayerCarsLeftfrag (String username, int value){
        TableLayout table = getTable(username);
        TableRow row = (TableRow) table.getChildAt(2);
        TextView vi = (TextView) row.getChildAt(1);

        vi.setGravity(Gravity.CENTER_HORIZONTAL);
        vi.setText(Integer.toString(value));
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map_fragment_game, container, false);
        usernames = communication.getUsernames();
        mLinearLayout = v.findViewById(R.id.linear_layout_for_tables_players);
        setUpUsers();
        mView1 = (ImageView) v.findViewById(R.id.map_show_card1);
        mView2 = (ImageView) v.findViewById(R.id.map_show_card2);
        mView3 = (ImageView) v.findViewById(R.id.map_show_card3);
        mView4 = (ImageView) v.findViewById(R.id.map_show_card4);
        mView5 = (ImageView) v.findViewById(R.id.map_show_card5);


       // mView1.setImageResource(R.drawable.black_train);
        return v;
    }


    public void setUpUsers(){
        int i = 0;
        for(String names: usernames){
            TableLayout table = new TableLayout(this.getContext());
            table.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            addTitleToTable(table, names, i);
            addRow(table, "Score", i);
            addRow(table, "No. Train", i);
            addRow(table, "Dest.", i);
            addRow(table, "Reaining Train", i);

            mLinearLayout.addView(table);
            listTables.add(table);
            i++;
        }

    }

    public void showToast(String toast){
        Toast.makeText(this.getContext(), toast, Toast.LENGTH_SHORT).show();

    }

    public void addTitleToTable(TableLayout table, String label, int index){
        TableRow title = new TableRow(this.getContext());
        title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
        title.setGravity(Gravity.CENTER);
        title.setBackgroundColor(Color.parseColor("#99474747"));

        TextView user = new TextView(this.getContext());
        user.setText(label);
        user.setGravity(Gravity.CENTER);
//        user.setPadding(5, 0,0, 0);
        user.setTextColor(Color.parseColor("#bbc5c1"));
        user.setTypeface(Typeface.DEFAULT_BOLD);

        title.addView(user);
        table.addView(title);

    }

    public void addRow(TableLayout table, String label, int index){
        //Set the score row.
        TableRow row = new TableRow(this.getContext());
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f));
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        row.setBackgroundColor(Color.parseColor("#99474747"));

        TextView title = new TextView(this.getContext());
        title.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f));
        title.setText(label);
//        title.setPadding(5, 0,0, 0);
        title.setTextSize(10);
        title.setTextColor(Color.parseColor("#bbc5c1"));

        TextView toChage = new TextView(this.getContext());
        toChage.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f));
        toChage.setText("0");
        toChage.setId(index);
        toChage.setGravity(Gravity.CENTER_HORIZONTAL);
        toChage.setTextColor(Color.parseColor("#cf3c26"));
        toChage.setTextSize(10);


        row.addView(title);
        row.addView(toChage);

        table.addView(row);




    }

}
