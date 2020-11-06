package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class MenuChoixDuJeu {
    private Pane pane = new Pane();
    private Stage stage;
    private Scene menuScene;
    private Button buttonExit = new Button("Quitter le menu");
    private RectanglePos rectangleJeu1;
    private RectanglePos rectangleJeu2;

    public MenuChoixDuJeu(Stage stage) {
        System.out.println("width " + Screen.getPrimary().getVisualBounds().getWidth()/4);
        menuScene = new Scene(pane,Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        Coordinate coordRect1 = new Coordinate(menuScene.getWidth()/2-584,menuScene.getHeight()/2-150);
        rectangleJeu1 = new RectanglePos(300,400, Color.BLUE,coordRect1);
        Coordinate coordRect2 = new Coordinate(menuScene.getWidth()/2+184,menuScene.getHeight()/2-150);
        rectangleJeu2 = new RectanglePos(300,400,Color.RED,coordRect2);


        pane.getChildren().addAll(rectangleJeu1.getRectangle(),rectangleJeu2.getRectangle());

        stage.setScene(menuScene);
        this.stage = stage ;
    }

    public RectanglePos getRectangleJeu1() {
        return rectangleJeu1;
    }

    public RectanglePos getRectangleJeu2() {
        return rectangleJeu2;
    }

    public Stage getStage() {
        return stage;
    }

    public void changerScene(Scene scene){
        stage.setScene(scene);
    }







    public Pane getPane() {
        return pane;
    }
}
