package GraphicsEngine;

import GamePlay.PacMan;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.text.html.ImageView;


public class Score {
    Label nbVies = new Label();
    Label nbPoints = new Label();
    HBox score = new HBox(10);
    HBox vies = new HBox(5);
    ImageViewSizePos logoCoeur = new ImageViewSizePos();

    public Score(VisualObject visualObject){
        logoCoeur.setImageView("./data/Logos/heart.png");
        PacMan pacMan = (PacMan)visualObject;
        nbVies.setText(String.valueOf(pacMan.getNbVies()));
        nbPoints.setText(String.valueOf(pacMan.getNbPoints()));
        setHbox();

    }

    public void setHbox(){
        nbVies.setTextFill(Color.WHITE);
        nbVies.setStyle("-fx-background-color: black");
        nbPoints.setTextFill(Color.WHITE);
        nbPoints.setStyle("-fx-background-color: black");
        vies.getChildren().addAll(logoCoeur.getImageView(),nbVies);
        score.getChildren().addAll(nbPoints,vies);
        score.setPrefSize(20,20);
        score.setTranslateX(14);
        score.setTranslateY(5);

    }

    public HBox getScore() {
        return score;
    }

    /*static void refresh(VisualObject visualObject){
        nbVies.setText(String.valueOf(pacMan.getNbVies()));
        nbPoints.setText(String.valueOf(pacMan.getNbPoints()));
    }*/
}
