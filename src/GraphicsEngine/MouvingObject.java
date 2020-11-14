package GraphicsEngine;

import AnimationEngine.Animation;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MouvingObject extends VisualObject{
    private final ArrayList<Action> listAction;
    private final Animation animation;
    public MouvingObject(String path, Coordinate coordinate, Scene scene, Pane pane){
        super(path, coordinate, scene, pane);
        animation = new Animation(path);
        super.getGameImage().setImage(animation.getInit());
        listAction = new ArrayList<>();
    }

    public void addSprite(String strImg){
        animation.addImg(strImg);
    }

    public void nextImg(){
        super.getGameImage().setImage(animation.nextImage());
    }

    public void previousImg(){
        super.getGameImage().setImage(animation.previousImage());
    }

    public void addAction(Action action){
        this.listAction.add(action);
    }


}
