package GamePlay.Pacman;

import GraphicsEngine.*;
import PhysicsEngine.ActionContinue;
import PhysicsEngine.MouvingObject;
import ReadFile.ReadFileCommandes;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

/**
 * class for the Pacman
 */
public class PacMan extends MouvingObject {

    private final int nbViesMax = 3;
    private SimpleIntegerProperty nbVies_restantes;
    private SimpleIntegerProperty nbPoints;
    private final int nbPointsMapMax;
    private int nbPointsMap;
    private float valueTps = (float)10;
    private Coordinate coordinate;
    private ChangeListener<Number> changeListenerVies;
    private ChangeListener<Number> changeListenerPoint;
    private final Stage stage;
    private final ReadFileCommandes pacmanControle;
    private boolean superPacman;

    public PacMan(String path, Coordinate coordinate, Scene scene, int nbPointsMapMax, Stage stage){
        super(path, coordinate, scene);
        superPacman = false;
        this.stage = stage;
        nbPoints = new SimpleIntegerProperty(0);
        this.nbPointsMapMax = nbPointsMapMax;
        this.nbPointsMap = 0;
        nbVies_restantes = new SimpleIntegerProperty(nbViesMax);
        pacmanControle = new ReadFileCommandes("./data/Controles/Pacman/controles.txt",true);
        for(int i = 0; i<pacmanControle.getDirectionSolo().size(); i++){

            addAction(new ActionContinue(scene,
                    pacmanControle.getToucheSolo().get(i), pacmanControle.getxCoordSolo().get(i),
                    pacmanControle.getyCoordSolo().get(i), i, pacmanControle.getDirectionSolo().get(i),
                    valueTps, this));
        }
        this.coordinate = coordinate;
    }

    /**
     * increments the number of points by 1
     */
    public void incrementPoints(){
        nbPointsMap++;
        if(getNbPointsMap()==getNbPointsMapMax()){
            ConclusionPacman conclusion = new ConclusionPacman(stage,true,nbPoints.get(), this);
        }
    }

    public int getNbPointsMapMax(){
        return nbPointsMapMax;
    }

    public int getNbPointsMap(){
        return nbPointsMap;
    }

    /**
     *
     * @param coordinate position of the new coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = new Coordinate(coordinate.getX(),coordinate.getY());
        super.getImageView().setX(coordinate.getX());
        super.getImageView().setY(coordinate.getY());
        super.move(coordinate.getX(),coordinate.getY());
    }

    /**
     * when the pacman dies
     */
    public void death(){
        setCoordinate(super.getGameImage().getCoordInit());
        diminueVies();
        super.initAnimation();

    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getNbVies() {
        return nbViesMax;
    }

    public int getNbVies_restantes() {
        return nbVies_restantes.get();
    }


    /**
     *
     * @return nb de points du Pacman.
     */
    public int getNbPoints() {
        return nbPoints.get();
    }

    /**
     *
     * @param visualObjects image of the colliion object
     * @return true si on peut passer, false sinon.
     */
    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        if(visualObjects!=null && (visualObjects.getClass()==Fantome.class||visualObjects.getClass().getGenericSuperclass()==Fantome.class)){
            Fantome fantome = ((Fantome) visualObjects);
            if(isSuperPacman()){
                fantome.death();
            }
            else {
                fantome.death();
                death();
            }

        }
        return false;
    }

    /**
     *
     * @param nb number of the points that we want to add
     */
    public void ajoutPoint(int nb){

        nbPoints.set(nbPoints.get()+nb);
    }

    /**
     * diminue la vie de PacMan.
     */
    public void diminueVies() {

         nbVies_restantes.set(nbVies_restantes.get()-1);

    }

    public void setListenerPoint(ChangeListener<Number> changeListener){
        changeListenerPoint = changeListener;
        nbPoints.addListener(changeListenerPoint);
    }
    public void setListenerNbVies(ChangeListener<Number> changeListener){
        changeListenerVies=changeListener;
        nbVies_restantes.addListener(changeListenerVies);
    }
    public void removeListenerPoint(){
        if(changeListenerPoint != null)
            nbPoints.removeListener(changeListenerPoint);
        changeListenerPoint = null;
    }
    public void removeListenerVies(){
        if(changeListenerVies != null)
            nbVies_restantes.removeListener(changeListenerVies);
        changeListenerVies = null;
    }

    /**
     *
     * @return true si Pacman est en super pacman, false sinon.
     */
    public boolean isSuperPacman() {
        return superPacman;
    }

    /**
     * allows the pacman to turn into a superpacman for 5 seconds
     */
    public void superPacman(){
        setSuperPacman(true);

        for(VisualObject v : Map.visualObjects){
            if(v.getClass().getGenericSuperclass() == Fantome.class){
                ((Fantome)v).changeSpriteAnimation("./data/SpriteMouvement/FantomePeur/");
                ((Fantome)v).initAnimation();
            }
        }
        new Thread(() -> {
            //mettre la nouvelle image du superpacman
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setSuperPacman(false);
            for(VisualObject v : Map.getVisualObjects()){
                if(v.getClass().getGenericSuperclass() == Fantome.class){
                    ((Fantome)v).initSpriteAnimation();
                    ((Fantome)v).initAnimation();
                }
            }
        }).start();

    }

    public void setSuperPacman(boolean superPacman) {
        this.superPacman = superPacman;
    }
}
