package GraphicsEngine;

//import ReadFile.ReadFileMapPacman;
import GraphicsEngine.Maps.Map;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Action {


    private final double x;
    private final double y;
    private final String nameAction;
    private final GameImage gameImage;
    private final MouvingObject mouvingObject;
    private int dir;
    private Scene scene;


    public Action(GameImage gameImage, Scene scene, String carac, double x, double y, int dir, String
        nameAction, MouvingObject mouvingObject){
            this.nameAction = nameAction;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.scene = scene;
            this.mouvingObject = mouvingObject;
            this.gameImage = gameImage;
            runEvent(scene, carac, dir);
        }

    public Action(GameImage gameImage, Scene scene, MouvingObject mouvingObject){
        this.nameAction = "Action_IA";
        this.x = -1;
        this.y = -1;
        this.dir = -1;
        this.scene = scene;
        this.mouvingObject = mouvingObject;
        this.gameImage = gameImage;
        doWhenEventOccur(dir);


    }

    public boolean collisionImgView(double x, double y){
        ImageView imgV = new ImageView(gameImage.getImgView().getImage());
        imgV.setX(x);
        imgV.setY(y);
        return(collision(imgV));
    }


    public Scene getScene() {
        return scene;
    }

    public int getDir(){
            return dir;
    }

    public void runEvent (Scene scene, String carac,int dir){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            eventAppear(keyEvent, carac);
        });
    }

    public void eventAppear(KeyEvent keyEvent, String carac){
        if (keyEvent.getCode().getChar().toLowerCase().compareTo(
                carac.toLowerCase()) == 0) {
            doWhenEventOccur(dir);
        }
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



    public void doWhenBlock () {

    }

    private void move ( double x, double y, int dir){
        Coordinate c = new Coordinate(gameImage.getCoordinate().getX(), gameImage.getCoordinate().getY());
        if (x >= 0 && y >= 0) {
            gameImage.move(x, y);
            nextImage(dir);
        if(collision(mouvingObject)){
            gameImage.move(c.getX(), c.getY());
            previousImage(dir);
        }
        }
    }

    public void nextImage(int dir){
        mouvingObject.nextImage(dir);
    }

    public void previousImage(int dir){
        mouvingObject.previousImage(dir);
    }

    public GameImage getGameImage () {
        return gameImage;
    }


    public boolean collision (VisualObject a){
        for(VisualObject v : Map.visualObjects){
                if(intersect(a, v)) {
                    return true;
                }

        }
        return false;
    }
    public boolean collision(ImageView a){
        for(VisualObject v : Map.visualObjects){
            if(intersect(a, v)) {
                return true;
            }

        }
        return false;
    }

    public boolean intersect(VisualObject a, VisualObject b) {
        if (a.getImageView().getBoundsInParent().intersects(b.getImageView().getBoundsInParent())) {


            return b.effectCollision(a);
        }
        return false;
    }
    public boolean intersect(ImageView a, VisualObject b) {
        if (a.getBoundsInParent().intersects(b.getImageView().getBoundsInParent())) {

            return b.effectCollision(null);
        }
        return false;
    }




    }

