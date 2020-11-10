package GraphicsEngine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musique {
    String path;
    MediaPlayer mediaPlayer;
    int indexMusique = 100;


    public Musique(String path) {
        System.out.println(path);
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        this.path = path;
    }

    void lancerMusique(){
        mediaPlayer.play();
    }
    void modifMusique(Media nomMedia){
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(nomMedia);
    }
    void stopMusique(){
        mediaPlayer.stop();
    }
}
