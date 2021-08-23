package service;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.RegisterRequestModel;
import com.google.gson.Gson;

public class RegisterService {
    /*packages a request for the server*/
    public String register(String username, String password, String confPass){
        RegisterRequestModel  request = new RegisterRequestModel(username, password, confPass);
        Gson gson = new Gson();
        String input = gson.toJson(request);
        CommandData data = new CommandData(CommandType.REGISTER_COMMAND, input);
        return ServerProxy.getInstance().send(data);
    }
}
