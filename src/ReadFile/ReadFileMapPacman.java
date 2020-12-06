package ReadFile;

import GraphicsEngine.Coordinate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe permettant la lecture de tous les fichier servant à la création de la map pacman et de tous
 * les objets dessus
 */
public class ReadFileMapPacman extends ReadFileMap{
    private ArrayList<Coordinate> tabCoordNoPoint = new ArrayList<>();
    private ArrayList<PosFruitNSuperPointNPacManNFantoms> tabFruitNSupPoint = new ArrayList<>();
    private ArrayList<PosFruitNSuperPointNPacManNFantoms> tabPacmanNFantoms = new ArrayList<>();
    private File mapFile;
    private File pointsNFruit;
    private File pacmanAndFantoms;

    /**
     * Constructeur qui a partir d'un lien vers le dossier contenant les fichiers contenant les informations relatives
     * à la map de pacman initialise la liste des murs lu, initialise la liste des points interdits et des coordonnées des fruits
     * et initialise les coordonnées deu Pacman et des différents fantomes
     * @param mapFolderPath
     */
    public ReadFileMapPacman(String mapFolderPath){
        this.mapFile = new File(mapFolderPath + "PacmanMap.txt");
        this.pointsNFruit = new File(mapFolderPath + "Point&Fruit.txt");
        this.pacmanAndFantoms = new File(mapFolderPath + "PacMan_Fantoms.txt");
        initTabMurFctCoord();
        initTabNoPointFruitNSupPoint();
        initPosPacManNFantoms();
        /*for(PosFruitNSuperPointNPacManNFantoms posFSPPF : tabPacmanNFantoms){
            System.out.println();
            System.out.print("Coord : ");
            posFSPPF.getCoordinate().affichageCoord();
            System.out.print(" Character : ");
            System.out.println(posFSPPF.getCharacter());
        }*/
    }

    /**
     * Initialisation des tableaux de fruit et des pos interdites pour les points
     */
    public void initTabNoPointFruitNSupPoint(){
        initTabCoordNoPoint();
        initTabFruitNSupPoint();
    }

    /**
     * Initialise le tableau des coordonnées interdites pour les points
     */
    public void initTabCoordNoPoint(){
        try{
            Scanner pointNFruitScanner = new Scanner(pointsNFruit);
            String currentLine;
            while(pointNFruitScanner.hasNextLine()){
                currentLine = pointNFruitScanner.nextLine();
                while((currentLine.equals("zone interdites :")||(currentLine.equals("")||(currentLine.equals("fruits et super points :"))))){
                    currentLine = pointNFruitScanner.nextLine();
                }
                double absc = recupererAbsc(currentLine);
                double ord = recupererOrd(currentLine);
                ajouterATabCoordNoPoint(new Coordinate(absc,ord));
                }
            pointNFruitScanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Ajout à la listes des coordonnée interdite pour les points dans le cas ou la coordonnée n'appartiendrait pas déjà à la liste,
     * permet d'éviter les doublons
     * @param coordinate
     */
    public void ajouterATabCoordNoPoint(Coordinate coordinate){
        boolean add = true;
        for(Coordinate cood : tabCoordNoPoint){
            if(cood.compare(coordinate)){
                add = false;
            }
        }
        if(add){
            tabCoordNoPoint.add(coordinate);
        }
    }

    /**
     * Initialise la liste des fruits et des super point
     */
    public void initTabFruitNSupPoint(){
        try {
            Scanner pointNFruitScanner = new Scanner(pointsNFruit);
            String currentLine = pointNFruitScanner.nextLine();
            while(!currentLine.equals("fruits et super points :")){
                currentLine = pointNFruitScanner.nextLine();
            }
            while(pointNFruitScanner.hasNextLine()){
                currentLine = pointNFruitScanner.nextLine();
                double abs = recupererAbsc(currentLine);
                double ord = recupererOrd(currentLine);
                char character = recupererCharacter(currentLine);
                tabFruitNSupPoint.add(new PosFruitNSuperPointNPacManNFantoms(new Coordinate(abs,ord),character));
            }
            pointNFruitScanner.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Permet de récupérer le character dans le cas ou le fichier n'aurait pas une liste de character
     * @param currentLine
     * @return
     */
    public char recupererCharacter(String currentLine){
        int i = 0;
            while(currentLine.charAt(i) != ':'){
                i++;
            }
            i++;
            return currentLine.charAt(i);
    }

    /**
     * Récupère les abscisse et ordonnées notées dans le ficher et récupère la liste des characters également
     * ajoute un PosMursAssocies avec les informations récupérées
     */
    public void initTabMurFctCoord(){
        try {
            Scanner mapFileScanner = new Scanner(mapFile);
            while(mapFileScanner.hasNextLine()){
                String currentLine = mapFileScanner.nextLine();
                double absc = recupererAbsc(currentLine);
                double ord = recupererOrd(currentLine);

                if(getAbscMax() < absc){
                    setAbscMax(absc);
                }
                if(getOrdMax() < ord){
                    setOrdMax(ord);
                }

                ArrayList<Character> listOfWalls = recupererListOfWalls(currentLine);

                getTabMurFctCoord().add(new PosMursAssocies(new Coordinate(absc,ord),listOfWalls));

            }
            mapFileScanner.close();
        }catch(Exception e){
            System.out.println("Le fichier n'existe pas");
            System.exit(0); // quitte l'application
        }
    }

    /**
     * Lit dans le fichier correspondant les positions du Pacman et des fantomes
     */
    public void initPosPacManNFantoms(){
        try {
            Scanner pacManNFantomScanner = new Scanner(pacmanAndFantoms);
            while(pacManNFantomScanner.hasNextLine()){
                String currentLine = pacManNFantomScanner.nextLine();
                double absc = recupererAbsc(currentLine);
                double ord = recupererOrd(currentLine);
                initTabPacmanNFantoms(new Coordinate(absc,ord), currentLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialise la liste des Pacman et des fantomes en fonction des informations lu dans le fichier correspondant
     * @param coordinate
     * @param currentLine
     */
    public void initTabPacmanNFantoms(Coordinate coordinate, String currentLine ){
        int i = 0;
        while(currentLine.charAt(i) != ':'){
            i++;
        }
        i++;
        while(i < currentLine.length()){
            if(currentLine.charAt(i) == ','){
                i++;
            }
            else{
                char temporaire = currentLine.charAt(i);
                tabPacmanNFantoms.add(new PosFruitNSuperPointNPacManNFantoms(coordinate,temporaire));
                i++;
            }
        }
    }

    /**
     * Récupere l'abscisse en lisant dans le fichier
     * @param currentLine
     * @return
     */
    public double recupererAbsc(String currentLine){
        int i = 0;
        int absc;
        String tampon = "";
        while(currentLine.charAt(i) != ','){
            if(currentLine.charAt(i) == '('){
                i++;
            }
            else{
                tampon += currentLine.charAt(i);
                i++;
            }
        }
        absc = Integer.parseInt(tampon);
        return absc;
    }

    /**
     * Récupère l'ordonées en lisant dans le fichier
     * @param currentline
     * @return
     */
    public double recupererOrd(String currentline){
        int i = 0 ;
        int ord;
        String tampon = "";
        while (currentline.charAt(i) != ',') {
            i++;
        }
        i++;
        while(currentline.charAt(i) != ')'){
            tampon += currentline.charAt(i);
            i++;
        }
        ord = Integer.parseInt(tampon);
        return ord;
    }

    /**
     * Récupère la liste des character correspondant au différennts murs en lisant dans le fichier
     * @param currentLine
     * @return
     */
    public ArrayList<Character> recupererListOfWalls(String currentLine){
        ArrayList<Character> tamponList = new ArrayList<>();
        int i = 0;
        while (currentLine.charAt(i) != ':'){
            i++;
        }
        i++;
        while(i < currentLine.length()){
            if(currentLine.charAt(i) == ','){
                i++;
            }
            else{
                tamponList.add(currentLine.charAt(i));
                i++;
            }
        }

        return tamponList;
    }

    /**
     * Récupère l'array liste des Fruits et Super Points
     * @return
     */
    public ArrayList<PosFruitNSuperPointNPacManNFantoms> getTabFruitNSupPoint() {
        return tabFruitNSupPoint;
    }

    /**
     * Récupère l'array liste des coordonnées ou il ne doit pas y avoir de points
     * @return
     */
    public ArrayList<Coordinate> getTabCoordNoPoint() {
        return tabCoordNoPoint;
    }

    /**
     * Récupère l'abscisse maximale en faisant un appel à la méthode de la classe étendue
     * @return
     */
    public double getAbscMax() {
        return super.getAbscMax();
    }

    /**
     * Récupère l'ordonnée macimale en faisant un appel à la méthode de la classe étendue
     * @return
     */
    public double getOrdMax() {
        return super.getOrdMax();
    }

    /**
     * Récupère la liste des PosMurAssocies
     * @return
     */
    public ArrayList<PosMursAssocies> getTabMurFctCoord() {
        return super.getTabMurFctCoord();
    }

    /**
     * Récupère L'array liste des Position avec des pacman ou des fantomes associes au coordonnées
     * @return
     */
    public ArrayList<PosFruitNSuperPointNPacManNFantoms> getTabPacmanNFantoms() {
        return tabPacmanNFantoms;
    }

    /**
     * Récupère le fichier ou l'on lit les murs
     * @return
     */
    public File getMapFile() {
        return mapFile;
    }
}
