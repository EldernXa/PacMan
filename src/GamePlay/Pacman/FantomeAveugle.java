package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (otoboke)
 * Le fantôme orange sort de la base lorsque Pac-man à obtenu 3 quart des points  ,
 * il poursuit Pac-man quand celui-ci est à moins de 2 blocs de lui,
 * sinon il garde sa routine ***/
public class FantomeAveugle extends Fantome {

    private PacMan pacMan;
    private MapPacman mapPacman;
    private Coordinate coordinateScatter1 = new Coordinate(19,369);


    public FantomeAveugle(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        setRandomGoal();
        this.pacMan = pacMan;
        this.mapPacman = map;



    }



    public PacMan getPacMan() {
        return pacMan;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }


    @Override
    public int Chase(ArrayList<Character> listOfWalls) {

        if (pacMan.getNbPoints() >= 3 * (pacMan.getNbPointsMapMax() / 4)) {

            if (pacMan.isSuperPacman()) {

                super.setGoal(coordinateScatter1);

            } else {

                isNear();
            }

            return super.Chase(listOfWalls);




        }else {
            super.setGoal(getFantome());
            return super.Chase(listOfWalls);
        }


    }

        public void isNear(){

            if (getEuclidianDistanceFromPacMan(getFantome()) > 3.0 || getEuclidianDistanceFromPacMan(getFantome()) == 0.0) {

                setRandomGoal();


            } else {

                setGoal(pacMan.getCoordinate());


            }


        }


    }


