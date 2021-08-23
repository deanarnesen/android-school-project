package service;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.DrawTrainRequest;
import com.google.gson.Gson;

public class DrawTrainCardService {
    private DrawTrainRequest actionInfo;

    public DrawTrainCardService(String username, int cardPosition, String gameName){
        actionInfo = new DrawTrainRequest(cardPosition, gameName, username);
    }

    public void execute(){
        Gson gson = new Gson();
        String input = gson.toJson(actionInfo);
        CommandData command = new CommandData(CommandType.DRAW_TRAIN_CARD, input);
        ServerProxy.getInstance().send(command);
    }
}
