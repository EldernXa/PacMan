package GraphicsEngine;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MouvingObject extends VisualObject{
    private final ArrayList<Action> listAction;
    public MouvingObject(String path, Coordinate coordinate, Scene scene, Pane pane){
        super(path, coordinate, scene, pane);
        listAction = new ArrayList<>();
    }

    public void addAction(Action action){
        this.listAction.add(action);
    }


}
