package ServerCentral;

import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Objects;
import java.util.Queue;
import java.util.Vector;

public class TrainCardDeck implements ITrainCardDeck, java.io.Serializable {
    private Queue<TrainCarCard> deck;
    private Vector<TrainCarCard> discards;

    public TrainCardDeck(boolean isDrawDeck){
        deck = new ArrayDeque<>();

        if(isDrawDeck) {
            Gson gson = new Gson();
            String jsonString = readStringFromFile("server/resources/train_car_cards.txt");
            Type listType = new TypeToken<Vector<TrainCarCard>>() {
            }.getType();
            Vector<TrainCarCard> cards = gson.fromJson(jsonString, listType);

            Collections.shuffle(cards);
            for (TrainCarCard card : cards) {
                deck.add(card);
            }
            discards = new Vector<>();
        }
    }

    @Override
    public TrainCarCard drawCard() {
        return deck.poll();

    }

    @Override
    public void pushCard(TrainCarCard d) {
        deck.add(d);
    }

    @Override
    public int deckSize() {
        return deck.size();
    }

    @Override
    public void shuffleDeck() {
        Collections.shuffle(discards);
        deck.addAll(discards);
        discards = new Vector<>();
    }

    @Override
    public void discardCard(TrainCarCard c) {
        if(discards == null){
            discards = new Vector<>();
        }
        discards.add(c);
    }

    @Override
    public int discardSize() {
        return discards.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainCardDeck that = (TrainCardDeck) o;
        if(this.deck.size() != that.deck.size()){
            return false;
        }
        return Objects.equals(deck, that.deck);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deck);
    }

    private static String readStringFromFile(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null){
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
