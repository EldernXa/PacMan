import controller.Controller;
import javafx.application.Application;
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
    @Override
    public void start(Stage primaryStage) {
        try {

            Group root = new Group();//Creation groupe
            Scene scene = new Scene(root,800,500);//Creation fenetre de taille 400 sur 400 pixels
            Circle b = new Circle();
            b.setCenterX(400);
            b.setCenterY(250);
            b.setRadius(10);
            b.setFill(Color.BLUE);
            Controller a = new Controller(b,scene,root,primaryStage);
            a.controle();












        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
