package Tables;

public class ListGamesMongo {

    public ListGamesMongo(){}


    private String gameName;
    private String host;
    private int maxNumberPlayers;
    private int numberOfPlayers;
    private boolean publicGames;
    private boolean isStaretedGame;
    private String gamePassword;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getMaxNumberPlayers() {
        return maxNumberPlayers;
    }

    public void setMaxNumberPlayers(int maxNumberPlayers) {
        this.maxNumberPlayers = maxNumberPlayers;
    }

    public boolean getPublicGames() {
        return publicGames;
    }

    public void setPublicGames(boolean publicGames) {
        this.publicGames = publicGames;
    }

    public boolean isStaretedGame() {
        return isStaretedGame;
    }

    public void setStaretedGame(boolean staretedGame) {
        isStaretedGame = staretedGame;
    }

    public String getGamePassword() {
        return gamePassword;
    }

    public void setGamePassword(String gamePassword) {
        this.gamePassword = gamePassword;
    }
}
