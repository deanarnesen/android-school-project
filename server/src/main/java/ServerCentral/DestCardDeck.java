package ServerCentral;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.Vector;

public class DestCardDeck implements IDestCardDeck, java.io.Serializable {
    private Queue<DestinationCard> deck;

    public DestCardDeck(){
        Gson gson = new Gson();
        deck = new ArrayDeque<>();

        String jsonString = readStringFromFile("server/resources/destination_cards.txt");
        Type listType = new TypeToken<Vector<DestinationCard>>() {}.getType();
        Vector<DestinationCard> cards = gson.fromJson(jsonString, listType);

        Collections.shuffle(cards);
        for(DestinationCard card : cards){
            deck.add(card);
        }
    }

    @Override
    public DestinationCard drawCard() {
        return deck.poll();
    }

    @Override
    public void pushCard(DestinationCard d) {
        deck.add(d);
    }

    @Override
    public int deckSize(){
        return deck.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestCardDeck that = (DestCardDeck) o;
        if(this.deck.size() != that.deck.size()){
            return false;
        }
        return Objects.equals(deck, that.deck);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deck);
    }

    @Override
    public String toString() {
        return "DestCardDeck{" +
                "deck=" + deck +
                '}';
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
