package GamePlay;
import GraphicsEngine.*;


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
    private char lastCharacter;

    public Fantome(String path, Coordinate coordinate, Scene scene,Map map,Coordinate pacmanCoordinate) {
        super(path, coordinate, scene);
        setGoal(pacmanCoordinate);
        addAction(new ActionContinueFantome(getGameImage(),scene,valueTps,this,map));



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

    public Character getLastCharacter() {
        return lastCharacter;
    }

    public Character bestAction (Coordinate coordinate, ArrayList<Character> character){
            character = removeBackwardDirection(character,oppositeDirection(getLastCharacter()));
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
        }

        return false;
    }

    public Character oppositeDirection(char character){

        char newChar = new Character( ' ');
        if(character == 'H')newChar = 'B';
        if(character == 'B')newChar = 'H';
        if(character == 'D')newChar = 'G';
        if(character == 'G')newChar = 'D';
        System.out.println(newChar);
        return newChar;
    }

    public ArrayList<Character> removeBackwardDirection(ArrayList<Character> characters,Character character){
        characters.remove(character);
        return characters;
    }
}



