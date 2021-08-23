package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class BaseHandler implements HttpHandler {
    public BaseHandler(){}


    @Override
    public void handle(HttpExchange exchange)throws IOException {
        try {
            System.out.println("HandlerBase invoked.!!\n");
            if(exchange.getRequestMethod().toLowerCase().equals("get")){
                String path = exchange.getRequestURI().getPath();

                if(path.length() == 0 || path.equals("/")){
                    OutputStream responseBody = exchange.getResponseBody();
                    File file = new File("web/index.html");
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    Files.copy(file.toPath(), responseBody);
                    responseBody.close();
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();

        }

    }
}
