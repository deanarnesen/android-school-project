package service;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.UpdateDestDeckRequest;
import com.google.gson.Gson;

public class UpdateDestDeckService
{
    private UpdateDestDeckRequest updateDestDeckRequest;

    public UpdateDestDeckService(GameListing game)
    {
        updateDestDeckRequest = new UpdateDestDeckRequest(game);
        Gson gson = new Gson();
        String input = gson.toJson(updateDestDeckRequest);
        CommandData data = new CommandData(CommandType.UPDATE_DEST_DECK_COUNT, input);
        ServerProxy.getInstance().send(data);
    }
}
