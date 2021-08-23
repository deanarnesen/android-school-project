package serverCommands;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.AddDestCardRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.UpdateDestCardRequest;
import com.bignerdranch.android.shareddata.commands.serverModels.UpdateDestDeckRequest;
import com.google.gson.Gson;

import ServerCentral.ActiveGameModel;
import ServerCentral.DestCardDeck;
import ServerCentral.StaticData;

/**
 * Adds a single destination card to the correct Player or deck, then calls
 * the appropriate services to let everyone else know the new count.
 *
 * @Invariants assertNotNull(gson), assertNotNull(savedData)
 */
public class AddDestCardCommand implements IServerCommand
{
    private AddDestCardRequest req;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    /**
     *
     * @pre assertNotNull(req)
     *
     * @post assertNotNull(this.req)
     *
     * @param req request that will be added
     */
    public AddDestCardCommand(AddDestCardRequest req)
    {
        this.req = req;
    }

    /**
     *
     * Will add a destination card to either the players hand or the deck.  If the card is added to the deck an
     * update destination deck count command will be created to change the number of cards in the deck for all
     * the players to see. If the card is added to the player's hand, the card will show up in the view of the player.
     * In addition, the number of destination cards that player has will also increase and a new update destination
     * cards command will be executed so the other players in game can see that number as well.
     *
     * @pre assertNotNull(req.getCardToAdd()), assertNotNull(req.getGameName()), assertNotNull(req.getUsername())
     *      assertNotNull(savedData.getListActiveGames().get(req.getGameName()))[or in other words the game we are
     *      looking for must be in the list of active games]
     *
     * @post deck.size().equals(old(deck.size()) + 1) OR [depending on the branch]
     *       savedData.CommandsForPlayersInGame.size().equals(old(savedData.CommandsForPlayersInGame.size()) + 1)
     *       [expect for the user who will have a Command size of + 2 (adding the card and updating the count)]
     *
     * @return specific destination card for specific user or deck
     */
    public CommandData execute()
    {
        CommandData response = new CommandData();

        ActiveGameModel game = savedData.getListActiveGames().get(req.getGameName());
        GameListing officialGame = savedData.getSpecificGameByName(req.getGameName());
        DestinationCard card = req.getCardToAdd();
        String user = req.getUsername();

        if(user.equals("deck"))
        {
            DestCardDeck deck = game.getDestinationCardDrawDeck();
            deck.pushCard(card);

            UpdateDestDeckRequest deckRequest = new UpdateDestDeckRequest(officialGame);
            UpdateDestDeckCommand deckCommand = new UpdateDestDeckCommand(deckRequest);
            deckCommand.execute();
        }
        else
        {
            for (ServerGamePlayer player : game.getPlayersInGame())
            {
                if (player.getUsername().equals(user))
                {
                    player.addDestCard(card);
                    break;
                }
            }
            String data = gson.toJson(card);

            response = new CommandData(CommandType.ADD_DESTINATION_CARD, data);
            savedData.addCommand(response, user);

            UpdateDestCardRequest cardRequest = new UpdateDestCardRequest(officialGame, user);
            UpdateDestCardCommand cardCommand = new UpdateDestCardCommand(cardRequest);
            cardCommand.execute();
        }

        return response;

    }
}
