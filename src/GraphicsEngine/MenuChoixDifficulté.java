package GraphicsEngine;

import GamePlay.PacMan;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;


public class MenuChoixDifficulté {
    private StackPane pane = new StackPane();
    HBox hbox = new HBox(20);
    ImageViewSizePos revenir ;
    private Scene scene;
    private Scene sceneBack;

    public MenuChoixDifficulté(Stage stage, Game game,Scene scenep) {
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        scene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());
        hbox.setPrefWidth(800);
        hbox.setPrefHeight(100);
        revenir  = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2));
        sceneBack= scenep;
        revenir.getImageView().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                revenir.setImageView("./data/Logos/returnhover.png");
            }
        });

        revenir.getImageView().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                revenir.setImageView("./data/Logos/return.png");

            }
        });
        for(Difficulte difficulte : game.getListDifficultes()){
            Button button = new Button(difficulte.getName());
            button.setPrefWidth(hbox.getPrefWidth());
            button.setPrefHeight(hbox.getPrefHeight());
            button.getStyleClass().add("diff");
            hbox.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Pane root = new Pane();//Creation groupe
                    root.setMinSize(0, 0);
                    Scene scene = new Scene(root,getScene().getWidth()/2,getScene().getHeight()/2);//Creation fenetre de taille 400 sur 400 pixels
                    PacMan visualObject = new PacMan("./data/pacmanOuvert2.png", new Coordinate(385, 20), scene, root);
                    root.getChildren().add(visualObject.getImageView());
                    stage.setResizable(false);
                    root.setMaxWidth(getScene().getWidth()/2);
                    root.setMaxHeight(getScene().getHeight()/2);
                    ReadFileMapPacman readFileMapPacman = new ReadFileMapPacman(scene,root);
                    readFileMapPacman.decrypt();

                    for(int i = 0; i < readFileMapPacman.getVisualObjects().size() ; i++){
                        root.getChildren().add(readFileMapPacman.getVisualObjects().get(i).getImageView());
                    }

                    root.setStyle("-fx-background-color: black;");
                    Musique.mediaPlayer.stop();
                    stage.setScene(scene);
                    stage.centerOnScreen();
                }
            });

        }

        Tooltip tooltip_revenir=new Tooltip("Revenir en arrière");
        tooltip_revenir.setStyle(" -fx-background-color: gray;");
        tooltip_revenir.setShowDelay(new Duration(0));
        Tooltip.install(revenir.getImageView(),tooltip_revenir);


        ImageViewSizePos fond = new ImageViewSizePos("./data/Logos/"+ game.getName() + "menudujeu.jpg",scene.getWidth(),scene.getHeight());


        pane.getChildren().addAll(fond.getImageView(),hbox, revenir.getImageView());
        StackPane.setAlignment(revenir.getImageView(),Pos.TOP_LEFT);
        //StackPane.setAlignment(hbox,Pos.BOTTOM_CENTER);
        hbox.setAlignment(Pos.CENTER);
        System.out.println(hbox.getAlignment());
        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                 //a revoir lier avec le menu du choix du jeu

                stage.setScene(sceneBack);
            }
        });
        pane.setStyle("-fx-background-color: black");
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }

}
