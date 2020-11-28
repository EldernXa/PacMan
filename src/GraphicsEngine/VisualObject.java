package GraphicsEngine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

    public static void removeTimeline(Timeline timeline){
        stopTimelineParallel();
        if(parallelTransition.getChildren().contains(timeline)) {
            listVisualObjectTimeline.remove(parallelTransition.getChildren().indexOf(timeline));
            parallelTransition.getChildren().remove(timeline);
        }

        if(parallelTransition.getChildren().size()>0)
            startTimelineParallel();
    }

    public static void stopTimelineParallel(){
        parallelTransition.stop();
    }

    public static boolean containsTimeline(Timeline timeline){
        return parallelTransition.getChildren().contains(timeline);
    }

    public static void startTimelineParallel(){
        if(verifContainsParallel()) {
            parallelTransition.setCycleCount(Animation.INDEFINITE);
            parallelTransition.playFromStart();
        }
    }

    private static boolean verifContainsParallel(){
        for(Animation t : parallelTransition.getChildren()){
            if(((Timeline) t).getKeyFrames().size()>0)
                return true;
        }
        return false;
    }

    public static void clearTimelineParallel(){
        parallelTransition.getChildren().clear();
        listVisualObjectTimeline.clear();
    }

    public static boolean clearOrRemoveParallel(){
        return parallelTransition.getChildren().size()>1;
    }

    public static void clearTimeline(Timeline timeline, float tps){
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(tps),
                temps->{

                }
        ));
    }

    public ImageView getImageView(){
        return gameImage.getImgView();
    }

    public GameImage getGameImage(){
        return gameImage;
    }

    public abstract boolean effectCollision(VisualObject visualObjects);

}