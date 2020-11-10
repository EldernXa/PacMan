package GraphicsEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    String name;
    ArrayList<Difficulte> listDifficultes = new ArrayList();
    ArrayList<Musique> listMusiques = new ArrayList<>();
    ImageViewSizePos imageJeu ;



    public Game(String name) {
        this.name = name;

        try {
            File myObj = new File("./data/Jeux/"+name+"/ListeDifficultes.txt");
            Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {
            listDifficultes.add(new Difficulte(myReader.nextLine()));

        }
        myReader.close();
    } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        for(int i = 0;i<listDifficultes.size();i++){
            System.out.println(listDifficultes.get(i).getName());
        }



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
