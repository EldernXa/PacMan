package GamePlay.PacMan;

import GraphicsEngine.Coordinate;
import javafx.scene.Scene;

import java.util.ArrayList;

/***Personnalité: (otoboke)
 * Le fantôme orange sort de la base lorsque Pac-man à obtenu 3 quart des points  ,
 * il poursuit Pac-man quand celui-ci est à moins de 8 blocs de lui,
 * sinon il garde sa routine ***/
public class FantomeOrange extends Fantome {
    public FantomeOrange(String path, Coordinate coordinate, Scene scene, MapPacman map, PacMan pacMan) {
        super(path, coordinate, scene, map, pacMan);
    }

    @Override
    public int Chase(ArrayList<Character> listOfWalls) {
        return super.Chase(listOfWalls);
    }
}
