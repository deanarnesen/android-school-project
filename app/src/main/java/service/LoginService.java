package service;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LoginRequestModel;
import com.google.gson.Gson;

import presenter.LoginRegisterPresenter;

public class LoginService {
    LoginRequestModel request;
    /**
     * packages a login request for the server
     * */
    public String login(String username, String password){
        request = new LoginRequestModel(username, password);
        Gson gson = new Gson();
        String input = gson.toJson(request);
        CommandData data = new CommandData(CommandType.LOGIN_COMMAND, input);
        return ServerProxy.getInstance().send(data);
    }
}
