package serverCommands;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.UpdateDestDeckRequest;
import com.google.gson.Gson;

import ServerCentral.ActiveGameModel;
import ServerCentral.DestCardDeck;
import ServerCentral.StaticData;

public class UpdateDestDeckCommand implements IServerCommand
{
    private UpdateDestDeckRequest req;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public UpdateDestDeckCommand(UpdateDestDeckRequest req)
    {
        this.req = req;
    }

    public CommandData execute()
    {
        ActiveGameModel game = savedData.getListActiveGames().get(req.getGame().getGameName());
        DestCardDeck deck = game.getDestinationCardDrawDeck();
        String input = gson.toJson(deck.deckSize());

        CommandData response = new CommandData(CommandType.UPDATE_DEST_DECK_COUNT, input);
        savedData.addCommandForSpecificPlayers(req.getGame(), response);

        return response;
    }
}
