package ReadFile;

import GraphicsEngine.Coordinate;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFileMap2Pacman {
    private ArrayList<PosMursAssocies> tabMurFctCoord = new ArrayList<>();
    private double abscMax = 0;
    private double ordMax = 0;
    private File mapFile;

    public ReadFileMap2Pacman(String mapFilePath){
        this.mapFile = new File(mapFilePath);
        try {
            int i = 0;
            Scanner mapFileScanner = new Scanner(mapFile);
            while(mapFileScanner.hasNext()){
                String currentLine = mapFileScanner.nextLine();
                int absc = recupererAbsc(currentLine);
                int ord = recupererOrd(currentLine);

                if(abscMax < absc){
                    abscMax = absc;
                }
                if(ordMax < ord){
                    ordMax = ord;
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

                tabMurFctCoord.add(new PosMursAssocies(new Coordinate(absc,ord),listOfWalls));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int recupererAbsc(String currentLine){
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

    public int recupererOrd(String currentline){
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

    public double getAbscMax() {
        return abscMax;
    }

    public double getOrdMax() {
        return ordMax;
    }

    public ArrayList<PosMursAssocies> getTabMurFctCoord() {
        return tabMurFctCoord;
    }

    public File getMapFile() {
        return mapFile;
    }
}
