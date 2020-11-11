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


public class MenuChoixDifficulté {
    private StackPane pane = new StackPane();
    HBox hbox = new HBox(20);
    ImageViewSizePos revenir ;
    private Scene scene;
    private Scene sceneBack;

    public MenuChoixDifficulté(Stage stage, Game game,Scene scenep) {
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
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
            style(button);
            hbox.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Pane root = new Pane();//Creation groupe
                    root.setMinSize(0, 0);
                    Scene scene = new Scene(root,getScene().getWidth()/2,getScene().getHeight()/2);//Creation fenetre de taille 400 sur 400 pixels
                    PacMan visualObject = new PacMan("./data/pacmanOuvert2.png", new Coordinate(0, 0), scene, root);
                    root.getChildren().add(visualObject.getImageView());
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
            button.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    button.setStyle(" -fx-background-color: \n" +
                            "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                            "        #9d4024,\n" +
                            "        #d86e3a,\n" +
                            "        radial-gradient(center 50% 50%, radius 100%, #ea7f4b, #c54e2c);");
                }
            });
            button.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    style(button);
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
    public void style(Button button){
        button.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                "        #9d4024,\n" +
                "        #d86e3a,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");


    }
}
