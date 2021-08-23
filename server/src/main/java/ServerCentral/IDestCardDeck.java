package ServerCentral;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;

import java.util.Queue;

public interface IDestCardDeck {
    DestinationCard drawCard();
    void pushCard(DestinationCard d);
    int deckSize();
}
