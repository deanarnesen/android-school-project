package ServerCentral;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.serverModels.JoinGameRequest;

import org.junit.*;

import java.util.HashMap;
import java.util.Vector;

import static org.junit.Assert.*;

import database_plugin.IGameDAO;
import database_plugin.IPlayerDAO;
import database_plugin.IUserDAO;
import serverCommands.IServerCommand;
import serverCommands.JoinGameCommand;

public class ServerTest
{

    public StaticData savedData;
    private GameListing game;
    private Player person1;
    private Vector<Player> lonePlayerVector;
    private Player person2;

    @Before
    public void setUp() throws Exception
    {
        savedData = StaticData.getInstance();
        savedData.setDataBase("/Users/elevi/Library/Mobile Documents/com~apple~CloudDocs/SCHOOL/Winter2019/CS340/TicketToRide.v2/DatabaseMongoDB/build/libs",
                "DatabaseMongoDB.jar", "MongoDatabasePL");

        lonePlayerVector = new Vector<>();
        person1 = new Player();
        person1.setName("Mike");
        person1.setPassword("Destroy");
        person1.setGame("foo");
        lonePlayerVector.add(person1);
        savedData.setListOFPeople(lonePlayerVector);

        game = new GameListing();
        game.setNumPlayers(1);
        game.setMaxNumOfPlayers(5);
        game.setPublicGame(true);
        game.setGameName("foo");
        game.setPlayers(lonePlayerVector);
        game.setHost("Mike");
        game.setStarted(false);
        HashMap<String, GameListing> games = new HashMap<>();
        games.put(game.getGameName(), game);
        savedData.setListOFGames(games);
        savedData.addPlayerToGame(person1, game);

        person2 = new Player(); //dummy person for test use
        person2.setName("Ike");
        person2.setPassword("Resognator");

    }

    @After
    public void tearDown() throws Exception
    {
        savedData.clearDatabase();
    }

    @Test
    public void setListOFPeople()
    {
        Vector<Player> people = new Vector<>();
        people.add(person2);
        savedData.setListOFPeople(people);
    }

    @Test
    public void getListOFPeople()
    {
        Vector<Player> resultSet = savedData.getListOFPeople();
        assertEquals("Mike", resultSet.get(0).getName());
    }

    @Test
    public void addPlayerToGame(){
        savedData.addPlayerToGame(person2, game);
    }

    @Test
    public void removePlayerFromGame(){
        savedData.addPlayerToGame(person2, game);
        savedData.removePlayerFromGame(person2.getName());
    }

    @Test
    public void addActiveGame(){
        ActiveGameModel activeGame = new ActiveGameModel(game);
        savedData.addActiveGame(activeGame);
    }

    @Test
    public void getPlayerFromList(){
        Player test = savedData.getPlayerFromList("Mike");
        assertEquals("foo", test.getGame());
    }

    @Test
    public void getGame(){
        GameListing test = savedData.getSpecificGame(game);
        assertEquals("foo", test.getGameName());
    }

    //doesn't fill out players in game
    @Test
    public void getGameByName(){
        GameListing test = savedData.getSpecificGameByName(game.getGameName());
        assertEquals("foo", test.getGameName());
        assertEquals("Mike", test.getPlayers().firstElement().getName());
    }

    @Test
    public void setGameStarted(){
        savedData.setGameAsStarted(game);
        GameListing test = savedData.getSpecificGameByName(game.getGameName());
        assertTrue(test.isStarted());
    }

    @Test
    public void getListOfGames(){
        HashMap<String, GameListing> games = new HashMap<>();
        games.put(game.getGameName(), game);
        savedData.setListOFGames(games);
        games = savedData.getListOFGames();
        assertEquals(1, games.size());
        assertEquals("Mike", games.get("foo").getHost());
    }

    @Test
    public void setListOfGames(){
        HashMap<String, GameListing> games = new HashMap<>();
        games.put(game.getGameName(), game);
        savedData.setListOFGames(games);
    }





}