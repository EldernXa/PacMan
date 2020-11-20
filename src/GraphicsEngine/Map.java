package GraphicsEngine;

import GamePlay.Fantome;
import GamePlay.PacMan;
import ReadFile.PosMursAssocies;
import ReadFile.ReadFileMap2Pacman;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Map {
    private Stage stage;
    private ReadFileMap2Pacman readFileMap2Pacman;
    private ArrayList<Coordinate> realCoord = new ArrayList<>();

    public static ArrayList<VisualObject> visualObjects = new ArrayList<>();

    private double abscMax;
    private double ordMax;
    private double carreaux = 32;
    private double epaisseurMur = 18;
    private double longueurMur = 68;
    private Pane mapPane = new Pane();
    private Scene mapScene;



    public Map(Stage stage, String filePath){
        this.stage = stage;
        readFileMap2Pacman = new ReadFileMap2Pacman(filePath);
        abscMax = readFileMap2Pacman.getAbscMax();
        ordMax = readFileMap2Pacman.getOrdMax();
        creationDeMap();
        mapScene = new Scene(mapPane,(abscMax+1)*carreaux+(abscMax+2)*epaisseurMur,(ordMax+1)*carreaux+(ordMax+2)*epaisseurMur);
        stage.setScene(mapScene);

        for(PosMursAssocies posMursAssocies : readFileMap2Pacman.getTabMurFctCoord()){
            double fausseAbsc = posMursAssocies.getPointCoordinate().getX();
            double fausseOrd = posMursAssocies.getPointCoordinate().getY();
            Coordinate nouv = new Coordinate(fausseAbsc*(longueurMur-2*epaisseurMur)+(1+fausseAbsc)*epaisseurMur,fausseOrd*(longueurMur-2*epaisseurMur)+(1+fausseOrd)*epaisseurMur);
            posMursAssocies.getPointCoordinate().affichageCoord();
            System.out.print(" donne : ");
            nouv.affichageCoord();
            System.out.println();
            realCoord.add(nouv);
        }

        /*for(Coordinate coordinate : realCoord){
            coordinate.affichageCoord();
        }*/

        PacMan visualObject = new PacMan("./data/SpriteMouvement/Pacman/", new Coordinate(18*5+4*32, (8*18+7*32)+1), mapScene);
        System.out.println(visualObject.getImageView().getImage().getHeight());
        /*** Test pour ajouté un fantome (ici un autre pac-man)***/
        Fantome visualObject1 = new Fantome("./data/SpriteMouvement/Fantome/", new Coordinate(18*5+4*32, 3*32+4*18), mapScene, this,visualObject.getCoordinate());


        mapPane.getChildren().addAll(visualObject1.getGameImage().getImgView(),visualObject.getImageView());
    }

    public void creationDeMap(){
        for(PosMursAssocies posMursAssocies : readFileMap2Pacman.getTabMurFctCoord()){
            for(Character chara : posMursAssocies.getListOfWalls()){
                switch (chara){
                    case 'H':
                        creationMurHaut(posMursAssocies.getPointCoordinate().getX(),posMursAssocies.getPointCoordinate().getY());
                        break;
                    case 'D':
                        creationMurDroite(posMursAssocies.getPointCoordinate().getX(),posMursAssocies.getPointCoordinate().getY());
                        break;
                    case 'B':
                        creationMurBas(posMursAssocies.getPointCoordinate().getX(),posMursAssocies.getPointCoordinate().getY());
                        break;
                    case 'G':
                        creationMurGauche(posMursAssocies.getPointCoordinate().getX(),posMursAssocies.getPointCoordinate().getY());
                        break;
                }
            }
        }
    }

    public void creationMurHaut(double absc, double ord){
        double abscisse = (longueurMur-epaisseurMur)*absc;
        double ordonnee = (longueurMur-epaisseurMur)*ord;
        visualObjects.add(new Decor("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee),mapScene));
        mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee)).getImageView());
    }

    public void creationMurDroite(double absc, double ord){
        double abscisse = (longueurMur-epaisseurMur)*(absc+1);
        double ordonnee = (longueurMur-epaisseurMur)*ord;
        visualObjects.add(new Decor("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee),mapScene));
        mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee)).getImageView());
    }

    public void creationMurBas(double absc, double ord){
        if(ord == ordMax) {
            double abscisse = (longueurMur-epaisseurMur)*absc;
            double ordonnee = (longueurMur-epaisseurMur)*(ord+1);
            visualObjects.add(new Decor("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee),mapScene));
            mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murH18_32_18X18.png", new Coordinate(abscisse, ordonnee)).getImageView());
        }

    }

    public void creationMurGauche(double absc, double ord){
        if(absc == 0) {
            double abscisse = (longueurMur-epaisseurMur)*absc;
            double ordonnee = (longueurMur-epaisseurMur)*ord;
            visualObjects.add(new Decor("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee),mapScene));
            mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murV18X18_32_18.png", new Coordinate(abscisse, ordonnee)).getImageView());
        }
    }

    public Scene getMapScene() {
        return mapScene;
    }

    public Pane getMapPane() {
        return mapPane;
    }
    public ArrayList<Coordinate> getRealCoord() {
        return realCoord;
    }
}
