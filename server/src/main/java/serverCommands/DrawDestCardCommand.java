package serverCommands;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.CardsForPlayer;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.DrawDestCardRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Vector;

import ServerCentral.ActiveGameModel;
import ServerCentral.DestCardDeck;
import ServerCentral.StaticData;

public class DrawDestCardCommand implements IServerCommand
{
    private DrawDestCardRequest drawCardRequest;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public DrawDestCardCommand(DrawDestCardRequest drawCardRequest)
    {
        this.drawCardRequest = drawCardRequest;
    }

    public CommandData execute()
    {
        Vector<DestinationCard> threeCards = new Vector<>();
        ActiveGameModel game = savedData.getListActiveGames().get(drawCardRequest.getGameName());
        DestCardDeck deck = game.getDestinationCardDrawDeck();
        for(int i = 0; i < 3; i++)
        {
            threeCards.add(deck.drawCard());
        }

        CardsForPlayer cards = new CardsForPlayer(threeCards, drawCardRequest.getUsername());
        String data = gson.toJson(cards);

        CommandData response = new CommandData(CommandType.DRAW_DESTINATION_CARD, data);
        savedData.addCommand(response, drawCardRequest.getUsername());

        return response;
    }
}
