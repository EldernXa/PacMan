package GamePlay.PacMan;

import PhysicsEngine.Action;
import GraphicsEngine.GameImage;
import GraphicsEngine.Map;
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
    //private final Map map;


    public ActionContinueFantome(GameImage image, Scene scene, float tps, MouvingObject mouvingObject, int dir){
        super(image, scene, mouvingObject,dir);
        this.tps = tps;
        //this.map = map;

        doWhenEventOccur(dir);



    }

    @Override
    public void doWhenEventOccur(int dir) {
        //System.out.println("Ici");
        //getGameImage().getCoordinate().affichageCoord();
        if (!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())) {
            if (timeline == null)
                timeline = new Timeline();
            VisualObject.stopTimelineParallel();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {
                        //System.out.println(getX());
                        //System.out.println(getY());
                        //System.out.println(map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls().get(0));
                        //System.out.println(map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls());
                        getGameImage().getCoordinate().affichageCoord();
                        System.out.println(dir);

                        super.doWhenEventOccur(dir);

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
