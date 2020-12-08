package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import javafx.animation.Timeline;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (kimagure)
 * Le fantôme bleu sort après un certain temps de la base  ,
 * il se met à chasser Pac-man que quand celui-ci à ramassé assez de point,
 * pour la chasse il se sert à la fois de la position de Pac-man et du fantôme rouge***/



public class FantomeCalcul extends Fantome{



    private PacMan pacMan;
    private FantomeChasseur fantomeRouge;
    private Coordinate coordinateScatter1 ;

    /**
     *
     * @param path Permet de charger le sprite désiré
     * @param coordinate Permet de savoir ou charger le sprite
     * @param scene Permet d'ajouter notre fantôme
     * @param map Cela sert à passer des coordonnées réelles à celles de grilles
     * @param pacMan Cela sert à passer les coordonnées du PacMan au fantôme
     * @param fantomeRouge Cela sert à passer les coordonnées du fantôme chasseur
     */

    public FantomeCalcul(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan, FantomeChasseur fantomeRouge) {
        super(path, coordinate, scene, map, pacMan);

        this.pacMan = pacMan;
        this.fantomeRouge = fantomeRouge;
        this.coordinateScatter1 = coordinatesOfFear().get(coordinatesOfFear().size()-1);

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
     * @return Le fantôme chasseur
     */

    public FantomeChasseur getFantomeRouge() {
        return fantomeRouge;
    }

    /**
     *
     * @param fantomeRouge
     * Affecte le fantôme chasseur
     */

    public void setFantomeRouge(FantomeChasseur fantomeRouge) {
        this.fantomeRouge = fantomeRouge;
    }

    /**
     *
     * @param listOfWalls
     * @return La direction à prendre ,
     * afin que le fantôme atteigne son objectif.
     */
    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        //if (pacMan.getNbPoints() >= 20){
            if(pacMan.isSuperPacman()){
                setGoal(coordinateScatter1);
                }else {
                setGoal(Transition(fantomeRouge));
                }

                /*}else {
                setGoal(getFantome());

             }*/
        return super.Chase(listOfWalls);
    }



}
