package controller;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.ObjetControl;

public class Controller {
    private Circle b;
    private Scene scene;
    private Group root;
    private Stage primaryStage;


    public Controller(Circle b,Scene scene,Group root,Stage primaryStage) {
        this.b = b;
        this.scene = scene;
        this.root = root;
        this.primaryStage = primaryStage;
    }
    public void controle() {
        System.out.println(b.getCenterY()+"  "+scene.getHeight());
        HashSet<KeyCode> tab = new HashSet<KeyCode>();

        AnimationTimer aT = new AnimationTimer() {
            public void handle(long l) {
                for (KeyCode c : tab) {

                    switch (c) {

                        case Z:
                            if(b.getCenterY() > 0){


                            b.setFill(Color.WHITE);
                            b.setCenterY(b.getCenterY() - 1);
                            b.setFill(Color.BLUE);
                            }
                            break;

                        case S:
                            if(b.getCenterY() < scene.getHeight()) {
                                b.setFill(Color.WHITE);
                                b.setCenterY(b.getCenterY() + 1);
                                b.setFill(Color.BLUE);
                            }
                            break;

                        case D:
                            if(b.getCenterX() < scene.getWidth()) {
                                b.setFill(Color.WHITE);
                                b.setCenterX(b.getCenterX() + 1);
                                b.setFill(Color.BLUE);
                            }
                            break;

                        case Q:
                            if(b.getCenterX() > 0) {
                                b.setFill(Color.WHITE);
                                b.setCenterX(b.getCenterX() - 1);
                                b.setFill(Color.BLUE);
                            }
                            break;


                    }
                }

            }

            ;


        };

        scene.setOnKeyPressed(e ->{
            boolean wasEmpty = tab.isEmpty();
            if(tab.add(e.getCode()) && wasEmpty)
                aT.start();
        });
        scene.setOnKeyReleased(e ->{
            if(tab.remove(e.getCode()) && tab.isEmpty())
                aT.stop();
        });
        scene.setFill(Color.WHITE);
        root.getChildren().add(b);
        primaryStage.setTitle("Pac-man");//Ajout d'un titre a la fenetre
        primaryStage.setScene(b.getScene());//Ajout d'une scene dans la fenetre
        primaryStage.show();//Rendre visible la fenetre
    }


    public Scene getScene() {
        return scene;
    }
}
