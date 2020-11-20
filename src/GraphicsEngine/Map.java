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
    private Stage stage;
    private ReadFileMap2Pacman readFileMap2Pacman;
    private ArrayList<Point> pointArrayList = new ArrayList<>();
    private ArrayList<Coordinate> realCoord = new ArrayList<>();
    private ArrayList<Coordinate> pointsCoord = new ArrayList<>();

    public static ArrayList<VisualObject> visualObjects = new ArrayList<>();

    private double abscMax;
    private double ordMax;
    private double carreaux = 32;
    private double epaisseurMur = 18;
    private double longueurMur = 68;
    private Pane mapPane = new Pane();
    private Scene mapScene;
    private Coordinate pacmanInitCoord;


    public Map(Stage stage, String filePath){
        this.stage = stage;
        readFileMap2Pacman = new ReadFileMap2Pacman(filePath);
        abscMax = readFileMap2Pacman.getAbscMax();
        ordMax = readFileMap2Pacman.getOrdMax();
        creationDeMap();
        mapScene = new Scene(mapPane,(abscMax+1)*carreaux+(abscMax+2)*epaisseurMur,(ordMax+1)*carreaux+(ordMax+2)*epaisseurMur);
        stage.setScene(mapScene);

        PacMan imgPacman = new PacMan("./data/SpriteMouvement/Pacman/", new Coordinate((epaisseurMur*5+4*(longueurMur-2*epaisseurMur))+1, 8*epaisseurMur+7*(longueurMur-2*epaisseurMur)), mapScene);
        /*** Test pour ajouté un fantome (ici un autre pac-man)***/
        Fantome imgFantome = new Fantome("./data/SpriteMouvement/Fantome/", new Coordinate(epaisseurMur*5+4*(longueurMur-2*epaisseurMur), 3*(longueurMur-2*epaisseurMur)+4*18), mapScene, this,imgPacman.getCoordinate());

        this.pacmanInitCoord = imgPacman.getCoordinate();

        fillListPointsCoord();
        fillListWithRealCoord();
        initPoints();
        //System.out.println("Taille de la lsite de poin doit être egale a 71 : " + pointArrayList.size());
        afficherPoints();

        /*for(Coordinate coordinate : realCoord){
            coordinate.affichageCoord();
        }*/



        mapPane.getChildren().addAll(imgFantome.getGameImage().getImgView(),imgPacman.getImageView());
    }

    /**
     * Crée tout les murs de la map en lisant une liste extraite d'un fichier texte
     */
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

    /**
     * Crée le mur qui se trouve en haut de la case concernée indentifiée por les cord en attribut
     * @param absc
     * @param ord
     */
    public void creationMurHaut(double absc, double ord){
        double abscisse = (longueurMur-epaisseurMur)*absc;
        double ordonnee = (longueurMur-epaisseurMur)*ord;
        visualObjects.add(new Decor("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee),mapScene));
        mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee)).getImageView());
    }

    /**
     * Crée le mur qui se trouve à droite de la case concernée indentifiée por les cord en attribut
     * @param absc
     * @param ord
     */
    public void creationMurDroite(double absc, double ord){
        double abscisse = (longueurMur-epaisseurMur)*(absc+1);
        double ordonnee = (longueurMur-epaisseurMur)*ord;
        visualObjects.add(new Decor("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee),mapScene));
        mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee)).getImageView());
    }

    /**
     * Crée le mur qui se trouve à en bas de la case concernée indentifiée por les cord en attribut
     * @param absc
     * @param ord
     */
    public void creationMurBas(double absc, double ord){
        if(ord == ordMax) {
            double abscisse = (longueurMur-epaisseurMur)*absc;
            double ordonnee = (longueurMur-epaisseurMur)*(ord+1);
            visualObjects.add(new Decor("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee),mapScene));
            mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murH18_32_18X18.png", new Coordinate(abscisse, ordonnee)).getImageView());
        }

    }

    /**
     * Crée le mur qui se trouve à gauche de la case concernée indentifiée por les cord en attribut
     * @param absc
     * @param ord
     */
    public void creationMurGauche(double absc, double ord){
        if(absc == 0) {
            double abscisse = (longueurMur-epaisseurMur)*absc;
            double ordonnee = (longueurMur-epaisseurMur)*ord;
            visualObjects.add(new Decor("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee),mapScene));
            mapPane.getChildren().add(new ImageViewSizePos("./data/Murs/murV18X18_32_18.png", new Coordinate(abscisse, ordonnee)).getImageView());
        }
    }

    public void fillListPointsCoord(){
        for(PosMursAssocies posMursAssocies : readFileMap2Pacman.getTabMurFctCoord()){
            double fausseAbsc = posMursAssocies.getPointCoordinate().getX();
            double fausseOrd = posMursAssocies.getPointCoordinate().getY();
            Coordinate nouv = new Coordinate(epaisseurMur+(longueurMur-2*epaisseurMur)/2-2.5+fausseAbsc*(longueurMur-epaisseurMur),epaisseurMur+(longueurMur-2*epaisseurMur)/2-2.5+fausseOrd*(longueurMur-epaisseurMur));
            pointsCoord.add(nouv);
        }
    }



    /**
     * Remplit une liste avec les vrai coordonnées calculées
     */
    public void fillListWithRealCoord(){
        for(PosMursAssocies posMursAssocies : readFileMap2Pacman.getTabMurFctCoord()){
            double fausseAbsc = posMursAssocies.getPointCoordinate().getX();
            double fausseOrd = posMursAssocies.getPointCoordinate().getY();
            Coordinate nouv = new Coordinate(fausseAbsc*(longueurMur-2*epaisseurMur)+(1+fausseAbsc)*epaisseurMur+1,fausseOrd*(longueurMur-2*epaisseurMur)+(1+fausseOrd)*epaisseurMur);
            realCoord.add(nouv);
        }
    }

    /**
     * Methode qui met des point sur toutes lecases de la map sauf sur celle ou le Pacman se trouve au début
     */
    public void initPoints(){
        for(Coordinate cood : pointsCoord){
            /*System.out.print("Coord Pacman");
            pacmanInitCoord.affichageCoord();
            System.out.print(", Coord point : ");
            cood.affichageCoord();
            System.out.println(", same coord ? : " + cood.compare(pacmanInitCoord));*/
            if (!cood.compare(new Coordinate(231.5,381.5))) {//revoir
                pointArrayList.add(new Point(cood, mapScene));
            }
        }
    }

    /**
     * Ajoute la liste de points au Pane de la map
     */
    public void afficherPoints(){
        for(Point point : pointArrayList){
            mapPane.getChildren().add(point.getImageView());
            visualObjects.add(point);
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
