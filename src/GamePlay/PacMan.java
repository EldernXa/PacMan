package GamePlay;

import GraphicsEngine.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PacMan extends MouvingObject {

    private final int nbViesMax = 3;
    private int nbVies_restantes;
    private int nbPoints;
    private float valueTps = (float)10;
    private Coordinate coordinate;

    public PacMan(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        nbPoints = 0;
        nbVies_restantes = nbViesMax;
        addAction(new ActionContinue(getGameImage(), scene, "z", 0, -getGameImage().getValueMove(), 3, "Monter", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "s", 0, getGameImage().getValueMove(), 1, "Descendre", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "q", -getGameImage().getValueMove(), 0, 2, "Gauche", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "d", getGameImage().getValueMove(), 0, 0, "Droite", valueTps, this));
        this.coordinate = coordinate;
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
        return nbVies_restantes;
    }


    public int getNbPoints() {
        return nbPoints;
    }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        return false;
    }
    public void ajoutPoint(){
        nbPoints++;
    }


    public void setNbVies_restantes(int nbVies_restantes) {
        this.nbVies_restantes = nbVies_restantes;
    }
}
