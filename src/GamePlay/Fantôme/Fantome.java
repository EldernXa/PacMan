package GamePlay.Fantôme;

import GraphicsEngine.ActionContinue;
import GraphicsEngine.Coordinate;
import GraphicsEngine.MouvingObject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Fantome extends MouvingObject {
    private float valueTps = (float)100;

    public Fantome(String path, Coordinate coordinate, Scene scene, Pane pane){
        super(path, coordinate, scene, pane);
        addAction(new ActionContinue(getGameImage(), scene, "z", 'y', -getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "s", 'y', getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "q", 'x', -getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "d", 'x', getGameImage().getValueMove(), valueTps, this));
    }

}
