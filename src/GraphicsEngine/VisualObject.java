package GraphicsEngine;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Class for all object who are displayed. There are a GameImage (for display the image) and
 * a Action (for all the action he can do).
 */
public abstract class VisualObject {
    private final static ParallelTransition parallelTransition = new ParallelTransition();
    private final static ArrayList<VisualObject> listVisualObjectTimeline = new ArrayList<>();
    private final GameImage gameImage;

    public VisualObject(String path, Coordinate coordinate, Scene scene){
        gameImage = new GameImage(path, coordinate, 1);
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
        stopTimelineParallel();
        parallelTransition.getChildren().remove(ind);
        listVisualObjectTimeline.remove(ind);
        if(parallelTransition.getChildren().size()>0)
            startTimelineParallel();
    }

    public static void stopTimelineParallel(){
        parallelTransition.stop();
    }

    public static void startTimelineParallel(){
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();
    }

    public static void clearTimelineParallel(){
        parallelTransition.getChildren().clear();
        listVisualObjectTimeline.clear();
    }

    public ImageView getImageView(){
        return gameImage.getImgView();
    }

    public GameImage getGameImage(){
        return gameImage;
    }

    public abstract boolean effectCollision(VisualObject visualObjects);

}
