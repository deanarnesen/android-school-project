package serverCommands;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.google.gson.Gson;

import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import ServerCentral.Server;
import ServerCentral.StaticData;

public class GetCommandList implements IServerCommand
{
    String username;
    private StaticData savedData = StaticData.getInstance();
    private Gson gson = new Gson();

    public GetCommandList(String temp) {
        username = temp;
    }

    @Override
    public CommandData execute(){

        boolean found = false;
        CommandData response = new CommandData();
        TreeMap<String, Queue<CommandData>> commandList = savedData.getListOfCommandsPerPlayer();
        for(Map.Entry<String, Queue<CommandData>> entry : commandList.entrySet())
        {
            if(entry.getKey().equals(username))
            {
                if(entry.getValue().size() > 0) {
                    found = true;
                    Queue<CommandData> list = entry.getValue();
                    String listJson = gson.toJson(list);
                    response = new CommandData(CommandType.COMMAND_LIST, listJson);
                    break;
                }
            }
            else
            {

            }
        }

        return response;


    }
}
