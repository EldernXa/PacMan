package GraphicsEngine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class ActionContinue extends Action{

    private float tps;
    private static Timeline timeline;

    public ActionContinue(GameImage image, Scene scene, String carac, char dir, int valueMove, float tps){
        super(image, scene, carac, dir, valueMove);
        this.tps = tps;
    }

    private boolean collisionImgView(int valueMove, char dir){
        ImageView imgV = new ImageView(new Image(super.getGameImage().getImgView().getImage().getUrl()));
        imgV.setX(super.getGameImage().getImgView().getX());
        imgV.setY(super.getGameImage().getImgView().getY());
        if(dir=='x')
            imgV.setX(imgV.getX()+valueMove);
        else if(dir=='y')
            imgV.setY(imgV.getY()+valueMove);
        return(collision(imgV, ReadFileMapPacman.visualObjects));
    }

    @Override
    public void doWhenEventOccur(int valueMove, char dir){
        if(!collisionImgView(valueMove, dir)) {
            if (timeline == null)
                timeline = new Timeline();
            timeline.stop();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis(tps),
                    temps -> {
                        super.doWhenEventOccur(valueMove, dir);
                    }
            ));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    @Override
    void doWhenBlock(){
        timeline.stop();
    }

}
