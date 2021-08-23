package service;

import com.bignerdranch.android.shareddata.commands.clientModels.ClientModelRoot;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import clientSource.IOHelper;
import clientSource.Poller;

public class ServerProxy {

    private static ServerProxy instance = new ServerProxy();
    private final int RETRIES = 10;
    private static TimerTask timerTask;
    private static Timer timer;
    private IOHelper myHelper = IOHelper.getInstance();
    private URL url;
    private String ipAddress = "10.0.2.2";
    private String portNumber = "8080";
    Gson gson = new Gson();


    //private Serializer serializer;
    private ClientModelRoot modelRoot;


    private ServerProxy(){
        modelRoot = ClientModelRoot.getInstance();
        try{
            //serializer = new Serializer();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

  public void startPoller(){
        timerTask = new Poller(this);
        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
  }

  public void pausePoller(){
        try {
            timer.cancel();
            Thread.sleep(5000);
            startPoller();
        }catch(Exception e){
            e.printStackTrace();
        }
  }


    public static ServerProxy getInstance(){

        return instance;
    }

    public void setPortAndIP(String port, String ip){
        this.ipAddress = ip;
        this.portNumber = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public String send(CommandData data){
        String message = null;
        boolean delay = false;
        int retry = 0;
        //do {
            try {
                if(delay){
                    Thread.sleep(1000);
                }
                //new NewSend().send(data);
                URL url2 = new URL("http://" + ipAddress + ":" + portNumber + "/command");
                HttpURLConnection http;
                http = (HttpURLConnection) url2.openConnection();
                http.setDoOutput(true);
                http.setRequestMethod("POST");


                http.addRequestProperty("AUTHORIZATION", modelRoot.getClientUserName());

                http.connect();
                String commandDataString = gson.toJson(data);
                OutputStream reqBody = http.getOutputStream();
                myHelper.writeString(commandDataString, reqBody);
                reqBody.close();

                CommandData response = new CommandData();


                if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    InputStream stream = http.getInputStream();
                    String respData = readString(stream);

                    response = gson.fromJson(respData, CommandData.class);
                    if (response == null) {
                        message = "Loading";
                    } else if (response.getCommandType() == CommandType.DISPLAY_ERROR_MESSAGE) {
                        String error = response.getCommandData();
                        modelRoot.setCurrentError(error);
                        message = error; //this throws an exception
                    } else {
                        //On success, display loading
                        message = "Loading";
                    /*String list = response.getCommandData();
                    Type listType = new TypeToken<Queue<CommandData>>(){}.getType();
                    Queue<CommandData> commandDataQueue = gson.fromJson(list, listType);
                    CommandList commands = new CommandList(commandDataQueue);
                    commands.execute();*/
                    }
                    stream.close();
                    return message;
                } else {
                    response = new CommandData(CommandType.COMMAND_LIST, ("\"ERROR: \"" + http.getResponseMessage()));
                }

            } catch (Exception e) {
                e.printStackTrace();
                // RETURN SOMETHING  we have the stack trace....
                message = "Failed to connect to the server.";
            }
            retry++;
            delay = true;
        //}while(retry < RETRIES);

        return message;

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
