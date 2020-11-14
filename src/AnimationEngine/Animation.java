package AnimationEngine;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Animation {

    private final ArrayList<Image> listImage;
    private int ind;

    public Animation(String img){
        ind = 0;
        listImage = new ArrayList<>();
        addImg(img);
    }

    public void addImg(String img){
        listImage.add(new Image(new File(img).toURI().toString()));
    }

    public Image nextImage(){
        return listImage.get(++ind%(listImage.size()-1));
    }

    public Image previousImage(){
        return listImage.get(Math.abs(--ind)%(listImage.size()-1));
    }

    public Image getInit(){
        ind=0;
        return listImage.get(0);
    }

}
