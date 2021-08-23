package com.bignerdranch.android.shareddata.commands.clientModels;

import com.bignerdranch.android.shareddata.commands.cards.CardType;
import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.CardsForPlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatMessage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ChatStorage;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ClientGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.UpdateType;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Vector;


/*
@Invariable: clientPlayerInfo is not null
@Invariable: gameMembers is not null
@Invariable: gameMembers.size() > 0
@Invariable: trainCardDeckCount > 0
@Invariable: destCardDeckCount >= 0
@Invariable: faceUpCards != null
@Invariable: faceUpCards.size() >= 0 && <= 5
@Invariable: chatLog != null

 */
public class GameModel extends Observable {
    private boolean winnerStatus = true;
    private ServerGamePlayer clientPlayerInfo;
    private HashMap<String, ClientGamePlayer> gameMembers;
    private int trainCardDeckCount;
    private int destCardDeckCount;
    private Vector<TrainCarCard> faceUpCards;
    //private Vector<ChatMessage> chatLog;
    private ChatStorage chatLog;
    private String activePlayer;
    private Boolean firstTurn = true;
    private Boolean test = true;

    public CardsForPlayer getCardsShown() {
        return cardsShown;
    }

    private CardsForPlayer cardsShown;
    private AllRoutes allRoutes;

    private static GameModel instance = new GameModel();
    private GameModel() {
        clientPlayerInfo = new ServerGamePlayer(ClientModelRoot.getInstance().getClientUserName(), false);
        gameMembers = new HashMap<>();
        for(Player player : GameLobbyModel.getInstance().getCurrentPlayers()){
            gameMembers.put(player.getName(), new ClientGamePlayer(player));
        }
        faceUpCards = new Vector<>();
        activePlayer = clientPlayerInfo.getUsername(); //todo change to reflect server
        chatLog = ChatStorage.getInstance();
    }

    /*
    @Post returns a pointer to the GameModel
     */
    public static GameModel getInstance(){
        return instance;
    }

    /*
    @Pre information != null
    @Pre information conatains a valid username
    @Pre information conatains and int value >= 0

    @Post the number of train cards for the specified players is updated
     */
    public void updatePlayerTrainCardCount(Pair information){
        gameMembers.get(information.getUserName()).setNumTrainCards((int)information.getValue());
        setChanged();
        notifyObservers(UpdateType.PLAYER_INFO);
    }

    /*
    @Pre GameLobbyModel has a valid list of players

    @Post the list of current players is updated
     */
    public void updateCurrentPlayers(){
        for(Player player : GameLobbyModel.getInstance().getCurrentPlayers()){
            gameMembers.put(player.getName(), new ClientGamePlayer(player));
        }
    }

    /*
    @Pre cardToAdd is a valid trainCard object

    @Post the cardToAdd is added to the players hand
     */
    public void addTrainCarCards(TrainCarCard cardToAdd){
        clientPlayerInfo.getTrainCarCards().add(cardToAdd);
        setChanged();
        notifyObservers(UpdateType.HAND_UPDATE);
    }

    /*
    @Pre information != null
    @Pre information conatains a valid username
    @Pre information conatains and int value >= 0

    @Post the number of destination cards for the specified players is updated
     */
    public void updatePlayerDestCardCount(Pair information){
        gameMembers.get(information.getUserName()).setNumDestCards((int)information.getValue());
        setChanged();
        notifyObservers(UpdateType.PLAYER_INFO);
    }

    public AllRoutes getAllRoutes() {
        return allRoutes;
    }

    /*
        @Pre cardToAdd is a valid DestinationCard

        @Post the card to add is added to the players hand
         */
    public void addDestinationCard(DestinationCard cardToAdd){
        clientPlayerInfo.getDestinationCards().add(cardToAdd);
        setChanged();
        notifyObservers(UpdateType.HAND_UPDATE);
    }

    /*
    @Pre faceUPs != null
    @Pre faceUPs contains a vector of valid TrainCarCards

    @Post the face up cards shown to the user is updated
     */
    public void updateFaceUpCards(Vector<TrainCarCard> faceUPs){
        faceUpCards = new Vector<>();
        for(TrainCarCard card : faceUPs){
            faceUpCards.add(card);
        }
        setChanged();
        notifyObservers(UpdateType.DECK_UPDATE);
    }

    /*
    @Post returns a list of face up cards
     */
    public Vector<TrainCarCard> getFaceUpCards() {
        return faceUpCards;
    }

    /*
    @Pre information != null
    @Pre information conatains a valid username
    @Pre information conatains and int value >= 0

    @Post the score for the specified players is updated
     */
    public void updatePlayerScore(Pair information){
        gameMembers.get(information.getUserName()).setScore((int)information.getValue());
        setChanged();
        notifyObservers(UpdateType.PLAYER_INFO);
    }

    /*
    @Post returns a map of the current players
     */
    public HashMap<String, ClientGamePlayer> getGameMembers() {
        return gameMembers;
    }

    /*
    @Post returns the current players detailed info
     */
    public ServerGamePlayer getClientPlayerInfo() {
        return clientPlayerInfo;
    }

    /*
    @Pre information != null
    @Pre information conatains a valid username
    @Pre information conatains and int value >= 0

    @Post the number of train cars for the specified players is updated
     */
    public void updateTrainCarPieces(Pair information){
        gameMembers.get(information.getUserName()).setNumTrainCars((int)information.getValue());
        setChanged();
        notifyObservers(UpdateType.PLAYER_INFO);
    }

    /*
    @Pre count > 0

    @Post the number of cards in the train card deck is updated with count
     */
    public void updateTrainCardDeck(int count){
        trainCardDeckCount = count;
        setChanged();
        notifyObservers(UpdateType.DECK_UPDATE);
    }

    /*
    @Pre count >= 0

    @Post the number of cards in the desination card deck is updated with count
     */
    public void updateDestDeckCount(int count){
        destCardDeckCount = count;
        setChanged();
        notifyObservers(UpdateType.DECK_UPDATE);
    }

    /*
    @Pre cards != null
    @Pre cards.size() > 0

    @Post stores cards to display for user to select
     */
    //this won't notify anything yet, I don't think we have an observer
    public void drawDestCard(CardsForPlayer cards)
    {
        cardsShown = cards;
        setChanged();
        notifyObservers(UpdateType.DRAW_DEST_CARDS);
    }

    /*
    @Post returns the number of cards in the train card deck
     */
    public int getTrainCardDeckCount() {
        return trainCardDeckCount;
    }

    /*
    @Post returns the number of cards in the destination card deck
     */
    public int getDestCardDeckCount() {
        return destCardDeckCount;
    }

    /*
    @Pre messages != null

    @Post the chat log is updated with the messages
     */
//    public void updateChat(Vector<ChatMessage> messages){
//        //chatLog = messages;
//        setChanged();
//        notifyObservers(UpdateType.CHAT);
//    }
    public void updateChat(Vector<ChatMessage> messages){
        //chatLog = messages;
        setChanged();
        notifyObservers(UpdateType.CHAT);
    }

    public void addMessage(){
        setChanged();
        notifyObservers(UpdateType.CHAT);
    }

    /*
    @Post returns the chat log
     */
    public Vector<ChatMessage> getChatLog(){
        return chatLog.getLog();
    }

    /*
    @Pre activePlayer != null
    @Pre activePlayer is a valid game member

    @Post current player is updated with activePlayer
     */
    public void setActivePlayer(String activePlayer) {
        this.activePlayer = activePlayer;
        setChanged();
        notifyObservers(UpdateType.TURN_UPDATE);
    }

    /*
    @Pre sourceCity != null
    @Pre sourceCity is a valid city

    @Post returns a list of routes connected to the city
     */
    public ArrayList<Route> getAdjacents(String sourceCity){
        return allRoutes.getAvaliableAdjacents(sourceCity);
    }


    public void notifyLastRound()
    {
        setChanged();
        notifyObservers(UpdateType.LAST_ROUND_NOTIFICATION);
    }

    /*
    @Post return the username of the active player
     */
    public String getActivePlayer(){
        return activePlayer;
    }

    public void setAllRoutes(Vector<Route> routes){
        allRoutes = new AllRoutes(routes);
    }

    public int getNumberRed(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.RED){
                count++;
            }
        }
        return count;
    }

    public int getNumberOrange(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.ORANGE){
                count++;
            }
        }
        return count;
    }

    public int getNumberYellow(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.YELLOW){
                count++;
            }
        }
        return count;
    }

    public int getNumberGreen(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.GREEN){
                count++;
            }
        }
        return count;
    }

    public int getNumberBlue(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.BLUE){
                count++;
            }
        }
        return count;
    }

    public int getNumberPurple(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.PURPLE){
                count++;
            }
        }
        return count;
    }

    public int getNumberBlack(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.BLACK){
                count++;
            }
        }
        return count;
    }

    public int getNumberWhite(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.WHITE){
                count++;
            }
        }
        return count;
    }

    public int getNumberLocomotive(){
        int count = 0;
        for(TrainCarCard carCard : clientPlayerInfo.getTrainCarCards()){
            if(carCard.getCardType() == CardType.LOCOMOTIVE){
                count++;
            }
        }
        return count;
    }

    public Boolean getFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(Boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    public void addRouteToPlayer(Pair data){
        Route route = allRoutes.getRouteById((int)data.getValue());
        if(data.getUserName().equals(clientPlayerInfo.getUsername())){
            clientPlayerInfo.addRoute(route, false);
        }
        for(ClientGamePlayer player : gameMembers.values()){
            if(player.getUsername().equals(data.getUserName())){
                player.getPlayerRoutes().add(route);
            }
        }
        allRoutes.claimRoute((int)data.getValue());
        setChanged();
        notifyObservers(UpdateType.ROUTE_UPDATE);
    }

    public int getFinalScore(String username){
        return gameMembers.get(username).getFinalScore();
    }


    public Boolean getWinnerStatus(){
        return winnerStatus;
    }

    public ArrayList<String> grayColors(int length){
        ArrayList<String> colors = new ArrayList<>();
        length -= getNumberLocomotive();
        if(getNumberYellow() >= length){
            colors.add("Yellow");
        }
        if(getNumberBlue() >= length){
            colors.add("Blue");
        }
        if(getNumberRed() >= length){
            colors.add("Red");
        }
        if(getNumberWhite() >= length){
            colors.add("White");
        }
        if(getNumberBlack() >= length){
            colors.add("Black");
        }
        if(getNumberPurple() >= length){
            colors.add("Purple");
        }
        if(getNumberOrange() >= length){
            colors.add("Orange");
        }
        if(getNumberGreen() >= length){
            colors.add("Green");
        }
        return colors;
    }
}