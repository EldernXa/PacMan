package GraphicsEngine;

import AnimationEngine.Animation;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MouvingObject extends VisualObject{
    private final ArrayList<Action> listAction;
    private final Animation animation;
    public MouvingObject(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        animation = new Animation(path, 4);
        super.getGameImage().setImage(animation.getInitImage());
        listAction = new ArrayList<>();
    }

    public void nextImage(int dir){
        super.getGameImage().setImage(animation.nextImage(dir));
    }

    public void previousImage(int dir){
        super.getGameImage().setImage(animation.previousImage(dir));
    }

    public void addAction(Action action){
        this.listAction.add(action);
    }


}
