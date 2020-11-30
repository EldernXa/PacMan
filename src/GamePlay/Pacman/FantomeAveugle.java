package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (otoboke)
 * Le fantôme orange sort de la base lorsque Pac-man à obtenu 3 quart des points  ,
 * il poursuit Pac-man quand celui-ci est à moins de 2 blocs de lui,
 * sinon il garde sa routine ***/
public class FantomeAveugle extends Fantome {
    private boolean fuite;
    private int counterPoint;
    private PacMan pacMan;
    private MapPacman mapPacman;
    private Coordinate coordinateScatter1 = new Coordinate(0,7);


    public FantomeAveugle(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
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

            ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
            if (isNear(mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate(),2)) {

                setGoal(mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate());
                return super.Chase(listOfWalls);
            } else {
                setRandomGoal();
                return super.Chase(listOfWalls);
            }

        }else {
            int nulL = -1;
            return nulL;
        }
    }

    public boolean isNear(Coordinate coordinate,double range){

        if((Math.abs(coordinate.getX() - this.getGameImage().getCoordinate().getX()) <= range )||(Math.abs(coordinate.getY() - this.getGameImage().getCoordinate().getY()) <= range )){
            return true;
        }else {
            return false;
        }

    }

    public void scatterMode(){
        setGoal(this.coordinateScatter1);
        if (objectifReach(coordinateScatter1)&&this.fuite){
            setGoal(coordinateScatter1);
        }
    }
}

