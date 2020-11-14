package ReadFile;

import GraphicsEngine.Coordinate;
import GraphicsEngine.Decor;
import GraphicsEngine.VisualObject;
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


    private  String file[];
    private ArrayList<String> line;
    private int width;
    private int height;
    public static ArrayList<VisualObject> visualObjects = new ArrayList<>();
    private Scene scene;
    private Pane pane;

    public ReadFileMapPacman(Scene scene, Pane pane) {
        this.scene = scene;
        this.pane = pane;
        line = new ArrayList<>();
        Path path = Paths.get("./data/Map/PacmanMap1");
        try {
            this.file = Files.readString(path).split("\n");
            String mapSize[] = file[0].split("\\s+");;
            width = Integer.parseInt(mapSize[0]);
            height = Integer.parseInt(mapSize[1]);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void decrypt() {

        for(int i = 1 ; i < file.length ; i++) {

            try {
                String line[] = file[i].split("\\s+");
                if(line.length!=0) {
                    if (line[0].compareTo("") != 0 && line[0].charAt(0) != '/') {
                        Class aClass = Class.forName("GraphicsEngine." + line[0]);
                        Class[] parameters = new Class[]{String.class, Coordinate.class, Scene.class, Pane.class};
                        Constructor constructor = aClass.getConstructor(parameters);
                        Object o = constructor.newInstance(line[3], new Coordinate(Integer.parseInt(line[1]), Integer.parseInt(line[2])), scene, pane);

                        visualObjects.add((Decor) o);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


    public String[] getFile() {
        return file;
    }

    public ArrayList<String> getLine() {
        return line;
    }



    public ArrayList<VisualObject> getVisualObjects() {
        return visualObjects;
    }

    public Scene getScene() {
        return scene;
    }

    public Pane getPane() {
        return pane;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
