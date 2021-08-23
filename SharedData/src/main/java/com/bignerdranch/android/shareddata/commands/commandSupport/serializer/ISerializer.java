package com.bignerdranch.android.shareddata.commands.commandSupport.serializer;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;

import java.lang.reflect.Type;


public interface ISerializer {
    String serialzeObject(Object obj);
    CommandData deserialzeCommandDataObject(String s);
}
