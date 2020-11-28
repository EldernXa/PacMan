package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (machibuse)
 * Le fantôme rose sort de la base dès le début ,
 * il regarde la position de Pac-man ainsi que
 * sa direction et cible la position ou sera Pac-man dans 2 cases  ***/

public class FantomeRose extends Fantome{
    private PacMan pacMan;
    private MapPacman mapPacman;
    public FantomeRose(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
        this.mapPacman=map;
        this.pacMan = pacMan;
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

    /*public Coordinate anticipation(PacMan pacMan){
        switch (){
            case 0:
                double x0 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getX() ;
                double y0 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getY() ;
                return   new Coordinate(x0+2,y0);
                break;
            case 1:
                double x1 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getX() ;
                double y1 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x1,y1+2);
                break;

            case 2:
                double x2 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getX() ;
                double y2 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x2-2,y2);
                break;
            case 3:
                double x3 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getX() ;
                double y3 = mapPacman.getWrongCoorFromReal(pacMan.getGameImage().getCoordinate()).getPointCoordinate().getY() ;
                return  new Coordinate(x3,y3-2);
                break;

        }


    }*/
}
