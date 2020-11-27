package PhysicsEngine;

import GraphicsEngine.GameImage;
import GraphicsEngine.VisualObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.LinkedList;


public class ActionContinue extends Action{

    private final float tps;
    private Timeline timeline;
    private int indTimeline;
    private final MouvingObject mouvingObject;
    private final LinkedList<KeyCode> listKeyEvent;

    public ActionContinue(Scene scene, String carac, double x, double y, int dir, String nameAction, float tps, MouvingObject mouvingObject){
        super(scene, carac, x, y, dir, nameAction, mouvingObject);
        listKeyEvent = new LinkedList<>();
        this.mouvingObject = mouvingObject;
        this.tps = tps;
    }

    public Timeline getTimeline(){
        return timeline;
    }
    public int getIndTimeline(){
        return indTimeline;
    }
    @Override
    public void doWhenEventOccur(int dir){
        if (!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())) {
            if (timeline == null)
                timeline = new Timeline();
            VisualObject.stopTimelineParallel();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {
                        mouvingObject.incrementTpsAnimate((mouvingObject.getTpsAnimate()+1)%(int)tps);
                        if(mouvingObject.getActionNext()!=null &&mouvingObject.verifActionNext(getGameImage().getCoordinate().getX() + mouvingObject.getActionNext().getX(), getGameImage().getCoordinate().getY() + mouvingObject.getActionNext().getY())){
                            if(mouvingObject.getActualAction() !=null) {
                                VisualObject.removeTimeline(((ActionContinue) mouvingObject.getActualAction()).getTimeline());
                                mouvingObject.setActualAction(null);
                            }
                            Action newAction = mouvingObject.getActionNext();
                            mouvingObject.setActionNext(null);
                            newAction.doWhenEventOccur(newAction.getDir());
                        }else {
                            if(!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())) {
                                mouvingObject.setActualAction(this);
                                super.doWhenEventOccur(dir);
                            }else{
                                VisualObject.removeTimeline(timeline);
                                mouvingObject.setActualAction(null);
                                mouvingObject.setActionNext(null);
                            }
                        }
                    }
            ));
            indTimeline = VisualObject.addTimeline(timeline, mouvingObject);
            VisualObject.startTimelineParallel();
        }else{
            if(mouvingObject.getActualAction()!=this && mouvingObject.getActualAction()!=null)
                mouvingObject.setActionNext(this);
        }
    }

    @Override
    public void nextImage(int dir){
        if(mouvingObject.getTpsAnimate()%10==0){
            super.nextImage(dir);
        }
    }

    @Override
    public void previousImage(int dir){
        if(mouvingObject.getTpsAnimate()%10==0){
            super.previousImage(dir);
        }
    }

    @Override
    public void doWhenBlock(){
        VisualObject.stopTimelineParallel();
        VisualObject.removeTimeline(timeline);
        //VisualObject.startTimelineParallel();
        mouvingObject.setActionNext(null);
    }

    @Override
    public void eventAppear(KeyEvent keyEvent, String carac){
        if(!listKeyEvent.contains(keyEvent.getCode())){
            listKeyEvent.push(keyEvent.getCode());
            super.eventAppear(keyEvent, carac);
        }
    }

    @Override
    public void runEvent(Scene scene, String carac, int dir){
        super.runEvent(scene, carac, dir);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            listKeyEvent.remove(keyEvent.getCode());
        });
    }

}
