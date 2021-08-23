package Tests;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;

import org.junit.Test;

import java.util.Vector;

import cs340.tickettoride.MockMap;

public class MockMapTest {
    @Test
    public void updateAll(){
        GameLobbyModel gameLobbyModel = GameLobbyModel.getInstance();
        ClientModelRoot clientModelRoot = ClientModelRoot.getInstance();
        clientModelRoot.setClientUserName("HoopBoy");
        Vector<Player> players = new Vector<>();
        Player one = new Player();
        one.setName("HoopBoy");
        one.setPassword("j0rd@n");
        Player two = new Player();
        two.setName("Balluer");
        two.setPassword("ball_in");
        players.add(one);
        players.add(two);
        gameLobbyModel.addInitialPlayers(players);
        MockMap mockMap = new MockMap();
        mockMap.runUpdateTest();
    }
}
