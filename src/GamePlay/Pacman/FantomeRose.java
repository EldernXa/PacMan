package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (machibuse)
 * Le fantôme rose sort de la base dès le début ,
 * il regarde la position de Pac-man ainsi que
 * sa direction et cible la position ou sera Pac-man dans 2 cases  ***/

public class FantomeRose extends Fantome{
    private PacMan pacMan;
    private MapPacman mapPacman;
    public FantomeRose(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        this.mapPacman=map;
        this.pacMan = pacMan;
        setGoal(anticipation(pacMan,2));
    }
    @Override
    public int Chase(ArrayList<Character> listOfWalls) {

        setGoal(anticipation(pacMan,2));
        return super.Chase(listOfWalls);
    }


}
