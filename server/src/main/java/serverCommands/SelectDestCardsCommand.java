package serverCommands;


import com.bignerdranch.android.shareddata.commands.cards.DestinationCard;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.google.gson.Gson;

import java.util.ArrayList;

import ServerCentral.StaticData;

public class SelectDestCardsCommand implements IServerCommand {

    private ArrayList<DestinationCard> destCards;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    @Override
    public CommandData execute() {
        //todo
        return null;
    }

    public void setDestCards(ArrayList<DestinationCard> destCards) {
        this.destCards = destCards;
    }
}
