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
    private Coordinate coordinateScatter1 ;


    /**
     *
     * @param path Permet de charger le sprite désiré
     * @param coordinate Permet de savoir ou charger le sprite
     * @param scene Permet d'ajouter notre fantôme
     * @param map Cela sert à passer des coordonnées réelles à celles de grilles
     * @param pacMan Cela sert à passer les coordonnées du PacMan au fantôme
     */
    public FantomeChasseur(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        super.setGoal(pacMan.getGameImage().getCoordinate());
        this.pacMan = pacMan;
        this.coordinateScatter1 = coordinatesOfFear().get(1);


    }

    /**
     *
     * @param listOfWalls
     * @return La direction à prendre ,
     * afin que le fantôme atteigne son objectif.
     */
    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        //System.out.println(pacMan.getNbPoints());
       /* if(pacMan.getNbPoints() >= 2){
            //super.setWaiting(false);
            System.out.println("J'attend plus");*/


        if (pacMan.isSuperPacman()) {
            super.setGoal(coordinateScatter1);

        }else {

                super.setGoal(pacMan.getGameImage().getCoordinate());

        }
        /*}else {
            System.out.println("J'attend");
            //super.setWaiting(true);
        }*/

        return super.Chase(listOfWalls);
    }


}

