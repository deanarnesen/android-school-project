package ServerCentral;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import Handler.BaseHandler;
import Handler.CommandHandler;
import Handler.TestHandler;

public class ServerMain
{
    private static final int MAX_WAITING_CONNECTIONS = 30;

    private HttpServer server;

    private void run(String portnumber)
    {
        try
        {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portnumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);
        server.createContext("/", new BaseHandler());
        server.createContext("/command", new CommandHandler());
        server.createContext("/test", new TestHandler());
        server.start();
        System.out.println("Server started");
    }

    public static void main(String[] args)
    {
        String portNum = args[0];
        try {
            StaticData data = StaticData.getInstance();
            data.setDataBase(args[1], args[2], args[3]);
            //data.setGameDAO();
            //data.setUserDAO();
            //data.setPlayerDAO();
            data.setPersistance();


        }catch(Exception e){
            e.printStackTrace();
        }
        new ServerMain().run(portNum);
    }
}
