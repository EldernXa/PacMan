package GraphicsEngine;

//import ReadFile.ReadFileMapPacman;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Action {

    private final GameImage gameImage;
    private final MouvingObject mouvingObject;



    public Action(GameImage gameImage, Scene scene, String carac, char dir, int valueMove, MouvingObject mouvingObject){

    private final double x;
    private final double y;
    private final String nameAction;

    public Action(GameImage gameImage, Scene scene, String carac, double x, double y, int dir, String nameAction, MouvingObject mouvingObject){
        this.nameAction = nameAction;
        this.x = x;
        this.y = y;

        this.mouvingObject = mouvingObject;
        this.gameImage = gameImage;
        runEvent(scene, carac, dir);
    }
    public Action(GameImage gameImage,Scene scene, char dir, int valueMove, MouvingObject mouvingObject){
        this.mouvingObject = mouvingObject;
        this.gameImage = gameImage;
        this.scene = scene;
        runEventWithoutKey( valueMove, dir);
    }

    private void runEvent(Scene scene, String carac, int dir){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent->{
            if(keyEvent.getCode().getChar().toLowerCase().compareTo(
                    carac.toLowerCase())==0){
                doWhenEventOccur(dir);
            }
        });
    }
    private void runEventWithoutKey( int valueMove, char dir){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent->{

                doWhenEventOccur(valueMove, dir);

        });

    }

   public void doWhenEventOccur(int dir){
        move(gameImage.getCoordinate().getX() + x, gameImage.getCoordinate().getY() + y, dir);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }


    /*public void doInverseWhenEventOccur(int valueMove, char dir){
        choiceInverseMove(valueMove, dir);
    }*/

/*    public void choiceMove(int valueMove, char carac){
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
    }*/
/*
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
    }*/

    void doWhenBlock(){

    }

    private void move(double x, double y, int dir){
        Coordinate c = new Coordinate(gameImage.getCoordinate().getX(), gameImage.getCoordinate().getY());
        if(x>=0 && y>=0){
            gameImage.move(x, y);
            mouvingObject.nextImage(dir);
            /*if(collision(gameImage.getImgView(), ReadFileMapPacman.visualObjects)){
                gameImage.move(c.getX(), c.getY());
                mouvingObject.previousImage(dir);
            }*/
        }
    }

    /*private void monter() {
        if (gameImage.getCoordinate().getY() - gameImage.getValueMove() >= 0) {
            gameImage.monter();
            mouvingObject.nextImgDirUp();
            if (collision(gameImage.getImgView(), Map.visualObjects)) {
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
        if (collision(gameImage.getImgView(), Map.visualObjects)) {
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
        if (collision(gameImage.getImgView(), Map.visualObjects)) {
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
        if (collision(gameImage.getImgView(), Map.visualObjects)) {
            gameImage.gauche();
            mouvingObject.previousImgDirRight();
        }
    }*/

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
