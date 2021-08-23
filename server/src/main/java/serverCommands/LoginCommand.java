package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LoginRequestModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Vector;

import javax.xml.soap.SAAJMetaFactory;

import ServerCentral.StaticData;

public class LoginCommand implements IServerCommand {
    LoginRequestModel loginRequestModel;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public LoginCommand() {
        loginRequestModel = new LoginRequestModel();
    }

    public LoginCommand(LoginRequestModel model){
        loginRequestModel = model;
    }

    public CommandData execute()
    {
        CommandData response = new CommandData();
        Boolean error = false;
        Vector<Player> listofpeople = savedData.getListOFPeople();
        String username = loginRequestModel.getUsername();
        String password = loginRequestModel.getPassword();
        Vector<Player> newPlayerList = new Vector<>();

        for(Player player : listofpeople)
        {
            if(player.getName().equals(username) && player.getPassword().equals(password) && !player.getLoggedin())
            {
                error = false;
                player.setLoggedin(true);
                HashMap<String, GameListing> gamesOnServer = savedData.getListOFGames();
                String serverGames = gson.toJson(gamesOnServer);
                response = new CommandData(CommandType.ADD_INITIAL_GAMES, serverGames);
                savedData.addCommand(response, player.getName());
            }
            else if(player.getName().equals(username) && player.getPassword().equals(password) && player.getLoggedin())
            {
                String errormessage = "User already logged in";
                response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
            }
            newPlayerList.add(player);
        }

        if(error)
        {
            String errormessage = "Username or password is wrong";
            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
        }

        savedData.setListOFPeople(newPlayerList);

        return response;
    }
}
