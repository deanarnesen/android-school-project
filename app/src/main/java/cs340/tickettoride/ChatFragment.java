package cs340.tickettoride;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatStorage;

import java.util.ArrayList;

import cs340.tickettoride.adapters.AdapterMessage;
import cs340.tickettoride.adapters.AdapterPlayers;
import presenter.MapPresenter;


/**
 * A simple {@link Fragment} subclass.
 * This fragment shows the list of messages sent by each member of the team across the server.
 *
 * Operations are provided to send and receive ChatMessages objects.
 *
 * messageWritten : message that will be sent to the user
 * chatMessages : list of ChatMessages stored in the client that will be display when the chatFragment opens.y
 * getmRecyclerViewMessages : the RecyclerView that will create the list for the chatMessages to be display
 * mChatMessagesAdapter : the Adapter that will wrap every message into a specific xml
 * mSendChatButton : button that will send the chat across the server to the other clients
 *
 *
 * @invariant An activity has to be loaded before calling this fragment.
 * @invariant A recycler view has to be implemented
 * @invariant The specific Adapter that handle ChatMessages has to be implemented.
 *
 */
public class ChatFragment extends Fragment {

    private EditText messageWritten;
    private ArrayList<ChatMessage> chatMessages= new ArrayList<>();

    private RecyclerView getmRecyclerViewMessages;
    private AdapterMessage mChatMessagesAdapter;

    private Button mSendChatButton;

    /**
     * Empty constructor
     *
     */
    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Method that creates the view for the UI.
     * @param inflater
     * @param container
     * @param savedInstanceState
     *
     * @pre An activity has to be loaded before calling this function.
     * @post A view will be display in the interface
     *
     * @return a View object
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_chat, container, false);

        mSendChatButton = (Button) v.findViewById(R.id.fragment_chat_send_message);
        mSendChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendChatMessageAsync().execute();
            }
        });


        mSendChatButton.setEnabled(false);
        messageWritten = v.findViewById(R.id.fragment_chat_text_tosend);
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
        setChat(new ArrayList<ChatMessage>(ChatStorage.getInstance().getLog()), v);
        return v;
    }

    /**
     * Method that creates the list of messages sent by all users in the game.
     *
     * @param messages
     * @param v
     *
     * @pre The view from the fragment has to be set with a given RecyclerView on it
     * @post the recycler view will be populated based on how many items are given in the messages paramter
     *
     */
    public void setChat(ArrayList<ChatMessage> messages, View v)
    {
        this.chatMessages = messages;

        getmRecyclerViewMessages = (RecyclerView) v.findViewById(R.id.fragment_chat_list_messages);

        getmRecyclerViewMessages.setHasFixedSize(true);
        getmRecyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));

        mChatMessagesAdapter = new AdapterMessage(chatMessages, getContext());
        getmRecyclerViewMessages.setAdapter(mChatMessagesAdapter);
    }

    /**
     * Method that will update the list of messages sent by the user.
     * @param messages
     *
     * @pre The view from the fragment has to be set with a given RecyclerView on it
     * @post the recycler view will be populated based on how many items are given in the messages paramter
     *
     */
    public void updateMessages(ArrayList<ChatMessage> messages)
    {
        setChat(new ArrayList<ChatMessage>(messages), getView());
        messageWritten.getText().clear();

    }

    /**
     * Class that will called the MapPresenter sendChat() method.
     *
     * @pre A MapPresenter object has to be set in the class. A main activity of GameLobbyActivity has to exist in order for the chat to work.
     * @post the message will be populated in the recyclerview and the message will be sent over to the server to notify all users.
     *
     *
     */
    private class SendChatMessageAsync extends AsyncTask<GameLobbyActivity, String, Void> {
        @Override
        protected Void doInBackground(GameLobbyActivity... voids) {
            new MapPresenter().sendChat(messageWritten.getText().toString());
            return null;
        }
    }
}
