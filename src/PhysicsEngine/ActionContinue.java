package PhysicsEngine;

import GraphicsEngine.VisualObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.LinkedList;


/**
 * Permet des actions prolongées (qui continue tant qu'il y a une collision ou que l'on change d'action).
 */
public class ActionContinue extends Action{

    private final float tps;
    private Timeline timeline;
    private final MouvingObject mouvingObject;
    private final LinkedList<String> listKeyEvent;

    /**
     * Permet la mise en place de l'action.
     * @param scene permet de déclencher l'évènement pour l'action.
     * @param carac touche du clavier pour déclencher l'action.
     * @param x valeur de déplacement sur x.
     * @param y valeur de déplacement sur y.
     * @param dir direction de déplacement de l'action.
     * @param nameAction nom de l'action.
     * @param tps la durée de l'animation (tous les tps faire quelque chose).
     * @param mouvingObject image qui tente de se déplacer.
     */
    public ActionContinue(Scene scene, String carac, double x, double y, int dir, String nameAction, float tps, MouvingObject mouvingObject){
        super(scene, carac, x, y, dir, nameAction, mouvingObject);
        listKeyEvent = new LinkedList<>();
        this.mouvingObject = mouvingObject;
        this.tps = tps;
    }

    /**
     *
     * @return timeline de l'action.
     */
    public Timeline getTimeline(){
        return timeline;
    }

    /**
     * Réécriture de la classe mère Action. Même principe mais pour réaliser des actions continues.
     * @param dir direction de l'action.
     */
    @Override
    public void doWhenEventOccur(int dir){
        if (!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())) {
            if (timeline == null)
                timeline = new Timeline();
            VisualObject.stopTimelineParallel();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {
                        mouvingObject.incrementTpsAnimate((mouvingObject.getTpsAnimate() + 1) % (int) tps);
                        if (mouvingObject.getActionNext() != null && mouvingObject.verifActionNext(getGameImage().getCoordinate().getX() + mouvingObject.getActionNext().getX(), getGameImage().getCoordinate().getY() + mouvingObject.getActionNext().getY())) {
                            if (mouvingObject.getActualAction() != null) {
                                VisualObject.stopTimelineParallel();
                                if(!VisualObject.clearOrRemoveParallel())
                                    VisualObject.removeTimeline(((ActionContinue) mouvingObject.getActualAction()).getTimeline());
                                else{
                                    VisualObject.clearTimeline(((ActionContinue)mouvingObject.getActualAction()).getTimeline(), tps);
                                    VisualObject.startTimelineParallel();
                                }
                                mouvingObject.setActualAction(null);
                            }
                            Action newAction = mouvingObject.getActionNext();
                            mouvingObject.setActionNext(null);
                            newAction.doWhenEventOccur(newAction.getDir());
                        } else {
                            if (!collisionImgView(getGameImage().getCoordinate().getX() + getX(), getGameImage().getCoordinate().getY() + getY())) {
                                mouvingObject.setActualAction(this);
                                super.doWhenEventOccur(dir);
                            } else {
                                VisualObject.stopTimelineParallel();
                                if(!VisualObject.clearOrRemoveParallel())
                                    VisualObject.removeTimeline(timeline);
                                else {
                                    //timeline.getKeyFrames().clear();
                                    VisualObject.clearTimeline(timeline, tps);
                                    VisualObject.startTimelineParallel();
                                }
                                mouvingObject.setActualAction(null);
                                mouvingObject.setActionNext(null);
                            }
                        }
                    }
            ));
            if(!VisualObject.containsTimeline(timeline))
                VisualObject.addTimeline(timeline, mouvingObject);
            VisualObject.startTimelineParallel();
        }else{
            if(mouvingObject.getActualAction()!=this && mouvingObject.getActualAction()!=null)
                mouvingObject.setActionNext(this);
        }
    }

    /**
     * Passe à l'image d'Animation suivante avec un intervalle de temps.
     * @param dir direction du mouvement.
     */
    @Override
    public void nextImage(int dir){
        if(mouvingObject.getTpsAnimate()%10==0){
            super.nextImage(dir);
        }
    }

    /**
     * Passe à l'image d'Animation précédente avec un intervalle de temps.
     * @param dir direction du mouvement.
     */
    @Override
    public void previousImage(int dir){
        if(mouvingObject.getTpsAnimate()%10==0){
            super.previousImage(dir);
        }
    }

    /**
     * Lorsque l'action est bloquée par une collision.
     */
    @Override
    public void doWhenBlock(){
        VisualObject.stopTimelineParallel();
        timeline.getKeyFrames().clear();
        VisualObject.startTimelineParallel();
        mouvingObject.setActualAction(null);
        mouvingObject.setActionNext(null);
    }

    /**
     *
     * @param keyEvent évènement de clavier actuelle.
     * @param carac touche de clavier de cette action.
     */
    @Override
    public void eventAppear(KeyEvent keyEvent, String carac){
        if(!listKeyEvent.contains(keyEvent.getCode().getChar().toLowerCase())){
            listKeyEvent.push(keyEvent.getCode().getChar().toLowerCase());
            super.eventAppear(keyEvent, carac);
        }
    }

    /**
     *
     * @param scene permet de lancer l'évènement.
     * @param carac touche qui va lancer l'action.
     * @param dir la direction demandée.
     */
    @Override
    public void runEvent(Scene scene, String carac, int dir){
        super.runEvent(scene, carac, dir);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
                    listKeyEvent.remove(keyEvent.getCode().getChar().toLowerCase());
        });
    }

}
