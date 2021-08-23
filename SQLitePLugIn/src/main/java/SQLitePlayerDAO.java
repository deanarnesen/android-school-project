import com.example.sqliteplugin.ConnectToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database_plugin.IPlayerDAO;

public class SQLitePlayerDAO implements IPlayerDAO {

    public SQLitePlayerDAO(){
        System.out.println("I AM INSIDE SQLITE PLAYER DAO CONSTRUCTOR");
    }


    @Override
    public void createPlayerTable() {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();

        String sql = "CREATE TABLE IF NOT EXISTS Players (\n"
                + "Username text NOT NULL, \n"
                + "GameName text NOT NULL, \n"
                + "Color text NOT NULL\n" //also arguable taken care of, but will include since color is determined before the game starts
                //+ "NumTrainCars int, \n"
                //+ "Score int, \n" TONY SHOULD ARGUABLE HAVE THESE TAKEN CARE OF
                //+ "TurnState boolean\n"
                + ");";

        try( Statement stmt = db.getConn().createStatement()){
            stmt.execute(sql);
            db.closeConnection(true);
        }catch (SQLException e){
            System.out.println("Coming from SQLiteGameDAO: " + e.getMessage() );
            db.closeConnection(false);
        }
    }

    //this helps us know what color a new player can have
    public List<String> findAvailableColor(String gameName)
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try
        {
            Connection connection = db.getConn();
            String sql = "select Color from Players where GameName = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, gameName);
            rs = stmt.executeQuery();
            if(rs.isBeforeFirst())
            {
                while(rs.next())
                {
                    String color = rs.getString(1);
                    result.add(color);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        db.closeConnection(true);
        return result;
    }

    //if the user decides to join a game
    public void createPlayer(String pName, String pGame, String pColor)
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "insert into Players (Username, GameName, Color) values(?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, pName);
            stmt.setString(2, pGame);
            stmt.setString(3, pColor);
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        db.closeConnection(true);
    }

    //if the user moves back to the game listing view screen
    public void deletePlayer(String username)
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "delete from Players where Username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        db.closeConnection(true);
    }

    //returns the values of one player object
    public List<String> getSpecificPlayerByUsername(String username)
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try
        {
            Connection connection = db.getConn();
            String sql = "select * from Players where Username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.isBeforeFirst())
            {
                while(rs.next())
                {
                    String name = rs.getString(1);
                    String game = rs.getString(2);
                    String color = rs.getString(3);

                    result.add(name);
                    result.add(game);
                    result.add(color);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        db.closeConnection(true);
        return result;
    }

    public void clearAllPlayers()
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "delete from Players";
            stmt = connection.prepareStatement(sql);
            stmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        db.closeConnection(true);
    }

    //this will return the data needed to create many player objects
    public List<String> getSpecificPlayersByGameName(String gameName)
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try
        {
            Connection connection = db.getConn();
            String sql = "select * from Players where GameName = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, gameName);
            rs = stmt.executeQuery();
            if(rs.isBeforeFirst())
            {
                while(rs.next())
                {
                    String name = rs.getString(1);
                    String game = rs.getString(2);
                    String color = rs.getString(3);

                    result.add(name);
                    result.add(game);
                    result.add(color);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        db.closeConnection(true);
        return result;
    }

    //we can modify this with conditions if we want
    /*public List<Player> getAllPlayers()
    {
        ConnectToDatabase connectToDatabase = ConnectToDatabase.getInstance();
        connectToDatabase.connect();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Player> result = new ArrayList<>();

        try
        {
            Connection connection = connectToDatabase.conn;
            String sql = "select * from Players";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.isBeforeFirst())
            {
                while(rs.next())
                {
                    Player newPlayer = new Player();
                    String name = rs.getString(1);
                    String game = rs.getString(2);
                    String color = rs.getString(3);

                    newPlayer.setName(name);
                    newPlayer.setGame(game);
                    newPlayer.setColorString(color);

                    result.add(newPlayer);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        connectToDatabase.disconnect();
        return result;
    }*/
}
