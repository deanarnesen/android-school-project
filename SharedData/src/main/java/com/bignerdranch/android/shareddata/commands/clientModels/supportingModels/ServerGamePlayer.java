package com.bignerdranch.android.shareddata.commands.clientModels.supportingModels;

import com.bignerdranch.android.shareddata.commands.cards.CardType;
import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;

import java.util.Vector;

public class ServerGamePlayer implements java.io.Serializable{
    private String username;
    private Color color;
    private Vector<Route> routes;
    private Vector<DestinationCard> destinationCards;
    private Vector<TrainCarCard> trainCarCards;
    private int numTrainCars;
    private int score;
    private boolean turnState;

    public ServerGamePlayer(Player player, Vector<TrainCarCard> cards) {
        setUsername(player.getName());
        setColor(player.getColor());
        setTurnState(false);
        setNumTrainCars(45);
        setScore(0);
        this.trainCarCards = new Vector<>();
        for(TrainCarCard card : cards){
            this.trainCarCards.add(card);
        }
        destinationCards = new Vector<>();
        routes = new Vector<>();
    }
    public ServerGamePlayer(Player player) {
        setUsername(player.getName());
        setColor(player.getColor());
        setTurnState(false);
        setNumTrainCars(45);
        setScore(0);
        this.trainCarCards = new Vector<>();
        this.destinationCards = new Vector<>();
        this.routes = new Vector<>();
    }

    public ServerGamePlayer(String username, int score, boolean turnState) {
        setUsername(username);
        setTurnState(turnState);
        setNumTrainCars(45);
        setScore(score);
    }

    public ServerGamePlayer(String username, boolean turnState) {
        setUsername(username);
        setTurnState(turnState);
        setNumTrainCars(45);
        setScore(0);
        this.trainCarCards = new Vector<>();
        this.destinationCards = new Vector<>();
        this.routes = new Vector<>();
    }

    public ServerGamePlayer() {}

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Vector<Route> getRoutes() {
        return routes;
    }
    public void setRoutes(Vector<Route> routes) {
        this.routes = routes;
    }

    public Vector<DestinationCard> getDestinationCards() {
        return destinationCards;
    }
    public void setDestinationCards(Vector<DestinationCard> destinationCards) {
        this.destinationCards = destinationCards;
    }

    public Vector<TrainCarCard> getTrainCarCards() {
        return trainCarCards;
    }
    public void setTrainCarCards(Vector<TrainCarCard> trainCarCards) {
        this.trainCarCards = trainCarCards;
    }

    public int getNumTrainCars() {
        return numTrainCars;
    }
    public void setNumTrainCars(int numTrainCars) {
        this.numTrainCars = numTrainCars;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public boolean isTurnState() {
        return turnState;
    }
    public void setTurnState(boolean turnState) {
        this.turnState = turnState;
    }

    public void addTrainCard(TrainCarCard card){
        this.trainCarCards.add(card);
    }

    public void removeTrainCard(){
        //TODO implement removing cards from hand
    }

    public void addDestCard(DestinationCard card){
        this.destinationCards.add(card);
    }

    public int getNumTrainCards(){
        return trainCarCards.size();
    }

    public int getNumDestCards(){
        return destinationCards.size();
    }

    public Vector<TrainCarCard> addRoute(Route route, boolean serverSide){
        routes.add(route);
        if(serverSide){
            numTrainCars -= route.getLength();
            score += route.getPointValue();
        }
        return removeCardsFromHand(route);
    }

    private Vector<TrainCarCard> removeCardsFromHand(Route route){
        int numCards = route.getLength();
        RouteColor color = route.getRouteColor();
        Vector<TrainCarCard> cardsToReturn = new Vector<>();

        if(color.equals(RouteColor.GRAY)){
            //todo maybe a real method here, just going to use
            int length = route.getLength();
            int numYellow = 0;
            int numRed = 0;
            int numBlue = 0;
            int numWhite = 0;
            int numBlack = 0;
            int numGreen = 0;
            int numOrange = 0;
            int numPurple = 0;
            int numLoco = 0;


            for(TrainCarCard card : trainCarCards){
                CardType type = card.getCardType();
                switch (type){
                    case RED:
                        numRed++;
                        break;
                    case BLUE:
                        numBlue++;
                        break;
                    case BLACK:
                        numBlack++;
                        break;
                    case GREEN:
                        numGreen++;
                        break;
                    case WHITE:
                        numWhite++;
                        break;
                    case ORANGE:
                        numOrange++;
                        break;
                    case PURPLE:
                        numPurple++;
                        break;
                    case YELLOW:
                        numYellow++;
                        break;
                    case LOCOMOTIVE:
                        numLoco++;
                        break;
                        default:
                }
            }
            //find "best" use of cards for gray route claims
            CardType typeToUse = null;
            int currentBest = 20; //larger than any card count could be
            if(numRed >= length && numRed < currentBest){
                typeToUse = CardType.RED;
                currentBest = numRed;
            }
            if(numBlue >= length && numBlue < currentBest){
                typeToUse = CardType.BLUE;
                currentBest = numBlue;
            }
            if(numYellow >= length && numYellow < currentBest){
                typeToUse = CardType.YELLOW;
                currentBest = numYellow;
            }
            if(numWhite >= length && numWhite < currentBest){
                typeToUse = CardType.WHITE;
                currentBest = numWhite;
            }
            if(numBlack >= length && numBlack < currentBest){
                typeToUse = CardType.BLACK;
                currentBest = numBlack;
            }
            if(numOrange >= length && numOrange < currentBest){
                typeToUse = CardType.ORANGE;
                currentBest = numOrange;
            }
            if(numPurple >= length && numPurple < currentBest){
                typeToUse = CardType.PURPLE;
                currentBest = numPurple;
            }
            if(numGreen >= length && numGreen < currentBest){
                typeToUse = CardType.BLUE;
                currentBest = numGreen;
            }

            //need locomotives to complete
            if(typeToUse == null){
                if(numRed + numLoco >= length && numRed < currentBest){
                    typeToUse = CardType.RED;
                    currentBest = numRed;
                }
                if(numBlue + numLoco >= length && numBlue < currentBest){
                    typeToUse = CardType.BLUE;
                    currentBest = numBlue;
                }
                if(numYellow + numLoco >= length && numYellow < currentBest){
                    typeToUse = CardType.YELLOW;
                    currentBest = numYellow;
                }
                if(numWhite + numLoco >= length && numWhite < currentBest){
                    typeToUse = CardType.WHITE;
                    currentBest = numWhite;
                }
                if(numBlack + numLoco >= length && numBlack < currentBest){
                    typeToUse = CardType.BLACK;
                    currentBest = numBlack;
                }
                if(numOrange + numLoco >= length && numOrange < currentBest){
                    typeToUse = CardType.ORANGE;
                    currentBest = numOrange;
                }
                if(numPurple + numLoco >= length && numPurple < currentBest){
                    typeToUse = CardType.PURPLE;
                    currentBest = numPurple;
                }
                if(numGreen + numLoco >= length && numGreen < currentBest){
                    typeToUse = CardType.BLUE;
                }
            }

            boolean useLoco = false;
            for(int i = 0; length > 0; i++){
                if(trainCarCards.elementAt(i).getCardType().equals(typeToUse)){
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    length--;
                }
                if(useLoco){
                    if(trainCarCards.elementAt(i).getCardType().equals(CardType.LOCOMOTIVE)){
                        cardsToReturn.add(trainCarCards.elementAt(i));
                        trainCarCards.removeElementAt(i);
                        length--;
                    }
                }
                if (i == trainCarCards.size() - 1) {
                    i = 0;
                    useLoco = true;
                }
            }
            return cardsToReturn;
        }
        else{
            for(int i = trainCarCards.size() - 1; i >= 0; i--) {
                if (trainCarCards.elementAt(i).getCardType().equals(CardType.BLUE) && color.equals(RouteColor.BLUE)) {
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                } else if (trainCarCards.elementAt(i).getCardType().equals(CardType.BLACK) && color.equals(RouteColor.BLACK)) {
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                } else if (trainCarCards.elementAt(i).getCardType().equals(CardType.GREEN) && color.equals(RouteColor.GREEN)) {
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                } else if (trainCarCards.elementAt(i).getCardType().equals(CardType.ORANGE) && color.equals(RouteColor.ORANGE)) {
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                } else if (trainCarCards.elementAt(i).getCardType().equals(CardType.PURPLE) && color.equals(RouteColor.PURPLE)) {
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                } else if (trainCarCards.elementAt(i).getCardType().equals(CardType.RED) && color.equals(RouteColor.RED)) {
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                } else if (trainCarCards.elementAt(i).getCardType().equals(CardType.WHITE) && color.equals(RouteColor.WHITE)) {
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                } else if (trainCarCards.elementAt(i).getCardType().equals(CardType.YELLOW) && color.equals(RouteColor.YELLOW)) {
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                }
                if(numCards == 0){
                    break; //don't go negative
                }
            }
        }
        if(numCards > 0){
            for(int i = trainCarCards.size() - 1; i >= 0; i--){
                if(trainCarCards.elementAt(i).getCardType().equals(CardType.LOCOMOTIVE)){
                    cardsToReturn.add(trainCarCards.elementAt(i));
                    trainCarCards.removeElementAt(i);
                    numCards--;
                }
                if(numCards == 0){
                    break; //don't go negative
                }
            }
        }
        return cardsToReturn;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this){
            return true; //same
        }
        if(!(o instanceof ServerGamePlayer)){
            return false; //wrong class
        }
        return ((ServerGamePlayer) o).getUsername().equals(username); //return based on username
    }
}
