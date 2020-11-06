package GraphicsEngine;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;


public class MenuChoixDuJeu {
    private Pane pane = new Pane();
    private Stage stage;
    private Scene menuScene;
    private Button buttonExit = new Button("Quitter le menu");
    private ImageViewSizePos imageJeu1;
    private ImageViewSizePos imageJeu2;
    Label choixDuJeuLabel = new Label("Choisissez votre Jeu :");


    public MenuChoixDuJeu(Stage stage) {
        System.out.println("width " + Screen.getPrimary().getVisualBounds().getWidth()/4);
        menuScene = new Scene(pane,Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        menuScene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());
        choixDuJeuLabel.setFont(Font.font("Avenir Next", 45));
        choixDuJeuLabel.setUnderline(true);
        choixDuJeuLabel.setTranslateX(menuScene.getWidth()/2-220);
        choixDuJeuLabel.setTranslateY(70);
        choixDuJeuLabel.setStyle("-fx-border-color: black;");
        choixDuJeuLabel.setPadding(new Insets(7));
        choixDuJeuLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));

        imageJeu1 = new ImageViewSizePos("./data/Logos/pacmanmenuchoixdujeu.jpg",500,250);
        Coordinate coordImageJeu1 = new Coordinate(menuScene.getWidth()/2-(imageJeu1.getImageView().getFitWidth()/2)-(menuScene.getWidth()/4),menuScene.getHeight()/2-(imageJeu1.getImageView().getFitHeight()/2));
        imageJeu1.setCoordinate(coordImageJeu1);
        imageJeu2 = new ImageViewSizePos("./data/Logos/cassebriquemenuchoixdujeu.jpg",500,250);
        Coordinate coordImageJeu2 = new Coordinate(menuScene.getWidth()/2-(imageJeu2.getImageView().getFitWidth()/2)+(menuScene.getWidth()/4),menuScene.getHeight()/2-(imageJeu2.getImageView().getFitHeight()/2));
        imageJeu2.setCoordinate(coordImageJeu2);



        MenuDuJeu menuPacMan = new MenuDuJeu(stage,"pacman");
        MenuDuJeu menuCasseBrique = new MenuDuJeu(stage,"cassebrique");

        imageJeu1.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changerScene(menuPacMan.getMenuDuJeuScene());
            }
        });
        imageJeu2.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                changerScene(menuCasseBrique.getMenuDuJeuScene());
            }
        });
        buttonExit.setTranslateX(menuScene.getWidth()/2-100);
        buttonExit.setTranslateY(menuScene.getHeight()/2 + 250);
        buttonExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
                System.exit(0);
            }
        });

        pane.getChildren().addAll(imageJeu1.getImageView(), imageJeu2.getImageView(), choixDuJeuLabel,buttonExit);

        stage.setScene(menuScene);
        this.stage = stage ;
    }

    public ImageView getImageJeu1() {
        return imageJeu1.getImageView();
    }

    public ImageView getImageJeu2() {
        System.out.println("coco");
        return imageJeu2.getImageView();
    }

    public Stage getStage() {
        return stage;
    }

    public void changerScene(Scene scene){
        stage.setScene(scene);
    }

    public Scene getMenuScene() {
        return menuScene;
    }

    public Pane getPane() {
        return pane;
    }
}
