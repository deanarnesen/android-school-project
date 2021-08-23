package cs340.tickettoride.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;

import java.util.ArrayList;

import cs340.tickettoride.R;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.ViewHolder> {

    private ArrayList<ChatMessage> list;
    private Context mContext;
    private ChatMessage chatMessage;

    public AdapterMessage(ArrayList<ChatMessage> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item, viewGroup, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        chatMessage = list.get(i);

        viewHolder.mMessage.setText(chatMessage.getMessage());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(ArrayList<ChatMessage> gameList){
        list = gameList;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mMessage;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            mMessage = (TextView) itemView.findViewById(R.id.message_icon_message);

        }


    }




}
