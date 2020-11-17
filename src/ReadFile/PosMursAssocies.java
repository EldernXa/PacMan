package ReadFile;

import GraphicsEngine.Coordinate;

import java.util.ArrayList;

public class PosMursAssocies {
    private Coordinate pointCoordinate;
    private ArrayList<Character> listOfWalls;

    public PosMursAssocies(Coordinate pointCoordinate){
        this.pointCoordinate = pointCoordinate;
        this.listOfWalls = new ArrayList<>();
    }

    public PosMursAssocies(Coordinate pointCoordinate, ArrayList<Character> listOfWalls){
        this.pointCoordinate = pointCoordinate;
        this.listOfWalls = listOfWalls;
    }

    public Coordinate getPointCoordinate() {
        return pointCoordinate;
    }

    public void addToList(Character charactere){
        listOfWalls.add(charactere);
    }

    public ArrayList<Character> getListOfWalls() {
        return listOfWalls;
    }
}
