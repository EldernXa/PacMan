package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import PhysicsEngine.UnmouvingObj;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

public class Point extends UnmouvingObj {
    private boolean passe = false;
    public Point(Coordinate coordinate, Scene scene) {
        super("./data/Point&Fruit/point.png", coordinate, scene);
    }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        if(visualObjects!=null && visualObjects.getClass() == PacMan.class){
            PacMan pacMan = ((PacMan)visualObjects);
            if(!passe){
                pacMan.ajoutPoint();
                pacMan.incrementPoints();
            }
            passe = true;
            super.getImageView().setVisible(false);
        }
        return false;
    }
}
