package MusicEngine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musique {
    String path;
    public static MediaPlayer mediaPlayer;
    boolean playing = false;


    public Musique(String path) {
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void lancerMusique()
    {
        if(!playing) {
            mediaPlayer.play();
            mediaPlayer.setVolume(0.3);
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.stop();
                mediaPlayer.play();
            });
        }
    }
    public void modifMusique(Media nomMedia){
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(nomMedia);
    }
    public void stopMusique(){
        mediaPlayer.stop();
        playing=false;
        mediaPlayer.setMute(false);
    }
    public void mute(boolean mute){
        mediaPlayer.setMute(mute);
    }

}
