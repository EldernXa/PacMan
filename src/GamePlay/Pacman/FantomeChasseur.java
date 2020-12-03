package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (oikake)
 * Le fantôme rouge poursuit toujours Pac-man ,
 * excepté quand il est en mode fuite et
 * il commence hors de la base des fantômes   ***/

public class FantomeChasseur extends Fantome {
    private boolean fuite;
    private Coordinate goal;
    private MapPacman mapPacman;
    private PacMan pacMan;
    private Coordinate coordinateScatter1 = new Coordinate(419,19);
    private Coordinate coordinate;



    public FantomeChasseur(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        //this.coordinate = this.getGameImage().getCoordinate();
        super.setGoal(pacMan.getGameImage().getCoordinate());
        this.mapPacman = map;
        this.pacMan = pacMan;

    }

    @Override
    public void setGoal(Coordinate coordinate) {
        this.goal = coordinate;
    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        //System.out.println(pacMan.isSuperPacman());
        super.setGoal(coordinateScatter1);
        /*if (pacMan.isSuperPacman()) {
            super.getFantome().affichageCoord();
            super.setGoal(coordinateScatter1);
            System.out.println("J'ai peur");
        }else {
            super.setGoal(pacMan.getGameImage().getCoordinate());
        }*/

        return super.Chase(listOfWalls);
    }


}

