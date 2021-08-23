package database_plugin;

//todo: set functions you will need from the DAO here.

import java.util.List;

public interface IUserDAO {


    void createUserTable();

    /**
     * todo split into single attribute getters
     * @return A vector of JSON Player objects
     */
    //Vector<String> getListOfPeople();
    /**
     * todo split into single attribute setters
     * @param listOfPeople A vector of JSON Player objects
     */
    //void setListOfPeople(Vector<String> listOfPeople);
    /**
     * todo split into single command queue getter?
     * @return A treemap from username to JSON Queue of CommandData objects
     */
    //TreeMap<String, String> getListOfCommandsPerPlayer();

    /**
     * @param username of person to clear command list
     */
    void clearUser(String username);


    /**
     * Adds a command to all players on the server
     * @param command A JSON CommandData obj
     */
    void addAllCommand(String command);
    /**
     * Adds a command to all players in a game
     * @param command JSON CommandData object
     * @param game JSON GameListing object
     */
    void addGameCommand(String command, String game);

    /**
     * Duplicate of above, can be removed if static data method is refactored
     * @param game JSON GameListing object
     * @param command JSON CommandData object
     */
    void addCommandForSpecificPlayers(String game, String command);

    List<String> getAllUsers();

    void createNewUser(String pName, String pPassword);

    void clearAllUser();

    List<String> getSpecificUser(String username);
}
