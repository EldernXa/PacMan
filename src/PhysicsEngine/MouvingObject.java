package PhysicsEngine;

import AnimationEngine.Animation;
import GraphicsEngine.Coordinate;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

import java.util.ArrayList;

public abstract class MouvingObject extends VisualObject {
    private final ArrayList<Action> listAction;
    private Action actionNext;
    private Action actualAction;
    private final Animation animation;
    private int valueAnimate;
    private int dir;

    public MouvingObject(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        dir = 0;
        actionNext = null;
        actualAction = null;
        animation = new Animation(path);
        super.getGameImage().setImage(animation.getInitImage());
        listAction = new ArrayList<>();
    }

    public void setDir(int dir){
        this.dir = dir;
    }

    public int getDir(){
        return dir;
    }

    public ArrayList<Action> getListAction() {
        return listAction;
    }

    public void initAnimation(){
        super.getGameImage().setImage(animation.getInitImage());
    }

    public void setActionNext(Action action){
        actionNext = action;
    }

    public void setActualAction(Action action){
        actualAction = action;
        if(actualAction!=null)
            setDir(actualAction.getDir());
    }

    public Action getActualAction(){
        return actualAction;
    }

    public boolean verifActionNext(double x, double y){
        if(actionNext!=null){
            return !actionNext.collisionImgView(x, y);
        }
        return true;
    }

    public void clearListAction(){
        listAction.clear();
    }

    public Action getActionNext(){
        return actionNext;
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

    public void incrementTpsAnimate(int value){
        valueAnimate = value;
    }

    public int getTpsAnimate(){
        return valueAnimate;
    }

    public void move(double x, double y){
        super.getGameImage().getCoordinate().setX(x);
        super.getGameImage().getCoordinate().setY(y);
        super.getImageView().setX(super.getGameImage().getCoordinate().getX());
        super.getImageView().setY(super.getGameImage().getCoordinate().getY());
    }


}
