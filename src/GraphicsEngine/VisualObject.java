package GraphicsEngine;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
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
    private final static ParallelTransition parallelTransition = new ParallelTransition();
    private final static ArrayList<VisualObject> listVisualObjectTimeline = new ArrayList<>();
    private final GameImage gameImage;

    public VisualObject(String path, Coordinate coordinate, Scene scene, Pane root){ // a quoi ça sert, scene et root never used
        gameImage = new GameImage(path, coordinate, 10);
    }

    public static int addTimeline(Timeline timeline, VisualObject visualObject){
        int value = -1;
        for(int i = 0; i<listVisualObjectTimeline.size();i++)
        {
            if(listVisualObjectTimeline.get(i) == visualObject)
            {
                value = i;
            }
        }
        if(value>=0){
            parallelTransition.getChildren().remove(value);
            listVisualObjectTimeline.remove(value);
        }
        parallelTransition.getChildren().add(timeline);
        listVisualObjectTimeline.add(visualObject);
        return parallelTransition.getChildren().size()-1;
    }

    public static void removeTimeline(int ind){
        parallelTransition.getChildren().remove(ind);
        listVisualObjectTimeline.remove(ind);
        if(parallelTransition.getChildren().size()<=0)
            parallelTransition.stop();
    }

    public static void stopTimelineParallel(){
        parallelTransition.stop();
    }

    public static void startTimelineParallel(){
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();
    }

    public ImageView getImageView(){
        return gameImage.getImgView();
    }

    public GameImage getGameImage(){
        return gameImage;
    }
}
