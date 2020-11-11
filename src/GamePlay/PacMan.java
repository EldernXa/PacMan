package GamePlay;

import GraphicsEngine.Action;
import GraphicsEngine.Coordinate;
import GraphicsEngine.MouvingObject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class PacMan extends MouvingObject {

    public PacMan(String path, Coordinate coordinate, Scene scene, Pane pane){
        super(path, coordinate, scene, pane);
        addAction(new Action(getGameImage(), scene, "z", 'y', -getGameImage().getValueMove()));
        addAction(new Action(getGameImage(), scene, "s", 'y', getGameImage().getValueMove()));
        addAction(new Action(getGameImage(), scene, "q", 'x', -getGameImage().getValueMove()));
        addAction(new Action(getGameImage(), scene, "d", 'x', getGameImage().getValueMove()));
    }

}
