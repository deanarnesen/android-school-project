package Tests;


import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;

import org.junit.BeforeClass;
import org.junit.Test;

import Tests.mockClasses.TestObserver;

public class TestDistributeMessage
{
    private static TestObserver myTestObserver;
    private static GameLobbyModel myLoby;

    @BeforeClass
    public static void before(){
        myTestObserver = new TestObserver();
        myLoby = GameLobbyModel.getInstance();
        myLoby.addObserver(myTestObserver);
    }

    @Test
    public void testAddMessage(){
        //updated to match chatmessage constructor
        ChatMessage validMessage1 = new ChatMessage("Bobo", "hi guys", "Bobo");
        ChatMessage validMessage2 = new ChatMessage("Billo","get lost", "Billo");
        ChatMessage invalidUser = new ChatMessage(null, "message", null);
        ChatMessage invalidMessage = new ChatMessage("sender", null, "sender");
        /*myLoby.addMessage(validMessage1);
        myLoby.addMessage(validMessage2);
        myLoby.addMessage(invalidUser);
        myLoby.addMessage(invalidMessage);*/
    }

    @Test
    public void testAddPlayer(){

    }
}