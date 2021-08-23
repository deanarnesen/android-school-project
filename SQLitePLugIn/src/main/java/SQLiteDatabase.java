import com.example.sqliteplugin.ConnectToDatabase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import database_plugin.IDatabase;
import database_plugin.IGameDAO;
import database_plugin.IPlayerDAO;
import database_plugin.IUserDAO;

public class SQLiteDatabase implements IDatabase{

    private IUserDAO theuserDAO;
    private IPlayerDAO theplayerDAO;
    private IGameDAO thegameDAO;

    private static SQLiteDatabase instance = new SQLiteDatabase();

    public static SQLiteDatabase getInstance(){return instance;}

    static{
        try{
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch (ClassNotFoundException e){
            System.out.println("class not found");
        }
    }

    private Connection conn;

    public IUserDAO createUserDAO(File file)throws Exception{
        URL pluginURL = file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

        Class<? extends IUserDAO> dataBasePlugin = (Class<IUserDAO>) loader.loadClass("SQLiteUserDAO");
        theuserDAO = dataBasePlugin.getDeclaredConstructor(null).newInstance();
        return theuserDAO;
    }

    public IPlayerDAO createPlayerDAO(File file)throws Exception{
        URL pluginURL = file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

        Class<? extends IPlayerDAO> dataBasePlugin = (Class<IPlayerDAO>) loader.loadClass("SQLitePlayerDAO");
        theplayerDAO = dataBasePlugin.getDeclaredConstructor(null).newInstance();
        return theplayerDAO;

    }

    public IGameDAO createGameDAO(File file)throws Exception{
        URL pluginURL = file.toURI().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{pluginURL});

        Class<? extends IGameDAO> dataBasePlugin = (Class<IGameDAO>) loader.loadClass("SQLiteGameDAO");
        thegameDAO = dataBasePlugin.getDeclaredConstructor(null).newInstance();
        return thegameDAO;
    }

    @Override
    public void createDatabase() {
        theuserDAO.createUserTable();
        thegameDAO.createGameTable();
        theplayerDAO.createPlayerTable();
    }

    public void openConnection(){
        try {
            final String CONNECTION_URL = "jdbc:sqlite:tickettoridesqlite.db";
            conn = DriverManager.getConnection(CONNECTION_URL);
            conn.setAutoCommit(false);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(boolean commit){
        try{
            if(commit){
                conn.commit();
            }
            else{
                conn.rollback();
            }
            conn.close();
            conn = null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String readSchemaFromFile(){
        String text;
        try {
            text = new String(Files.readAllBytes(Paths.get("schema/TableSchemas.txt")));
            return text;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConn()
    {
        return conn;
    }
}
