package ServerCentral;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Vector;

import database_plugin.IDatabase;
import database_plugin.IGameDAO;
import database_plugin.IPlayerDAO;
import database_plugin.IUserDAO;

public class StaticData
{
    private static final StaticData ourInstance = new StaticData();

    private StaticData(){
        createDatabase();
        //setPersistance();
    }
    static public StaticData getInstance()
    {
        return ourInstance;
    }

    private ActiveGamePersistanceManager activeGamePersistanceManager = ActiveGamePersistanceManager.GetInstance();
    private Vector<Player> listOfUsers = new Vector<>(); //everyone in our database
    private HashMap<String, GameListing> listOFGames = new HashMap<>(); //mapped by game name
    private TreeMap<String, Queue<CommandData>> listOfCommandsPerPlayer = new TreeMap<>(); //mapped by username
    private HashMap<String, ActiveGameModel> listActiveGames;
    private String pluginDirectory;
    private String pluginJarName;
    private URLClassLoader loader;
    private IDatabase database;
    private IUserDAO userDAO;
    private IPlayerDAO playerDAO;
    private IGameDAO gameDAO;
    private File file;
    private boolean loadingData;

    public void setPersistance(){
        activeGamePersistanceManager.setStaticData(this);
        activeGamePersistanceManager.UpdateStaticData();
    }

    public void setDataBase(String pluginDirectory_in, String pluginJarName, String pluginClassName) throws Exception {

        pluginDirectory = pluginDirectory_in;
        // Get a class loader and set it up to load the jar file
        file = new File(pluginDirectory, pluginJarName);
        URL pluginURL = file.toURI().toURL();
        loader = new URLClassLoader(new URL[]{pluginURL});
        // Load the jar file's plugin class, create and return an instance
        Class<? extends IDatabase> dataBasePlugin = (Class<IDatabase>) loader.loadClass(pluginClassName);
        database = dataBasePlugin.getDeclaredConstructor(null).newInstance();
        //database.createDatabase();//testing
        setGameDAO();
        setUserDAO();
        setPlayerDAO();
        database.createDatabase();
//        initializeCommandsPerPlayer();
    }

    public void setUserDAO()throws Exception{
        userDAO = database.createUserDAO(file);
    }

    public void setPlayerDAO() throws Exception {
        playerDAO = database.createPlayerDAO(file);
    }

    public void setGameDAO() throws Exception {
        gameDAO = database.createGameDAO(file);
        gameDAO.createGameTable();
    }

    private void createDatabase()
    {
        listActiveGames = new HashMap<>();
    }

    //Specifically I am concerned about each player holding a different pointer
    //to the same Queue. They honestly shouldn't, but I am not sure.
    private void initializeCommandsPerPlayer()
    {
        Vector<Player> people = getListOFPeople();
        for(Player p : people)
        {
            Queue<CommandData> commandsForPlayers = new LinkedList<>();
            listOfCommandsPerPlayer.put(p.getName(), commandsForPlayers);
        }
    }

    //already tested
    public Vector<Player> getListOFPeople()
    {
        List<String> userList = userDAO.getAllUsers();
        Vector<Player> result = new Vector<>();
        for(int i = 0; i < userList.size(); i += 3)
        {
            Player player = new Player();
            player.setName(userList.get(i));
            player.setPassword(userList.get(i + 1));
            String booleanValue = userList.get(i + 2);
            if(booleanValue.equals("true"))
                player.setLoggedin(true);
            else
                player.setLoggedin(false);
            result.add(player);
        }
        return result;
    }

    //already tested
    public void setListOFPeople(Vector<Player> listOFPeople)
    {
        userDAO.clearAllUser();
        for(Player p : listOFPeople)
        {
            userDAO.createNewUser(p.getName(), p.getPassword());
        }
    }

    //called by join game, create game, and login
    public HashMap<String, GameListing> getListOFGames()
    {
        List<String> AllGameNames = gameDAO.getAllGameListings();
        HashMap<String, GameListing> gamesInDatabase = new HashMap<>();
        for(String gameName : AllGameNames)
        {
            GameListing temp = getSpecificGameByName(gameName);
            gamesInDatabase.put(gameName, temp);
        }

        return gamesInDatabase;
    }

    //this was refactored so join and create game actually call this function
    public void setListOFGames(HashMap<String, GameListing> listOFGames)
    {
        gameDAO.deleteALLGameListing();
        for (Map.Entry<String, GameListing> entry : listOFGames.entrySet())
        {
            List<String> neededValues = new ArrayList<>();
            GameListing toAdd = entry.getValue();
            neededValues.add(toAdd.getGameName());
            neededValues.add(toAdd.getHost());
            neededValues.add(Integer.toString(toAdd.getMaxNumOfPlayers()));
            neededValues.add(Integer.toString(toAdd.getNumPlayers()));
            if(toAdd.isPublicGame())
                neededValues.add("true");
            else
                neededValues.add("false");
            if(toAdd.isStarted())
                neededValues.add("true");
            else
                neededValues.add("false");
            neededValues.add(toAdd.getPassword());
            gameDAO.createGameListing(neededValues);
        }
        //this.listOFGames = listOFGames;
    }

    public TreeMap<String, Queue<CommandData>> getListOfCommandsPerPlayer()
    {
        return listOfCommandsPerPlayer;
    }

    public void clearUser(String user){
        Queue<CommandData> empty = new LinkedList<>();
        listOfCommandsPerPlayer.put(user, empty);
    }

    public void addCommand(CommandData command, String user){
        if(!loadingData) {
            listOfCommandsPerPlayer.get(user).add(command);
        }
    }

    public void addAllCommand(CommandData command)
    {
        if(!loadingData) {
            for (Map.Entry<String, Queue<CommandData>> entry : listOfCommandsPerPlayer.entrySet()) {
                entry.getValue().add(command);
            }
        }
    }

    public void addCommandForSpecificPlayers(GameListing game, CommandData command)
    {
        Vector<Player> peopleInSpecificGame = game.getPlayers();
        for(Player player : peopleInSpecificGame)
        {
            String playerName = player.getName();
            listOfCommandsPerPlayer.get(playerName).add(command);
        }
    }

    //a few commands will be calling this one. (Join and StartGame are the most important of them though)
    public void addGameCommand(CommandData command, GameListing game){
        //Vector<Player> players;
        //players = listOFGames.get(game.getGameName()).getPlayers();
        if(!loadingData) {
            List<String> playerList = playerDAO.getSpecificPlayersByGameName(game.getGameName());
            Vector<Player> resultVector = new Vector<>();
            for (int i = 0; i < playerList.size(); i += 3) {
                Player player = new Player();
                player.setName(playerList.get(i));
                player.setGame(playerList.get(i + 1));
                String playerColor = playerList.get(i + 2);
                player.setColorString(playerColor);
                resultVector.add(player);
            }

            //if(playerList != null)
            //{
            for (Player player : resultVector) {
                listOfCommandsPerPlayer.get(player.getName()).add(command);
            }
            //}
        }
    }

    public void addPlayerToGame(Player player, GameListing gameListing){
        List<String> colorList = playerDAO.findAvailableColor(gameListing.getGameName());
        GameListing game = new GameListing();
        String colorForPlayer = game.giveMeFreeColor(colorList);
        playerDAO.createPlayer(player.getName(), gameListing.getGameName(), colorForPlayer);
        //listOFGames.get(gameListing.getGameName()).addPlayer(player);
    }

    public void removePlayerFromGame(String username)
    {
        playerDAO.deletePlayer(username);
        /*Vector<Player> newListOfPoeople = new Vector<>();
        for(Player p : listOFPeople)
        {
            if(p.getName().equals(username))
            {
                p.setGame(null);
                p.setColor(null);
                newListOfPoeople.add(p);
            }
            else
            {
                newListOfPoeople.add(p);
            }
        }
        listOFPeople = newListOfPoeople;*/
    }

    public void addActiveGame(ActiveGameModel activeGame){
        //gameDAO.setGameAsStarted(activeGame.getGameName());
        listActiveGames.put(activeGame.getGameName(), activeGame);
        activeGamePersistanceManager.AddGame(activeGame);
    }

    //called by join game command
    public Player getPlayerFromList(String username)
    {
        Player player = new Player();
        List<String> playerData = userDAO.getSpecificUser(username);
        player.setName(playerData.get(0));
        /*for(Player p : listOFPeople)
        {
            if(p.getName().equals(username))
            {
                returnValue = p;
            }
        }*/
        return player;
    }

    //called by commands in game (no need to add functionality)
    public HashMap<String, ActiveGameModel> getListActiveGames() {
        return listActiveGames;
    }

    //never called
    public void setListActiveGames(HashMap<String, ActiveGameModel> listActiveGames) {
        this.listActiveGames = listActiveGames;
    }

    /**
     * return null if game is not there (called by join game command)
     * @param game_in
     * @return
     */
    public GameListing getSpecificGame (GameListing game_in){
        //local call
        GameListing gameListing = getSpecificGameByName(game_in.getGameName());

        List<String> playerList = playerDAO.getSpecificPlayersByGameName(game_in.getGameName());
        Vector<Player> resultVector = new Vector<>();
        for(int i = 0; i < playerList.size(); i += 3)
        {
            Player player = new Player();
            player.setName(playerList.get(i));
            player.setGame(playerList.get(i + 1));
            String playerColor = playerList.get(i + 2);
            player.setColorString(playerColor);
            resultVector.add(player);
        }

        gameListing.setPlayers(resultVector);
        //GameListing toReturn = listOFGames.get(game_in.getGameName());
        return gameListing;
    }

    //called by function above and maybe somewhere else todo make me less fragile :/
    public GameListing getSpecificGameByName (String game_in){
        List<String> StringValuesOfGame = gameDAO.returnSpecificGameListing(game_in);
        GameListing gameListing = new GameListing();
        gameListing.setGameName(StringValuesOfGame.get(0));
        gameListing.setHost(StringValuesOfGame.get(1));
        gameListing.setMaxNumOfPlayers(Integer.parseInt(StringValuesOfGame.get(2)));
        gameListing.setNumPlayers(Integer.parseInt(StringValuesOfGame.get(3)));
        if(StringValuesOfGame.get(4).equals("true"))
            gameListing.setPublicGame(true);
        else
            gameListing.setPublicGame(false);
        if(StringValuesOfGame.get(5).equals("true"))
            gameListing.setStarted(true);
        else
            gameListing.setStarted(false);

        Vector<Player> playersInGame = new Vector<>();
        for(int i = 7; i < StringValuesOfGame.size(); i+=3){
            Player player = new Player();
            player.setName(StringValuesOfGame.get(i));
            player.setGame(StringValuesOfGame.get(i+1));
            String color = StringValuesOfGame.get(i+2);
            switch(color){
                case "BLUE":
                    player.setColor(Color.BLUE);
                    break;
                case "BLACK":
                    player.setColor(Color.BLACK);
                    break;
                case "RED":
                    player.setColor(Color.RED);
                    break;
                case "PINK":
                    player.setColor(Color.PINK);
                    break;
                case "GREEN":
                    player.setColor(Color.GREEN);
                    break;
            }
            playersInGame.add(player);
        }
        //GameListing toReturn = listOFGames.get(game_in);
        gameListing.setPlayers(playersInGame);
        return gameListing;
    }

    /**
     * Updates the number of players on the game (could not find implementation, probably not needed to refactor)
     * @param game
     * @param list
     */
    public void updateGameOnMap(GameListing game, Vector<Player> list){
        //listOfPlayersPerGame.put(game, list);
        listOFGames.get(game.getGameName()).setPlayers(list);
    }

    //called by Start Game Command
    public void setGameAsStarted(GameListing game)
    {
        gameDAO.setGameAsStarted(game.getGameName());
        //game.setStarted(true);
        //listOFGames.put(game.getGameName(), game);
    }

    public void setLoadingData(boolean loading){
        this.loadingData = loading;
    }

    public boolean isLoadingData(){return this.loadingData;}

    public void clearDatabase(){
        gameDAO.deleteALLGameListing();
        userDAO.clearAllUser();
        playerDAO.clearAllPlayers();
    }
}
