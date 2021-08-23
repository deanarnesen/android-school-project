package serverCommands;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.GameListing;
import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.Player;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.RegisterRequestModel;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import ServerCentral.StaticData;

public class RegisterCommand implements IServerCommand {
    RegisterRequestModel registerRequestModel;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public RegisterCommand(RegisterRequestModel model) {
        registerRequestModel = model;
    }

    public CommandData execute(){
        CommandData response = new CommandData();
        Boolean found = false;
        Vector<Player> listofpeople = savedData.getListOFPeople();
        String username = registerRequestModel.getUsername();
        String password = registerRequestModel.getPassword();
        String confirmPassword = registerRequestModel.getConfirmPassword();

        if(username.length() > 10)
        {
            String errormessage = "Usernames may only be a max of 10 characters long";
            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
        }
        else if(!password.equals(confirmPassword))
        {
            String errormessage = "Password and confirm password fields did not match";
            response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
        }
        else
        {

            for(Player player : listofpeople)
            {
                if(player.getName().equals(username))
                {
                    found = true;
                    String errormessage = "Username already taken";
                    response = new CommandData(CommandType.DISPLAY_ERROR_MESSAGE, errormessage);
                }
            }

            if(!found)
            {
                Player newPlayer = new Player();
                newPlayer.setName(username);
                newPlayer.setPassword(password);
                newPlayer.setLoggedin(true);
                listofpeople.add(newPlayer);
                savedData.setListOFPeople(listofpeople);

                HashMap<String, GameListing> gamesOnServer = savedData.getListOFGames();
                String gameList = gson.toJson(gamesOnServer);
                response = new CommandData(CommandType.ADD_INITIAL_GAMES, gameList);

                savedData.getListOfCommandsPerPlayer().put(newPlayer.getName(), new LinkedList<CommandData>());
                savedData.addCommand(response, newPlayer.getName());
            }
        }

        return response;

    }
}
