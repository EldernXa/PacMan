package GraphicsEngine;

import GamePlay.PacMan;
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
        return false;
    }
}
