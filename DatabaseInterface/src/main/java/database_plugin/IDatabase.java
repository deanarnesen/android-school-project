package database_plugin;

import java.io.File;

public interface IDatabase {

    /**
     * Initialize/Load the database
     */
    void createDatabase();
    IUserDAO createUserDAO(File file)throws Exception;
    IPlayerDAO createPlayerDAO(File file)throws Exception;
    IGameDAO createGameDAO(File file)throws Exception;

}
