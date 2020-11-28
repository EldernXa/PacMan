package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (otoboke)
 * Le fantôme orange sort de la base lorsque Pac-man à obtenu 3 quart des points  ,
 * il poursuit Pac-man quand celui-ci est à moins de 2 blocs de lui,
 * sinon il garde sa routine ***/
public class FantomeOrange extends Fantome {
    private int counterPoint;
    private PacMan pacMan;
    private MapPacman mapPacman;

    public FantomeOrange(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        this.pacMan = pacMan;
        this.mapPacman = map;
        counterPoint = 0;
    }

    public int getCounterPoint() {
        return counterPoint;
    }

    public void setCounterPoint() {
        this.counterPoint = pacMan.getNbPoints();
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public MapPacman getMapPacman() {
        return mapPacman;
    }

    public void setMapPacman(MapPacman mapPacman) {
        this.mapPacman = mapPacman;
    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        if (getCounterPoint() >= 3 * (pacMan.getNbPointsMapMax() / 4)) {

            //mapPacman.getWrongCoorFromReal(pacMan.getCoordinate()).getPointCoordinate().affichageCoord();
            ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
            if (objectifReach(getGoal())) {
                setRandomGoal();
            } else if (getEuclidianDistance(mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate()) <= 4.0) {

                switch (bestAction(pacMan.getGameImage().getCoordinate(), charactersFeasable)) {
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

            } else {
                switch (bestAction(getGoal(), charactersFeasable)) {
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


            }
            int nulL = -1;
            return nulL;
        }else {
            int nulL = -1;
            return nulL;
        }
    }
}

