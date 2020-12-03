package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.animation.Timeline;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (kimagure)
 * Le fantôme bleu sort après un certain temps de la base  ,
 * il se met à chasser Pac-man que quand celui-ci à ramassé assez de point,
 * pour la chasse il se sert à la fois de la position de Pac-man et du fantôme rouge***/



public class FantomeCalcul extends Fantome{
    private boolean fuite;
    private final float startTime = (float)10 ;
    private int counterPacmanPoint ;
    private Timeline timeline;
    private MapPacman mapPacman;
    private PacMan pacMan;
    private FantomeChasseur fantomeRouge;
    private Coordinate coordinateScatter1 = new Coordinate(419,419);



    public FantomeCalcul(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan, FantomeChasseur fantomeRouge) {
        super(path, coordinate, scene, map, pacMan);
        this.mapPacman= map;
        this.pacMan = pacMan;
        this.fantomeRouge = fantomeRouge;
        this.fuite = false;

    }

    public float getStartTime() {
        return startTime;
    }

    public int getCounterPacmanPoint() {
        return counterPacmanPoint;
    }

    public void setCounterPacmanPoint(int counterPacmanPoint) {
        this.counterPacmanPoint = counterPacmanPoint;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public MapPacman getMapPacman() {
        return mapPacman;
    }

    public void setMapPacman(MapPacman mapPacman) {
        this.mapPacman = mapPacman;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public FantomeChasseur getFantomeRouge() {
        return fantomeRouge;
    }

    public void setFantomeRouge(FantomeChasseur fantomeRouge) {
        this.fantomeRouge = fantomeRouge;
    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        //if (timeline.getKeyFrames().get(0).getTime().equals(  startTime)){
        if(pacMan.isSuperPacman()){
            setGoal(coordinateScatter1);
        }else {
            setGoal(Transition(fantomeRouge));
        }

            return super.Chase(listOfWalls);
       /* }else {
            setRandomGoal();
            return super.Chase(listOfWalls);
        }*/

    }



}
