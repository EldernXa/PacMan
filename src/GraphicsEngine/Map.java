package GraphicsEngine;

import ReadFile.ReadFileMap;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public abstract class Map {
    private Stage stage;
    public static ArrayList<VisualObject> visualObjects = new ArrayList<>();
    private Scene mapScene;
    private Pane mapPane;
    private double abscMax;
    private double ordMax;
    private double carreaux = 32;
    private double epaisseurMur = 18;
    private double longueurMur = 68;

    public Map(){
    }


    public Map(Stage stage, String mapFolderPath){
        this.stage = stage;
        initReadFile(mapFolderPath);
        this.mapPane = initMapPane();
        creationDeMap();
        mapScene = initMapScene();
        /*Pane pane = new Pane();
        Scene scene = new Scene(pane,500,500);
        stage.setScene(scene);*/
        stage.setScene(mapScene);
        stage.centerOnScreen();
        stage.sizeToScene();
    }

    public abstract void creationDeMap();
    public abstract Scene initMapScene();
    public abstract void initReadFile(String mapFolderPath);
    public abstract Pane initMapPane();
    public abstract ReadFileMap getReadFileMap();


    public double getCarreaux(){
        return this.longueurMur;
    }
    public double getEpaisseurMur(){
        return this.getEpaisseurMur();
    }
    public double getLongueurMur() {
        return this.getLongueurMur();
    }

    public double getAbscMax() {
        return getReadFileMap().getAbscMax();
    }

    public double getOrdMax() {
        return getReadFileMap().getOrdMax();
    }

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
