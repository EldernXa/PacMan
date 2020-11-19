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

    /*public void addSpriteDirRight(String strImg){
        animation.addImgDirRight(strImg);
    }

    public void addSpriteDirLeft(String strImg){
        animation.addImgDirLeft(strImg);
    }

    public void addSpriteDirUp(String strImg){
        animation.addImgDirUp(strImg);
    }

    public void addSpriteDirDown(String strImg){
        animation.addImgDirDown(strImg);
    }*/

    public void nextImage(int dir){
        super.getGameImage().setImage(animation.nextImage(dir));
    }

    public void previousImage(int dir){
        super.getGameImage().setImage(animation.previousImage(dir));
    }

    /*public void nextImgDirRight(){
        super.getGameImage().setImage(animation.nextImageRight());
    }

    public void nextImgDirLeft(){
        super.getGameImage().setImage(animation.nextImageLeft());
    }

    public void nextImgDirUp(){
        super.getGameImage().setImage(animation.nextImageUp());
    }

    public void nextImgDirDown(){
        super.getGameImage().setImage(animation.nextImageDown());
    }

    public void previousImgDirRight(){
        super.getGameImage().setImage(animation.previousImageRight());
    }

    public void previousImgDirLeft(){
        super.getGameImage().setImage(animation.previousImageLeft());
    }

    public void previousImgDirUp(){
        super.getGameImage().setImage(animation.previousImageUp());
    }

    public void previousImgDirDown(){
        super.getGameImage().setImage(animation.previousImageDown());
    }*/

    public void addAction(Action action){
        this.listAction.add(action);
    }


}
