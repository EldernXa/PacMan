package GraphicsEngine;

//import ReadFile.ReadFileMapPacman;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Action {


    private final double x;
    private final double y;
    private final String nameAction;
    private final GameImage gameImage;
    private final MouvingObject mouvingObject;
    private int dir;


    public Action(GameImage gameImage, Scene scene, String carac, double x, double y, int dir, String
        nameAction, MouvingObject mouvingObject){
            this.nameAction = nameAction;
            this.x = x;
            this.y = y;
            this.dir = dir;

            this.mouvingObject = mouvingObject;
            this.gameImage = gameImage;
            runEvent(scene, carac, dir);
        }
    /*public Action(GameImage gameImage, Scene scene, char dir, int valueMove, MouvingObject mouvingObject){
            this.mouvingObject = mouvingObject;
            this.gameImage = gameImage;
            this.scene = scene;
            runEventWithoutKey(valueMove, dir);
        }*/

        public int getDir(){
            return dir;
        }
        private void runEvent (Scene scene, String carac,int dir){
            scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                if (keyEvent.getCode().getChar().toLowerCase().compareTo(
                        carac.toLowerCase()) == 0) {
                    doWhenEventOccur(dir);
                }
            });
        }

        public void doWhenEventOccur ( int dir){
            move(gameImage.getCoordinate().getX() + x, gameImage.getCoordinate().getY() + y, dir);
        }

        public double getX () {
            return x;
        }

        public double getY () {
            return y;
        }



        void doWhenBlock () {

        }

        private void move ( double x, double y, int dir){
            Coordinate c = new Coordinate(gameImage.getCoordinate().getX(), gameImage.getCoordinate().getY());
            if (x >= 0 && y >= 0) {
                gameImage.move(x, y);
                mouvingObject.nextImage(dir);
            if(collision(gameImage.getImgView())){
                gameImage.move(c.getX(), c.getY());
                mouvingObject.previousImage(dir);
            }
            }
        }

        public GameImage getGameImage () {
            return gameImage;
        }


        public boolean collision (ImageView a){
            for(VisualObject v : Map.visualObjects){
                    if(intersect(a, v.getImageView())) {
                        return true;
                    }

            }
            return false;
        }

        public boolean intersect(ImageView imgViewA, ImageView imgViewB){
            if(imgViewA.getX()>=imgViewB.getX() && imgViewA.getX()<=(imgViewB.getX()+imgViewB.getImage().getWidth()) &&
                    imgViewA.getY()>=imgViewB.getY() && imgViewA.getY()<=(imgViewB.getY()+imgViewB.getImage().getHeight())) {
                return true;
            }
            else if((imgViewA.getX()+imgViewA.getImage().getWidth())>=imgViewB.getX() && (imgViewA.getX()+imgViewA.getImage().getWidth())<=(imgViewB.getX()
                    +imgViewB.getImage().getWidth()) && (imgViewA.getY()+imgViewA.getImage().getHeight())>=imgViewB.getY()&&(imgViewA.getY()+imgViewA.getImage().getHeight())<=(imgViewB.getY()
                    +imgViewB.getImage().getHeight())) {
                return true;
            }else if(imgViewA.getX()>=imgViewB.getX() && imgViewA.getX()<=(imgViewB.getX()+imgViewB.getImage().getWidth()) &&
                    (imgViewA.getY()+imgViewA.getImage().getHeight())>=imgViewB.getY() && (imgViewA.getY()+imgViewA.getImage().getHeight())<=(imgViewB.getY()+imgViewB.getImage().getHeight())){
                return true;
            }else if((imgViewA.getX()+imgViewA.getImage().getHeight())>=imgViewB.getX() && (imgViewA.getX()+imgViewA.getImage().getHeight())<=(imgViewB.getX()+imgViewB.getImage().getWidth()) &&
                    imgViewA.getY()>=imgViewB.getY() && imgViewA.getY()<=(imgViewB.getY()+imgViewB.getImage().getHeight())) {
                return true;
            }

            return false;
        }


    }

