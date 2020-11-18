package GraphicsEngine;

import GamePlay.Fantome;
import GamePlay.PacMan;
import ReadFile.PosMursAssocies;
import ReadFile.ReadFileMap2Pacman;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Map {
    Stage stage;
    ReadFileMap2Pacman readFileMap2Pacman;
    public static ArrayList<VisualObject> visualObjects = new ArrayList<>();
    double abscMax;
    double ordMax;
    double carreaux = 32;
    double epaisseurMur = 18;
    double longueurMur = 68;
    Pane mapPane = new Pane();
    Scene mapScene;

    public Map(Stage stage, String filePath){
        this.stage = stage;
        readFileMap2Pacman = new ReadFileMap2Pacman(filePath);
        abscMax = readFileMap2Pacman.getAbscMax();
        ordMax = readFileMap2Pacman.getOrdMax();
        creationDeMap();
        mapScene = new Scene(mapPane,(abscMax+1)*carreaux+(abscMax+2)*epaisseurMur,(ordMax+1)*carreaux+(ordMax+2)*epaisseurMur);
        stage.setScene(mapScene);

        PacMan visualObject = new PacMan("./data/SpriteMouvement/Pacman/pacmanDroite1.png", new Coordinate(18*5+4*32, 8*18+7*32), mapScene, mapPane);
        visualObject.addSpriteDirRight("./data/SpriteMouvement/Pacman/pacmanDroite2.png");
        visualObject.addSpriteDirRight("./data/SpriteMouvement/Pacman/pacmanDroite3.png");
        visualObject.addSpriteDirRight("./data/SpriteMouvement/Pacman/pacmanDroite4.png");
        visualObject.addSpriteDirDown("./data/SpriteMouvement/Pacman/pacmanBas1.png");
        visualObject.addSpriteDirDown("./data/SpriteMouvement/Pacman/pacmanBas2.png");
        visualObject.addSpriteDirDown("./data/SpriteMouvement/Pacman/pacmanBas3.png");
        visualObject.addSpriteDirDown("./data/SpriteMouvement/Pacman/pacmanBas4.png");
        visualObject.addSpriteDirUp("./data/SpriteMouvement/Pacman/pacmanHaut1.png");
        visualObject.addSpriteDirUp("./data/SpriteMouvement/Pacman/pacmanHaut2.png");
        visualObject.addSpriteDirUp("./data/SpriteMouvement/Pacman/pacmanHaut3.png");
        visualObject.addSpriteDirUp("./data/SpriteMouvement/Pacman/pacmanHaut4.png");
        visualObject.addSpriteDirLeft("./data/SpriteMouvement/Pacman/pacmanGauche1.png");
        visualObject.addSpriteDirLeft("./data/SpriteMouvement/Pacman/pacmanGauche2.png");
        visualObject.addSpriteDirLeft("./data/SpriteMouvement/Pacman/pacmanGauche3.png");
        visualObject.addSpriteDirLeft("./data/SpriteMouvement/Pacman/pacmanGauche4.png");

        /*** Test pour ajouté un fantome (ici un autre pac-man)***/
        Fantome visualObject1 = new Fantome("./data/SpriteMouvement/Fantome/fantomeGrisDroite1.png", new Coordinate(18*5+4*32, 3*32+4*18), mapScene, mapPane);
        visualObject1.addSpriteDirRight("./data/SpriteMouvement/Fantome/fantomeGrisDroite2.png");
        visualObject1.addSpriteDirDown("./data/SpriteMouvement/Fantome/fantomeGrisBas1.png");
        visualObject1.addSpriteDirDown("./data/SpriteMouvement/Fantome/fantomeGrisBas2.png");
        visualObject1.addSpriteDirUp("./data/SpriteMouvement/Fantome/fantomeGrisHaut1.png");
        visualObject1.addSpriteDirUp("./data/SpriteMouvement/Fantome/fantomeGrisHaut2.png");
        visualObject1.addSpriteDirLeft("./data/SpriteMouvement/Fantome/fantomeGrisGauche1.png");
        visualObject1.addSpriteDirLeft("./data/SpriteMouvement/Fantome/fantomeGrisGauche2.png");

        mapPane.getChildren().addAll(visualObject.getGameImage().getImgView(),visualObject1.getGameImage().getImgView());
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
        visualObjects.add(new Decor("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee),mapScene,mapPane));
        mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee)).getImageView());
    }

    public void creationMurDroite(double absc, double ord){
        double abscisse = (longueurMur-epaisseurMur)*(absc+1);
        double ordonnee = (longueurMur-epaisseurMur)*ord;
        visualObjects.add(new Decor("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee),mapScene,mapPane));
        mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee)).getImageView());
    }

    public void creationMurBas(double absc, double ord){
        if(ord == ordMax) {
            double abscisse = (longueurMur-epaisseurMur)*absc;
            double ordonnee = (longueurMur-epaisseurMur)*(ord+1);
            visualObjects.add(new Decor("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee),mapScene,mapPane));
            mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murH18_32_18X18.png", new Coordinate(abscisse, ordonnee)).getImageView());
        }

    }

    public void creationMurGauche(double absc, double ord){
        if(absc == 0) {
            double abscisse = (longueurMur-epaisseurMur)*absc;
            double ordonnee = (longueurMur-epaisseurMur)*ord;
            visualObjects.add(new Decor("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee),mapScene,mapPane));
            mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murV18X18_32_18.png", new Coordinate(abscisse, ordonnee)).getImageView());
        }
    }

    public Scene getMapScene() {
        return mapScene;
    }

    public Pane getMapPane() {
        return mapPane;
    }
}
