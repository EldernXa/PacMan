package GraphicsEngine;

import GameEngine.Difficulte;
import ReadFile.ReadFileMap;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Cette classe permet grace aux classe qui en hérite de créer la map d'un jeu et de l'afficher
 */
public abstract class Map {
    private Stage stage;
    public static ArrayList<VisualObject> visualObjects = new ArrayList<>();
    private Scene mapScene;
    private Pane mapPane;
    private double abscMax;
    private double ordMax;
    private double carreaux;
    private double epaisseurMur;
    private double longueurMur;
    static public Difficulte diff;
    private static final ArrayList<EventHandler<KeyEvent>> listEventHandler = new ArrayList<>();
    private static boolean multi;

    public Map(){
    }

    /**
     * Constructeur eprmettant d'initialiser la map, de la créer, de dimensioner la scene sur laquelle elle sera
     * et de mettre cette scene en tant que scene courant d'un stage, et de redimensionner le stage afin qu'il
     * soit de la dimensiond de la scene, ce constructeur prend en argument un lien vers un dossier afin de savoir
     * ou se trouve le fichier d'initialisation de la map
     * @param stage
     * @param mapFolderPath
     * @param carreaux
     * @param epaisseurMur
     * @param longueurMur
     * @param multi
     */
    public Map(Stage stage, String mapFolderPath,double carreaux, double epaisseurMur, double longueurMur, boolean multi){
        this.carreaux = carreaux;
        this.epaisseurMur = epaisseurMur;
        this.longueurMur = longueurMur;
        this.stage = stage;
        initReadFile(mapFolderPath);
        this.mapPane = initMapPane();
        creationDeMap();
        mapScene = initMapScene();
        stage.setScene(mapScene);
        stage.centerOnScreen();
        stage.sizeToScene();
        this.multi = multi;
    }

    /**
     * Constructeur eprmettant d'initialiser la map, de la créer, de dimensioner la scene sur laquelle elle sera
     * et de mettre cette scene en tant que scene courant d'un stage, et de redimensionner le stage afin qu'il
     * soit de la dimensiond de la scene, ce constructeur prend en argument une difficulté grace à laquelle on peut
     * récupérer le nom du fichier ou se trouve l'initialisation de la map
     * @param stage
     * @param difficulte
     * @param carreaux
     * @param epaisseurMur
     * @param longueurMur
     * @param multi
     */
    public Map(Stage stage, Difficulte difficulte,double carreaux, double epaisseurMur, double longueurMur, boolean multi){
        diff = difficulte;
        String mapFolderPath = "./data/Map/" + diff.getName() + "_";
        this.carreaux = carreaux;
        this.epaisseurMur = epaisseurMur;
        this.longueurMur = longueurMur;
        this.stage = stage;
        initReadFile(mapFolderPath);
        this.mapPane = initMapPane();
        creationDeMap();
        mapScene = initMapScene();
        stage.setScene(mapScene);
        stage.centerOnScreen();
        stage.sizeToScene();
        this.multi = multi;
    }

    public static void addEventHandler(EventHandler<KeyEvent> eventHandler){
        listEventHandler.add(eventHandler);
    }

    public static void clearEventHandler(){
        listEventHandler.clear();
    }

    public static ArrayList<EventHandler<KeyEvent>> getListEventHandler(){
        return listEventHandler;
    }

    /**
     * Permet aux classes fille de choisir comment creer la map
     */
    public abstract void creationDeMap();

    /**
     * Permet aux classes fille de choisir comment initialiser la Scene
     * @return
     */
    public abstract Scene initMapScene();

    /**
     * Permet aux classes fille de choisir comment elles vont initialiser le ReadFile
     * @param mapFolderPath
     */
    public abstract void initReadFile(String mapFolderPath);

    /**
     * Permet aux classes filles de chosiir comment initialiser le Pane
     * @return
     */
    public abstract Pane initMapPane();

    /**
     * Permet aux classes fille de chosiir quel ReadFile elle souhaitent
     * @return
     */
    public abstract ReadFileMap getReadFileMap();

    /**
     * Permet de récupérer La longueur d'un carreux
     * @return
     */
    public double getCarreaux(){
        return this.carreaux;
    }

    /**
     * Permet de récupérer l'épasseur d'un mur
     * @return
     */
    public double getEpaisseurMur(){
        return this.epaisseurMur;
    }

    /**
     * Permet de récupérer la longueur d'un mur
     * @return
     */
    public double getLongueurMur() {
        return this.longueurMur
                ;
    }

    /**
     * Permet de récupérer l'abscisse Maximale
     * @return
     */
    public double getAbscMax() {
        return getReadFileMap().getAbscMax();
    }

    /**
     * Permet de récupérer l'ordonnée maxiamle
     * @return
     */
    public double getOrdMax() {
        return getReadFileMap().getOrdMax();
    }

    /**
     * Permet de récupérer le Pane
     * @return
     */
    public Pane getMapPane() {
        return mapPane;
    }

    /**
     * Permet de récupérer La scene
     * @return
     */
    public Scene getMapScene() {
        return mapScene;
    }

    /**
     * Permet d'affecter la Scene
     * @param mapScene
     */
    public void setMapScene(Scene mapScene) {
        this.mapScene = mapScene;
    }

    /**
     * Permet de récupérer l'array Liste de VIsualObject
     * @return
     */
    public static ArrayList<VisualObject> getVisualObjects() {
        return visualObjects;
    }

    /**
     * Permet d'affecter la liste de VisualObject
     * @param visualObjects
     */
    public static void setVisualObjects(ArrayList<VisualObject> visualObjects) {
        Map.visualObjects = visualObjects;
    }

    /**
     * Permet de récupérer le Stage
     * @return
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Permet d'affecter le Stage
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Permet de savoir si on veut l'option mutli ou non
     * @return
     */
    public static boolean isMulti() {
        return multi;
    }
}
