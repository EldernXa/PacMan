package GraphicsEngine;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VisualObject {
    private final GameImage gameImage;
    private final Action action;

    public VisualObject(String path, Coordinate coordinate, Scene scene, Pane root, Coordinate maxCoord){
        gameImage = new GameImage(path, coordinate, 10, root);
        action = new Action(gameImage, scene, maxCoord);
        action.runEvent();
    }

    public ImageView getImageView(){
        return gameImage.getImgView();
    }
}
