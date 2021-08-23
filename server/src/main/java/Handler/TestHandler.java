package Handler;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LastRoundRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

import serverCommands.LastRoundCommand;

public class TestHandler implements HttpHandler {
    public TestHandler(){}

    @Override
    public void handle(HttpExchange exchange)throws IOException {
        try {
            System.out.println("TESTING HANDLE invoked.!!\n");

            CommandData command = new CommandData();
            command.setMyCommandType(CommandType.LAST_ROUND);

            LastRoundRequest request = new LastRoundRequest("test");


            LastRoundCommand roundCommand = new LastRoundCommand(request);
            roundCommand.execute();




            if(exchange.getRequestMethod().toLowerCase().equals("post")){



                String path = exchange.getRequestURI().getPath();
                OutputStream responseBody = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);












                    exchange.close();
//
//                if(path.length() == 0 || path.equals("/")){
//                    OutputStream responseBody = exchange.getResponseBody();
//                    File file = new File("web/index.html");
//                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//                    Files.copy(file.toPath(), responseBody);
//                    responseBody.close();
//                }
            }
        }
        catch(IOException e){
            e.printStackTrace();

        }

    }


}
