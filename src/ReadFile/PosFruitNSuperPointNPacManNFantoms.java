package ReadFile;

import GraphicsEngine.Coordinate;

public class PosFruitNSuperPointNPacManNFantoms {
    Coordinate coordinate;
    Character character;

    public PosFruitNSuperPointNPacManNFantoms(Coordinate coordinate, Character character){
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
