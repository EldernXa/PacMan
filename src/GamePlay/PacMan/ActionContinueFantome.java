package GamePlay.PacMan;

import GraphicsEngine.*;
import GraphicsEngine.Maps.Map;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

public class ActionContinueFantome extends Action {

    private float tps;
    private Timeline timeline;
    private int indTimeline;
    private final Map map;



    public ActionContinueFantome(GameImage image, Scene scene, float tps, MouvingObject mouvingObject, Map map, int dir){
        super(image, scene, mouvingObject,dir);
        this.tps = tps;
        this.map = map;

        //Test(map,scene,tps);
        doWhenEventOccur(dir);



    }



    @Override
    public void doWhenEventOccur(int dir){
       // System.out.println(dir);
            if (timeline == null)
                timeline = new Timeline();
            VisualObject.stopTimelineParallel();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {
                        getGameImage().getCoordinate().affichageCoord();
                        System.out.println(dir);
                        System.out.println("Here");

                        super.doWhenEventOccur(dir);

                    }
            ));
            indTimeline = VisualObject.addTimeline(timeline, super.getMouvingObject());
            VisualObject.startTimelineParallel();
    }
    /*public void Test(Map map,Scene scene,float valueTps){
        if (timeline == null)
            timeline = new Timeline();
        VisualObject.stopTimelineParallel();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(valueTps),
                temps -> {
                    int temp = ((Fantome)getMouvingObject()).Chase(((Fantome)getMouvingObject()).getGoal(),map.getWrongCoorFromReal(((Fantome)getMouvingObject()).getFantome()).getListOfWalls());
                    System.out.println(temp);
                    switch (temp){
                        case 0:
                            new ActionContinueFantome(getGameImage(),scene,valueTps,getMouvingObject(),map,0,getGameImage().getValueMove(),0);
                            break;
                        case 1:
                            new ActionContinueFantome(getGameImage(),scene,valueTps,getMouvingObject(),map,1,0,-getGameImage().getValueMove());
                            break;
                        case 2:
                            new ActionContinueFantome(getGameImage(),scene,valueTps,getMouvingObject(),map,2,-getGameImage().getValueMove(),0);
                            break;
                        case 3:
                            new ActionContinueFantome(getGameImage(),scene,valueTps,getMouvingObject(),map,3,0,getGameImage().getValueMove());
                            break;
                    }
                    //doWhenEventOccur(dir);

                }
        ));
        indTimeline = VisualObject.addTimeline(timeline, getMouvingObject());
        VisualObject.startTimelineParallel();
    }*/

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
