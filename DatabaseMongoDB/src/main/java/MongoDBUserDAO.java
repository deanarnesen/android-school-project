import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Tables.ListGamesMongo;
import Tables.ListUserMongo;
import database_plugin.IUserDAO;

public class MongoDBUserDAO implements IUserDAO {

    public MongoDBUserDAO (){
        System.out.println("I am in MongoDB UserDAO");
    }


    private static PrintWriter writer;
    private static FileReader fileReader;
    private SerializableString ser;
    private ListUserMongo row;



    @Override
    public void createUserTable() {
        ser = new SerializableString();
        row = new ListUserMongo();
        File game = new File("USER_TABLE");
        game.mkdirs();

    }

    @Override
    public void clearUser(String s) {
        File directory = new File("USER_TABLE");

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

    public static void delete(File file)throws IOException {

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








    @Override
    public void addCommand(String s, String s1) {

    }

    @Override
    public void addAllCommand(String s) {

    }

    @Override
    public void addGameCommand(String s, String s1) {

    }

    @Override
    public void addCommandForSpecificPlayers(String s, String s1) {

    }

    @Override
    public List<String> getAllUsers() {
        File gameDir = new File("USER_TABLE");
        File[] filesinDir = gameDir.listFiles();

        ArrayList<String> results = new ArrayList<>();

        for (File folder: filesinDir){

            results.add(loadGameFolder(folder));
        }
        return results;




    }

    public String loadGameFolder (File gameFolder){

        try{
            BufferedReader reader = new BufferedReader(new FileReader(gameFolder + File.separator +  "user.txt"));

            String input = reader.readLine();

            ListUserMongo use = ser.getUserCode(input);

            return use.getUsername();

        } catch(Exception e){
            e.printStackTrace();
            return  null;
        }

    }



    @Override
    public void createNewUser(String s, String s1) {

        ListUserMongo user = new ListUserMongo();

        user.setUsername(s);
        user.setPassword(s1);
        user.setIsLoggedIn(1);


        String path = "USER_TABLE" + File.separator + s;
        String filename = path + File.separator + "user.txt";


        File gameFile = new File(filename);
        gameFile.getParentFile().mkdirs();
        writeToFile(filename);
    }

    @Override
    public void clearAllUser() {

    }


    public void writeToFile(String in){
        String code = ser.getSerializableUserMongo(row);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(in));
            bw.write(code);
            bw.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
