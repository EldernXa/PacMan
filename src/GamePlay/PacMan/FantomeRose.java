package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (machibuse)
 * Le fantôme rose sort de la base dès le début ,
 * il regarde la position de Pac-man ainsi que
 * sa direction et cible la position ou sera Pac-man dans 4 cases  ***/

public class FantomeRose extends Fantome{
    public FantomeRose(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
    }
    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        return super.Chase(listOfWalls);
    }
}
