package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import PhysicsEngine.Action;
import GraphicsEngine.GameImage;
import GraphicsEngine.Map;
import PhysicsEngine.ActionContinue;
import PhysicsEngine.MouvingObject;
import GraphicsEngine.VisualObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class ActionContinueFantome extends Action {

    private float tps;
    private Timeline timeline;
    private int indTimeline;
    private MouvingObject mouvingObject;
    private final MapPacman map;
    private PacMan pacMan;
    private int dir;
    private Coordinate previous;



    public ActionContinueFantome(GameImage image, Scene scene, float tps, MouvingObject mouvingObject, int dir,MapPacman map,PacMan pacMan){
        super(scene, mouvingObject,dir);
        this.tps = tps;
        this.mouvingObject = mouvingObject;
        this.map = map;
        this.dir = dir;
        setPacMan(pacMan);
        //doWhenEventOccur(dir);



    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public Coordinate getPrevious() {
        return previous;
    }

    public void setPrevious(Coordinate previous) {
        this.previous = previous;
    }

    @Override
    public void doWhenEventOccur(int dir) {


        if (!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())) {
            Timeline timeline2 = timeline;
            if (timeline == null)
                timeline = new Timeline();
            VisualObject.stopTimelineParallel();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {



                        setPrevious(getGameImage().getCoordinate());
                        super.doWhenEventOccur(dir);

                        if(asMove()){
                            newMove();
                        }

                       /* if(collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())){
                            //System.out.println("bloqué");
                            doWhenBlock(); // A modifier
                        }*/

                    }
            ));
            indTimeline = VisualObject.addTimeline(timeline, super.getMouvingObject());
            VisualObject.startTimelineParallel();
        }
    }


    @Override
   public void doWhenBlock(){
        VisualObject.stopTimelineParallel();
        VisualObject.removeTimeline(timeline);
        VisualObject.startTimelineParallel();

        //System.out.println(((Fantome)mouvingObject).bestAction(getGameImage().getCoordinate(),map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls()));
        int temp = ((Fantome)mouvingObject).Chase(pacMan.getCoordinate(), map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls());
        Fantome.setDirection(temp);
        //asMove();
        //System.out.println(temp);
        //System.out.println(getX()+" "+getY());
        for(Action a : mouvingObject.getListAction()){
            if(a.getDir()==temp){
                a.doWhenEventOccur(temp);
            }
        }
        //doWhenEventOccur(temp);
    }
    public void newMove(){
            VisualObject.removeTimeline(timeline);
            //System.out.println(((Fantome)mouvingObject).bestAction(getGameImage().getCoordinate(),map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls()));
            int temp = ((Fantome)mouvingObject).Chase(pacMan.getCoordinate(), map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls());
            Fantome.setDirection(temp);
            asMove();
            //System.out.println(temp);
            //System.out.println(getX()+" "+getY());
            doWhenEventOccur(temp);
        }


    @Override
    public void eventAppear(KeyEvent keyEvent, String carac) {
        if (getDir() == Fantome.getDirection()){
            doWhenEventOccur(dir);
        }
    }

    public boolean asMove(){
        try{
            if(!map.getWrongCoorFromReal(getGameImage().getCoordinate()).getPointCoordinate().compare(previous)){
                return true;
            }

        }catch (Exception e){


        }
        return false;


    }
}
