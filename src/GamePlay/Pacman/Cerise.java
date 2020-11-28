package GamePlay.Pacman;

import GraphicsEngine.Coordinate;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;

public class Cerise extends Fruit {



    public Cerise(Coordinate coordinate, Scene scene) {
        super("./data/Point&Fruit/cerisee.png", coordinate, scene);
    }


    @Override
    public boolean effectCollision(VisualObject visualObjects) {


        if(visualObjects!=null && visualObjects.getClass() == PacMan.class){
            PacMan pacman = ((PacMan)visualObjects);
            if(!isPasse()){
                pacman.ajoutPoint(100);
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
