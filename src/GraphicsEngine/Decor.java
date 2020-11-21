package GraphicsEngine;

import javafx.scene.Scene;


public class Decor extends UnmouvingObj{


    public Decor(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
    }

    @Override
    public boolean effectCollision(VisualObject visualObjects) {

        return true;
    }


}
