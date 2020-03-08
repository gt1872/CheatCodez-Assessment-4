package com.misc;
import com.Kroy;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Json;

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
            FileInputStream f = new FileInputStream(new File("./gamesaves/"+filepath));
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

    public void saveGame(ArrayList objects) {

        try {
            // Create filename of datetime
            String filename = "./gamesaves/"+this.genSaveName();

            // Open/Create the new file save using OutputStream as we are writing
            FileOutputStream f = new FileOutputStream(new File(filename));
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
}
