package com.bignerdranch.android.shareddata.commands.commandSupport.serializer;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
//import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer implements ISerializer {
    public Serializer(){}

    public String serialzeObject(Object o){
        /*try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bo);
            oos.writeObject(o);
            oos.flush();
            String objBytes = bo.toString();
            return objBytes;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;*/
        //return new Gson().toJson(o);
        return null;
    }

    public CommandData deserialzeCommandDataObject(String s){
        try{
            byte b[] = s.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream ois = new ObjectInputStream(bi);
            CommandData cD = (CommandData) ois.readObject();
            return cD;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
        //return new Gson().fromJson(s, CommandData.class);
    }
}
