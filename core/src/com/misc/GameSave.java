package com.misc;
import com.Kroy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import jdk.internal.vm.compiler.collections.EconomicMap;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
=====================================================================
                    ADDED FOR ASSESSMENT 4
=====================================================================

 */
public class GameSave {

    private String genSaveName(){
        // Create timestamp based on save time
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy-HH-mm-ss");
        Date date = new Date();
        // Return formatted date
        return formatter.format(date);
    }


    public ArrayList loadGame(String filepath) {
        try {
            System.out.println("./assets/gamesaves/"+filepath);

            FileInputStream f = new FileInputStream(
                    new File(
                        Gdx.files.internal("./assets/gamesaves/"+filepath).path()));
            ObjectInputStream o = new ObjectInputStream(f);

            Object obj = o.readObject();
            ArrayList Aobj = (ArrayList) obj;

            o.close();
            f.close();

            System.out.println(obj.toString());

            return Aobj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveGame(ArrayList objects, String file) {

        try {
            // Create filename of datetime
            String filename = "./assets/gamesaves/"+file+"-"+this.genSaveName();

            deleteOldSave(file);
            // Open/Create the new file save using OutputStream as we are writing
            FileOutputStream f = new FileOutputStream(
                    new File(filename));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write the objects array we made in GameScreen, this allows us to recover the whole array later
            o.writeObject(objects);

            // Close file and streams
            o.close();
            f.close();

        } catch (IOError | IOException e){
            // Output any errors
            System.out.println(e);
        }
    }

    public void deleteOldSave(String file){
        String dirName = "./assets/gamesaves/";

        File fileName = new File(dirName);
        File[] fileList = fileName.listFiles();
        for (File f : fileList) {
            if (f.getName().startsWith(file+"-")) {
                f.delete();
            }
        }
    }

    public ArrayList<String> getSavedFiles(){
        ArrayList<String> files = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            files.add("Empty");
        }

        String dirName = "./assets/gamesaves/";

        File fileName = new File(Gdx.files.internal(dirName).path());
        File[] fileList = fileName.listFiles();

        for (int f=0; f<5; f++){
            try {
                String fname = fileList[f].getName();
                int saveNum = Integer.parseInt(fname.split("-")[0]);
                files.remove(saveNum-1);
                files.add(saveNum-1, fname);

            } catch (Exception e){
                System.out.println(e);
            }
        }


        System.out.println(files);
        return  files;
    }
}
