package cs340.tickettoride;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Color;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;

import java.util.ArrayList;
import java.util.Vector;

public interface IMapInterface {
    void claimRoute(Color color, Route route);
    void setPlayerCarsLeft(String username, int numCars);
    void setPlayerScore(String username, int score);
    void setTurnOrder(ArrayList<String> turnOrder);
    void setActivePlayer(String username);
    void setTrainCardNumber(String username, int cards);
    void setDestCardNumber(String username, int cards);
    void showToast(String toast);
    void updateChat(Vector<ChatMessage> currentChat);
    void updateDestDeck(int cardsRemaining);
    void updateTrainDeck(int cardsRemaining);
    void setFaceupTrainCards(Vector<TrainCarCard> trainCards);
    void setPlayerTrainCards(Vector<TrainCarCard> trainCards);
    void setPlayerDestCards(Vector<DestinationCard> destCards);
    void runUpdateTest();
    void promptDestCards(Vector<DestinationCard> destinationCards);
    void displayLastRoundNotificaiton();
    void toEndScreen();
    void grayRouteColorSelector(ArrayList<String> avaliableColors);
}