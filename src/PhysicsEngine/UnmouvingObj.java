package PhysicsEngine;

import GraphicsEngine.Coordinate;
import GraphicsEngine.VisualObject;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;

public abstract class UnmouvingObj extends VisualObject {

    /**
     *
     * @param path chemin de l'image.
     * @param coordinate coordonnées initiale de l'image.
     * @param scene scene de l'image.
     */
    public UnmouvingObj(String path, Coordinate coordinate, Scene scene){
        super(path, coordinate, scene);
        super.getGameImage().setImage(new Image(new File(path).toURI().toString()));
    }



}
