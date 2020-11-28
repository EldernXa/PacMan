package AnimationEngine;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Animation {

    private final ArrayList<ArrayList<Image>> listImage;
    private int ind;

    public Animation(String path) {
        ind = 0;
        listImage = new ArrayList<>();
        /*for (int i = 0; i < taille; i++) {
            listImage.add(new ArrayList<>());
        }
        File file = new File(path);
        for (File f : Objects.requireNonNull(file.listFiles())) {
            for (File fImage : Objects.requireNonNull(f.listFiles())) {
                listImage.get(Integer.parseInt(f.getName().split("sprite")[1])).add(new Image(fImage.toURI().toString()));
            }
        }*/
        changeAnimation(path);
    }

    public void changeAnimation(String path){
        ind = 0;
        listImage.clear();
        File file = new File(path);
        for(File f:Objects.requireNonNull(file.listFiles())){
            listImage.add(new ArrayList<>());
        }
        for(File f : Objects.requireNonNull(file.listFiles())){
            for(File fImage:Objects.requireNonNull(f.listFiles())){
                listImage.get(Integer.parseInt(f.getName().split("sprite")[1])).add(new Image(fImage.toURI().toString()));
            }
        }
    }

    public Image nextImage(int dir){
        return listImage.get(dir).get(++ind%(listImage.get(dir).size()));
    }


    public Image previousImage(int dir){
        return listImage.get(dir).get(Math.abs(--ind)%(listImage.get(dir).size()));
    }


    public Image getInitImage(){
        ind=0;
        return listImage.get(0).get(ind);
    }

}
