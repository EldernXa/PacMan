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
    private final PosMursAssocies posMursAssocies;


    public ActionContinueFantome(GameImage image, Scene scene, String carac, double x, double y, int dir, String nameAction, float tps, MouvingObject mouvingObject,PosMursAssocies posMursAssocies){
        super(image, scene, carac, x, y, dir, nameAction, mouvingObject);
        this.mouvingObject = mouvingObject;
        this.tps = tps;
        this.posMursAssocies = new PosMursAssocies(posMursAssocies.getPointCoordinate(),posMursAssocies.getListOfWalls());

    }


    private boolean collisionImgView(double x, double y){
        ImageView imgV = new ImageView(new Image(super.getGameImage().getImgView().getImage().getUrl()));
        imgV.setX(super.getGameImage().getImgView().getX());
        imgV.setY(super.getGameImage().getImgView().getY());
        imgV.setX(x);
        imgV.setY(y);
        return(collision(imgV));
    }

    public PosMursAssocies getPosMursAssocies() {
        return posMursAssocies;
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
                        int temp = ((Fantome) mouvingObject).Chase(getGameImage().getCoordinate(),getScene(),getPosMursAssocies().getListOfWalls());
                        super.doWhenEventOccur(temp);

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
