package ReadFile;

import GraphicsEngine.Coordinate;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFileMapPacman extends ReadFileMap{
    private ArrayList<Coordinate> tabCoordNoPoint = new ArrayList<>();
    private ArrayList<PosFruitNSuperPoint> tabFruitNSupPoint = new ArrayList<>();
    private File mapFile;
    private File pointsNFruit;

    public ReadFileMapPacman(String mapFolderPath){
        this.mapFile = new File(mapFolderPath + "PacmanMap.txt");
        this.pointsNFruit = new File(mapFolderPath + "Point&Fruit.txt");
        initTabMurFctCoord();
        initTabNoPointFruitNSupPoint();
    }

    public void initTabNoPointFruitNSupPoint(){
        initTabCoordNoPoint();
        initTabFruitNSupPoint();
    }

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
                tabFruitNSupPoint.add(new PosFruitNSuperPoint(new Coordinate(abs,ord),character));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public char recupererCharacter(String currentLine){
        int i = 0;
            while(currentLine.charAt(i) != ':'){
                System.out.println("Character courant : " + currentLine.charAt(i));
                i++;
            }
            System.out.println("Character courant : " + currentLine.charAt(i));
            i++;
            System.out.println("Character courant : " + currentLine.charAt(i));
            return currentLine.charAt(i);
    }

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


                /*System.out.println("Point : (" + absc + "," + ord + ")");
                System.out.println("List des murs pour les coordonnées : (" + absc + "," + ord + ") :");
                System.out.print("[");

                for(int j = 0; j < listOfWalls.size(); j++){
                    System.out.print(listOfWalls.get(j));
                    if(j != listOfWalls.size()-1){
                        System.out.print(",");
                    }
                }
                System.out.println("]\n");*/

                getTabMurFctCoord().add(new PosMursAssocies(new Coordinate(absc,ord),listOfWalls));

            }
            mapFileScanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

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

    public ArrayList<PosFruitNSuperPoint> getTabFruitNSupPoint() {
        return tabFruitNSupPoint;
    }

    public ArrayList<Coordinate> getTabCoordNoPoint() {
        return tabCoordNoPoint;
    }

    public double getAbscMax() {
        return super.getAbscMax();
    }

    public double getOrdMax() {
        return super.getOrdMax();
    }

    public ArrayList<PosMursAssocies> getTabMurFctCoord() {
        return super.getTabMurFctCoord();
    }

    public File getMapFile() {
        return mapFile;
    }
}
