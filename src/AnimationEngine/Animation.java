package AnimationEngine;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Gére l'Animation.
 */
public class Animation {

    private final ArrayList<ArrayList<Image>> listImage;
    private int ind;
    private final String initPath;

    /**
     *
     * @param path chemin de départ de l'Animation.
     */
    public Animation(String path) {
        ind = 0;
        initPath = path;
        listImage = new ArrayList<>();
        changeAnimation(path);
    }

    /**
     * modifie l'Animation.
     * @param path chemin de la nouvelle Animation.
     */
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

    /**
     * initialise l'image avec le chemin initiale.
     */
    public void initAnimation(){
        changeAnimation(initPath);
    }

    /**
     *
     * @param dir direction de l'image.
     * @return la prochaine image de l'Animation.
     */
    public Image nextImage(int dir){
        if(listImage.get(dir).size()!=0)
            ind = (ind+1)%listImage.get(dir).size();
        return listImage.get(dir).get(ind);
    }

    /**
     *
     * @param dir direction de l'image.
     * @return true si l'animation est faisable, false sinon.
     */
    public boolean verifAnimation(int dir){
        return listImage.get(dir).size()!=0;
    }

    /**
     *
     * @param dir direction de l'image.
     * @return la précédente image de l'Animation.
     */
    public Image previousImage(int dir){
        if(listImage.get(dir).size()!=0)
            ind = (Math.abs(ind-1)%(listImage.get(dir).size()));
        return listImage.get(dir).get(ind);
    }

    /**
     *
     * @return l'image initiale de l'Animation.
     */
    public Image getInitImage(){
        ind=0;
        return listImage.get(0).get(ind);
    }

}
