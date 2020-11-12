package GraphicsEngine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musique {
    String path;
    static MediaPlayer mediaPlayer;
    boolean playing = false;


    public Musique(String path) {
        //System.out.println(path);
        System.out.println(path);
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    void lancerMusique()
    {

        if(!playing) {
            mediaPlayer.play();
            playing= true;
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.stop();
                mediaPlayer.play();
            });

        }
    }
    void modifMusique(Media nomMedia){
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(nomMedia);
    }
    void stopMusique(){
        mediaPlayer.stop();
        playing=false;
        mediaPlayer.setMute(false);
    }
    void mute(boolean mute){
        mediaPlayer.setMute(mute);
    }

}
