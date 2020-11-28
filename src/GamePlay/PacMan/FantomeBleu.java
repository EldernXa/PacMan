package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (kimagure)
 * Le fantôme bleu sort après un certain temps de la base  ,
 * il se met à chasser Pac-man que quand celui-ci à ramassé assez de point,
 * pour la chasse il se sert à la fois de la position de Pac-man et du fantôme rouge***/

public class FantomeBleu extends Fantome{
    public FantomeBleu(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        return super.Chase(listOfWalls);
    }
}
