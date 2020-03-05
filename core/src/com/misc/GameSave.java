package com.misc;
import com.Kroy;
import com.badlogic.gdx.Screen;

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy-HH-mm");
        Date date = new Date();
        return formatter.format(date);
    }


    public void loadGame(String filepath) throws IOException {
        FileOutputStream f = new FileOutputStream(new File(filepath));
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.close();
        f.close();
    }

    public void saveGame(ArrayList objects) {

        try {
            String filename = "./"+this.genSaveName();

            FileOutputStream f = new FileOutputStream(new File(filename));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(objects);
            o.close();
            f.close();

        } catch (IOError | IOException e){
            System.out.println(e);
        }
    }
}
