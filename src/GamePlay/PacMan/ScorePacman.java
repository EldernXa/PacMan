package GamePlay.PacMan;

import GraphicsEngine.ImageViewSizePos;
import GraphicsEngine.Score;
import GraphicsEngine.VisualObject;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ScorePacman implements Score {
    Label nbVies = new Label();
    Label nbPoints = new Label();
    HBox score = new HBox(390);
    HBox vies = new HBox(5);
    ImageViewSizePos logoCoeur = new ImageViewSizePos();


    public ScorePacman(Stage stage, VisualObject visualObject){
        logoCoeur.setImageView("./data/Logos/heart.png");
        PacMan pacMan = (PacMan)visualObject;
        nbVies.setText(String.valueOf(pacMan.getNbVies()));
        nbPoints.setText(String.valueOf(pacMan.getNbPoints()));
        setHbox();
        modifyRealTimeValue(pacMan,stage);


    }

    public void setHbox(){
        nbVies.setTextFill(Color.WHITE);
        nbPoints.setTextFill(Color.WHITE);
        nbPoints.setMinWidth(40);
        vies.getChildren().addAll(logoCoeur.getImageView(),nbVies);
        score.getChildren().addAll(nbPoints,vies);
        score.setPrefSize(40,40);
        logoCoeur.setSize(20,20);
        nbPoints.setFont(Font.font("Arial",20));
        nbVies.setFont(Font.font("Arial",20));
        score.setTranslateX(14);
        score.setTranslateY(5);
    }
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
                    ConclusionPacman conclusion = new ConclusionPacman(stage,false,pacMan.getNbPoints());
                }

            }
        }));
        pacMan.setListenerNbVies(changeListenerVies);
    }

    public HBox getScore() {
        return score;
    }

    /*static void refresh(VisualObject visualObject){
        nbVies.setText(String.valueOf(pacMan.getNbVies()));
        nbPoints.setText(String.valueOf(pacMan.getNbPoints()));
    }*/
}
