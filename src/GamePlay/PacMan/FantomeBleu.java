package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.util.ArrayList;

/***Personnalité: (kimagure)
 * Le fantôme bleu sort après un certain temps de la base  ,
 * il se met à chasser Pac-man que quand celui-ci à ramassé assez de point,
 * pour la chasse il se sert à la fois de la position de Pac-man et du fantôme rouge***/



public class FantomeBleu extends Fantome{
    private final float startTime = (float)10 ;
    private int counterPacmanPoint ;
    private Timeline timeline;
    private MapPacman mapPacman;
    private PacMan pacMan;
    private FantomeRouge fantomeRouge;

    public FantomeBleu(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan, FantomeRouge fantomeRouge) {
        super(path, coordinate, scene, map, pacMan);
        this.mapPacman= map;
        this.pacMan = pacMan;
        this.fantomeRouge = fantomeRouge;

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

    public FantomeRouge getFantomeRouge() {
        return fantomeRouge;
    }

    public void setFantomeRouge(FantomeRouge fantomeRouge) {
        this.fantomeRouge = fantomeRouge;
    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        if (timeline.getKeyFrames().get(0).getTime().equals(  startTime)){
            setGoal(Transition(fantomeRouge));
            return super.Chase(listOfWalls);
        }else {
            setRandomGoal();
            return super.Chase(listOfWalls);
        }

    }


}
