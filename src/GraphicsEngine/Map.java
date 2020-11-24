package GraphicsEngine.Maps;

import GamePlay.Fantome;
import GamePlay.PacMan;
import GamePlay.Point;
import GamePlay.ScorePacman;
import GraphicsEngine.Coordinate;
import GraphicsEngine.Decor;
import GraphicsEngine.ImageViewSizePos;
import GraphicsEngine.VisualObject;
import ReadFile.PosMursAssocies;
import ReadFile.ReadFileMap;
import ReadFile.ReadFileMapPacman;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public abstract class Map {
    private Stage stage;
    public static ArrayList<VisualObject> visualObjects = new ArrayList<>();
    private Scene mapScene;
    private Pane mapPane;

    public Map(){
    }


    public Map(Stage stage, String mapFolderPath){
        this.stage = stage;
        initReadFile(mapFolderPath);
        this.mapPane = initMapPane();
        creationDeMap();
        mapScene = initMapScene();
        stage.setScene(mapScene);
        stage.centerOnScreen();
        stage.sizeToScene();
    }

    public abstract void creationDeMap();
    public abstract Scene initMapScene();
    public abstract void initReadFile(String mapFolderPath);
    public abstract Pane initMapPane();

    public Pane getMapPane() {
        return mapPane;
    }

    public Scene getMapScene() {
        return mapScene;
    }

    public void setMapScene(Scene mapScene) {
        this.mapScene = mapScene;
    }

    public static ArrayList<VisualObject> getVisualObjects() {
        return visualObjects;
    }

    public static void setVisualObjects(ArrayList<VisualObject> visualObjects) {
        Map.visualObjects = visualObjects;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
