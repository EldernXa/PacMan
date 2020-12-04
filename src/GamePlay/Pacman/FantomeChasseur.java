package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (oikake)
 * Le fantôme rouge poursuit toujours Pac-man ,
 * excepté quand il est en mode fuite et
 * il commence hors de la base des fantômes   ***/

public class FantomeChasseur extends Fantome {

    private PacMan pacMan;
    private Coordinate coordinateScatter1 = new Coordinate(419,19);




    public FantomeChasseur(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        super.setGoal(pacMan.getGameImage().getCoordinate());
        this.pacMan = pacMan;


    }


    @Override
    public int Chase(ArrayList<Character> listOfWalls) {

        if (pacMan.isSuperPacman()) {
            super.setGoal(coordinateScatter1);

        }else {

                super.setGoal(pacMan.getGameImage().getCoordinate());

        }

        return super.Chase(listOfWalls);
    }


}

