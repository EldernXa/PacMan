package GamePlay;
import GraphicsEngine.*;

import ReadFile.PosMursAssocies;
import ReadFile.ReadFileMap2Pacman;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Fantome extends MouvingObject {
    private float valueTps = (float) 10;
    private Coordinate goal;
    private Coordinate fantome;
    private Timeline timeline;
    private int indTimeline;

    public Fantome(String path, Coordinate coordinate, Scene scene,Map map,Coordinate pacmanCoordinate) {
        super(path, coordinate, scene);
        setGoal(pacmanCoordinate);
        addAction(new ActionContinueFantome(getGameImage(),scene,valueTps,this,map));



    }
    public void Test(Coordinate pacman,Map map,Scene scene){

        if (timeline == null)
            timeline = new Timeline();
        VisualObject.stopTimelineParallel();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(valueTps),
                temps -> {
                    //System.out.println(bestAction(getGoal(),map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls()));

                 int temp = Chase(getGoal(),map.getWrongCoorFromReal(getGameImage().getCoordinate()).getListOfWalls());

                    getGoal().affichageCoord();
                    getFantome().affichageCoord();


                  switch (temp){
                      case 0:
                          new ActionContinueFantome(getGameImage(), scene, valueTps,this,map, getGameImage().getValueMove(),0,0,"Droite");
                          break;
                      case 1:
                          new ActionContinueFantome(getGameImage(), scene, valueTps,this,map, 0,-getGameImage().getValueMove(),1,"Bas");
                          break;
                      case 2:
                          new ActionContinueFantome(getGameImage(), scene, valueTps,this,map, -getGameImage().getValueMove(),0,2,"Gauche");
                          break;
                      case 3:
                          new ActionContinueFantome(getGameImage(), scene, valueTps,this,map, 0,getGameImage().getValueMove(),3,"Haut");
                          break;
                  }


                }
        ));
        indTimeline = VisualObject.addTimeline(timeline, this);
        //System.out.println(indTimeline);
        VisualObject.startTimelineParallel();

    }


    public Coordinate getFantome() {
        return fantome;
    }

    public void setGoal(Coordinate coordinate) {
        Random rX = new Random();
        double randomValueX = 18 + (382 - 18) * rX.nextDouble();
        Random rY = new Random();
        double randomValueY = 18 + (382 - 18) * rY.nextDouble();
        Coordinate temp =new Coordinate( randomValueX,  randomValueY);

        if(validCoordinate(temp)) {
            this.goal = new Coordinate( randomValueX,  randomValueY);
        }else{
            setGoal(coordinate);
        }
    }


        public Coordinate getGoal () {
            return goal;
        }

        public boolean validCoordinate(Coordinate coordinate){
        Double maxXY = 400.0;
        Double minXY = 0.0;
        if(coordinate.getX() > maxXY || coordinate.getX()< minXY || coordinate.getY() > maxXY || coordinate.getY() < minXY) {

            return false;

        } else {

            return true;
        }

        }

        public double getEuclidianDistance (Coordinate coordinate){
            double xGF = Math.pow(this.fantome.getX() - coordinate.getX(), 2);
            double yGF = Math.pow(this.fantome.getY() - coordinate.getY(), 2);
            return Math.sqrt(xGF + yGF);


        }
        public boolean objectifReach(Coordinate coordinate){
            if(getEuclidianDistance(coordinate) <= 5.0){
                return true;
            }else{
                return false;
                }
        }

        public int Chase (Coordinate pacManCoordinate, ArrayList<Character> listOfWalls){

            ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
            if(objectifReach(getGoal())){
                setGoal(getFantome());
            }else if (getEuclidianDistance(pacManCoordinate) <= 30.0) {

                switch (bestAction(pacManCoordinate, charactersFeasable)) {
                    case 'H':
                        System.out.println("yo");
                        int temp = 3;
                        return temp;
                    case 'B':
                        int temp1 = 1;
                        return temp1;
                    case 'D':
                        int temp2 = 0;
                        return temp2;
                    case 'G':
                        int temp3 = 2;
                        return temp3;

                }

            } else {
                switch (bestAction(getGoal(), charactersFeasable)){
                    case 'H':
                        System.out.println("y0 2");
                        int temp = 3;
                        return temp;
                    case 'B':
                        int temp1 = 1;
                        return temp1;
                    case 'D':
                        int temp2 = 0;
                        return temp2;
                    case 'G':
                        int temp3 = 2;
                        return temp3;

                }


            }
            int nulL = -1;
            return nulL;
        }
        public ArrayList<Character> actionPossible (ArrayList <Character> list) {
            ArrayList<Character> characters = new ArrayList<>();
            if (list.contains('H')) {
                characters.add('B');
                characters.add('D');
                characters.add('G');
                return characters;
            }
            if (list.contains('H') && list.contains('G')) {
                characters.add('B');
                characters.add('D');
                return characters;
            }
            if (list.contains('H') && list.contains('D')) {
                characters.add('B');
                characters.add('G');
                return characters;


            }
            if (list.contains('H') && list.contains('B')) {
                characters.add('G');
                characters.add('D');
                return characters;


            }
            if (list.contains('H') && list.contains('G') && list.contains('B')) {

                characters.add('D');
                return characters;


            }
            if (list.contains('H') && list.contains('D') && list.contains('B')) {
                characters.add('G');
                return characters;

            }
            if (list.contains('H') && list.contains('D') && list.contains('G')) {
                characters.add('B');
                return characters;

            }
            if (list.contains('B')) {
                characters.add('H');
                characters.add('D');
                characters.add('G');
                return characters;

            }
            if (list.contains('B') && list.contains('G')) {
                characters.add('H');
                characters.add('D');
                return characters;

            }
            if (list.contains('B') && list.contains('D')) {
                characters.add('H');
                characters.add('G');
                return characters;

            }

            if (list.contains('B') && list.contains('D') && list.contains('G')) {
                characters.add('H');
                return characters;

            }

            if (list.contains('G')) {
                characters.add('H');
                characters.add('D');
                characters.add('B');
                return characters;

            }
            if (list.contains('G') && list.contains('D')) {
                characters.add('H');
                characters.add('B');
                return characters;

            }
            if (list.contains('D')) {
                characters.add('H');
                characters.add('G');
                characters.add('B');
                return characters;

            }

            return characters;

        }

        public Character bestAction (Coordinate coordinate,ArrayList<Character> character){
            double smallerDistance = 1000000;
            Character chaR = new Character(' ');
            if (character.contains('H')) {
                double newY = coordinate.getY() - getGameImage().getValueMove();
                Coordinate Newcoordinate = new Coordinate(coordinate.getX(), newY);

                //System.out.println(smallerDistance);
                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'H';

                }

            }
            if (character.contains('B')) {

                double newY = coordinate.getY() + Double.valueOf(getGameImage().getValueMove());
                Coordinate Newcoordinate = new Coordinate(coordinate.getX(), newY);

                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'B';

                }
            }

            if (character.contains('D')) {

                double newX = coordinate.getX() - Double.valueOf(getGameImage().getValueMove());

                Coordinate Newcoordinate = new Coordinate(newX, coordinate.getY());

                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {

                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'D';
                }

            }
            if (character.contains('G')) {

                double newX = coordinate.getX() + Double.valueOf(getGameImage().getValueMove());

                Coordinate Newcoordinate = new Coordinate(newX, coordinate.getY());

                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {

                    chaR = 'G';
                }

            }

            return chaR;

        }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        PacMan pacman = ((PacMan)visualObjects);
        if(visualObjects != null){
            pacman.death();
            if(pacman.getNbVies() <= 0){
                //mettre la scene de fin avec la defaite
            }
        }

        return false;
    }
}



