package PhysicsEngine;

import AnimationEngine.Animation;
import GraphicsEngine.Coordinate;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

import java.util.ArrayList;

/**
 * Représente une image qui bouge.
 */
public abstract class MouvingObject extends VisualObject {
    private final ArrayList<Action> listAction;
    private Action actionNext;
    private Action actualAction;
    private final Animation animation;
    private int valueAnimate;
    private int dir;

    /**
     *
     * @param path chemin de l'image.
     * @param coordinate coordonnées initiale de l'image.
     * @param scene scene de l'image.
     */
    public MouvingObject(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        dir = 0;
        actionNext = null;
        actualAction = null;
        animation = new Animation(path);
        super.getGameImage().setImage(animation.getInitImage());
        listAction = new ArrayList<>();
    }

    /**
     *
     * @param dir direction de l'image.
     */
    public void setDir(int dir){
        this.dir = dir;
    }

    /**
     *
     * @return direction de l'image.
     */
    public int getDir(){
        return dir;
    }

    /**
     *
     * @return liste d'action de cette image.
     */
    public ArrayList<Action> getListAction() {
        return listAction;
    }

    /**
     * Initialise l'Animation pour l'image.
     */
    public void initAnimation(){
        super.getGameImage().setImage(animation.getInitImage());
    }

    /**
     * Permet de changer les images concernant les Animations.
     * @param path
     */
    public void changeSpriteAnimation(String path){
        animation.changeAnimation(path);
    }

    /**
     * Remet les images initiales de l'Animation.
     */
    public void initSpriteAnimation(){
        animation.initAnimation();
    }

    /**
     * Change la mémoire tamporaire de l'action suivante avec une nouvelle action.
     * @param action nouvelle action à mémoriser.
     */
    public void setActionNext(Action action){
        actionNext = action;
    }

    /**
     * Change l'Action actuelle mémorisée pour une nouvelle action.
     * @param action nouvelle action à mémoriser.
     */
    public void setActualAction(Action action){
        actualAction = action;
        if(actualAction!=null)
            setDir(actualAction.getDir());
    }

    /**
     *
     * @return l'Action actuelle.
     */
    public Action getActualAction(){
        return actualAction;
    }

    /**
     *
     * @param x coordonnées x
     * @param y coordonnées y
     * @return vrai si l'Action mémorisée dans la mémoire tamporaire est possible, faux sinon.
     */
    public boolean verifActionNext(double x, double y){
        if(actionNext!=null){
            return !actionNext.collisionImgView(x, y);
        }
        return true;
    }

    /**
     * Vide la liste des actions.
     */
    public void clearListAction(){
        listAction.clear();
    }

    /**
     *
     * @return l'Action mémorisée dans la mémoire tamporaire.
     */
    public Action getActionNext(){
        return actionNext;
    }

    /**
     * Passe à l'image d'Animation suivante.
     * @param dir direction de l'image.
     */
    public void nextImage(int dir){
        if(animation.verifAnimation(dir))
            super.getGameImage().setImage(animation.nextImage(dir));
    }

    /**
     * Passe à l'image d'Animation précédente.
     * @param dir direction de l'image.
     */
    public void previousImage(int dir){
        super.getGameImage().setImage(animation.previousImage(dir));
    }

    /**
     * ajouter une Action dans la liste d'Action.
     * @param action action à rajouter.
     */
    public void addAction(Action action){
        this.listAction.add(action);
    }

    /**
     * Permet le changement d'Animation à intervalle de temps.
     * @param value valeur à rajouter au temps d'animation.
     */
    public void incrementTpsAnimate(int value){
        valueAnimate = value;
    }

    /**
     *
     * @return la valeur d'Animation.
     */
    public int getTpsAnimate(){
        return valueAnimate;
    }

    /**
     * bouge l'image au coordonnées demandées.
     * @param x coordonnées x
     * @param y coordonnées y
     */
    public void move(double x, double y){
        super.getGameImage().getCoordinate().setX(x);
        super.getGameImage().getCoordinate().setY(y);
        super.getImageView().setX(super.getGameImage().getCoordinate().getX());
        super.getImageView().setY(super.getGameImage().getCoordinate().getY());
    }


}
