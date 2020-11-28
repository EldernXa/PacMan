package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (oikake)
 * Le fantôme rouge poursuit toujours Pac-man ,
 * excepté quand il est en mode fuite et
 * il commence hors de la base des fantômes   ***/

public class FantomeRouge extends Fantome{
    private Coordinate goal;
    private MapPacman mapPacman;
    private PacMan pacMan;


    public FantomeRouge(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        setGoal(pacMan.getGameImage().getCoordinate());
        this.mapPacman = map;
        this.pacMan= pacMan;

    }

    @Override
    public void setGoal(Coordinate coordinate) {
        this.goal = coordinate;
    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().affichageCoord();
        ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
            switch (bestAction(getGoal(), charactersFeasable)){
                case 'H':
                    setLastCharacter('H');
                    int temp = 3;
                    return temp;
                case 'B':
                    setLastCharacter('B');
                    int temp1 = 1;
                    return temp1;
                case 'D':
                    setLastCharacter('D');
                    int temp2 = 0;
                    return temp2;
                case 'G':
                    setLastCharacter('G');
                    int temp3 = 2;
                    return temp3;

            }



        int nulL = -1;
        return nulL;
    }
    }

