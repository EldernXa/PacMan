package GamePlay.PacMan;

import PhysicsEngine.Action;
import GraphicsEngine.GameImage;
import GraphicsEngine.Map;
import PhysicsEngine.ActionContinue;
import PhysicsEngine.MouvingObject;
import GraphicsEngine.VisualObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

public class ActionContinueFantome extends Action {

    private float tps;
    private Timeline timeline;
    private int indTimeline;
    private MouvingObject mouvingObject;
    private final Map map;
    private int dir;



    public ActionContinueFantome(GameImage image, Scene scene, float tps, MouvingObject mouvingObject, int dir,Map map){
        super(image, scene, mouvingObject,dir);
        this.tps = tps;
        this.mouvingObject = mouvingObject;
        this.map = map;
        this.dir = dir;
        doWhenEventOccur(dir);



    }

    @Override
    public void doWhenEventOccur(int dir) {
        if(collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())){
            System.out.println("test");
        }
        if (!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())) {
            if (timeline == null)
                timeline = new Timeline();
            VisualObject.stopTimelineParallel();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {
                        System.out.println(!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY()));
                        getGameImage().getCoordinate().affichageCoord();
                        System.out.println(dir);


                        super.doWhenEventOccur(dir);
                        if(collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())){
                            doWhenBlock(); // A modifier
                        }

                    }
            ));
            indTimeline = VisualObject.addTimeline(timeline, super.getMouvingObject());
            VisualObject.startTimelineParallel();
        }
    }


    @Override
   public void doWhenBlock(){
        VisualObject.stopTimelineParallel();
        VisualObject.removeTimeline(indTimeline);
        VisualObject.startTimelineParallel();
        System.out.println("Bloqué");
    }



}
