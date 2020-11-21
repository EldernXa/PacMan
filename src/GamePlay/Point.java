package GamePlay;

import GraphicsEngine.Coordinate;
import GraphicsEngine.UnmouvingObj;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

public class Point extends UnmouvingObj {
    private boolean passe = false;
    public Point(Coordinate coordinate, Scene scene) {
        super("./data/Point&Fruit/point.png", coordinate, scene);
    }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        if(visualObjects != null){
            PacMan pacMan;
            try {
                pacMan = ((PacMan)visualObjects);
            }catch (Exception e){
                return false;
            }

            if(!passe){
                pacMan.ajoutPoint();
            }
            passe = true;
            super.getImageView().setVisible(false);
        }
        return false;
    }
}
