package GamePlay;

import GraphicsEngine.ActionContinue;
import GraphicsEngine.Conclusion;
import GraphicsEngine.Coordinate;
import GraphicsEngine.MouvingObject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class PacMan extends MouvingObject {

    private final int nbVies = 3;
    private int nbVies_restantes = nbVies;
    private int nbPoints = 0;
    private float valueTps = (float)100;

    public PacMan(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        addAction(new ActionContinue(getGameImage(), scene, "z", 0, -getGameImage().getValueMove(), 3, "Monter", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "s", 0, getGameImage().getValueMove(), 1, "Descendre", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "q", -getGameImage().getValueMove(), 0, 2, "Gauche", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "d", getGameImage().getValueMove(), 0, 0, "Droite", valueTps, this));
    }

    public PacMan(String path, Coordinate coordinate, Scene scene, int nbViesMax, int nbVies) {
        super(path, coordinate, scene);
    }


    public int getNbVies() {
        return nbVies;
    }

    public int getNbVies_restantes() {
        return nbVies_restantes;
    }


    public int getNbPoints() {
        return nbPoints;
    }
}
