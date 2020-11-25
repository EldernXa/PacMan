package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

public class Cerise extends Fruit {



    public Cerise(Coordinate coordinate, Scene scene) {
        super("./data/Point&Fruit/cerisee.png", coordinate, scene);
    }


    @Override
    public boolean effectCollision(VisualObject visualObjects) {

         PacMan pacman = ((PacMan)visualObjects);
        if(visualObjects != null){
            if(!isPasse()){
                pacman.ajoutPoint(25);
            }
            setPasse(true);
            super.getImageView().setVisible(false);
        }
        return false;
    }

    @Override
    public boolean isPasse() {
        return super.isPasse();
    }

    @Override
    public void setPasse(boolean passe) {
        super.setPasse(passe);
    }
}
