package GraphicsEngine;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;

public class Decor extends UnmouvingObj{

    private final boolean isCrossable = false;

    public Decor(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        super.getGameImage().setImage(new Image(new File(path).toURI().toString()));
    }




}
