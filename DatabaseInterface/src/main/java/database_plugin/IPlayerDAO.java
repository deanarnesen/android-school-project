package database_plugin;


import java.util.List;

public interface IPlayerDAO {

    void createPlayerTable();

    void clearAllPlayers();

    List<String> findAvailableColor(String gameName);

    void deletePlayer(String username);

    void createPlayer(String pName, String pGame, String pColor);

    List<String> getSpecificPlayerByUsername(String username);

    List<String> getSpecificPlayersByGameName(String gameName);
}
