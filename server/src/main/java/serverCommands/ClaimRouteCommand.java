package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerGamePlayer;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerPair;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.serverModels.LastRoundRequest;

import java.util.Vector;

import ServerCentral.ActiveGameModel;
import ServerCentral.StaticData;

public class ClaimRouteCommand implements IServerCommand {
    private ServerPair userInfo;
    private StaticData data = StaticData.getInstance();

    public ClaimRouteCommand(ServerPair userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public CommandData execute() {
        ActiveGameModel game = data.getListActiveGames().get(userInfo.getGameName());
        String user = userInfo.getUserName(); //
        Integer routeID = userInfo.getValue(); //
        game.addRouteToPlayer(user, routeID); //

        //if any player (read: claiming player) has less than three cars left, begin last round
        Vector<ServerGamePlayer> playerlist = game.getPlayersInGame();
        for(ServerGamePlayer player: playerlist) {
            if(player.getNumTrainCars() < 3) {
                LastRoundRequest lastReq = new LastRoundRequest(userInfo.getGameName());
                LastRoundCommand command = new LastRoundCommand(lastReq);
                command.execute();
            }
        }
        return null;
    }
}
