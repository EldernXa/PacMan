package GamePlay.PacMan;

import GraphicsEngine.*;
import PhysicsEngine.ActionContinue;
import PhysicsEngine.MouvingObject;
import ReadFile.ReadFileCommandes;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class PacMan extends MouvingObject {

    private final int nbViesMax = 3;
    private SimpleIntegerProperty nbVies_restantes;
    private SimpleIntegerProperty nbPoints;
    private final int nbPointsMapMax;
    private int nbPointsMap;
    private float valueTps = (float)10;
    private Coordinate coordinate;
    private ChangeListener<Number> changeListenerVies;
    private ChangeListener<Number> changeListenerPoint;
    private final Stage stage;
    private final ReadFileCommandes pacmanControle;
    private boolean superPacman;

    public PacMan(String path, Coordinate coordinate, Scene scene, int nbPointsMapMax, Stage stage){
        super(path, coordinate, scene);
        superPacman = false;
        this.stage = stage;
        nbPoints = new SimpleIntegerProperty(0);
        this.nbPointsMapMax = nbPointsMapMax;
        this.nbPointsMap = 0;
        nbVies_restantes = new SimpleIntegerProperty(nbViesMax);
        pacmanControle = new ReadFileCommandes("./data/Controles/Pacman/controles.txt",true);
        for(int i = 0; i<pacmanControle.getDirectionSolo().size(); i++){

            addAction(new ActionContinue(scene,
                    pacmanControle.getToucheSolo().get(i), pacmanControle.getxCoordSolo().get(i),
                    pacmanControle.getyCoordSolo().get(i), i, pacmanControle.getDirectionSolo().get(i),
                    valueTps, this));
        }
        /*addAction(new ActionContinue(getGameImage(), scene, "z", 0, -getGameImage().getValueMove(), 3, "Monter", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "s", 0, getGameImage().getValueMove(), 1, "Descendre", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "q", -getGameImage().getValueMove(), 0, 2, "Gauche", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "d", getGameImage().getValueMove(), 0, 0, "Droite", valueTps, this));*/
        this.coordinate = coordinate;
    }

    public void incrementPoints(){
        nbPointsMap++;
        if(getNbPointsMap()==getNbPointsMapMax()){
            ConclusionPacman conclusion = new ConclusionPacman(stage,true,nbPoints.get());
        }
    }

    public int getNbPointsMapMax(){
        return nbPointsMapMax;
    }

    public int getNbPointsMap(){
        return nbPointsMap;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = new Coordinate(coordinate.getX(),coordinate.getY());
        super.getImageView().setX(coordinate.getX());
        super.getImageView().setY(coordinate.getY());
        super.move(coordinate.getX(),coordinate.getY());
    }
    public void death(){
        setCoordinate(super.getGameImage().getCoordInit());
        diminueVies();
        super.initAnimation();

    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getNbVies() {
        return nbViesMax;
    }

    public int getNbVies_restantes() {
        return nbVies_restantes.get();
    }


    public int getNbPoints() {
        return nbPoints.get();
    }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        if(visualObjects!=null && visualObjects.getClass()==Fantome.class){
            Fantome fantome = ((Fantome) visualObjects);
            if(isSuperPacman()){
                fantome.death();
            }
            else {
                death();
            }

        }
        return false;
    }
    public void ajoutPoint(int nb){

        nbPoints.set(nbPoints.get()+nb);
    }



    public void diminueVies() {

         nbVies_restantes.set(nbVies_restantes.get()-1);

    }

    public void setListenerPoint(ChangeListener<Number> changeListener){
        changeListenerPoint = changeListener;
        nbPoints.addListener(changeListenerPoint);
    }
    public void setListenerNbVies(ChangeListener<Number> changeListener){
        changeListenerVies=changeListener;
        nbVies_restantes.addListener(changeListenerVies);
    }
    public void removeListenerPoint(){
        if(changeListenerPoint != null)
            nbPoints.removeListener(changeListenerPoint);
        changeListenerPoint = null;
    }
    public void removeListenerVies(){
        if(changeListenerVies != null)
            nbVies_restantes.removeListener(changeListenerVies);
        changeListenerVies = null;
    }

    public boolean isSuperPacman() {
        return superPacman;
    }
    public void superPacman(){
        setSuperPacman(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //mettre la nouvelle image du superpacman
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setSuperPacman(false);
            }
        }).start();

    }

    public void setSuperPacman(boolean superPacman) {
        this.superPacman = superPacman;
    }
}
