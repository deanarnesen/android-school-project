package database_plugin;

import java.util.List;

public interface IGameDAO {


    boolean createGameTable();


    /**
     * todo split into single attribute getters
     * @return A hashmap from gamename to JSON GameListing objects
     */
    //HashMap<String, String> getListOfGames();

    /**
     * todo split into multiple getters
     * Get the list of active games
     * @return A hashmap from gamename to ActiveGameModel JSON object
     */
    //HashMap<String, String> getListActiveGames();
    /**
     * todo split into multiple getters
     * Get game by gamename
     * @param gamename name of game to return
     * @return GameListing JSON object if found, null otherwise
     */
    //String getGame(String gamename);

    /**
     * Set a game as started
     * @param game JSON of started game
     */
    void setGameAsStarted(String game);

    List<String> returnSpecificGameListing(String gameName);

    List<String> getAllGameListings();

    void deleteALLGameListing();

    void createGameListing(List<String> game);
}
