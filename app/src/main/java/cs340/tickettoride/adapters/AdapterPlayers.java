package cs340.tickettoride.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;

import java.util.ArrayList;

import cs340.tickettoride.R;

public class AdapterPlayers extends RecyclerView.Adapter<AdapterPlayers.ViewHolder> {

    private ArrayList<Player> list;
    private Context mContext;
    private Player player;

    public AdapterPlayers(ArrayList<Player> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        player = list.get(i);

//        viewHolder.mGameName.setText(gameListing.getName());
//        viewHolder.mTag = gameListing.getName();

        viewHolder.mUsername.setText(player.getName());
        if(player.getColor() != null){
            viewHolder.setImage(player.getColor());
        }
        else{
            System.out.println("player has no color");
        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mUsername;
        public Color color;
        public ImageView colorView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mUsername = (TextView) itemView.findViewById(R.id.user_item_username);
            colorView = (ImageView) itemView.findViewById(R.id.player_color);


        }

        public void setImage(Color color){
            if(color == null){
                colorView.setImageResource(R.drawable.mybutton);
            }
            switch (color){
                case RED:
                    colorView.setImageResource(R.drawable.red);
                    break;
                case BLUE:
                    colorView.setImageResource(R.drawable.blue);
                    break;
                case BLACK:
                    colorView.setImageResource(R.drawable.black);
                    break;
                case GREEN:
                    colorView.setImageResource(R.drawable.green);
                    break;
                default:
                    colorView.setImageResource(R.drawable.yellow);
                    break;

            }
        }
    }
}
