package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import PhysicsEngine.UnmouvingObj;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

public class Fruit extends UnmouvingObj {
    private boolean passe = false;
    public Fruit(String path, Coordinate coordinate, Scene scene) {
        super(path, coordinate, scene);
    }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        /*PacMan pacman = ((PacMan)visualObjects);
        if(visualObjects != null){
            if(!passe){
                pacman.ajoutPoint();
            }
            passe = true;
            super.getImageView().setVisible(false);
        }
        return false;*/
        // test
        return false;
    }

    public boolean isPasse() {
        return passe;
    }

    public void setPasse(boolean passe) {
        this.passe = passe;
    }
}
