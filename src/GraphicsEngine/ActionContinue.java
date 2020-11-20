package GraphicsEngine;

//import ReadFile.ReadFileMapPacman;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class ActionContinue extends Action{

    private float tps;
    private Timeline timeline;
    private int indTimeline;
    private final MouvingObject mouvingObject;

    public ActionContinue(GameImage image, Scene scene, String carac, double x, double y, int dir, String nameAction, float tps, MouvingObject mouvingObject){
        super(image, scene, carac, x, y, dir, nameAction, mouvingObject);
        this.mouvingObject = mouvingObject;
        this.tps = tps;
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
                        if(mouvingObject.getActionNext()!=null &&mouvingObject.verifActionNext(getGameImage().getCoordinate().getX() + mouvingObject.getActionNext().getX(), getGameImage().getCoordinate().getY() + mouvingObject.getActionNext().getY())){
                            if(((ActionContinue)mouvingObject.getActualAction())!=null) {
                                VisualObject.removeTimeline(((ActionContinue) mouvingObject.getActualAction()).getIndTimeline());
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
                                VisualObject.stopTimelineParallel();
                                VisualObject.removeTimeline(getIndTimeline());
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
    void doWhenBlock(){
        VisualObject.stopTimelineParallel();
        VisualObject.removeTimeline(indTimeline);
        VisualObject.startTimelineParallel();
        mouvingObject.setActionNext(null);
    }

}
