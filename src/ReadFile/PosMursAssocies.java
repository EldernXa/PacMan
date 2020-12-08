package ReadFile;

import GraphicsEngine.Coordinate;

import java.util.ArrayList;

/**
 * Classe permettant la création de coordonnées et d'y associé des murs
 */

public class PosMursAssocies {
    private Coordinate pointCoordinate;
    private ArrayList<Character> listOfWalls;

    /**
     * Constructeur créant un PosMutAssocies avec une cordonnée donnée en paramètre et avec une liste de characters initialisée a vide
     * @param pointCoordinate
     */
    public PosMursAssocies(Coordinate pointCoordinate){
        this.pointCoordinate = pointCoordinate;
        this.listOfWalls = new ArrayList<>();
    }

    /**
     * Constructeur créant un PosMurAssocies avec une coordonnée et une liste de character donnée en paramètre
     * @param pointCoordinate
     * @param listOfWalls
     */
    public PosMursAssocies(Coordinate pointCoordinate, ArrayList<Character> listOfWalls){
        this.pointCoordinate = pointCoordinate;
        this.listOfWalls = listOfWalls;
    }

    /**
     * Récupère les coordonnées
     * @return
     */
    public Coordinate getPointCoordinate() {
        return pointCoordinate;
    }

    /**
     * Permet d'ajouter facilement à la liste un nouveau character
     * @param charactere
     */
    public void addToList(Character charactere){
        listOfWalls.add(charactere);
    }

    /**
     * Récupère la liste de character
     * @return
     */
    public ArrayList<Character> getListOfWalls() {
        return listOfWalls;
    }
}
