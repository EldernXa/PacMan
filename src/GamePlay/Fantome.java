package GamePlay;

import GraphicsEngine.ActionContinue;
import GraphicsEngine.Coordinate;
import GraphicsEngine.MouvingObject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Fantome extends MouvingObject {
    private float valueTps = (float)100;

    public Fantome(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        addAction(new ActionContinue(getGameImage(), scene, "o", 0, -getGameImage().getValueMove(), 3, "Monter", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "l", 0, getGameImage().getValueMove(), 1, "Descendre", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "k", -getGameImage().getValueMove(), 0, 2, "Gauche", valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "m", getGameImage().getValueMove(), 0, 0, "Droite", valueTps, this));
    }

}
