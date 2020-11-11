package GraphicsEngine;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFileMapPacman {


    String file[];
    String mapSize;
    ArrayList<UnmouvingObj> unmouvingObjs = new ArrayList<>();

    public ReadFileMapPacman() {
        ArrayList<String> line = new ArrayList<>();
        Path path = Paths.get("./data/Map/PacmanMap1");
        try {
            this.file = Files.readString(path).split("\n");
            mapSize = file[0];

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void decrypt() {


        for (int i = 1; i < file.length; i++) {


        }


        String line[] = file[1].split(" ");
        System.out.println(line[0]);
        try {
            Class test = Class.forName("GraphicsEngine." + line[0]);
/*

            // I need an array as follows to describe the signature
            Class[] parameters = new Class[] {String.class , Coordinate.class, Scene.class, Pane.class};

// Now I can get a reference to the right constructor
            Constructor constructor = test.getConstructor(parameters);

// And I can use that Constructor to instantiate the class
            Object o = constructor.newInstance(null,null,null,null);

// To prove it's really there...

*/

            System.out.println(test.getClass());

            System.out.println(test.getConstructor().getParameterCount());

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
