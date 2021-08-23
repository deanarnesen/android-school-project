package service;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.DrawDestCardRequest;
import com.google.gson.Gson;

public class DrawDestCardService
{
    private DrawDestCardRequest drawCardRequest;

    public void drawCards(String username, String gameName)
    {
        drawCardRequest = new DrawDestCardRequest(username, gameName);
        Gson gson = new Gson();
        String input = gson.toJson(drawCardRequest);
        CommandData data = new CommandData(CommandType.DRAW_DESTINATION_CARD, input);
        ServerProxy.getInstance().send(data);
    }

}
