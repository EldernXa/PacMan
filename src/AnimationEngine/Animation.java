package AnimationEngine;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Animation {

    private final ArrayList<ArrayList<Image>> listImage;
    private int ind;
    private final String initPath;

    public Animation(String path) {
        ind = 0;
        initPath = path;
        listImage = new ArrayList<>();
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

    public void initAnimation(){
        changeAnimation(initPath);
    }

    public Image nextImage(int dir){
        if(listImage.get(dir).size()!=0)
            ind = (ind+1)%listImage.get(dir).size();
        return listImage.get(dir).get(ind);
    }

    public boolean verifAnimation(int dir){
        return listImage.get(dir).size()!=0;
    }

    public Image previousImage(int dir){
        if(listImage.get(dir).size()!=0)
            ind = (Math.abs(ind-1)%(listImage.get(dir).size()));
        return listImage.get(dir).get(ind);
    }


    public Image getInitImage(){
        ind=0;
        return listImage.get(0).get(ind);
    }

}
