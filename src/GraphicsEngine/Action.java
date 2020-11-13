package GraphicsEngine;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Action {

    private final GameImage gameImage;
    private final Scene scene;

    public Action(GameImage gameImage, Scene scene, String carac, char dir, int valueMove){
        this.scene = scene;
        this.gameImage = gameImage;
        runEvent(scene, carac, valueMove, dir);
    }

    private void runEvent(Scene scene, String carac, int valueMove, char dir){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent->{
            if(keyEvent.getCode().getChar().toLowerCase().compareTo(
                    carac.toLowerCase())==0){
                doWhenEventOccur(valueMove, dir);
            }
        });
    }

    public void doWhenEventOccur(int valueMove, char dir){
        choiceMove(valueMove, dir);
    }

    public void doInverseWhenEventOccur(int valueMove, char dir){
        choiceInverseMove(valueMove, dir);
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

    public void choiceInverseMove(int valueMove, char carac){
        if(carac=='x'){
            if(valueMove<0)
                droite();
            else
                gauche();
        }else if(carac=='y'){
            if(valueMove<0)
                descendre();
            else
                monter();
        }
    }

    void doWhenBlock(){

    }

    private void monter() {
        if (gameImage.getCoordinate().getY() - gameImage.getValueMove() >= 0) {

            gameImage.monter();

            if (collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)) {
                gameImage.descendre();
            }

        }else{
            doWhenBlock();
        }

    }

    private void descendre() {
        if (gameImage.getCoordinate().getY() + gameImage.getValueMove() <= (scene.getHeight()-gameImage.getImgView().getImage().getHeight()))
            gameImage.descendre();
        else
            doWhenBlock();
        if (collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)) {
            gameImage.monter();
        }
    }

    private void gauche() {
        if (gameImage.getCoordinate().getX() - gameImage.getValueMove() >= 0)

            gameImage.gauche();
        else
            doWhenBlock();
        if (collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)) {
            gameImage.droite();
        }
    }

    private void droite() {
        if (gameImage.getCoordinate().getX() + gameImage.getValueMove() <= (scene.getWidth()-gameImage.getImgView().getImage().getWidth()))

            gameImage.droite();
        else
            doWhenBlock();
        if (collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)) {
            gameImage.gauche();
        }
    }

    public GameImage getGameImage(){
        return gameImage;
    }


    public boolean collision(ImageView a, ArrayList<VisualObject> b) {
        for (int i = 0; i < b.size(); i++) {
           if(b.get(i).getClass() == Decor.class){
               if (a.getBoundsInParent().intersects(b.get(i).getImageView().getBoundsInParent())) {

                   return true;
               }

           }

        }
        return false;
    }


}
