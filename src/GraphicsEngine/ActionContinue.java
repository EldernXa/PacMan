package GraphicsEngine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;

public class ActionContinue extends Action{

    private float tps;
    private static Timeline timeline;

    public ActionContinue(GameImage image, Scene scene, String carac, char dir, int valueMove, float tps){
        super(image, scene, carac, dir, valueMove);
        this.tps = tps;
    }

    @Override
    public void doWhenEventOccur(int valueMove, char dir){
        if(timeline==null)
            timeline = new Timeline();
        timeline.stop();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(tps),
                temps->{
                    super.doWhenEventOccur(valueMove, dir);
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @Override
    void doWhenBlock(){
        timeline.stop();
    }

}
