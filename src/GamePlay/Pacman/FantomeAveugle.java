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
    private Coordinate coordinateScatter1 ;

    /**
     *
     * @param path Permet de charger le sprite désiré
     * @param coordinate Permet de savoir ou charger le sprite
     * @param scene Permet d'ajouter notre fantôme
     * @param map Cela sert à passer des coordonnées réelles à celles de grilles
     * @param pacMan Cela sert à passer les coordonnées du PacMan au fantôme
     */


    public FantomeAveugle(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        this.pacMan = pacMan;
        this.mapPacman = map;
        this.coordinateScatter1 = coordinatesOfFear().get(2);


    }


    /**
     *
     * @return Le pacman
     */
    public PacMan getPacMan() {
        return pacMan;
    }

    /**
     *
     * @param pacMan
     * Affecte le pacman
     */
    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    /**
     *
     * @param listOfWalls
     * @return La direction à prendre ,
     * afin que le fantôme atteigne son objectif.
     */
    @Override
    public int Chase(ArrayList<Character> listOfWalls) {

        //if (pacMan.getNbPoints() >= 3 * (pacMan.getNbPointsMapMax() / 4)) {
            if(isInSpawn(getFantome())){
            super.setGoal(getCoordinateInFrontSpawn());
            }

            if (pacMan.isSuperPacman()) {

                super.setGoal(coordinateScatter1);

            } else {

                isNear(2.5);
            }

            return super.Chase(listOfWalls);




       /* }else {
            super.setRandomGoal();
            return super.Chase(listOfWalls);
        }*/


    }

    /**
     *
     * @param distance
     * Défini l'objectif sur pacman si celui-ci est trop proche ,
     * autrement défini un objectif aléatoire.
     */
        public void isNear(double distance){
            if (getEuclidianDistanceFromPacMan(getFantome())<= distance){
                setGoal(pacMan.getCoordinate());
            }else{
                setRandomGoal();
            }


        }


    }


