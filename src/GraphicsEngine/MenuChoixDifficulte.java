package GraphicsEngine;

import GamePlay.Fantome;
import GamePlay.PacMan;
//import ReadFile.ReadFileMapPacman;
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


public class MenuChoixDifficulte {
    private final StackPane pane = new StackPane();
    private final HBox hbox = new HBox(20);
    private final ImageViewSizePos revenir = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2)); ;
    private final Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());;
    private final Scene sceneBack;
    private final Game game;
    private final Stage stage;


    public MenuChoixDifficulte(Stage stage, Game game, Scene scenep) {
        this.stage = stage;
        this.game= game;
        scene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());
        sceneBack= scenep;
        ImageViewSizePos fond = new ImageViewSizePos("./data/Logos/"+ game.getName() + "menudujeu.jpg",scene.getWidth(),scene.getHeight());
        setHbox();
        setRevenir();
        setChoixDiff();
        setToolTip();


        pane.getChildren().addAll(fond.getImageView(),hbox, revenir.getImageView());
        pane.setStyle("-fx-background-color: black");
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }

    public void setHbox(){
        hbox.setPrefWidth(800);
        hbox.setPrefHeight(100);
        hbox.setAlignment(Pos.CENTER);
    }
    public void setToolTip(){
        Tooltip tooltip_revenir=new Tooltip("Revenir en arrière");
        tooltip_revenir.setStyle(" -fx-background-color: gray;");
        tooltip_revenir.setShowDelay(new Duration(0));
        Tooltip.install(revenir.getImageView(),tooltip_revenir);
    }

    public void setChoixDiff(){
        for(Difficulte difficulte : game.getListDifficultes()){
            Button button = new Button(difficulte.getName());
            button.setPrefWidth(hbox.getPrefWidth());
            button.setPrefHeight(hbox.getPrefHeight());
            button.getStyleClass().add("diff");
            hbox.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
//                    Pane root = new Pane();//Creation groupe
                    stage.setFullScreen(false);
                    /*Scene scene = new Scene(root,655,365);//Creation fenetre de taille 400 sur 400 pixels
                    root.setMinHeight(scene.getHeight());
                    root.setMaxHeight(scene.getHeight());
                    root.setPrefHeight(scene.getHeight());
                    root.setMinWidth(scene.getWidth());
                    root.setMaxWidth(scene.getWidth());

                    root.setPrefWidth(scene.getWidth());
                    PacMan visualObject = new PacMan("./data/SpriteMouvement/Pacman/pacmanDroite1.png", new Coordinate(385, 20), scene, root);
                    visualObject.addSpriteDirRight("./data/SpriteMouvement/Pacman/pacmanDroite2.png");
                    visualObject.addSpriteDirRight("./data/SpriteMouvement/Pacman/pacmanDroite3.png");
                    visualObject.addSpriteDirRight("./data/SpriteMouvement/Pacman/pacmanDroite4.png");
                    visualObject.addSpriteDirDown("./data/SpriteMouvement/Pacman/pacmanBas1.png");
                    visualObject.addSpriteDirDown("./data/SpriteMouvement/Pacman/pacmanBas2.png");
                    visualObject.addSpriteDirDown("./data/SpriteMouvement/Pacman/pacmanBas3.png");
                    visualObject.addSpriteDirDown("./data/SpriteMouvement/Pacman/pacmanBas4.png");
                    visualObject.addSpriteDirUp("./data/SpriteMouvement/Pacman/pacmanHaut1.png");
                    visualObject.addSpriteDirUp("./data/SpriteMouvement/Pacman/pacmanHaut2.png");
                    visualObject.addSpriteDirUp("./data/SpriteMouvement/Pacman/pacmanHaut3.png");
                    visualObject.addSpriteDirUp("./data/SpriteMouvement/Pacman/pacmanHaut4.png");
                    visualObject.addSpriteDirLeft("./data/SpriteMouvement/Pacman/pacmanGauche1.png");
                    visualObject.addSpriteDirLeft("./data/SpriteMouvement/Pacman/pacmanGauche2.png");
                    visualObject.addSpriteDirLeft("./data/SpriteMouvement/Pacman/pacmanGauche3.png");
                    visualObject.addSpriteDirLeft("./data/SpriteMouvement/Pacman/pacmanGauche4.png");

                    /*** Test pour ajouté un fantome (ici un autre pac-man)***/
                   /* Fantome visualObject1 = new Fantome("./data/SpriteMouvement/Fantome/fantomeGrisDroite1.png", new Coordinate(385, 20), scene, root);
                    visualObject1.addSpriteDirRight("./data/SpriteMouvement/Fantome/fantomeGrisDroite2.png");
                    visualObject1.addSpriteDirDown("./data/SpriteMouvement/Fantome/fantomeGrisBas1.png");
                    visualObject1.addSpriteDirDown("./data/SpriteMouvement/Fantome/fantomeGrisBas2.png");
                    visualObject1.addSpriteDirUp("./data/SpriteMouvement/Fantome/fantomeGrisHaut1.png");
                    visualObject1.addSpriteDirUp("./data/SpriteMouvement/Fantome/fantomeGrisHaut2.png");
                    visualObject1.addSpriteDirLeft("./data/SpriteMouvement/Fantome/fantomeGrisGauche1.png");
                    visualObject1.addSpriteDirLeft("./data/SpriteMouvement/Fantome/fantomeGrisGauche2.png");*/


                    root.getChildren().add(visualObject.getImageView());
                    //root.getChildren().add(visualObject1.getImageView());

                    root.setPrefWidth(scene.getWidth());*/

                    Map currentMap = new Map(stage,"./data/Map/PacmanMap.txt");
                    currentMap.getMapPane().setStyle("-fx-background-color: black;");


                    stage.setMaximized(false);



                    /*ReadFileMapPacman readFileMapPacman = new ReadFileMapPacman(scene,root, "./data/Map/PacmanMap1");
                    readFileMapPacman.decrypt();


                    for(int i = 0; i < readFileMapPacman.getVisualObjects().size() ; i++){
                        root.getChildren().add(readFileMapPacman.getVisualObjects().get(i).getImageView());
                    }*/

//                    root.setStyle("-fx-background-color: black;");
                    Musique.mediaPlayer.stop();
//                    stage.setScene(scene);
                    stage.centerOnScreen();
                    //stage.setResizable(false);
                    stage.sizeToScene();
                }
            });

        }
    }
    public void setRevenir(){
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
        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //a revoir lier avec le menu du choix du jeu

                stage.setScene(sceneBack);
            }
        });
        StackPane.setAlignment(revenir.getImageView(),Pos.TOP_LEFT);
    }

}
