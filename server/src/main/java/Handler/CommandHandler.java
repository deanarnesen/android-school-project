package Handler;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;
import com.bignerdranch.android.shareddata.commands.commandSupport.CommandType;
import com.bignerdranch.android.shareddata.commands.serverModels.LeaveGameRequest;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;

import ServerCentral.ActiveGamePersistanceManager;
import ServerCentral.StaticData;
import serverCommands.GetCommandList;
import serverCommands.IServerCommand;
import serverCommands.LeaveGameCommand;
import serverCommands.ServerCommandBuilder;

public class CommandHandler implements HttpHandler
{
    private StaticData savedData = StaticData.getInstance();
    private ActiveGamePersistanceManager persistanceManager = ActiveGamePersistanceManager.GetInstance();
    public void handle(HttpExchange exchange) throws IOException
    {
        try
        {
            String user = exchange.getRequestHeaders().getFirst("AUTHORIZATION");
            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                CommandData response = new CommandData();
                Gson gson = new Gson();
                Reader reader = new InputStreamReader(exchange.getRequestBody());
                CommandData req = gson.fromJson(reader, CommandData.class);
                Boolean needList = false;
                IServerCommand myCommand = ServerCommandBuilder.buildCommand(req, user);
                response = null;

                if(req.getCommandType() == CommandType.CREATE_GAME_COMMAND)
                {
                    response = myCommand.execute();
                }
                else if (req.getCommandType() == CommandType.DISTRIBUTE_CHAT_MESSAGE)
                {
                    response = myCommand.execute();
                }
                else if (req.getCommandType() == CommandType.GET_COMMAND_LIST)
                {
                    GetCommandList commandList = new GetCommandList(user);
                    response = commandList.execute();
                    savedData.clearUser(user);
                }
                else if (req.getCommandType() == CommandType.JOIN_GAME_COMMAND)
                {
                    response = myCommand.execute();
                }
                else if (req.getCommandType() == CommandType.LEAVE_GAME)
                {
                    response = myCommand.execute();
                    if(response.getCommandType() == CommandType.LEAVE_GAME){
                        //savedData.addCommand(response, user);
                        LeaveGameCommand join = (LeaveGameCommand) myCommand;
                        LeaveGameRequest request = join.getLeaveRequest();

                        if(request.getGameName().equals("MikeNIke"))
                        {
                            savedData.clearUser("John");
                            savedData.clearUser("Ike");
                        }
                    }

                }
                else if (req.getCommandType() == CommandType.LOGIN_COMMAND)
                {
                    response = myCommand.execute();
                }
                else if (req.getCommandType() == CommandType.REGISTER_COMMAND)
                {
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.START_GAME_COMMAND)
                {
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.UPDATE_DEST_CARD_COUNT)
                {
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.UPDATE_DEST_DECK_COUNT)
                {
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.ADD_DESTINATION_CARD)
                {
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.DRAW_DESTINATION_CARD)
                {
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.RETURN_CHOSEN){
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.DRAW_TRAIN_CARD)
                {
                    if(myCommand != null){
                        response = myCommand.execute(); //safety first :)
                    }
                }
                else if(req.getCommandType() == CommandType.CLAIM_ROUTE){
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.PASS_TURN){
                    response = myCommand.execute();
                }
                else if(req.getCommandType() == CommandType.LAST_ROUND)
                {
                    response = myCommand.execute();
                }
                else if(myCommand != null)
                {
                    response = myCommand.execute();
                }

                if(response != null) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStream respBody = exchange.getResponseBody();
                    String jsonStr = gson.toJson(response);
                    writeString(jsonStr, respBody);
                    exchange.getResponseBody().close();
                }
                else{
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, -1);
                    exchange.getResponseBody().close();
                }
            }
        }
        catch (IOException e)
        {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        }
    }


    /*
    Writes a String to an OutputStream.
    */
    private void writeString(String str, OutputStream os) throws IOException
    {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
