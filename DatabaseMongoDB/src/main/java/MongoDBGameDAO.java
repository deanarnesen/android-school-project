


import Tables.ListGamesMongo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import database_plugin.IGameDAO;

public class MongoDBGameDAO implements IGameDAO {

    public MongoDBGameDAO (){
        System.out.println("I am in mongoDB GameDAO");
    }

    private static PrintWriter writer;
    private static FileReader fileReader;
    private SerializableString ser;

    private ListGamesMongo row;



    @Override
    public boolean createGameTable() {
        ser = new SerializableString();
        row = new ListGamesMongo();
        File game = new File("GAMES_TABLE");
        game.mkdirs();

        return false;
    }





    @Override
    public void setGameAsStarted(String s) {


        ListGamesMongo foundGame = getGame(s);

        if(foundGame != null) {
            foundGame.setStaretedGame(true);

            String path = "GAMES_TABLE" + File.separator + foundGame.getGameName();
            String filename = path + File.separator + "game.txt";

            File gameFile = new File(filename);
            gameFile.getParentFile().mkdirs();
            foundGame.setStaretedGame(true);
            row = foundGame;
            writeToFile(filename);
        }else{
            System.out.println("GAME NOT FOUND");
        }


    }


    private ListGamesMongo getGame(String name){
        File gameDir = new File("GAMES_TABLE");
        File[] filesinDir = gameDir.listFiles();

        ArrayList<String> results = new ArrayList<>();

        for (File folder: filesinDir){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(folder + File.separator + "game.txt"));

                String input = reader.readLine();
                ListGamesMongo gam = ser.getDeserializableListGamesMongo(input);

                if (gam.getGameName().equals(name)){
                    return gam;
                }

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }
        return null;
    }

    public List<String> returnSpecificGameListing(String s) {
        ListGamesMongo gam = getGame(s);
        List<String> listtoreturn = new ArrayList<>();

        listtoreturn.add(gam.getGameName());
        listtoreturn.add(gam.getHost());
        listtoreturn.add(Integer.toString(gam.getMaxNumberPlayers()));
        listtoreturn.add(Integer.toString(gam.getNumberOfPlayers()));
        if (gam.getPublicGames() == false){
            listtoreturn.add("0");
        }else{
            listtoreturn.add("1");
        }
        if(gam.isStaretedGame() == false){
            listtoreturn.add("0");
        }else{
            listtoreturn.add("1");
        }
        listtoreturn.add(gam.getGamePassword());
        return listtoreturn;




    }

    public List<String> getAllGameListings() {
        File gameDir = new File("GAMES_TABLE");
        File[] filesinDir = gameDir.listFiles();

        ArrayList<String> results = new ArrayList<>();

        for (File folder: filesinDir){

            results.add(loadGameFolder(folder));
        }
        return results;


    }


    public String loadGameFolder (File gameFolder){

        try{
            BufferedReader reader = new BufferedReader(new FileReader(gameFolder + File.separator +  "game.txt"));

            String input = reader.readLine();

            ListGamesMongo gam = ser.getDeserializableListGamesMongo(input);

            return gam.getGameName();

        } catch(Exception e){
            e.printStackTrace();
            return  null;
        }

    }


    public void deleteALLGameListing() {

        File directory = new File("GAMES_TABLE");

        //make sure directory exists
        if(!directory.exists()){

            System.out.println("Directory does not exist.");
            System.exit(0);

        }else{

            try{

                delete(directory);

            }catch(IOException e){
                e.printStackTrace();
                System.exit(0);
            }
        }

        //PrintWriter writer = new PrintWriter();

    }


    public static void delete(File file)
            throws IOException{

        if(file.isDirectory()){

            //directory is empty, then delete it
            if(file.list().length==0){

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            }else{

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if(file.list().length==0){
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        }else{
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }







    public void createGameListing(List<String> list) {
        row = new ListGamesMongo();

        row.setGameName(list.get(0));
        row.setHost(list.get(1));

        row.setMaxNumberPlayers(Integer.parseInt(list.get(2)));
        row.setNumberOfPlayers(Integer.parseInt(list.get(3)));
        if (list.get(4).equals("false")){
            row.setPublicGames(false);
        }else{
            row.setPublicGames(true);
        }
        if (list.get(5).equals("false")){
            row.setStaretedGame(false);
        }else{
            row.setStaretedGame(true);
        }
        row.setGamePassword(list.get(6));


        String path = "GAMES_TABLE" + File.separator + list.get(0);
        String filename = path + File.separator + "game.txt";


        File gameFile = new File(filename);
        gameFile.getParentFile().mkdirs();
        writeToFile(filename);

    }



    public void writeToFile(String in){
        String code = ser.getSerializableListGamesMongo(row);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(in));
            bw.write(code);
            bw.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Sets the row to the object read from the file
     */
    public void readFromFile(){
        try {
            BufferedReader brTest = new BufferedReader(new FileReader("GAME_TABLE_TICKET_TO_RIDE.txt"));
            String txt = brTest.readLine();
            row = ser.getDeserializableListGamesMongo(txt);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
