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
    private final ArrayList<Action> listAction;

    public VisualObject(String path, Coordinate coordinate, Scene scene, Pane root){
        listAction = new ArrayList<>();
        gameImage = new GameImage(path, coordinate, 10, root);
        listAction.add(new Action(gameImage, scene, "z", 'y', -gameImage.getValueMove()));
        listAction.add(new Action(gameImage, scene, "s", 'y', gameImage.getValueMove()));
        listAction.add(new Action(gameImage, scene, "q", 'x', -gameImage.getValueMove()));
        listAction.add(new Action(gameImage, scene, "d", 'x', gameImage.getValueMove()));
    }

    public ImageView getImageView(){
        return gameImage.getImgView();
    }
}
