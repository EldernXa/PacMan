package GraphicsEngine;

import GamePlay.Fantome;
import GamePlay.PacMan;
import GamePlay.Point;
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

        PacMan imgPacman = new PacMan("./data/SpriteMouvement/Pacman/", new Coordinate((epaisseurMur*5+4*(longueurMur-2*epaisseurMur))+1, 8*epaisseurMur+7*(longueurMur-2*epaisseurMur) +1), mapScene);
        /*** Test pour ajouté un fantome (ici un autre pac-man)***/


        this.pacmanInitCoord = imgPacman.getCoordinate();
        visualObjects.add(imgPacman);
        fillListPointsCoord();
        fillListWithRealCoord();
        initPoints();
        afficherPoints();

        /*for(Coordinate coordinate : realCoord){
            System.out.print("Coordonnées de base : ");
            coordinate.affichageCoord();
            System.out.print("Coordonnées calculées : ");
            getWrongCoorFromReal(coordinate).getPointCoordinate().affichageCoord();
            System.out.println();
        }*/
        Fantome imgFantome = new Fantome("./data/SpriteMouvement/Fantome/", new Coordinate(epaisseurMur*5+4*(longueurMur-2*epaisseurMur)+1, 3*(longueurMur-2*epaisseurMur)+4*18+1), mapScene, this,imgPacman.getCoordinate());
        visualObjects.add(imgFantome);
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

    /**
     * Remplit une liste avec des points calculés en fct des positions possible pour le pacman
     */
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
            Coordinate nouv = new Coordinate(fausseAbsc*(longueurMur-2*epaisseurMur)+(1+fausseAbsc)*epaisseurMur+1,fausseOrd*(longueurMur-2*epaisseurMur)+(1+fausseOrd)*epaisseurMur+1);
            realCoord.add(nouv);
        }
    }

    /**
     * Methode qui met des point sur toutes lecases de la map sauf sur celle ou le Pacman se trouve au début
     */
    public void initPoints(){
        for(Coordinate cood : pointsCoord){
            if (!cood.compare(coordPointUnderPacman())) {
                pointArrayList.add(new Point(cood, mapScene));
            }
        }
    }

    /**
     * Permet de récuperer les coordonnées du point qui se trouverai en dessous de pacman au lancement du jeu et de
     * se servir de cela pour ne pas le creer
     * @return
     */
    public Coordinate coordPointUnderPacman(){
        for(Coordinate coord : pointsCoord){
            if(((coord.getX() >= pacmanInitCoord.getX())&&(coord.getX() <= pacmanInitCoord.getX()+30))&&(coord.getY() >= pacmanInitCoord.getY())&&(coord.getY() <= pacmanInitCoord.getY()+30)){
                return coord;
            }
        }
        return null;
    }

    public ReadFileMap2Pacman getReadFileMap2Pacman() {
        return readFileMap2Pacman;
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

    public PosMursAssocies getWrongCoorFromReal(Coordinate coord){
        for(int i = 0; i < realCoord.size(); i++){
            if(coord.compare(realCoord.get(i))){
                return readFileMap2Pacman.getTabMurFctCoord().get(i);
            }
        }
        return null;
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
