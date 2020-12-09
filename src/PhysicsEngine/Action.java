package PhysicsEngine;

import GraphicsEngine.Coordinate;
import GraphicsEngine.GameImage;
import GraphicsEngine.Map;
import GraphicsEngine.VisualObject;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * Représente une action possible dans le jeu.
 */
public class Action {

    private final double x;
    private final double y;
    private final String nameAction;
    private final MouvingObject mouvingObject;
    private final int dir;
    private final Scene scene;
    private final String carac;
    private EventHandler<KeyEvent> eventHandler;
    private boolean start = false;


    /**
     * Constructeur initiale (liée au évènement)
     * @param scene modifier la scene actuelle (tel que déplacer une image présente sur la scene).
     * @param carac touche du clavier qui permet l'action.
     * @param x valeur de déplacement sur x.
     * @param y valeur de déplacement sur y.
     * @param dir la direction du déplacement.
     * @param nameAction le nom de l'action.
     * @param mouvingObject l'image qui tente de se déplacer.
     */
    public Action(Scene scene, String carac, double x, double y, int dir, String nameAction, MouvingObject mouvingObject){
            this.nameAction = nameAction;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.scene = scene;
            this.mouvingObject = mouvingObject;
            this.carac = carac;
            runEvent(scene, carac, dir);
        }

    /**
     * Utilisée pour l'IA
     * @param scene modifier la scene actuelle (tel que déplacer une image présente sur la scene).
     * @param mouvingObject l'image qui tente de se déplacer.
     * @param dir La direction souhaitée.
     */
    public Action(Scene scene, MouvingObject mouvingObject, int dir){
        this.nameAction = "Action_IA";
        this.dir = dir;
        this.scene = scene;
        this.mouvingObject = mouvingObject;
        this.carac = null;
        switch (dir){
            /*case -1:
                this.x = 0;
                this.y = 0;
                break;*/
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
        //doWhenEventOccur(dir);

    }

    /**
     *
     * @return le caractère qui déclenche l'action.
     */
    public String getCarac(){
        return carac;
    }

    /**
     *
     * @return l'image qui est associée à cette action.
     */
    public MouvingObject getMouvingObject(){
        return mouvingObject;
    }

    /**
     *
     * @param x coordonnées en x.
     * @param y coordonnées en y.
     * @return vrai si la position (x, y) entre en collision avec une image qu'il ne devrait pas.
     */
    public boolean collisionImgView(double x, double y){
        ImageView imgV = new ImageView(mouvingObject.getGameImage().getImgView().getImage());
        imgV.setX(x);
        imgV.setY(y);
        return(collision(imgV));
    }


    /**
     *
     * @return la scene utilisée dans cette action.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     *
     * @return la direction de cette action.
     */
    public int getDir(){
            return dir;
    }

    /**
     * Déclenche l'évènement de clavier.
     * @param scene permet de lancer l'évènement.
     * @param carac touche qui va lancer l'action.
     * @param dir la direction demandée.
     */
    public void runEvent (Scene scene, String carac,int dir){
        eventHandler = keyEvent -> {
            eventAppear(keyEvent, carac);
        };
        Map.addEventHandler(eventHandler);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
    }

    /**
     * Déclenche l'évènement de clavier (pour l'IA - l'appuie sur n'importe quel touche le fais démarrer).
     * @param scene permet de lancer l'évènement.
     * @param dir la direction demandée.
     */
    public void runEvent (Scene scene,int dir){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(!start) {
                eventAppear(keyEvent, "");
                start = true;
            }
        });
    }

    /**
     * lancement de l'évènement actuelle si la touche de clavier appuyée est la même que carac.
     * @param keyEvent évènement de clavier actuelle.
     * @param carac touche de clavier de cette action.
     */
    public void eventAppear(KeyEvent keyEvent, String carac){
        if (keyEvent.getCode().getChar().toLowerCase().compareTo(
                carac.toLowerCase()) == 0) {
            doWhenEventOccur(dir);
        }
    }

    /**
     * Permet de lancer le mouvement après vérification de l'évènement.
     * @param dir direction de l'action.
     */
    public void doWhenEventOccur ( int dir){
        move(mouvingObject.getGameImage().getCoordinate().getX() + x, mouvingObject.getGameImage().getCoordinate().getY() + y, dir);
    }

    /**
     *
     * @return la valeur du x du déplacement de l'action.
     */
    public double getX () {
        return x;
    }

    /**
     *
     * @return la valeur du y du déplacement de l'action.
     */
    public double getY () {
        return y;
    }



    public void doWhenBlock () {

    }


    /**
     * Permet le déplacement de l'image en fonction de l'action en changeant les sprites pour les animations.
     * @param x valeur du x du déplacement.
     * @param y valeur du y du déplacement.
     * @param dir direction du déplacement.
     */
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

    /**
     * passe à l'image d'Animation suivante.
     * @param dir direction du mouvement.
     */
    public void nextImage(int dir){
        mouvingObject.nextImage(dir);
    }

    /**
     * passe à l'image d'Animation précédente.
     * @param dir direction du mouvement.
     */
    public void previousImage(int dir){
        mouvingObject.previousImage(dir);
    }

    /**
     *
     * @return le gameImage associée au mouvingObject de l'action.
     */
    public GameImage getGameImage () {
        return mouvingObject.getGameImage();
    }

    /**
     *
     * @param a une image
     * @return vrai si a est en collision avec une autre images (quelle ne devrait pas), faux sinon.
     */
    public boolean collision (VisualObject a){
        for(VisualObject v : Map.visualObjects){
            if(a!=v) {
                if (intersect(a, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param a une image
     * @return vrai si a est en collision avec une autre images (quelle ne devrait pas), faux sinon.
     */
    public boolean collision(ImageView a){
        for(VisualObject v : Map.visualObjects){
            if(intersect(a, v)) {
                return true;
            }

        }
        return false;
    }

    /**
     *
     * @param a une image.
     * @param b une image.
     * @return vrai si a est en collision avec b (et que b n'est pas franchissable), faux sinon.
     */
    public boolean intersect(VisualObject a, VisualObject b) {
        if (a.getImageView().getBoundsInParent().intersects(b.getImageView().getBoundsInParent())) {
            return b.effectCollision(a);
        }
        return false;
    }

    /**
     *
     * @param a une image.
     * @param b une image.
     * @return vrai si a est en collision avec b (et que b n'est pas franchissable), faux sinon.
     */
    public boolean intersect(ImageView a, VisualObject b) {
        if (a.getBoundsInParent().intersects(b.getImageView().getBoundsInParent())) {

            return b.effectCollision(null);
        }
        return false;
    }




    }

