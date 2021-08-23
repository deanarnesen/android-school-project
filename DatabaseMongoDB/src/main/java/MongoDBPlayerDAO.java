import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;

import database_plugin.IPlayerDAO;

public class MongoDBPlayerDAO implements IPlayerDAO {
    private String path = System.getProperty("user.dir") + "\\Players\\table.txt";

    public MongoDBPlayerDAO(){
        System.out.println("I am in MONGODB PLayerDAO");
    }


    @Override
    public void createPlayerTable() {

    }

    public void clearAllPlayers() {
        Vector<TempPlayer> players = new Vector<>();
        WriteToFile(players);
    }

    @Override
    public List<String> findAvailableColor(String gameName) {
        return null;
    }

    @Override
    public void deletePlayer(String playerName) {
        Vector<TempPlayer> players = ReadPlayers();
        int index = -1;
        for(int i = 0; i < players.size(); i++){
            if(players.elementAt(i).getName().equals(playerName)){
                index = i;
            }
        }
        if(index != -1){
            players.removeElementAt(index);
        }
        WriteToFile(players);
    }

    @Override
    public void createPlayer(String playerName, String playerGame, String playerColor) {
        TempPlayer player = new TempPlayer(playerName, playerGame, playerColor);
        Vector<TempPlayer> players = ReadPlayers();
        players.add(player);
        WriteToFile(players);
    }

    public List<String> getSpecificPlayerByUsername(String username) {
        List<String> playerInfo = null;
        Vector<TempPlayer> players = ReadPlayers();
        int index = -1;
        for(int i = 0; i < players.size(); i++){
            if(players.elementAt(i).getName().equals(username)){
                index = i;
            }
        }
        if(index != -1){
            playerInfo = new Vector<>();
            ((Vector<String>) playerInfo).addElement(players.elementAt(index).getName());
            ((Vector<String>) playerInfo).addElement(players.elementAt(index).getGame());
            ((Vector<String>) playerInfo).addElement(players.elementAt(index).getColor());

        }
        return playerInfo;
    }

    public List<String> getSpecificPlayersByGameName(String gameName) {
        List<String> playerInfo = null;
        Vector<TempPlayer> players = ReadPlayers();
        Vector<Integer> indexes = new Vector<>();
        for(int i = 0; i < players.size(); i++){
            if(players.elementAt(i).getGame().equals(gameName)){
                indexes.add(i);
            }
        }
        for(int index : indexes) {
            playerInfo = new Vector<>();
            ((Vector<String>) playerInfo).addElement(players.elementAt(index).getName());
            ((Vector<String>) playerInfo).addElement(players.elementAt(index).getGame());
            ((Vector<String>) playerInfo).addElement(players.elementAt(index).getColor());
        }
        return playerInfo;
    }


    private void WriteToFile(Vector<TempPlayer> player){
        try {
            File newFile = new File(path);
            newFile.createNewFile();
            FileOutputStream fileOut =
                    new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(player);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private Vector<TempPlayer> ReadPlayers(){
        Vector<TempPlayer> tempPLayers = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempPLayers = (Vector<TempPlayer>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return tempPLayers;
        } catch (ClassNotFoundException c) {
            System.out.println("ActiveGameModel class not found");
            c.printStackTrace();
            return tempPLayers;
        }
        return tempPLayers;
    }
}
