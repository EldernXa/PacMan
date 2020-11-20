package GraphicsEngine;

import GamePlay.Fantome;
import ReadFile.PosMursAssocies;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ActionContinueFantome extends Action{

    private float tps;
    private Timeline timeline;
    private int indTimeline;
    private final MouvingObject mouvingObject;
    private final Map map;


    public ActionContinueFantome(GameImage image, Scene scene, float tps,MouvingObject mouvingObject, Map map){
        super(image, scene, mouvingObject);
        this.mouvingObject = mouvingObject;
        this.tps = tps;
        this.map = map;
        //int temp = ((Fantome) mouvingObject).Chase(getGameImage().getCoordinate(),getScene(),map.getRealCoord().);

        doWhenEventOccur(2);

    }


    private boolean collisionImgView(double x, double y){
        ImageView imgV = new ImageView(new Image(super.getGameImage().getImgView().getImage().getUrl()));
        imgV.setX(super.getGameImage().getImgView().getX());
        imgV.setY(super.getGameImage().getImgView().getY());
        imgV.setX(x);
        imgV.setY(y);
        return(collision(imgV));
    }



    @Override
    public void doWhenEventOccur(int dir){
        if(!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())) {
            if (timeline == null)
                timeline = new Timeline();
            VisualObject.stopTimelineParallel();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {


                        super.doWhenEventOccur(dir);
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
