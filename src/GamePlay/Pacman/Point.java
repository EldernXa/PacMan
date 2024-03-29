package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import PhysicsEngine.UnmouvingObj;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

/**
 * class for the points on the map
 */
public class Point extends UnmouvingObj {
    private boolean passe = false;

    public Point(Coordinate coordinate, Scene scene) {
        super("./data/Point&Fruit/point.png", coordinate, scene);
    }

    public Point(String path, Coordinate coordinate, Scene scene) {
        super(path, coordinate, scene);
    }

    /**
     * @param visualObjects image of the collision image
     * @return
     */
    @Override
    public boolean effectCollision(VisualObject visualObjects) {

        if(visualObjects!=null && visualObjects.getClass() == PacMan.class){
            PacMan pacMan = ((PacMan)visualObjects);

            if(!passe){
                pacMan.ajoutPoint(1);
                pacMan.incrementPoints();
            }
            passe = true;
            super.getImageView().setVisible(false);
        }
        return false;
    }

    public boolean isPasse() {
        return passe;
    }

    public void setPasse(boolean passe) {
        this.passe = passe;
    }
}
