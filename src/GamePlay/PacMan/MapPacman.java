package GamePlay.PacMan;

import GraphicsEngine.*;
import PhysicsEngine.UnmouvingObj;
import ReadFile.PosFruitNSuperPoint;
import ReadFile.PosMursAssocies;
import ReadFile.ReadFileMap;
import ReadFile.ReadFileMapPacman;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.nio.charset.CoderResult;
import java.util.ArrayList;

public class MapPacman extends Map {
    private ReadFileMapPacman readFileMapPacman;
    private ArrayList<Point> pointArrayList = new ArrayList<>();
    private ArrayList<PosFruitNSuperPoint> posFruitNSuperPointArrayList = new ArrayList<>();
    private ArrayList<UnmouvingObj> FruitNSuperPointArrayList = new ArrayList<>();
    private ArrayList<Coordinate> realCoord = new ArrayList<>();
    private ArrayList<Coordinate> pointsCoord = new ArrayList<>();
    private Coordinate pacmanInitCoord;


    public MapPacman(Stage stage, String mapFolderPath){
        super(stage, mapFolderPath,32,18,68);
        this.pacmanInitCoord = new Coordinate((getEpaisseurMur()*5+4*(getLongueurMur()-2*getEpaisseurMur()))+1, 8*getEpaisseurMur()+7*(getLongueurMur()-2*getEpaisseurMur()) +1);
        fillListPointsCoord();
        fillListWithRealCoord();
        initPoints();
        afficherPoints();
        fillListPosFruitSuperPoint();
        initFruitNSuperPoint();
        afficherFruitNSuperPoint();
        for(Coordinate coor : readFileMapPacman.getTabCoordNoPoint()){
            coor.affichageCoord();
            System.out.println();
        }
        PacMan imgPacman = new PacMan("./data/SpriteMouvement/Pacman/", new Coordinate(this.pacmanInitCoord.getX(), this.pacmanInitCoord.getY()), getMapScene(), pointArrayList.size(), stage);
        /*** Test pour ajouté un fantome (ici un autre pac-man)***/
        visualObjects.add(imgPacman);
        getMapPane().setStyle("-fx-background-color: black");
        Fantome imgFantome = new Fantome("./data/SpriteMouvement/FantomeJoueur/", new Coordinate(getEpaisseurMur()*5+4*(getLongueurMur()-2*getEpaisseurMur())+1, 3*(getLongueurMur()-2*getEpaisseurMur())+4*18+1+50), getMapScene(), this,imgPacman);
        getMapPane().getChildren().addAll(imgPacman.getImageView(), imgFantome.getImageView());
        visualObjects.add(imgFantome);
        score(imgPacman);
    }

    /**
     * Crée tout les murs de la map en lisant une liste extraite d'un fichier texte
     */
    @Override
    public void creationDeMap() {
        for(PosMursAssocies posMursAssocies : readFileMapPacman.getTabMurFctCoord()){
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
        double abscisse = (getLongueurMur()-getEpaisseurMur())*absc;
        double ordonnee = (getLongueurMur()-getEpaisseurMur())*ord;
        visualObjects.add(new Decor("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee),getMapScene()));
        getMapPane().getChildren().add(new ImageViewSizePos("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee)).getImageView());
    }

    /**
     * Crée le mur qui se trouve à droite de la case concernée indentifiée por les cord en attribut
     * @param absc
     * @param ord
     */
    public void creationMurDroite(double absc, double ord){
        double abscisse = (getLongueurMur()-getEpaisseurMur())*(absc+1);
        double ordonnee = (getLongueurMur()-getEpaisseurMur())*ord;
        visualObjects.add(new Decor("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee),getMapScene()));
        getMapPane().getChildren().add(new ImageViewSizePos("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee)).getImageView());
    }

    /**
     * Crée le mur qui se trouve à en bas de la case concernée indentifiée por les cord en attribut
     * @param absc
     * @param ord
     */
    public void creationMurBas(double absc, double ord){
        if(ord == getOrdMax()) {
            double abscisse = (getLongueurMur()-getEpaisseurMur())*absc;
            double ordonnee = (getLongueurMur()-getEpaisseurMur())*(ord+1);
            visualObjects.add(new Decor("./data/Murs/murH18_32_18X18.png",new Coordinate(abscisse,ordonnee),getMapScene()));
            getMapPane().getChildren().add(new ImageViewSizePos("./data/Murs/murH18_32_18X18.png", new Coordinate(abscisse, ordonnee)).getImageView());
        }
    }

    /**
     * Crée le mur qui se trouve à gauche de la case concernée indentifiée por les cord en attribut
     * @param absc
     * @param ord
     */
    public void creationMurGauche(double absc, double ord){
        if(absc == 0) {
            double abscisse = (getLongueurMur()-getEpaisseurMur())*absc;
            double ordonnee = (getLongueurMur()-getEpaisseurMur())*ord;
            visualObjects.add(new Decor("./data/Murs/murV18X18_32_18.png",new Coordinate(abscisse,ordonnee),getMapScene()));
            getMapPane().getChildren().add(new ImageViewSizePos("./data/Murs/murV18X18_32_18.png", new Coordinate(abscisse, ordonnee)).getImageView());
        }
    }

    public void fillListPosFruitSuperPoint(){
        for(PosFruitNSuperPoint posFruitNSuperPoint : readFileMapPacman.getTabFruitNSupPoint()){
            double fausseAbsc = posFruitNSuperPoint.getCoordinate().getX();
            double fausseOrd = posFruitNSuperPoint.getCoordinate().getY();
            Coordinate nouv = new Coordinate(getEpaisseurMur()+(getLongueurMur()-2*getEpaisseurMur())/2-7.5+fausseAbsc*(getLongueurMur()-getEpaisseurMur()),getEpaisseurMur()+(getLongueurMur()-2*getEpaisseurMur())/2-7.5+fausseOrd*(getLongueurMur()-getEpaisseurMur()));
            this.posFruitNSuperPointArrayList.add(new PosFruitNSuperPoint(nouv,posFruitNSuperPoint.getCharacter()));
        }
    }

    /**
     * Remplit une liste avec des points calculés en fct des positions possible pour le pacman
     */
    public void fillListPointsCoord(){
        for(PosMursAssocies posMursAssocies : readFileMapPacman.getTabMurFctCoord()){
            double fausseAbsc = posMursAssocies.getPointCoordinate().getX();
            double fausseOrd = posMursAssocies.getPointCoordinate().getY();
            Coordinate nouv = new Coordinate(getEpaisseurMur()+(getLongueurMur()-2*getEpaisseurMur())/2-2.5+fausseAbsc*(getLongueurMur()-getEpaisseurMur()),getEpaisseurMur()+(getLongueurMur()-2*getEpaisseurMur())/2-2.5+fausseOrd*(getLongueurMur()-getEpaisseurMur()));
            pointsCoord.add(nouv);
        }
    }

    /**
     * Remplit une liste avec les vrai coordonnées calculées
     */
    public void fillListWithRealCoord(){
        for(PosMursAssocies posMursAssocies : readFileMapPacman.getTabMurFctCoord()){
            double fausseAbsc = posMursAssocies.getPointCoordinate().getX();
            double fausseOrd = posMursAssocies.getPointCoordinate().getY();
            Coordinate nouv = new Coordinate(fausseAbsc*(getLongueurMur()-2*getEpaisseurMur())+(1+fausseAbsc)*getEpaisseurMur()+1,fausseOrd*(getLongueurMur()-2*getEpaisseurMur())+(1+fausseOrd)*getEpaisseurMur()+1);
            realCoord.add(nouv);
        }
    }

    public boolean belongToZoneInterdite(Coordinate coordinate){
        for(Coordinate coord : readFileMapPacman.getTabCoordNoPoint()){
            double fausseAbsc = coord.getX();
            double fausseOrd = coord.getY();
            Coordinate nouv = new Coordinate(getEpaisseurMur()+(getLongueurMur()-2*getEpaisseurMur())/2-2.5+fausseAbsc*(getLongueurMur()-getEpaisseurMur()),getEpaisseurMur()+(getLongueurMur()-2*getEpaisseurMur())/2-2.5+fausseOrd*(getLongueurMur()-getEpaisseurMur()));
            if (coordinate.compare(nouv)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Pane initMapPane() {
        return new Pane();
    }

    @Override
    public void initReadFile(String mapFolderPath) {
        this.readFileMapPacman = new ReadFileMapPacman(mapFolderPath);
    }

    @Override
    public Scene initMapScene() {
        System.out.println("AbscMax : " + getAbscMax());
        System.out.println("OrdMax : " + getOrdMax());
        System.out.println("Carreaux : " + getCarreaux());
        System.out.println("Epaisseur des murs : " + getEpaisseurMur());
        return new Scene(getMapPane(),(getAbscMax()+1)*getCarreaux()+(getAbscMax()+2)*getEpaisseurMur(),(getOrdMax()+1)*getCarreaux()+(getOrdMax()+2)*getEpaisseurMur());
    }

    /**
     * Methode qui met des point sur toutes lecases de la map sauf sur celle ou le Pacman se trouve au début
     */
    public void initPoints(){
        for(Coordinate cood : pointsCoord){
            if ((!cood.compare(coordPointUnderPacman()))&&(!belongToZoneInterdite(cood))) {
                pointArrayList.add(new Point(cood, getMapScene()));
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

    @Override
    public ReadFileMap getReadFileMap() {
        return readFileMapPacman;
    }

    public void initFruitNSuperPoint(){
        for(PosFruitNSuperPoint posFruitNSuperPoint : this.posFruitNSuperPointArrayList){
            switch (posFruitNSuperPoint.getCharacter()){
                case 'C':
                    System.out.println("Creation cerise");
                    FruitNSuperPointArrayList.add(new Cerise(posFruitNSuperPoint.getCoordinate(),getMapScene()));
                    break;
                case 'P':
                    FruitNSuperPointArrayList.add(new SuperPoint(posFruitNSuperPoint.getCoordinate(),getMapScene()));
                    break;
            }
        }
    }

    public void afficherFruitNSuperPoint(){
        for(UnmouvingObj fruitNSuperPoint : FruitNSuperPointArrayList){
            getMapPane().getChildren().add(fruitNSuperPoint.getImageView());
            visualObjects.add(fruitNSuperPoint);
        }
    }

    /**
     * Ajoute la liste de points au Pane de la map
     */
    public void afficherPoints(){
        for(Point point : pointArrayList){
            getMapPane().getChildren().add(point.getImageView());
            visualObjects.add(point);
        }
    }

    public PosMursAssocies getWrongCoorFromReal(Coordinate coord){
        for(int i = 0; i < realCoord.size(); i++){
            if(coord.compare(realCoord.get(i))){
                return readFileMapPacman.getTabMurFctCoord().get(i);
            }
        }
        return null;
    }

    public void score(VisualObject visualObject){
        ScorePacman score = new ScorePacman(getStage(),visualObject);
        getMapPane().getChildren().add(score.getScore());


    }

    public Scene getMapScene() {
        return super.getMapScene();
    }
    public Pane getMapPane() {
        return super.getMapPane();
    }
    public ArrayList<Coordinate> getRealCoord() {
        return realCoord;
    }

    @Override
    public double getCarreaux() {
        return super.getCarreaux();
    }

    @Override
    public double getEpaisseurMur() {
        return super.getEpaisseurMur();
    }

    @Override
    public double getLongueurMur() {
        return super.getLongueurMur();
    }
}
