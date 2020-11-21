package GamePlay;

import GraphicsEngine.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PacMan extends MouvingObject {

    private final int nbViesMax = 3;
    private SimpleIntegerProperty nbVies_restantes;
    private SimpleIntegerProperty nbPoints;
    private float valueTps = (float)10;
    private Coordinate coordinate;
    private ChangeListener<Number> changeListenerVies;
    private ChangeListener<Number> changeListenerPoint;

    public PacMan(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        nbPoints = new SimpleIntegerProperty(0);
        nbVies_restantes = new SimpleIntegerProperty(nbViesMax);
        addAction(new ActionContinue(getGameImage(), scene, "z", 0, -getGameImage().getValueMove(), 3, "Monter", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "s", 0, getGameImage().getValueMove(), 1, "Descendre", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "q", -getGameImage().getValueMove(), 0, 2, "Gauche", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "d", getGameImage().getValueMove(), 0, 0, "Droite", valueTps, this));
        this.coordinate = coordinate;
    }


    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = new Coordinate(coordinate.getX(),coordinate.getY());
        super.getImageView().setX(coordinate.getX());
        super.getImageView().setY(coordinate.getY());
        super.getGameImage().move(coordinate.getX(),coordinate.getY());
    }
    public void death(){
        setCoordinate(super.getGameImage().getCoordInit());
        diminueVies();
        super.initAnimation();

    }

    public PacMan(String path, Coordinate coordinate, Scene scene, int nbViesMax, int nbVies) {
        super(path, coordinate, scene);
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
        return false;
    }
    public void ajoutPoint(){

        nbPoints.set(nbPoints.get()+1);
        System.out.println(nbPoints);
    }


    public void diminueVies() {

         nbVies_restantes.set(nbVies_restantes.get()-1);

        System.out.println(nbVies_restantes);

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
}
