package GraphicsEngine;

import GamePlay.PacMan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Score {
    Label nbVies = new Label();
    Label nbPoints = new Label();
    HBox score = new HBox(400);
    HBox vies = new HBox(5);
    ImageViewSizePos logoCoeur = new ImageViewSizePos();


    public Score(VisualObject visualObject){
        logoCoeur.setImageView("./data/Logos/heart.png");
        PacMan pacMan = (PacMan)visualObject;
        nbVies.setText(String.valueOf(pacMan.getNbVies()));
        nbPoints.setText(String.valueOf(pacMan.getNbPoints()));
        setHbox();
        ChangeListener<Number> changeListenerPoint = (((observableValue, number, t1) -> {
            if(!t1.equals(number))
                nbPoints.setText(String.valueOf(pacMan.getNbPoints()));
        }));
        pacMan.setListenerPoint(changeListenerPoint);
        ChangeListener<Number> changeListenerVies = (((observableValue, number, t1) ->{
            if(!t1.equals(number)){
                if(pacMan.getNbVies_restantes() == 0){


                }else{
                    nbVies.setText(String.valueOf(pacMan.getNbVies_restantes()));
                }

            }
        }));
        pacMan.setListenerNbVies(changeListenerVies);
    }

    public void setHbox(){
        nbVies.setTextFill(Color.WHITE);
        nbPoints.setTextFill(Color.WHITE);
        nbPoints.setMinWidth(20);
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
