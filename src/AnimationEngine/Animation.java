package AnimationEngine;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class Animation {

    private final ArrayList<Image> listImageDirRight;
    private final ArrayList<Image> listImageDirLeft;
    private final ArrayList<Image> listImageDirUp;
    private final ArrayList<Image> listImageDirDown;
    private int ind;

    public Animation(String img){
        ind = 0;
        listImageDirRight = new ArrayList<>();
        listImageDirLeft = new ArrayList<>();
        listImageDirUp = new ArrayList<>();
        listImageDirDown = new ArrayList<>();
        addImgDirRight(img);
    }

    public void addImgDirRight(String img){
        listImageDirRight.add(new Image(new File(img).toURI().toString()));
    }

    public void addImgDirLeft(String img){
        listImageDirLeft.add(new Image(new File(img).toURI().toString()));
    }

    public void addImgDirUp(String img){
        listImageDirUp.add(new Image(new File(img).toURI().toString()));
    }

    public void addImgDirDown(String img){
        listImageDirDown.add(new Image(new File(img).toURI().toString()));
    }

    public Image nextImageRight(){
        return listImageDirRight.get(++ind%(listImageDirRight.size()));
    }

    public Image nextImageLeft(){
        return listImageDirLeft.get(++ind%(listImageDirLeft.size()));
    }

    public Image nextImageUp(){
        return listImageDirUp.get(++ind%(listImageDirUp.size()));
    }

    public Image nextImageDown(){
        return listImageDirDown.get(++ind%(listImageDirDown.size()));
    }

    public Image previousImageRight(){
        return listImageDirRight.get(Math.abs(--ind)%(listImageDirRight.size()));
    }

    public Image previousImageLeft(){
        return listImageDirLeft.get(Math.abs(--ind)%(listImageDirLeft.size()));
    }

    public Image previousImageUp(){
        return listImageDirUp.get(Math.abs(--ind)%(listImageDirUp.size()));
    }

    public Image previousImageDown(){
        return listImageDirDown.get(Math.abs(--ind)%(listImageDirDown.size()));
    }

    public Image getInitRight(){
        ind=0;
        return listImageDirRight.get(0);
    }

}
