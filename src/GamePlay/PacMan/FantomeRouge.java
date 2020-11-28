package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (oikake)
 * Le fantôme rouge poursuit toujours Pac-man ,
 * excepté quand il est en mode fuite et
 * il commence hors de la base des fantômes   ***/

public class FantomeRouge extends Fantome {
    private Coordinate goal;
    private MapPacman mapPacman;
    private PacMan pacMan;


    public FantomeRouge(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        setGoal(pacMan.getGameImage().getCoordinate());
        this.mapPacman = map;
        this.pacMan = pacMan;

    }

    @Override
    public void setGoal(Coordinate coordinate) {
        this.goal = coordinate;
    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        setGoal(pacMan.getGameImage().getCoordinate());
        return super.Chase(listOfWalls);
    }
}

