package ServerCentral;

import com.bignerdranch.android.shareddata.commands.cards.CardType;
import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.cards.TrainCarCard;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.EndGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Route;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class ActiveGameModel implements Serializable {
    private String gameName;
    private Vector<ServerGamePlayer> playersInGame;
    private ServerGamePlayer activePlayer;
    private Vector<Route> allRoutes;
    //Map from player name to routes owned, might be redundant
    private HashMap<Integer, Route> unclaimedRoutes;
    private Vector<TrainCarCard> faceUpTrainCards;
    private TrainCardDeck trainCardDrawDeck;
    //private TrainCardDeck trainCardDiscardDeck;
    private DestCardDeck destinationCardDrawDeck;
    private transient StaticData savedData = StaticData.getInstance();
    private HashMap<String, HashMap<String,HashSet<String>>> playerAdjacenyList;
    private transient Gson gson = new Gson();
    private int turnsLeft;
    private boolean lastRound;

    public ActiveGameModel(){

    }

    public void reInitializeTransients(){
        savedData = StaticData.getInstance();
        gson = new Gson();
    }

    public ActiveGameModel(GameListing game) {
        this.gameName = game.getGameName();
        this.playersInGame = new Vector<>();
        //TODO implement discard and route functionality later
        this.unclaimedRoutes = new HashMap<>();
        this.allRoutes = new Vector<>();

        String json = null;
        Gson gson = new Gson();
        Type type = new TypeToken<Vector<Route>>(){}.getType();
        try{
            System.out.println("Working Directory = " +
                    System.getProperty("user.dir"));
            json = readFile("routes_json.txt");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        if(json != null){
            allRoutes = gson.fromJson(json, type);
            for(Route route : allRoutes){
                unclaimedRoutes.put(route.getId(), route);
            }
        }
        else{
            System.out.printf("error reading from route file");
        }

        playerAdjacenyList = new HashMap<>();
        //trainCardDiscardDeck = new TrainCardDeck(false);
        trainCardDrawDeck = new TrainCardDeck(true);
        destinationCardDrawDeck = new DestCardDeck();
        for(Player player : game.getPlayers()){
            Vector<TrainCarCard> cards = new Vector<>();
            for(int i = 0; i < 4; i++){
                cards.add(trainCardDrawDeck.drawCard());
            }
            this.playersInGame.add(new ServerGamePlayer(player, cards));
            playerAdjacenyList.put(player.getName(), new HashMap<String, HashSet<String>>());
        }

        faceUpTrainCards = new Vector<>();
        for(int i = 0; i < 5; i++){
            faceUpTrainCards.add(trainCardDrawDeck.drawCard());
        }
        checkFaceUpCards();

    }

    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Vector<ServerGamePlayer> getPlayersInGame() {
        return playersInGame;
    }
    public void setPlayersInGame(Vector<ServerGamePlayer> playersInGame) {
        this.playersInGame = playersInGame;
    }

    public Vector<Route> getAllRoutes() {
        return allRoutes;
    }
    public void setAllRoutes(Vector<Route> claimedRoutes) {
        this.allRoutes = claimedRoutes;
    }

    public HashMap<Integer, Route> getUnclaimedRoutes() {
        return unclaimedRoutes;
    }
    public void setUnclaimedRoutes(HashMap<Integer, Route> unclaimedRoutes) {
        this.unclaimedRoutes = unclaimedRoutes;
    }

    public Vector<TrainCarCard> getFaceUpTrainCards() {
        checkFaceUpCards();
        return faceUpTrainCards;
    }
    public void setFaceUpTrainCards(Vector<TrainCarCard> faceUpTrainCards) {
        this.faceUpTrainCards = faceUpTrainCards;
    }
    private void checkFaceUpCards(){
        int locomotives = 0;
        boolean toMany = true;
        while (toMany){
            for(TrainCarCard card : faceUpTrainCards){
                if(card.getCardType().equals(CardType.LOCOMOTIVE)){
                    locomotives++;
                }
            }
            if(locomotives > 2){
                toMany = true;
                for(int i = 4; i>= 0; i--){
                    trainCardDrawDeck.discardCard(faceUpTrainCards.elementAt(i));
                    faceUpTrainCards.setElementAt(drawCard(), i);
                }
                locomotives = 0;
            }
            else{
                toMany = false;
            }
        }
    }

    public TrainCardDeck getTrainCardDrawDeck() {
        return trainCardDrawDeck;
    }
    public void setTrainCardDrawDeck(TrainCardDeck trainCardDrawDeck) {
        this.trainCardDrawDeck = trainCardDrawDeck;
    }

//    public TrainCardDeck getTrainCardDiscardDeck() {
//        return trainCardDiscardDeck;
//    }
//    public void setTrainCardDiscardDeck(TrainCardDeck trainCardDiscardDeck) {
//        this.trainCardDiscardDeck = trainCardDiscardDeck;
//    }

    public DestCardDeck getDestinationCardDrawDeck() {
        return destinationCardDrawDeck;
    }
    public void setDestinationCardDrawDeck(DestCardDeck destinationCardDrawDeck) {
        this.destinationCardDrawDeck = destinationCardDrawDeck;
    }

    public void returnDestinationCardToDeck(DestinationCard card){
        this.destinationCardDrawDeck.pushCard(card);
    }

    public void addTrainCarCard(String name, TrainCarCard card){
        for(ServerGamePlayer player : playersInGame){
            if(name.equals(player.getUsername())){
                player.addTrainCard(card);
                String data = gson.toJson(card);
                CommandData response = new CommandData(CommandType.ADD_TRAIN_CARD, data);
                savedData.addCommand(response, name);
                GameListing gameListing = new GameListing();
                gameListing.setGameName(gameName);
                Pair userdata = new Pair(name, player.getNumTrainCards());
                data = gson.toJson(userdata);
                response = new CommandData(CommandType.UPDATE_TRAIN_CARDS_COUNT, data);
                savedData.addGameCommand(response, gameListing);
            }
        }
    }

    public void setFaceupCard(int index){
        faceUpTrainCards.setElementAt(trainCardDrawDeck.drawCard(), index);
        checkFaceUpCards();
        String data = gson.toJson(faceUpTrainCards);
        CommandData response = new CommandData(CommandType.UPDATE_FACE_UP_TRAIN_CARDS, data);
        GameListing gameListing = new GameListing();
        gameListing.setGameName(gameName);
        savedData.addGameCommand(response, gameListing);
        //update deck count
        data = gson.toJson(trainCardDrawDeck.deckSize());
        response = new CommandData(CommandType.UPDATE_TRAIN_DECK_COUNT, data);
        savedData.addGameCommand(response, gameListing);
    }

    public void nextTurn(){
        if(playersInGame.size() <= 0) {
            return;
        }
        if(activePlayer == null){
            activePlayer = playersInGame.elementAt(0);
        }
        else{
            for(int i = 0; i < playersInGame.size(); i++){
                if (playersInGame.elementAt(i).equals(activePlayer)){
                    //move to next player in player vector
                    activePlayer = playersInGame.elementAt((i+1)%playersInGame.size());
                    break;
                }
            }
        }
        GameListing gameListing = new GameListing();
        gameListing.setGameName(gameName);
        String data = gson.toJson(activePlayer.getUsername());
        CommandData response = new CommandData(CommandType.UPDATE_TURN, data);
        savedData.addGameCommand(response, gameListing);
        if(lastRound){
            turnsLeft--;
            if(turnsLeft == 0){
                Vector<EndGamePlayer> finalTotal = getFinalScreenInfo();
                String playerData = gson.toJson(finalTotal);
                response = new CommandData(CommandType.MOVE_TO_END_GAME_SCREEN, playerData);
                savedData.addGameCommand(response, gameListing);
            }
        }
    }

    public void setActivePlayer(String username){
        for(ServerGamePlayer player : playersInGame){
            if(player.getUsername().equals(username)){
                activePlayer = player;
            }
        }
    }

    public void addRouteToPlayer(String username, int routeID){
        if(unclaimedRoutes.containsKey(routeID)){
            for(ServerGamePlayer player : playersInGame){
                if(player.getUsername().equals(username)){
                    Route route = unclaimedRoutes.get(routeID);
                    Vector<TrainCarCard> cards = player.addRoute(route, true);
                    for(TrainCarCard card : cards){
                        trainCardDrawDeck.discardCard(card);
                    }
                    unclaimedRoutes.remove(routeID);
                    updatePlayerAdjList(username, route);

                    Pair data = new Pair(username, routeID);
                    CommandData commandData = new CommandData(CommandType.ADD_ROUTE, gson.toJson(data));
                    GameListing gameListing = new GameListing();
                    gameListing.setGameName(gameName);
                    StaticData.getInstance().addGameCommand(commandData, gameListing);

                    data = new Pair(username, player.getNumTrainCards());
                    commandData = new CommandData(CommandType.UPDATE_TRAIN_CARDS_COUNT, gson.toJson(data));
                    StaticData.getInstance().addGameCommand(commandData, gameListing);

                    data = new Pair(username, player.getNumTrainCars());
                    commandData = new CommandData(CommandType.UPDATE_TRAIN_CARS, gson.toJson(data));
                    StaticData.getInstance().addGameCommand(commandData, gameListing);

                    data = new Pair(username, player.getScore());
                    commandData = new CommandData(CommandType.UPDATE_SCORE, gson.toJson(data));
                    StaticData.getInstance().addGameCommand(commandData, gameListing);
                    return;
                }
            }
        }
    }

    private void updatePlayerAdjList(String username, Route r){
        HashMap<String, HashSet<String>> playerAdjList = playerAdjacenyList.get(username);
        Boolean onlyOneCityInSet = true;
        Boolean bothCitiesNotInSet = false;
        Boolean bothCitiesInSet = false;
        String srcCity = null;
        String destCity = null;
        if(playerAdjList.containsKey(r.getStartCity()) && !playerAdjList.containsKey(r.getEndCity())){
            srcCity = r.getStartCity();
            destCity = r.getEndCity();
            bothCitiesNotInSet = false;
        }
        else if(playerAdjList.containsKey(r.getEndCity()) && !playerAdjList.containsKey(r.getStartCity())){
            srcCity = r.getEndCity();
            destCity = r.getStartCity();
            bothCitiesNotInSet = false;
        }
        else if(playerAdjList.containsKey(r.getEndCity()) && playerAdjList.containsKey(r.getStartCity())){
            bothCitiesInSet = true;
            bothCitiesNotInSet = false;
            onlyOneCityInSet = false;
        }
        else{
            bothCitiesInSet = false;
            onlyOneCityInSet = false;
            bothCitiesNotInSet = true;
            srcCity = r.getStartCity();
            destCity = r.getEndCity();
        }

        if(onlyOneCityInSet) {
            HashSet<String> set = playerAdjList.get(srcCity);
            HashSet<String> newAdjList = new HashSet<>();
            newAdjList.add(srcCity);
            for (String s : set) {
                playerAdjList.get(s).add(destCity);
                newAdjList.add(s);
            }
            set.add(destCity);
            playerAdjList.put(destCity, newAdjList);
        }

        if(bothCitiesNotInSet){
            HashSet<String> srcSet = new HashSet<>();
            HashSet<String> destSet = new HashSet<>();
            srcSet.add(srcCity);
            destSet.add(destCity);
            playerAdjList.put(srcCity, destSet);
            playerAdjList.put(destCity, srcSet);
        }

        if(bothCitiesInSet){
            HashSet<String> setOne = playerAdjList.get(r.getStartCity());
            HashSet<String> setTwo = playerAdjList.get(r.getEndCity());
            for(String s : setOne){
                for(String p : setTwo){
                    playerAdjList.get(p).add(s);
                    playerAdjList.get(p).add(r.getStartCity());
                }
            }
            for(String p : setTwo){
                for(String s : setOne){
                    playerAdjList.get(s).add(p);
                    playerAdjList.get(s).add(r.getEndCity());
                }
            }
        }
    }

    public void distributeFinalScore(){
        Vector<EndGamePlayer> players = getFinalScreenInfo();
        GameListing gameListing = new GameListing();
        gameListing.setGameName(gameName);
        CommandData commandData = new CommandData(CommandType.DISTRIBUTE_FINAL_SCORE, gson.toJson(players));
        StaticData.getInstance().addGameCommand(commandData, gameListing);
    }

    public Vector<EndGamePlayer> getFinalScreenInfo(){
        Vector<EndGamePlayer> finalScores = new Vector<>();
        int topScore = -1000000;
        int playerIndex = 0;
        int winnerIndex = 0;
        int mostRoutes = 0;
        ArrayList<EndGamePlayer> routeBonusWinners = new ArrayList<>();
        for(ServerGamePlayer player : playersInGame){

            EndGamePlayer finalScore = getFinalScore(player.getUsername(), playerIndex);
            if(player.getRoutes().size() > mostRoutes){
                mostRoutes = player.getRoutes().size();
                routeBonusWinners.clear();
                routeBonusWinners.add(finalScore);
            }
            else if(player.getRoutes().size() == mostRoutes){
                routeBonusWinners.add(finalScore);
            }
            if(finalScore.getFinalScore() > topScore){
                topScore = finalScore.getFinalScore();
                winnerIndex = playerIndex;
            }
            for(EndGamePlayer endGamePlayer : routeBonusWinners){
                endGamePlayer.setRouteBonus(10);
            }
            finalScores.add(finalScore);
            playerIndex++;
        }
        Boolean status = true;
        finalScores.get(winnerIndex).setWinner(status);

        return finalScores;
    }

    private EndGamePlayer getFinalScore(String username, int playerIndex){
        EndGamePlayer returnPlayer = new EndGamePlayer(username);

        int bonusPoints = 0;
        int negativePoints = 0;

        HashMap<String, HashSet<String>> routeAdj = playerAdjacenyList.get(username);
        for(DestinationCard card : playersInGame.elementAt(playerIndex).getDestinationCards()){
            if(routeAdj.containsKey(card.getStartCity())){
                if(routeAdj.get(card.getStartCity()).contains(card.getEndCity())){
                    bonusPoints += card.getPointValue();
                }
                else{
                    negativePoints += card.getPointValue();
                }
            }
            else{
                negativePoints += card.getPointValue();
            }
        }

        int playerScore = playersInGame.elementAt(playerIndex).getScore();
        returnPlayer.setFinalScore(playerScore + bonusPoints - negativePoints);
        returnPlayer.setPointsFromDest(bonusPoints);
        returnPlayer.setPointsLostFromDest(negativePoints);
        returnPlayer.setWinner(false);
        return returnPlayer;
    }

    public TrainCarCard drawCard(){
        TrainCarCard cardToReturn = null;
        if(trainCardDrawDeck.deckSize() > 0){
            cardToReturn =  trainCardDrawDeck.drawCard();
        }
        else if(trainCardDrawDeck.discardSize() > 0){
            trainCardDrawDeck.shuffleDeck();
            cardToReturn = trainCardDrawDeck.drawCard();
        }
        CommandData commandData = new CommandData(CommandType.UPDATE_TRAIN_DECK_COUNT,
                gson.toJson(trainCardDrawDeck.deckSize()));
        GameListing gameListing = new GameListing();
        gameListing.setGameName(gameName);
        StaticData.getInstance().addGameCommand(commandData, gameListing);
        return cardToReturn;
    }


    String readFile(String fileName) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        finally {
            br.close();
        }
    }

    public void lastRound(){
        lastRound = true;
        turnsLeft = playersInGame.size() +1;
    }

    public boolean isLastRound() {
        return lastRound;
    }

    public void resetTransients(StaticData data){
        this.savedData = data;
        this.gson = new Gson();
    }
}
