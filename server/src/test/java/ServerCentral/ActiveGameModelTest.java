package ServerCentral;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;

import org.junit.BeforeClass;
import org.junit.Test;

public class ActiveGameModelTest {
    private static GameListing game;

    @BeforeClass
    public static void init(){
        game = new GameListing();
        game.setGameName("this");
        game.setMaxNumOfPlayers(3);
        Player p1 = new Player();
        p1.setName("BoB");
        p1.setColor(Color.BLUE);
        game.addPlayer(p1);
        Player p2 = new Player();
        p2.setName("Bill");
        p2.setColor(Color.BLACK);
        game.addPlayer(p2);
    }

    @Test
    public void initActiveGame(){
        ActiveGameModel aGame = new ActiveGameModel(game);
        System.out.println();
    }
}