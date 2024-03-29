package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (machibuse)
 * Le fantôme rose sort de la base dès le début ,
 * il regarde la position de Pac-man ainsi que
 * sa direction et cible la position ou sera Pac-man dans 2 cases  ***/

public class FantomeStratège extends Fantome{
    private boolean fuite;
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
    public FantomeStratège(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        this.mapPacman=map;
        this.pacMan = pacMan;
        this.coordinateScatter1 = coordinatesOfFear().get(0);
        setGoal(anticipation(pacMan,2));

    }

    /**
     *
     * @param listOfWalls
     * @return La direction à prendre ,
     * afin que le fantôme atteigne son objectif.
     */
    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        if(isInSpawn(getFantome())){
            System.out.println("Ici");
            super.setGoal(getCoordinateInFrontSpawn());
        }
        if (pacMan.isSuperPacman()){
            super.setGoal(coordinateScatter1);
        }
        setGoal(anticipation(pacMan,2));

        return super.Chase(listOfWalls);
    }




}
