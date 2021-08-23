package presenter;

import com.bignerdranch.android.shareddata.commands.clientModels.GameLobbyModel;
import com.bignerdranch.android.shareddata.commands.clientModels.GameModel;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.RouteColor;

/**
 * Default behavior for these functions
 */
public class PlayerState {
    public String claimRoute(Integer routeID, MapPresenter presenter, GameModel model, GameLobbyModel lobby){
        return "Invalid Action";
    }
    public String claimRouteWithColor(Integer routeID, RouteColor color, MapPresenter presenter, GameModel model, GameLobbyModel lobbyModel){
        return "Invalid Action";
    }
    public String drawDestCards(MapPresenter presenter, String username, String gameName){
        return "Invalid Action";
    }
    public String drawTrain(int cardPosition, MapPresenter presenter, GameModel model){
        return "Invalid Action";
    }
}
