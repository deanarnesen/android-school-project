package service;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.AddDestCardRequest;
import com.google.gson.Gson;

public class AddDestCardService
{
    private AddDestCardRequest requestedCardsToAdd;

    public AddDestCardService(DestinationCard cardToAdd, String gameName, String username)
    {
        requestedCardsToAdd = new AddDestCardRequest(cardToAdd, gameName, username);
        Gson gson = new Gson();
        String input = gson.toJson(requestedCardsToAdd);
        CommandData data = new CommandData(CommandType.ADD_DESTINATION_CARD, input);
        ServerProxy.getInstance().send(data);
    }

}
