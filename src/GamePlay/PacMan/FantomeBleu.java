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

    public FantomeBleu(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);


    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        if (timeline.getKeyFrames().get(0).getTime().equals(  startTime)){

        }else {
            int nulL = -1;
            return nulL;
        }
        int nulL = -1;
        return nulL;
    }
}
