package GamePlay.PacMan;

import GraphicsEngine.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

public class ActionContinueFantome extends Action {

    private float tps;
    private Timeline timeline;
    private int indTimeline;
    private final MouvingObject mouvingObject;
    private final Map map;


    public ActionContinueFantome(GameImage image, Scene scene, float tps, MouvingObject mouvingObject, Map map){
        super(image, scene, mouvingObject);
        this.mouvingObject = mouvingObject;
        this.tps = tps;
        this.map = map;

        doWhenEventOccur(super.getDir());



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
    public void doWhenBlock(){
        VisualObject.stopTimelineParallel();
        VisualObject.removeTimeline(indTimeline);
        VisualObject.startTimelineParallel();
    }

    /*public int returnDir(int dir) {


        try {
            int temp = ((Fantome) mouvingObject).Chase(mouvingObject.getGameImage().getCoordinate(), map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls());
            return temp;
        } catch (Exception e) {
            System.out.println("Dommage");
        }

                        /*for (Action action: mouvingObject.getListAction()
                        ) {
                            if(dir != temp){

                                super.doWhenEventOccur(temp);
                            }else{
                                super.doWhenEventOccur(dir);
                            }

                        }
        return -1;
    }*/

}
