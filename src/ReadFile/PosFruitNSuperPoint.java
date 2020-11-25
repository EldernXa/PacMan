package ReadFile;

import GraphicsEngine.Coordinate;

public class PosFruitNSuperPoint {
    Coordinate coordinate;
    Character character;

    public PosFruitNSuperPoint(Coordinate coordinate, Character character){
        this.coordinate = coordinate;
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
