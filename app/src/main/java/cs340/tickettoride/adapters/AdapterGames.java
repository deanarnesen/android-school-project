package cs340.tickettoride.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;

import java.util.ArrayList;

import cs340.tickettoride.ListGameActivity;
import cs340.tickettoride.R;

public class AdapterGames extends RecyclerView.Adapter<AdapterGames.ViewHolder> {

        public AdapterGames(ArrayList<GameListing> list, Context context) {
            this.list = list;
            mContext = context;
        }


        private ArrayList<GameListing> list;
        private Context mContext;
        private GameListing gameListing;




        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.game_item, viewGroup, false);

            return new ViewHolder(v);

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

            gameListing = list.get(i);

            viewHolder.mGameName.setText(gameListing.getGameName());
            viewHolder.mTag = gameListing.getGameName();
            viewHolder.gameListing = gameListing;
            int numPlayers = gameListing.getNumPlayers();

            viewHolder.mNumPlayers.setText(Integer.toString(numPlayers));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void updateList(ArrayList<GameListing> gameList){
            list = gameList;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView mGameName;
            public TextView mNumPlayers;
            public Button mButtonJoin;
            public String mTag;
            public GameListing gameListing;



            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                //itemView.setOnClickListener(this);
                mGameName = (TextView) itemView.findViewById(R.id.game_itme_game_name);
                mNumPlayers = (TextView) itemView.findViewById(R.id.game_item_no_players);
                mButtonJoin = (Button) itemView.findViewById(R.id.game_item_join_button_game);

                mButtonJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(mContext, mTag, Toast.LENGTH_SHORT).show();

                        joinButtonPressed(gameListing);
                        //Start new activity
                        /*Intent intent = new Intent(mContext, GameLobbyActivity.class);
                        intent.putExtra("Name", mTag );

                        mContext.startActivity(intent);*/

                    }
                });

//            textViewHeadLName = (TextView) itemView.findViewById(R.id.recycler_lname_out);
//            textViewHeadGender = (TextView) itemView.findViewById(R.id.recycler_gender_out);


            }

            /**
             * Functin called after pressing the join button for that specific game.
             */
            public void joinButtonPressed (GameListing gameListing){

                //calls activity, which then calls presenter
                if(mContext instanceof ListGameActivity){
                    ((ListGameActivity) mContext).setSelectedGame(gameListing);
                    ((ListGameActivity)mContext).joinGame(gameListing);
                }



            }




        }


}
