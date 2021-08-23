package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class GameListing implements Serializable {
    private int numPlayers;
    private int maxNumOfPlayers;
    private boolean publicGame;
    private boolean started;
    private String password;
    private String gameName;
    private String host;
    private HashMap<Color, Boolean> gameColors = new HashMap<>();
    private Vector<Player> players = new Vector<>();

    public GameListing(){
        publicGame = true;
        gameColors.put(Color.RED, false);
        gameColors.put(Color.BLUE, false);
        gameColors.put(Color.GREEN, false);
        gameColors.put(Color.YELLOW, false);
        gameColors.put(Color.BLACK, false);
    }


    public boolean isPublicGame() {
        return publicGame;
    }
    public void setPublicGame(boolean publicGame) {
        this.publicGame = publicGame;
    }

    public void setNumPlayers(int numPlayers){
        this.numPlayers = numPlayers;
    }
    public int getNumPlayers() { return players.size(); }

    public void setMaxNumOfPlayers(int maxNumOfPlayers)
    {
        this.maxNumOfPlayers = maxNumOfPlayers;
    }
    public int getMaxNumOfPlayers() {return maxNumOfPlayers; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }


    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public String getGameName() {
        return gameName;
    }


    public void setHost(String host)
    {
        this.host = host;
    }
    public String getHost() { return host; }


    public void setStarted(boolean started)
    {
        this.started = started;
    }
    public boolean isStarted()
    {
        return started;
    }

    public void setPlayers(Vector<Player> players)
    {
        this.players = players;
    }

    public Vector<Player> getPlayers()
    {
        return players;
    }

    public void addPlayer(Player p){
        if(players.size() != maxNumOfPlayers){
            defaultSetColor(p);
            players.add(p);
        }else{
            System.out.println("EXCEEDIING the number of players allowed per game. Error on GameListing.addPlayer()\n\n");
        }
    }
    public void removePlayer(Player p){
        if(players.size() > 0) {
            int index = players.indexOf(p);
            if(index <= -1) {
                System.out.println("Unable to remove player, player does not exist. Error on GameListing.removePlayer\n\n");
            }else{
                gameColors.put(p.getColor(), false);
                players.remove(index);
            }
        }
    }

    public void removePlayerByName(String name){
        Player toFind = new Player();
        toFind = getPlayerByName(name);
        removePlayer(toFind);
    }


    public Player getPlayerByName (String userName){

        for(Player player : players){
            if(player.getName().equals(userName)) {
                return player;
            }
        }
        return null;
    }



    @Override
    public String toString() {
        return "GameListing{" +
                "name='" + gameName + '\'' +
                ", maxPlayers=" + maxNumOfPlayers +
                ", numPlayers=" + numPlayers +
                ", started=" + started +
                '}';
    }


    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof GameListing)) {
            return false;
        }

        GameListing g = (GameListing) o;

        return this.gameName.equals(g.gameName);
    }

    @Override
    public int hashCode(){
        int prime = 31;
        return prime + (this.gameName == null ? 0 : this.gameName.hashCode());



    }

    private void defaultSetColor(Player playerNeedingColor)//, GameListing whichGameHeIsIn)
    {
        //HashMap<GameListing, HashMap<Color, Boolean>> gameForColors = savedData.getColorsChosen();
        Color color = Color.BLACK;
        HashMap<Color, Boolean> newListOfColors = new HashMap<>();
        boolean foundColor = false;

        for (/*Map.Entry<GameListing, */Map.Entry<Color, Boolean> entry : gameColors.entrySet())
        {
            /*if(entry.getKey().getGameName().equals(whichGameHeIsIn.getGameName()))
            {
                HashMap<Color, Boolean> colorsChosen = entry.getValue();
                for(Map.Entry<Color, Boolean> entry2 : colorsChosen.entrySet())
                {*/
                    if(!entry.getValue() && !foundColor)
                    {
                        color = entry.getKey();
                        Boolean change = true;
                        foundColor = true;
                        newListOfColors.put(entry.getKey(), change);
                    }
                    else
                    {
                        newListOfColors.put(entry.getKey(), entry.getValue());
                    }
                /*}
                break;
            }*/
        }

        //gameForColors.put(whichGameHeIsIn, newListOfColors);
        gameColors = newListOfColors;
        playerNeedingColor.setColor(color);
        //return playerNeedingColor;
    }

    public String giveMeFreeColor(List<String> ColorList)
    {
//        for(String color : ColorList)
//        {
//            Color style = Color.PINK;
//            if(color.equals("RED"))
//                style = Color.RED;
//            else if (color.equals("BLUE"))
//                style = Color.BLUE;
//            else if (color.equals("GREEN"))
//                style = Color.GREEN;
//            else if (color.equals("YELLOW"))
//                style = Color.YELLOW;
//            else if (color.equals("BLACK"))
//                style = Color.BLACK;
//
//            if(style == Color.PINK)
//                return color;
//        }
//        return "BLACK";
        return "BLACK";
    }

    public void setGameColors(HashMap<Color, Boolean> gameColors) {
        this.gameColors = gameColors;
    }
}
