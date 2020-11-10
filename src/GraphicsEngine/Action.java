package GraphicsEngine;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Action {

    private final GameImage gameImage;
    private final Scene scene;
    private final Coordinate maxCoord;

    public Action(GameImage gameImage, Scene scene, Coordinate maxCoord, String carac, char dir, int valueMove){
        this.maxCoord = maxCoord;
        this.scene = scene;
        this.gameImage = gameImage;
        runEvent(scene, carac, valueMove, dir);
    }

    private void runEvent(Scene scene, String carac, int valueMove, char dir){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent->{
            if(keyEvent.getCode().getChar().toLowerCase().compareTo(
                    carac.toLowerCase())==0){
                choiceMove(valueMove, dir);
            }
        });
    }

    public void choiceMove(int valueMove, char carac){
        if(carac=='x'){
            if(valueMove<0)
                gauche();
            else
                droite();
        }
        else if(carac=='y')
            if(valueMove<0)
                monter();
            else
                descendre();
    }

    private void monter(){
        if(gameImage.getCoordinate().getY()-gameImage.getValueMove()>=0)
            gameImage.monter();
    }

    private void descendre(){
        if(gameImage.getCoordinate().getY()+gameImage.getValueMove()<=scene.getHeight())
            gameImage.descendre();
    }

    private void gauche(){
        if(gameImage.getCoordinate().getX()-gameImage.getValueMove()>=0)
            gameImage.gauche();
    }

    private void droite(){
        if(gameImage.getCoordinate().getX()+gameImage.getValueMove()<=scene.getWidth())
            gameImage.droite();
    }

}
