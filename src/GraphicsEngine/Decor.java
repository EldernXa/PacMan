package GraphicsEngine;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Decor extends UnmouvingObj{

    private final boolean isCrossable = false;

    public Decor(String path, Coordinate coordinate, Scene scene, Pane root){


        super(path, coordinate, scene, root);
    }




}
