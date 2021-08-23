package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String password;
    private Color color;
    private String game;
    private Boolean isLoggedin = false;


//
//    public Player (String name, String password, Color color, String game){
//        this.name = name;
//        this.password = password;
//        this.color = color;
//        this.game = game;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColorString(String color)
    {
        if(color.equals("RED"))
            this.color = Color.RED;
        else if (color.equals("BLUE"))
            this.color = Color.BLUE;
        else if (color.equals("GREEN"))
            this.color = Color.GREEN;
        else if (color.equals("YELLOW"))
            this.color = Color.YELLOW;
        else if (color.equals("BLACK"))
            this.color = Color.BLACK;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Boolean getLoggedin()
    {
        return isLoggedin;
    }

    public void setLoggedin(Boolean loggedin)
    {
        isLoggedin = loggedin;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", game='" + game + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o){

        if (o == this){
            return true;
        }

        if(!(o instanceof Player)){
            return false;
        }

        Player p = (Player) o;

        if(this.name.equals(p.name)) {
            return true;
        }

        return false;


    }





}
