package service;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.UpdateDestCardRequest;
import com.google.gson.Gson;

public class UpdateDestCardService
{
    private UpdateDestCardRequest updateDestCardRequest;

    public UpdateDestCardService(GameListing game, String username)
    {
        updateDestCardRequest = new UpdateDestCardRequest(game, username);
        Gson gson = new Gson();
        String input = gson.toJson(updateDestCardRequest);
        CommandData data = new CommandData(CommandType.UPDATE_DEST_CARD_COUNT, input);
        ServerProxy.getInstance().send(data);
    }
}
