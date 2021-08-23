package com.bignerdranch.android.shareddata.commands.commandSupport;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class CommandData implements ICommandData {
    private CommandType myCommandType;
    private String commandData;

    public CommandData() { }

    public CommandData(CommandType myCommandType, String commandData) {
        this.myCommandType = myCommandType;
        this.commandData = commandData;
    }



    public CommandType getCommandType(){
        return myCommandType;
    }
    public String getCommandData(){
        return commandData;
    }

    public void setMyCommandType(CommandType myCommandType)
    {
        this.myCommandType = myCommandType;
    }

    private byte[] converteToByteArray(Object o){
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bo);
            oos.writeObject(o);
            oos.flush();
            return bo.toByteArray();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*private Object convertFromByteArray(){
        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(commandData);
            ObjectInputStream ois = new ObjectInputStream(bi);
            return ois.readObject();
        }
        catch (Exception e){

        }
        return null;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandData that = (CommandData) o;
        return myCommandType == that.myCommandType &&
                Objects.equals(commandData, that.commandData);
    }

    @Override
    public int hashCode() {

        return Objects.hash(myCommandType, commandData);
    }

    @Override
    public String toString() {
        return "CommandData{" +
                "myCommandType=" + myCommandType +
                ", commandData=" + commandData +
                '}';
    }
}
