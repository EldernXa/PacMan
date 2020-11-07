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

    public Action(GameImage gameImage, Scene scene, Coordinate maxCoord){
        this.maxCoord = maxCoord;
        this.scene = scene;
        this.gameImage = gameImage;
    }
    public void runEvent(){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent->{
            if(keyEvent.getCode().getChar().toLowerCase().compareTo("z")==0)
                monter();
        });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent->{
            if(keyEvent.getCode().getChar().toLowerCase().compareTo("s")==0)
                descendre();
        });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent->{
            if(keyEvent.getCode().getChar().toLowerCase().compareTo("q")==0)
                gauche();
        });
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent->{
            if(keyEvent.getCode().getChar().toLowerCase().compareTo("d")==0)
                droite();
        });
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
