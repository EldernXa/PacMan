package GraphicsEngine;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;

public class Decor extends UnmouvingObj{

    private final boolean isCrossable = false;

    public Decor(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
    }

    @Override
    public boolean effectCollision(ArrayList<VisualObject> visualObjects) {

        return true;
    }


}
