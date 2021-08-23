//import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.example.sqliteplugin.ConnectToDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database_plugin.IUserDAO;

public class SQLiteUserDAO implements IUserDAO {


    public SQLiteUserDAO (){

        System.out.println("I AM INSIDE SQLITE USER DAO CONSTRUCTOR");
    }

    @Override
    public void createUserTable() {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();

        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + "Username text NOT NULL, \n"
                + "Password text NOT NULL, \n"
                + "IsLoggedIn integer\n"
                + ");";

        try( Statement stmt = db.getConn().createStatement()){
            stmt.execute(sql);
            db.closeConnection(true);
        }catch (SQLException e){
            System.out.println("Coming from SQLiteGameDAO: " + e.getMessage() );
            db.closeConnection(false);
        }
    }

    @Override
    public void clearUser(String username) {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "delete from Users where Username = ?";
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

    public void clearAllUser()
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "delete from Users";
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

    @Override
    public void addCommand(String s, String s1) {

    }

    @Override
    public void addAllCommand(String s) {

    }

    @Override
    public void addGameCommand(String s, String s1) {

    }

    @Override
    public void addCommandForSpecificPlayers(String s, String s1) {

    }

    //called for newly registered people this will set the user's login status to true as well
    public void createNewUser(String pName, String pPassword)
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = db.getConn();
            String sql = "insert into Users (Username, Password, IsLoggedIn) values(?,?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, pName);
            stmt.setString(2, pPassword);
            stmt.setInt(3, 1);
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

    /*public void LogoutUser(Player p)
    {
        ConnectToDatabase connectToDatabase = ConnectToDatabase.getInstance();
        connectToDatabase.connect();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = connectToDatabase.conn;
            String sql = "update Users set isLoggedIn = 0 where username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getName());
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

        connectToDatabase.disconnect();
    }

    public void LogInUser(Player p)
    {
        ConnectToDatabase connectToDatabase = ConnectToDatabase.getInstance();
        connectToDatabase.connect();
        PreparedStatement stmt = null;

        try
        {
            Connection connection = connectToDatabase.conn;
            String sql = "update Users set isLoggedIn = 1 where username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getName());
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

        connectToDatabase.disconnect();
    }*/

    public List<String> getSpecificUser(String username)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> playerData = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();

        try
        {

            Connection connection = db.getConn();
            String sql = "select * from Users where Username = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.isBeforeFirst())
            {
                while(rs.next())
                {
                    String name = rs.getString(1);
                    String password = rs.getString(2);
                    int isLoggedInNUMBER = rs.getInt(3);
                    boolean isLoggedIn;
                    if(isLoggedInNUMBER == 1)
                        isLoggedIn = true;
                    else
                        isLoggedIn = false;

                    playerData.add(name);
                    playerData.add(password);
                    if(isLoggedIn){
                        playerData.add("true");
                    }
                    if(isLoggedIn){
                        playerData.add("false");
                    }
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
        return playerData;
    }

    //returns the values needed for all users in database
    @Override
    public List<String> getAllUsers()
    {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.openConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> result = new ArrayList<>();

        try
        {
            Connection connection = db.getConn();
            String sql = "select * from Users";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            if(rs.isBeforeFirst())
            {
                while(rs.next())
                {

                    String name = rs.getString(1);
                    String password = rs.getString(2);
                    int isLoggedInNUMBER = rs.getInt(3);
                    String isLoggedIn;
                    if(isLoggedInNUMBER == 1)
                        isLoggedIn = "true";
                    else
                        isLoggedIn = "false";

                    result.add(name);
                    result.add(password);
                    result.add(isLoggedIn);
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
