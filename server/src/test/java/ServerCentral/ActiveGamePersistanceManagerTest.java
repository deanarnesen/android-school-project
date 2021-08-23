package ServerCentral;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.DrawTrainRequest;
import com.google.gson.Gson;

import org.junit.BeforeClass;
import org.junit.Test;

import serverCommands.DrawTrainCarCard;

import static org.junit.Assert.*;

public class ActiveGamePersistanceManagerTest {
    private static ActiveGamePersistanceManager gamePersistanceManager;
    public static ActiveGameModel game;


    @BeforeClass
    public static void setup(){
        gamePersistanceManager = ActiveGamePersistanceManager.GetInstance();
        game = new ActiveGameModel();
        game.setGameName("TheGame");
        ServerGamePlayer player = new ServerGamePlayer();
        player.setUsername("bob");
    }

    @Test
    public void testSavingGame(){
        Gson gson = new Gson();
        gamePersistanceManager.AddGame(game);
        DrawTrainRequest req = new DrawTrainRequest(1, game.getGameName(), "bob");
        CommandData commandData = new CommandData(CommandType.DRAW_TRAIN_CARD, gson.toJson(req));
        for(int i = 0; i < 3; i++){
            gamePersistanceManager.AddCommand(commandData, game.getGameName());
        }
        gamePersistanceManager.UpdateStaticData();
        //gamePersistanceManager.UpdateStaticData();
    }


}