package service;

import android.os.AsyncTask;

import com.bignerdranch.android.shareddata.commands.clientModels.supportingModels.ServerPair;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.google.gson.Gson;

public class ClaimRouteService {
    private String gameName;
    private String userName;
    private Integer routeID;
    CommandData command;
    public ClaimRouteService(String gameName, String userName, Integer route_id){
        this.gameName = gameName;
        this.userName = userName;
        routeID = route_id;
        command = null;
    }
    public void execute(){
        Gson gson = new Gson();
        ServerPair data = new ServerPair(userName, gameName, routeID);
        String dataJson = gson.toJson(data);
        command = new CommandData(CommandType.CLAIM_ROUTE, dataJson);
        new SendAsync().execute();
    }

    private class SendAsync extends AsyncTask<Void, Void, Void>{

        @Override
        public Void doInBackground(Void... voids){
            ServerProxy.getInstance().send(command);
            return null;
        }
    }
}
