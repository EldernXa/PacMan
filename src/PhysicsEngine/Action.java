package PhysicsEngine;

//import ReadFile.ReadFileMapPacman;
import GraphicsEngine.Coordinate;
import GraphicsEngine.GameImage;
import GraphicsEngine.Map;
import GraphicsEngine.VisualObject;
import PhysicsEngine.MouvingObject;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Action {


    private final double x;
    private final double y;
    private final String nameAction;
    private final MouvingObject mouvingObject;
    private final int dir;
    private final Scene scene;


    public Action(Scene scene, String carac, double x, double y, int dir, String
        nameAction, MouvingObject mouvingObject){
            this.nameAction = nameAction;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.scene = scene;
            this.mouvingObject = mouvingObject;
            runEvent(scene, carac, dir);
        }

    public Action(Scene scene, MouvingObject mouvingObject, int dir){
        this.nameAction = "Action_IA";
        this.dir = dir;
        this.scene = scene;
        this.mouvingObject = mouvingObject;
        switch (dir){
            case 0:
                this.x = getGameImage().getValueMove();
                this.y = 0;
                break;
            case 1:
                this.x = 0;
                this.y = getGameImage().getValueMove();
                break;
            case 2:
                this.x = -getGameImage().getValueMove();
                this.y = 0;
                break;
            case 3:
                this.x = 0;
                this.y = -getGameImage().getValueMove();
                break;
            default:
                this.x = 0;
                this.y = 0;
                break;
        }

       runEvent(scene,dir);


    }

    public MouvingObject getMouvingObject(){
        return mouvingObject;
    }

    public boolean collisionImgView(double x, double y){
        ImageView imgV = new ImageView(mouvingObject.getGameImage().getImgView().getImage());
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
    public void runEvent (Scene scene,int dir){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            eventAppear(keyEvent);
        });
    }

    public void eventAppear(KeyEvent keyEvent, String carac){
        if (keyEvent.getCode().getChar().toLowerCase().compareTo(
                carac.toLowerCase()) == 0) {
            doWhenEventOccur(dir);
        }
    }
    public void eventAppear(KeyEvent keyEvent){

            doWhenEventOccur(dir);

    }

    public void doWhenEventOccur ( int dir){
        move(mouvingObject.getGameImage().getCoordinate().getX() + x, mouvingObject.getGameImage().getCoordinate().getY() + y, dir);
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
        Coordinate c = new Coordinate(mouvingObject.getGameImage().getCoordinate().getX(), mouvingObject.getGameImage().getCoordinate().getY());
        if (x >= 0 && y >= 0) {
            mouvingObject.move(x, y);
            nextImage(dir);
        if(collision(mouvingObject)){
            mouvingObject.move(c.getX(), c.getY());
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
        return mouvingObject.getGameImage();
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

