package GamePlay;

import GraphicsEngine.Coordinate;
import GraphicsEngine.UnmouvingObj;
import GraphicsEngine.VisualObject;
import com.sun.javafx.runtime.VersionInfo;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Point extends UnmouvingObj {
    private boolean passe = false;
    public Point(Coordinate coordinate, Scene scene) {
        super("./data/Point&Fruit/point.png", coordinate, scene);
    }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {
        PacMan pacman = ((PacMan)visualObjects);
        if(visualObjects != null){
            if(!passe){
                pacman.ajoutPoint();
            }
            passe = true;
            super.getImageView().setVisible(false);
        }

        return false;
    }
}
