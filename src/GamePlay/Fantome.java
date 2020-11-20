package GamePlay;
import GraphicsEngine.*;

import ReadFile.PosMursAssocies;
import ReadFile.ReadFileMap2Pacman;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Random;

public class Fantome extends MouvingObject {
    private float valueTps = (float) 10;
    private Coordinate goal;

    public Fantome(String path, Coordinate coordinate, Scene scene,Map map,Coordinate pacmanCoordinate) {
        super(path, coordinate, scene);
        setGoal(pacmanCoordinate);
        addAction(new ActionContinueFantome(getGameImage(),scene,valueTps,this,map));



    }

    public void setGoal(Coordinate coordinate) {
        Random rX = new Random();
        double randomValueX = 30 + (250 - 30) * rX.nextDouble();
        Random rY = new Random();
        double randomValueY = 30 + (250 - 30) * rY.nextDouble();
        this.goal = new Coordinate(coordinate.getX() + randomValueX, coordinate.getY() + randomValueY);
    }







        public Coordinate getGoal () {
            return goal;
        }

        public double getEuclidianDistance (Coordinate coordinate){
            double xGF = Math.pow(this.goal.getX() - coordinate.getX(), 2);
            double yGF = Math.pow(this.goal.getY() - coordinate.getY(), 2);
            return Math.sqrt(xGF + yGF);


        }

        public int Chase (Coordinate pacManCoordinate, ArrayList<Character> listOfWalls){

            ArrayList<Character> charactersFeasable = actionPossible(listOfWalls);
            if (getEuclidianDistance(pacManCoordinate) <= 80.0) {

                switch (bestAction(pacManCoordinate, charactersFeasable)) {
                    case 'H':
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
        public ArrayList<Character> actionPossible (ArrayList < Character > list) {
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
                double newY = coordinate.getY() + Double.valueOf(getGameImage().getValueMove());

                Coordinate Newcoordinate = new Coordinate(coordinate.getX(), newY);
                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {
                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'H';

                }

            }
            if (character.contains('B')) {
                double newY = coordinate.getY() - Double.valueOf(getGameImage().getValueMove());

                Coordinate Newcoordinate = new Coordinate(coordinate.getX(), newY);
                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {
                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'B';

                }

            }
            if (character.contains('D')) {
                double newX = coordinate.getX() + Double.valueOf(getGameImage().getValueMove());

                Coordinate Newcoordinate = new Coordinate(newX, coordinate.getY());
                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {
                    smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'D';
                }

            }
            if (character.contains('G')) {
                double newX = coordinate.getX() - Double.valueOf(getGameImage().getValueMove());

                Coordinate Newcoordinate = new Coordinate(newX, coordinate.getY());
                if (getEuclidianDistance(Newcoordinate) < smallerDistance) {
                    //smallerDistance = getEuclidianDistance(Newcoordinate);
                    chaR = 'G';
                }

            }
            return chaR;

        }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        return false;
    }
}



