package serverCommands;

import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.CardsChosen;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.commandSupport.Pair;
import com.google.gson.Gson;

import java.util.Vector;

import ServerCentral.ActiveGameModel;
import ServerCentral.StaticData;

public class ReturnPlayerChosenCards implements IServerCommand {
    private CardsChosen cardsChosen;

    public ReturnPlayerChosenCards(CardsChosen cardsChosen) {
        this.cardsChosen = cardsChosen;
    }

    @Override
    public CommandData execute() {
        StaticData savedData = StaticData.getInstance();
        ActiveGameModel gameModel = StaticData.getInstance().getListActiveGames().get(cardsChosen.getGameName());
        Vector<ServerGamePlayer> players = gameModel.getPlayersInGame();
        ServerGamePlayer client = new ServerGamePlayer();

        for(ServerGamePlayer player : players){
            if(player.getUsername().equals(cardsChosen.getUsername())){
                client = player;
            }
        }

        String commandData;
        Gson gson = new Gson();
        for(DestinationCard card: cardsChosen.getChosen()){
            client.addDestCard(card);
            commandData = gson.toJson(card);
            savedData.addCommand(new CommandData(CommandType.ADD_DESTINATION_CARD,
                    commandData), cardsChosen.getUsername());
        }

        for(DestinationCard card : cardsChosen.getNotChosen()){
            gameModel.returnDestinationCardToDeck(card);
        }

        for(ServerGamePlayer player : players){
            commandData = gson.toJson(new Pair(client.getUsername(), client.getDestinationCards().size()));
            savedData.addCommand(new CommandData(CommandType.UPDATE_DEST_CARD_COUNT, commandData),
                    player.getUsername());
        }
        return null;
    }
}
