package service;

import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LastRoundRequest;
import com.google.gson.Gson;

//todo if needed the dto that holds all the necessary end game information can go here too
public class EndGameScreenService
{
    private LastRoundRequest req;

    public EndGameScreenService(String gameName)
    {
        req = new LastRoundRequest(gameName);
        Gson gson = new Gson();
        String input = gson.toJson(req);
        CommandData data = new CommandData(CommandType.MOVE_TO_END_GAME_SCREEN, input);
        ServerProxy.getInstance().send(data);
        input = gson.toJson(gameName);
        data = new CommandData(CommandType.DISTRIBUTE_FINAL_SCORE, input);
        ServerProxy.getInstance().send(data);

    }
}
