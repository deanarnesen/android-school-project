package clientSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Queue;
import java.util.TimerTask;

import com.bignerdranch.android.shareddata.commands.IClientCommand;
import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import client_commands.CommandList;
import service.ServerProxy;

public class Poller extends TimerTask {
    private ServerProxy proxy;
    private String ipAddress = "10.0.2.2";
    private String portNum = "8080";


    public Poller(ServerProxy proxy){
        this.proxy = proxy;
        this.ipAddress = proxy.getIpAddress();
        this.portNum = proxy.getPortNumber();
    }

        public void fetch(){
        try{
            Gson gson = new Gson();
            URL url = new URL("http://"+ipAddress+":"+portNum+"/command");
            HttpURLConnection fetchHttp;
            fetchHttp = (HttpURLConnection)url.openConnection();
            fetchHttp.setDoOutput(true);
            fetchHttp.setRequestMethod("POST");
            ClientModelRoot modelRoot = ClientModelRoot.getInstance();
            fetchHttp.addRequestProperty("AUTHORIZATION", modelRoot.getClientUserName());
            fetchHttp.connect();

            String commandDataString = gson.toJson(new CommandData(CommandType.GET_COMMAND_LIST, modelRoot.getClientUserName()));
            OutputStream reqBody = fetchHttp.getOutputStream();
            IOHelper myHelper = IOHelper.getInstance();
            myHelper.writeString(commandDataString, reqBody);
            reqBody.close();

            CommandData response;
            if(fetchHttp.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStream stream = fetchHttp.getInputStream();
                String respData = readString(stream);

                response = gson.fromJson(respData, CommandData.class);
                stream.close();
            }
            else
            {
                response = new CommandData(CommandType.COMMAND_LIST, ("\"ERROR: \"" + fetchHttp.getResponseMessage()));
                //ServerProxy.getInstance().pausePoller();
            }
            String list = response.getCommandData();
            Type listType = new TypeToken<Queue<CommandData>>(){}.getType();
            Queue<CommandData> commandDataQueue = gson.fromJson(list, listType);
            CommandList commands = new CommandList(commandDataQueue);
            commands.execute();
        }
        catch(Exception e){
            e.printStackTrace();
            //Return an object to display it in the login, error connecting to the server.


        }

    }

    public void run(){
        fetch();
        /*try {
            ClientModelRoot modelRoot = ClientModelRoot.getInstance();
            Serializer mySerializer = new Serializer();
            IOHelper myHelper = IOHelper.getInstance();
            URL url = new URL("http://10.0.2.2:8080/command");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);

            http.addRequestProperty("AUTHORIZATION", modelRoot.getClientUserName());
            String commandDataString = mySerializer.serialzeObject(new CommandData(
                    CommandType.GET_COMMAND_LIST, null));
            OutputStream reqBody = http.getOutputStream();
            myHelper.writeString(commandDataString, reqBody);
            reqBody.close();

            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){
                executeCommand(http);
            }
            else{
                this.cancel();
            }

        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
    }

    private void executeCommand(HttpURLConnection http) throws IOException {
        InputStream response = http.getInputStream();
        String responseString = IOHelper.getInstance().readString(response);
        Gson gson = new Gson();
        IClientCommand command = gson.fromJson(responseString, IClientCommand.class);
        command.execute();
    }

    private String readString(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0)
        {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
