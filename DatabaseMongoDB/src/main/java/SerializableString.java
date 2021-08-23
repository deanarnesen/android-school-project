import Tables.ListGamesMongo;
import Tables.ListUserMongo;

import com.google.gson.Gson;

public class SerializableString {


    private Gson gson = new Gson();

    public SerializableString (){}

    public String getSerializableListGamesMongo(ListGamesMongo toSerizalize){
        String code = gson.toJson(toSerizalize);
        return code;
    }

    public ListGamesMongo getDeserializableListGamesMongo (String toDecode){
        ListGamesMongo toreturn = gson.fromJson(toDecode, ListGamesMongo.class);
        return toreturn;
    }

    public String getSerializableUserMongo(ListUserMongo user){
        String code = gson.toJson(user);
        return code;
    }

    public ListUserMongo getUserCode(String toDecode){
        ListUserMongo user = gson.fromJson(toDecode, ListUserMongo.class);
        return user;
    }


}
