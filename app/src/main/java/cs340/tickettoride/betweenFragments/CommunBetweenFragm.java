package cs340.tickettoride.betweenFragments;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;

import java.util.ArrayList;
import java.util.Vector;

public class CommunBetweenFragm {

    private static CommunBetweenFragm INSTANCE = new CommunBetweenFragm();


    public Vector<TrainCarCard> getTrainCarCards() {
        return trainCarCards;
    }

    public void setTrainCarCards(Vector<TrainCarCard> trainCarCards) {
        this.trainCarCards = trainCarCards;
    }

    Vector <TrainCarCard> trainCarCards;
    Vector<DestinationCard> destCards;
    Vector<TrainCarCard> trainCards;
    Vector<DestinationCard> cardsToChoose;
    ArrayList<String> usernames = new ArrayList<>();

    private CommunBetweenFragm(){ }

    public static CommunBetweenFragm getInstance(){
        return INSTANCE;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }


    public void setCardsToChoose(Vector<DestinationCard> cardsToChoose) {
        this.cardsToChoose = cardsToChoose;
    }
    public Vector<DestinationCard> getCardsToChoose() {
        return cardsToChoose;
    }

    public Vector<TrainCarCard> getTrainCards() {
        return trainCards;
    }

    public void setTrainCards(Vector<TrainCarCard> trainCards) {
        this.trainCards = trainCards;
    }

    public Vector<DestinationCard> getDestCards() {
        return destCards;
    }

    public void setDestCards(Vector<DestinationCard> destCards) {
        this.destCards = destCards;
    }
}
