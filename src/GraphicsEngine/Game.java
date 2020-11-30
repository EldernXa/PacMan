package GraphicsEngine;

import MusicEngine.Musique;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for the game
 */
public class Game {
    String name;
    ArrayList<Difficulte> listDifficultes = new ArrayList();
    ArrayList<Musique> listMusiques = new ArrayList<>();
    ImageViewSizePos imageJeu = new ImageViewSizePos();

    public Game(){
    }

    public Game(String name) {
        this.name = name;
        setListDifficultesFromString(name);

    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * it set the list of difficulties for the game
     * @param name name of the game
     */
    public void setListDifficultesFromString(String name) {
        try {
            File myObj = new File("./data/Jeux/"+name+"/ListeDifficultes.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                boolean egale = false;
                String difficulteStr = myReader.nextLine();
                //les difficultés sont écrites par ordre croissant

                for(Difficulte diff : listDifficultes){
                    if(diff.getName().equals(difficulteStr)){
                        egale = true;
                    }
                }

                if(!egale){
                    listDifficultes.add(new Difficulte(difficulteStr));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void setImage(ImageViewSizePos imageViewSizePos) {
        this.imageJeu.getImageView().setImage(imageViewSizePos.getImageView().getImage());
    }

    public void setListDifficultes(ArrayList<Difficulte> listDifficultes) {
        this.listDifficultes = listDifficultes;
    }

    public ArrayList<Difficulte> getListDifficultes() {
        return listDifficultes;
    }

    public ImageViewSizePos getImageJeu() {
        return imageJeu;
    }

    public ArrayList<Musique> getListMusiques() {
        return listMusiques;
    }

    public void setListMusiques(ArrayList<Musique> listMusiques) {
        this.listMusiques = listMusiques;
    }

    public void setImageJeu(ImageViewSizePos imageJeu) {
        this.imageJeu = imageJeu;
    }

    public String getName() {
        return name;
    }
}
