package GamePlay;
import GraphicsEngine.ActionContinue;
import GraphicsEngine.Action;
import GraphicsEngine.Coordinate;
import GraphicsEngine.MouvingObject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public class Fantome extends MouvingObject {
    private float valueTps = (float)100;
    private Coordinate goal ;

    public Fantome(String path, Coordinate coordinate, Scene scene, Pane pane){

        super(path, coordinate, scene, pane);
        setGoal(coordinate);
        addAction(new ActionContinue(getGameImage(), scene, "o", 'y', -getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "l", 'y', getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "k", 'x', -getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "m", 'x', getGameImage().getValueMove(), valueTps, this));
    }
    public void setGoal(Coordinate coordinate){
        Random rX = new Random();
        double randomValueX = 30 + (250 - 30) * rX.nextDouble();
        Random rY = new Random();
        double randomValueY = 30 + (250 - 30) * rY.nextDouble();
        setGoal(new Coordinate(coordinate.getX()+randomValueX,coordinate.getY()+randomValueY));


    }

    public Coordinate getGoal() {
        return goal;
    }

    public double getEuclidianDistance(Coordinate coordinate){
        double xGF = Math.pow(this.goal.getX()-coordinate.getX(),2);
        double yGF = Math.pow(this.goal.getY()-coordinate.getY(),2);
        return Math.sqrt(xGF+yGF);


    }

    public void  Chase(Coordinate pacManCoordinate, ArrayList<Character> listOfWalls){
        ArrayList<Character> characters = actionPossible(listOfWalls);
        if(getEuclidianDistance(pacManCoordinate)<= 80.0){


            }

        }
        public ArrayList<Character> actionPossible(ArrayList<Character> list){
         ArrayList<Character> characters = new ArrayList<>();
           if(list.contains('H')){
               characters.add('B');
               characters.add('D');
               characters.add('G');
                return characters;
           }
            if(list.contains('H')&&list.contains('G')){
                characters.add('B');
                characters.add('D');
                return characters;
            }
            if(list.contains('H')&&list.contains('D')){
                characters.add('B');
                characters.add('G');
                return characters;


            }
            if(list.contains('H')&&list.contains('B')){
                characters.add('G');
                characters.add('D');
                return characters;


            }
            if(list.contains('H')&&list.contains('G')&&list.contains('B')){

                characters.add('D');
                return characters;


            }
            if(list.contains('H')&&list.contains('D')&&list.contains('B')){
                characters.add('G');
                return characters;

            }
            if(list.contains('H')&&list.contains('D')&&list.contains('G')){
                characters.add('B');
                return characters;

            }
            if(list.contains('B')){
                characters.add('H');
                characters.add('D');
                characters.add('G');
                return characters;

            }
            if(list.contains('B')&&list.contains('G')){
                characters.add('H');
                characters.add('D');
                return characters;

            }
            if(list.contains('B')&&list.contains('D')){
                characters.add('H');
                characters.add('G');
                return characters;

            }

            if(list.contains('B')&&list.contains('D')&&list.contains('G')){
                characters.add('H');
                return characters;

            }

            if(list.contains('G')){
                characters.add('H');
                characters.add('D');
                characters.add('B');
                return characters;

            }
            if(list.contains('G')&&list.contains('D')){
                characters.add('H');
                characters.add('B');
                return characters;

            }
            if(list.contains('D')){
                characters.add('H');
                characters.add('G');
                characters.add('B');
                return characters;

            }

            return characters;

        }

        /*public Character bestAction(Character character){

        }*/

    }


