package GamePlay;

import GraphicsEngine.ActionContinue;
import GraphicsEngine.Conclusion;
import GraphicsEngine.Coordinate;
import GraphicsEngine.MouvingObject;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class PacMan extends MouvingObject {

    private final int nbVies = 3;
    private int nbVies_restantes = 3;
    private int nbPoints = 0;
    boolean canEatGhost = false;
    private float valueTps = (float)100;

    public PacMan(String path, Coordinate coordinate, Scene scene, Pane pane){
        super(path, coordinate, scene, pane);
        addAction(new ActionContinue(getGameImage(), scene, "z", 'y', -getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "s", 'y', getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "q", 'x', -getGameImage().getValueMove(), valueTps, this));
        addAction(new ActionContinue(getGameImage(), scene, "d", 'x', getGameImage().getValueMove(), valueTps, this));
    }

    public PacMan(String path, Coordinate coordinate, Scene scene, Pane pane, int nbViesMax, int nbVies) {
        super(path, coordinate, scene, pane);
    }


    public int getNbVies() {
        return nbVies;
    }

    public int getNbVies_restantes() {
        return nbVies_restantes;
    }

    public void manger(String item){
        if(item.equals("boule")){
            nbPoints++;
        }
        else if(item.equals("cerise")){
            nbPoints+=2;
            canEatGhost = true;

        }
        else if(item.equals("Fantome")){
            nbPoints+=5;
        }
    }
    public int getNbPoints() {
        return nbPoints;
    }
}
