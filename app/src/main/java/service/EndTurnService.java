package service;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.PassTurnRequest;
import com.google.gson.Gson;

public class EndTurnService {
    private String gameName;
    public EndTurnService(String gameName){
        this.gameName = gameName;
    }
    public void execute(){
        Gson gson = new Gson();
        PassTurnRequest request = new PassTurnRequest(gameName);
        String input = gson.toJson(request);

        CommandData data = new CommandData(CommandType.PASS_TURN, input);
        ServerProxy.getInstance().send(data);
    }
}
