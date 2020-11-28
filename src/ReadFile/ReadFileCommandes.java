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

    private final boolean multijoueur;

    public ReadFileCommandes(String str, boolean multijoueur) {
        pathName = str;
        direction = new ArrayList<>();
        touche = new ArrayList<>();
        xCoord = new ArrayList<>();
        yCoord = new ArrayList<>();
;

        this.multijoueur = multijoueur;

        Path path = Paths.get(str);
        try {
            this.file = Files.readString(path).split("\n");
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

    private void write(String direction, char touche, boolean multijoueur) {
        try {

            int length;
            int start;
            if (multijoueur) {
                start = file.length / 2;
                length = file.length;
            } else {
                start = 0;
                length = file.length / 2;
            }
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(pathName), StandardCharsets.UTF_8));
            if (this.direction.contains(direction)) {
                for (int i = start; i < length; i++) {
                    String line[] = file[i].split("\\s+");
                    if (line[0].equals(direction)) {
                        fileContent.set(i, direction + " " + touche + " " + xCoord.get(i) + " " + yCoord.get(i));
                        Files.write(Paths.get(pathName), fileContent, StandardCharsets.UTF_8);
                        return;

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSolo(String direction, char touche) {
        write(direction, touche, false);

    }

    public void writeMulti(String direction, char touche) {

        write(direction, touche, true);

    }

    public List<String> getDirectionSolo() {
        return  direction.subList(0,direction.size()/2);
    }

    public List<String> getToucheSolo() {
        return touche.subList(0,touche.size()/2);
    }

    public List<Integer> getxCoordSolo() {
        return  xCoord.subList(0,xCoord.size()/2);
    }

    public List<Integer> getyCoordSolo() {
        return  yCoord.subList(0,yCoord.size()/2);
    }

    public List<String> getDirectionMulti() {
        return direction.subList(direction.size()/2,direction.size());
    }

    public List<String> getToucheMulti() {
        return  touche.subList(touche.size()/2,touche.size());
    }

    public List<Integer> getxCoordMulti() {
        return  xCoord.subList(xCoord.size()/2,xCoord.size());
    }

    public List<Integer> getyCoordMulti() {
        return  yCoord.subList(yCoord.size()/2,yCoord.size());
    }
}
