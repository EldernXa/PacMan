import GraphicsEngine.Coordinate;
import GraphicsEngine.GameImage;
import GraphicsEngine.VisualObject;
import controller.Controller;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Main extends Application {
    private final int xMax = 800;
    private final int yMax = 500;
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();//Creation groupe
        root.setMaxHeight(yMax);
        root.setMaxWidth(xMax);
        root.setMinSize(0, 0);
        Scene scene = new Scene(root,xMax,yMax);//Creation fenetre de taille 400 sur 400 pixels
        /*Circle b = new Circle();
        b.setCenterX(400);
        b.setCenterY(250);
        b.setRadius(10);
        b.setFill(Color.BLUE);
        Controller a = new Controller(b,scene,root,primaryStage);
        a.controle();*/
        VisualObject visualObject = new VisualObject("./data/pacmanOuvert2.png", new Coordinate(0, 0), scene, root, new Coordinate(xMax, yMax));
        root.getChildren().add(visualObject.getImageView());
        root.setStyle("-fx-background-color: black;");
        System.out.println(scene.heightProperty());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
