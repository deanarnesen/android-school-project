package com.example.sqliteplugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {

    //I am not a big fan of not having it private but that way we don't have to pass
    // the whole url for the database over and over again.
    public static Connection conn = null;

    public void setDirectoryForqlitedb(String directoryForqlitedb) {
        this.directoryForqlitedb = directoryForqlitedb;
    }

    private String directoryForqlitedb ;

    private static ConnectToDatabase instance = new ConnectToDatabase();

    private ConnectToDatabase (){}

    public static ConnectToDatabase getInstance(){return instance;}


    public static void connect() {
        try {
            String url = "jdbc:sqlite:tickettoridesqlite.db";

            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
 //               System.out.println(ex.getMessage());
 //           }
        }
    }

    public static void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Comming from the disconnect on class ConnectToDatabase: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
