package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class MenuChoixDuJeu {
    private Pane pane = new Pane();
    private Stage stage;
    private Scene menuScene;
    private Button buttonExit = new Button("Quitter le menu");
    private ImageView imageJeu1;
    private ImageView imageJeu2;

    public MenuChoixDuJeu(Stage stage) {
        System.out.println("width " + Screen.getPrimary().getVisualBounds().getWidth()/4);
        menuScene = new Scene(pane,Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        imageJeu1 = new ImageView(new Image(new File("./data/Logos/pacmanmenuchoixdujeu.jpg").toURI().toString()));
        imageJeu1.setFitWidth(500);
        imageJeu1.setFitHeight(250);
        imageJeu2 = new ImageView(new Image(new File("./data/Logos/cassebriquemenuchoixdujeu.jpg").toURI().toString()));
        imageJeu2.setFitWidth(500);
        imageJeu2.setFitHeight(250);
        Coordinate coordImageJeu1 = new Coordinate(menuScene.getWidth()/2-(imageJeu1.getFitWidth()/2)-(menuScene.getWidth()/4),menuScene.getHeight()/2-(imageJeu1.getFitHeight()/2));
        imageJeu1.setTranslateX(coordImageJeu1.getX());
        imageJeu1.setTranslateY(coordImageJeu1.getY());
        Coordinate coordImageJeu2 = new Coordinate(menuScene.getWidth()/2-(imageJeu2.getFitWidth()/2)+(menuScene.getWidth()/4),menuScene.getHeight()/2-(imageJeu2.getFitHeight()/2));
        imageJeu2.setTranslateX(coordImageJeu2.getX());
        imageJeu2.setTranslateY(coordImageJeu2.getY());


        MenuDuJeu menuPacMan = new MenuDuJeu(stage, "./data/Logos/pacmanmenudujeu.jpg");
        MenuDuJeu menuCasseBrique = new MenuDuJeu(stage,"./data/Logos/cassebriquemenudujeu.jpg");

        imageJeu1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changerScene(menuPacMan.getMenuDuJeuScene());
            }
        });
        imageJeu2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changerScene(menuCasseBrique.getMenuDuJeuScene());
            }
        });

        pane.getChildren().addAll(imageJeu1, imageJeu2);

        stage.setScene(menuScene);
        this.stage = stage ;
    }

    public ImageView getImageJeu1() {
        return imageJeu1;
    }

    public ImageView getImageJeu2() {
        return imageJeu2;
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
