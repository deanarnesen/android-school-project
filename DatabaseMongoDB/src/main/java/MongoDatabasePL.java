


import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;



import database_plugin.IDatabase;
import database_plugin.IGameDAO;
import database_plugin.IPlayerDAO;
import database_plugin.IUserDAO;

public class MongoDatabasePL implements IDatabase {


    private IUserDAO theuserDAO;
    private IPlayerDAO theplayerDAO;
    private IGameDAO thegameDAO;
    //MongoClient mongoClient = new MongoClient("localhost", 27017);

    public MongoDatabasePL(){

    }


    public void createDatabase() {
        System.out.println("I AM INSIDE MONGODB");

        thegameDAO.createGameTable();
        theuserDAO.createUserTable();
        theplayerDAO.createPlayerTable();

    }
    public IUserDAO createUserDAO(File file)throws Exception{
        URL pluginURL = file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

        Class<? extends IUserDAO> dataBasePlugin = (Class<IUserDAO>) loader.loadClass("MongoDBUserDAO");
        theuserDAO = dataBasePlugin.getDeclaredConstructor(null).newInstance();
        return theuserDAO;

    }
    public IPlayerDAO createPlayerDAO(File file)throws Exception{
        URL pluginURL = file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

        Class<? extends IPlayerDAO> dataBasePlugin = (Class<IPlayerDAO>) loader.loadClass("MongoDBPlayerDAO");
        theplayerDAO = dataBasePlugin.getDeclaredConstructor(null).newInstance();
        return theplayerDAO;
    }
    public IGameDAO createGameDAO(File file)throws Exception{
        URL pluginURL = file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

        Class<? extends IGameDAO> dataBasePlugin = (Class<IGameDAO>) loader.loadClass("MongoDBGameDAO");
        thegameDAO = dataBasePlugin.getDeclaredConstructor(null).newInstance();
        return thegameDAO;
    }

}
