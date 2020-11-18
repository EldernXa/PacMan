package AnimationEngine;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Animation {

    /*private final ArrayList<Image> listImageDirRight;
    private final ArrayList<Image> listImageDirLeft;
    private final ArrayList<Image> listImageDirUp;
    private final ArrayList<Image> listImageDirDown;*/
    private final ArrayList<ArrayList<Image>> listImage;
    private int ind;

    public Animation(String path, int taille){
        ind = 0;
        listImage = new ArrayList<>();
        for(int i=0; i<taille;i++){
            listImage.add(new ArrayList<>());
        }
        File file = new File(path);
        for(File f: Objects.requireNonNull(file.listFiles())){
            for(File fImage:Objects.requireNonNull(f.listFiles())) {
                listImage.get(Integer.parseInt(f.getName().split("sprite")[1])).add(new Image(fImage.toURI().toString()));
            }
        }
        /*listImageDirRight = new ArrayList<>();
        listImageDirLeft = new ArrayList<>();
        listImageDirUp = new ArrayList<>();
        listImageDirDown = new ArrayList<>();*/
        //addImgDirRight(img);
    }

    /*public void addImgDirRight(String img){
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
    }*/

    public Image nextImage(int dir){
        return listImage.get(dir).get(++ind%(listImage.get(dir).size()));
    }
    /*public Image nextImageRight(){
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
    }*/

    public Image previousImage(int dir){
        return listImage.get(dir).get(Math.abs(--ind)%(listImage.get(dir).size()));
    }

    /*public Image previousImageRight(){
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
    }*/

    public Image getInitImage(){
        ind=0;
        return listImage.get(0).get(ind);
    }

}
