package GraphicsEngine;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Class for all object who are displayed. There are a GameImage (for display the image) and
 * a Action (for all the action he can do).
 */
public class VisualObject {
    private final GameImage gameImage;

    public VisualObject(String path, Coordinate coordinate, Scene scene, Pane root){
        gameImage = new GameImage(path, coordinate, 10, root);
    }

    public ImageView getImageView(){
        return gameImage.getImgView();
    }

    public GameImage getGameImage(){
        return gameImage;
    }
}
