package GraphicsEngine;

import ReadFile.ReadFileMapPacman;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class ActionContinue extends Action{

    private float tps;
    private static Timeline timeline;
    private int indTimeline;
    private final MouvingObject mouvingObject;

    public ActionContinue(GameImage image, Scene scene, String carac, char dir, int valueMove, float tps, MouvingObject mouvingObject){
        super(image, scene, carac, dir, valueMove, mouvingObject);
        this.mouvingObject = mouvingObject;
        this.tps = tps;
    }
    public ActionContinue(GameImage image, Scene scene, char dir, int valueMove, float tps, MouvingObject mouvingObject){
        super(image, scene, dir, valueMove, mouvingObject);
        this.mouvingObject = mouvingObject;
        this.tps = tps;
    }

    private boolean collisionImgView(int valueMove, char dir){
        ImageView imgV = new ImageView(new Image(super.getGameImage().getImgView().getImage().getUrl()));
        imgV.setX(super.getGameImage().getImgView().getX());
        imgV.setY(super.getGameImage().getImgView().getY());
        if(dir=='x')
            imgV.setX(imgV.getX()+valueMove);
        else if(dir=='y')
            imgV.setY(imgV.getY()+valueMove);
        return(collision(imgV, ReadFileMapPacman.visualObjects));
    }

    @Override
    public void doWhenEventOccur(int valueMove, char dir){
        if(!collisionImgView(valueMove, dir)) {
            if (timeline == null)
                timeline = new Timeline();
            VisualObject.stopTimelineParallel();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {
                        super.doWhenEventOccur(valueMove, dir);
                    }
            ));
            indTimeline = VisualObject.addTimeline(timeline, mouvingObject);
            VisualObject.startTimelineParallel();
        }
    }

    @Override
    void doWhenBlock(){
        VisualObject.stopTimelineParallel();
        VisualObject.removeTimeline(indTimeline);
        VisualObject.startTimelineParallel();
    }

}
