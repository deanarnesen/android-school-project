//import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.example.sqliteplugin.ConnectToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database_plugin.IGameDAO;

public class SQLiteGameDAO implements IGameDAO {

    public SQLiteGameDAO(){
        System.out.println("I AM INSIDE GAMEDAO FROM SQLITE");
    }

    public boolean createGameTable(){
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();

        String sql = "CREATE TABLE IF NOT EXISTS Games (\n"
                + "GameName text NOT NULL, \n"
                + "Host text NOT NULL, \n"
                + "MaxNumberPlayers integers NOT NULL, \n"
                + "NumOfPlayers integer, \n"
                + "PublicGame integer, \n"
                + "IsStartedGame integer, \n"
                + "GamePassword text\n"
                + ");";

        try( Statement stmt = db.getConn().createStatement()){
            stmt.execute(sql);
            db.closeConnection(true);
            return true;
        }catch (SQLException e){
            System.out.println("Coming from SQLiteGameDAO: " + e.getMessage() );
            db.closeConnection(false);
            return false;
        }
    }

    //set game as started
    @Override
    public void setGameAsStarted(String gameName) {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "update Games set IsStartedGame = 1 where GameName = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, gameName);
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

    //gives enough information to create a game listing entry
    public void createGameListing(List<String> game)
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "insert into Games " +
                "(GameName, Host, MaxNumberPlayers, NumOfPlayers, PublicGame, IsStartedGame, GamePassword) values(?,?,?,?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, game.get(0));
            stmt.setString(2, game.get(1));
            stmt.setInt(3, Integer.parseInt(game.get(2)));
            stmt.setInt(4, Integer.parseInt(game.get(3)));
            if(game.get(4).equals("true"))
                stmt.setInt(5, 1);
            else
                stmt.setInt(5, 0);
            if(game.get(5).equals("true"))
                stmt.setInt(6, 1);
            else
                stmt.setInt(6, 0);
            stmt.setString(7, game.get(6));
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

    public void deleteALLGameListing()
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "delete from Games";
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

    //returns the values of one GameList object
    public List<String> returnSpecificGameListing(String gameName)
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try
        {
            Connection connection = db.getConn();
            String sql = "select * from Games where GameName = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, gameName);
            rs = stmt.executeQuery();
            if(rs.isBeforeFirst())
            {
                while(rs.next())
                {
                    String name = rs.getString(1);
                    String host = rs.getString(2);
                    Integer maxPlayers = rs.getInt(3);
                    String maxPlayersSTRING = maxPlayers.toString();
                    Integer numOfPlayers = rs.getInt(4);
                    String maxOfPlayersSTRING = numOfPlayers.toString();
                    int isPlublicNUMBER = rs.getInt(5);
                    String isPulbic;
                    if(isPlublicNUMBER == 1)
                        isPulbic = "true";
                    else
                        isPulbic = "false";
                    int isStartedNUMBER = rs.getInt(6);
                    String isStarted;
                    if(isStartedNUMBER == 1)
                        isStarted = "true";
                    else
                        isStarted = "false";
                    String password = rs.getString(7);

                    result.add(name);
                    result.add(host);
                    result.add(maxPlayersSTRING);
                    result.add(maxOfPlayersSTRING);
                    result.add(isPulbic);
                    result.add(isStarted);
                    result.add(password);
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
        SQLitePlayerDAO helper = new SQLitePlayerDAO();
        result.addAll(helper.getSpecificPlayersByGameName(gameName));
        return result;
    }

    //returns the game name of all gameListings
    public List<String> getAllGameListings()
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try
        {
            Connection connection = db.getConn();
            String sql = "select * from Games";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.isBeforeFirst())
            {
                while(rs.next())
                {
                    String name = rs.getString(1);
                    result.add(name);
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
}
