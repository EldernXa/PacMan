package GraphicsEngine;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;

public abstract class UnmouvingObj extends VisualObject{

    public UnmouvingObj(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        super.getGameImage().setImage(new Image(new File(path).toURI().toString()));
    }



}
