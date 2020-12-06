package ReadFile;

import GraphicsEngine.Coordinate;

/**
 * Classe utilisée aussi bien pour la création d'un fruit, d'un Super point, d'un Pacman et d'un Fantome, en indiquant
 * des coordonnées et un character en fonction de ce que l'on souhaitre créer
 */
public class PosFruitNSuperPointNPacManNFantoms {
    Coordinate coordinate;
    Character character;

    /**
     * Constructeur créant un PosFruitPointNPacManNFantoms à partir de coordonnées et d'un character
     * @param coordinate
     * @param character
     */
    public PosFruitNSuperPointNPacManNFantoms(Coordinate coordinate, Character character){
        this.coordinate = coordinate;
        this.character = character;
    }

    /**
     * Récupère le character
     * @return
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Récupère les coordonnées
     * @return
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
}
