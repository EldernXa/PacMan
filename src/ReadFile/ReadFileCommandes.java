package ReadFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFileCommandes {
    private String file[];
    private String pathName;
    private ArrayList<String> direction;
    private ArrayList<String> touche;
    private ArrayList<Integer> xCoord;
    private ArrayList<Integer> yCoord;

    public ReadFileCommandes(String  str) {
        pathName = str;
        direction = new ArrayList<>();
        touche = new ArrayList<>();
        xCoord = new ArrayList<>();
        yCoord = new ArrayList<>();

        Path path = Paths.get(str);
        try {
            this.file = Files.readString(path).split("\n");
            String mapSize[] = file[0].split("\\s+");
            read();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void read() {

        for (int i = 0; i < file.length; i++) {

            try {
                String line[] = file[i].split("\\s+");
                direction.add(line[0]);
                touche.add(line[1]);
                xCoord.add(Integer.parseInt(line[2]));
                yCoord.add(Integer.parseInt(line[3]));


            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


    public void write(String direction, char touche) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathName), StandardCharsets.UTF_8));
            System.out.println(this.direction.contains(direction));
            if(this.direction.contains(direction) ){
                for (int i = 0; i < file.length; i++) {
                    String line[] = file[i].split("\\s+");
                    if(line[0].equals(direction)){
                        fileContent.set(i, direction +" " + touche+" " + xCoord.get(i)+" " +yCoord.get(i));
                        Files.write(Paths.get(pathName), fileContent, StandardCharsets.UTF_8);
                        return;

                    }
                }
            }




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getDirection() {
        return direction;
    }

    public ArrayList<String> getTouche() {
        return touche;
    }

    public ArrayList<Integer> getxCoord() {
        return xCoord;
    }

    public ArrayList<Integer> getyCoord() {
        return yCoord;
    }
}
