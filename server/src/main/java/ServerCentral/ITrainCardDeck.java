package ServerCentral;

import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;

import java.util.Queue;

public interface ITrainCardDeck{
    TrainCarCard drawCard();
    void pushCard(TrainCarCard d);
    int deckSize();
    int discardSize();
    void shuffleDeck();
    void discardCard(TrainCarCard c);
}
