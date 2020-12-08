package GamePlay.Pacman;

import GameEngine.Game;
import GraphicsEngine.*;
import GraphicsEngine.Map;
import MusicEngine.Musique;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

/**
 * Build the conclusion of Pacman
 */
public class ConclusionPacman extends Conclusion {

    private final int nbPoints;

    /**
     *
     * @param stageJeu stage du jeu actuelle.
     * @param bool réussite ou échec.
     * @param nbPoints nbPoints du pacman (pour l'affichage).
     * @param pacMan pacman.
     */
    public ConclusionPacman(Stage stageJeu, boolean bool,int nbPoints, PacMan pacMan){
        super(stageJeu, bool);
        this.nbPoints = nbPoints;
        super.labelForGame();
        super.nextLevel(stageJeu);
    }

    /**
     *
     * @return nbPoints
     */
    public int getNbPoints(){
        return nbPoints;
    }

    /**
     *
     * @param lbl label à modifier par le jeu.
     */
    public void initLabel(Label lbl){
        if(getBool()){
            lbl.setText("Vous avez gagné ! \nVous avez obtenu : " + getNbPoints()+" Points.");
            lbl.setTextFill(Color.GREEN);
        }else{
            lbl.setText("Vous avez perdu! \nVous avez obtenu : " + getNbPoints() + " Points.");
            lbl.setTextFill(Color.RED);
        }
    }

    /**
     *
     * @param stageJeu stage du jeu actuelle.
     * @return une nouvelle map.
     */
    public Map initMap(Stage stageJeu){
        return new MapPacman(stageJeu, "./data/Map/"+Map.diff.getName()+"_",Map.isMulti());
    }

    /**
     *
     * @return un game.
     */
    public Game initGame(){
        Game game = new Game("Pacman");
        game.setImageJeu(new ImageViewSizePos("./data/Jeux/"+game.getName() +"/menuchoixdujeu.jpg", 500, 250));
        for(String string : Objects.requireNonNull(new File("./data/Jeux/" + game.getName()).list())){
            if(string.substring(0,7).equals("musique")){
                game.getListMusiques().add(new Musique("./data/Jeux/"+game.getName()+"/"+string));
            }
        }
        return game;
    }

}
