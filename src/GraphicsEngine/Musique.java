package GraphicsEngine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musique {
    String path;
    static MediaPlayer mediaPlayer;
    int indexMusique = 100;
    boolean playing = false;


    public Musique(String path) {
        //System.out.println(path);
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        this.path = path;
        mediaPlayer.play();

    }

    public String getPath() {
        return path;
    }

    void lancerMusique()
    {

        System.out.println(mediaPlayer);
        if(!playing) {
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(() -> {
                System.out.println("coo");
                mediaPlayer.stop();
                mediaPlayer.play();
            });
            playing= true;
        }
    }
    void modifMusique(Media nomMedia){
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(nomMedia);
    }
    void stopMusique(){
        mediaPlayer.stop();
    }

}
