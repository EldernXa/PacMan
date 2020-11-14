package GraphicsEngine;

import ReadFile.ReadFileMapPacman;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Action {

    private final GameImage gameImage;
    private final Scene scene;
    private final MouvingObject mouvingObject;

    public Action(GameImage gameImage, Scene scene, String carac, char dir, int valueMove, MouvingObject mouvingObject){
        this.mouvingObject = mouvingObject;
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
            mouvingObject.nextImgDirUp();
            if (collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)) {
                gameImage.descendre();
                mouvingObject.previousImgDirUp();
            }

        }else{
            doWhenBlock();
        }

    }

    private void descendre() {
        if (gameImage.getCoordinate().getY() + gameImage.getValueMove() <= (scene.getHeight()-gameImage.getImgView().getImage().getHeight())) {
            gameImage.descendre();
            mouvingObject.nextImgDirDown();
        }
        else
            doWhenBlock();
        if (collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)) {
            gameImage.monter();
            mouvingObject.previousImgDirDown();
        }
    }

    private void gauche() {
        if (gameImage.getCoordinate().getX() - gameImage.getValueMove() >= 0) {
            gameImage.gauche();
            mouvingObject.nextImgDirLeft();
        }
        else
            doWhenBlock();
        if (collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)) {
            gameImage.droite();
            mouvingObject.previousImgDirLeft();
        }
    }

    private void droite() {
        if (gameImage.getCoordinate().getX() + gameImage.getValueMove() <= (scene.getWidth()-gameImage.getImgView().getImage().getWidth())) {
            gameImage.droite();
            mouvingObject.nextImgDirRight();
        }
        else
            doWhenBlock();
        if (collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)) {
            gameImage.gauche();
            mouvingObject.previousImgDirRight();
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
