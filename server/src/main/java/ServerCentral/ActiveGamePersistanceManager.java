package ServerCentral;

import com.bignerdranch.android.shareddata.commands.commandSupport.CommandData;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Vector;

import serverCommands.IServerCommand;
import serverCommands.ServerCommandBuilder;

public class ActiveGamePersistanceManager {
    private static ActiveGamePersistanceManager instance = new ActiveGamePersistanceManager();
    private HashMap<String, Integer> currentNValues;
    private int numberOfCommandsSaved = 5;
    private StaticData staticData;

    private ActiveGamePersistanceManager(){
        currentNValues = new HashMap<String, Integer>();
    }

    public static ActiveGamePersistanceManager GetInstance(){
        return instance;
    }

    public void setNumberOfCommandsSaved(int num){
        this.numberOfCommandsSaved = num;
    }

    public void AddGame(ActiveGameModel gameModel){
        if(staticData != null && !staticData.isLoadingData()) {
            String curDir = System.getProperty("user.dir") + "\\server\\resources\\persistance_files\\";
            String gameFileName = gameModel.getGameName() + "-game.txt";
            String commandsDir = curDir + gameModel.getGameName();
            currentNValues.put(gameModel.getGameName(), 0);
            WriteToFile(curDir + gameFileName, gameModel);
            new File(commandsDir).mkdir();
        }
    }

    public void AddCommand(CommandData data, String gameName){
        if(!staticData.isLoadingData()) {
            String curDir = System.getProperty("user.dir") + "\\server\\resources\\persistance_files\\" + gameName + "\\";
            if (currentNValues.containsKey(gameName)) {
                int curVal = currentNValues.get(gameName);
                curVal += 1;
                currentNValues.remove(gameName);
                currentNValues.put(gameName, curVal);

                WriteToFile(curDir + Integer.toString(curVal) + gameName + "-command.txt", data);
            }
            if (currentNValues.get(gameName) > numberOfCommandsSaved) {
                UpdateSavedInstance(gameName);
                int curVal = 0;
                currentNValues.remove(gameName);
                currentNValues.put(gameName, curVal);
                ClearCommandsForGame(gameName);
            }
        }
    }

    public void ClearCommandsForGame(String gameName){
        String curDir = System.getProperty("user.dir") + "\\server\\resources\\persistance_files\\" + gameName + "\\";
        File dir = new File(curDir);
        if(dir.isDirectory()){
            try {
                FileUtils.cleanDirectory(dir);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void UpdateSavedInstance(String gameName){
        //staticData = StaticData.getInstance();
        String curDir = System.getProperty("user.dir") + "\\server\\resources\\persistance_files\\";
        ActiveGameModel game = staticData.getListActiveGames().get(gameName);
        WriteToFile(curDir + gameName + "-game.txt", game);
        ClearCommandsForGame(gameName);
    }

    private void UpdateAllSaveInstances(){
        HashMap<String, ActiveGameModel> activeGameModels = staticData.getListActiveGames();
        if(activeGameModels != null) {
            for (String name : activeGameModels.keySet()) {
                UpdateSavedInstance(name);
            }
        }
    }

    public void UpdateStaticData(){
        //staticData = StaticData.getInstance();
        staticData.setLoadingData(true);
        currentNValues = new HashMap<>();
        String curDir = System.getProperty("user.dir") + "\\server\\resources\\persistance_files\\";
        ActiveGameModel game;
        File dir = new File(curDir);
        File[] fileListings = dir.listFiles();
        if(fileListings != null){
            for(File child : fileListings){
                if(child.getName().contains("-game.txt")){
                    game = new ActiveGameModel();
                    game = ReadGame(child);
                    if(game != null){
                        staticData.addActiveGame(game);
                        staticData.getListActiveGames().get(game.getGameName()).resetTransients(staticData);
                        currentNValues.put(game.getGameName(), 0);
                    }
                }
            }
            for(File child : fileListings){
                if(child.isDirectory()){
                    ReadChildDirectory(child, child.getName());
                }
            }
        }
        UpdateAllSaveInstances();
        staticData.setLoadingData(false);
    }

    private void ReadChildDirectory(File dir, String gameName){
        CommandData data;
        IServerCommand command;
        for(File child : dir.listFiles()){
            data = ReadCommand(child);
            command = ServerCommandBuilder.buildCommand(data, gameName);
            command.execute();
        }
    }

    private void WriteToFile(String path, Object game){
        try {
            File newFile = new File(path);
            newFile.createNewFile();
            FileOutputStream fileOut =
                    new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private ActiveGameModel ReadGame(File child){
        ActiveGameModel gameModel = null;
        try {
            FileInputStream fileIn = new FileInputStream(child);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gameModel = (ActiveGameModel) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return gameModel;
        } catch (ClassNotFoundException c) {
            System.out.println("ActiveGameModel class not found");
            c.printStackTrace();
            return gameModel;
        }
        return gameModel;
    }

    private CommandData ReadCommand(File child){
        CommandData data = null;
        try {
            FileInputStream fileIn = new FileInputStream(child);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (CommandData) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return data;
        } catch (ClassNotFoundException c) {
            System.out.println("CommandData class not found");
            c.printStackTrace();
            return data;
        }
        return data;
    }

    public void setStaticData(StaticData data){
        this.staticData = data;
    }
}
