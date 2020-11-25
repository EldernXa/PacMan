package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

public class SuperPoint extends Point {
    public SuperPoint(Coordinate coordinate, Scene scene) {
        super("./data/Point&Fruit/gros_point.png", coordinate, scene);
    }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {

        if(visualObjects!=null && visualObjects.getClass() == PacMan.class){
            PacMan pacMan = ((PacMan)visualObjects);
            pacMan.setSuperPacman(true);
            if(!isPasse()){
                pacMan.ajoutPoint(50);
            }
            setPasse(true);
            super.getImageView().setVisible(false);
        }
        return false;
    }
}
