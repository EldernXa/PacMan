package GamePlay.Pacman;

import GraphicsEngine.ImageViewSizePos;
import GraphicsEngine.Score;
import GraphicsEngine.VisualObject;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * class for score points
 */

public class ScorePacman extends Score {
    Label nbVies = new Label();
    Label nbPoints = new Label();
    HBox vies = new HBox(5);
    ImageViewSizePos logoCoeur = new ImageViewSizePos();
    private final PacMan pacMan;


    public ScorePacman(Stage stage, VisualObject visualObject){
        super(stage);
        logoCoeur.setImageView("./data/Logos/heart.png");
        pacMan = (PacMan)visualObject;
        nbVies.setText(String.valueOf(pacMan.getNbVies()));
        nbPoints.setText(String.valueOf(pacMan.getNbPoints()));
        setHbox();
        modifyRealTimeValue(pacMan,stage);
    }

    /**
     * np param, it set the hbox for the display of score points
     */
    public void setHbox(){
        nbVies.setTextFill(Color.WHITE);
        nbPoints.setTextFill(Color.WHITE);
        nbPoints.setMinWidth(40);
        vies.getChildren().addAll(logoCoeur.getImageView(),nbVies);
        logoCoeur.setSize(20,20);
        nbPoints.setFont(Font.font("Arial",20));
        nbVies.setFont(Font.font("Arial",20));
        super.addNode(nbPoints);
        super.addNode(vies);
        super.setHBox();
        super.getScore().setPrefSize(40, 40);
        super.getScore().setTranslateX(14);
        super.getScore().setTranslateY(5);
    }

    /**
     *
     * @param visualObject the image object
     * @param stage stage of the window
     */
    public void modifyRealTimeValue(VisualObject visualObject,Stage stage){
        PacMan pacMan = (PacMan)visualObject;
        ChangeListener<Number> changeListenerPoint = (((observableValue, number, t1) -> {
            if(!t1.equals(number))
                nbPoints.setText(String.valueOf(pacMan.getNbPoints()));
        }));
        pacMan.setListenerPoint(changeListenerPoint);
        ChangeListener<Number> changeListenerVies = (((observableValue, number, t1) ->{
            if(!t1.equals(number)){
                nbVies.setText(String.valueOf(pacMan.getNbVies_restantes()));
                if(pacMan.getNbVies_restantes() == 0){
                    ConclusionPacman conclusion = new ConclusionPacman(stage,false,pacMan.getNbPoints(), pacMan);
                }

            }
        }));
        pacMan.setListenerNbVies(changeListenerVies);
    }

}
