package GraphicsEngine;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class MenuDuJeu {
    StackPane pane = new StackPane();
    Button singlePlayer = new Button("SinglePlayer".toUpperCase());
    Button multiPlayer = new Button("MultiPlayer".toUpperCase());
    VBox buttonContainers = new VBox(15);
    Button retouner = new Button("Retourner au choix du jeu".toUpperCase());
    ImageViewSizePos param;
    ImageViewSizePos soundAndNoSound;
    Scene menuDuJeuScene;
    HBox hbox = new HBox(20);

    public MenuDuJeu(Stage stage,String name) {
        Musique pacman = new Musique("./data/Jeux/"+name+"/musiquemenu.wav");
        pacman.lancerMusique();
        buttonContainers.setPrefWidth(400);
        System.out.println(Screen.getPrimary().getVisualBounds().getWidth());
        System.out.println(Screen.getPrimary().getVisualBounds().getHeight());
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        menuDuJeuScene = new Scene(pane, screenWidth,screenHeight);
        menuDuJeuScene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());

        param = new ImageViewSizePos("./data/Logos/settings.png",40, 40);
        soundAndNoSound = new ImageViewSizePos("./data/Logos/sound.png",40,40);
        ImageViewSizePos fondEcran = new ImageViewSizePos("./data/Jeux/" + name + "/menudujeu.jpg",menuDuJeuScene.getWidth(),menuDuJeuScene.getHeight());
        pane.getChildren().add(fondEcran.getImageView());

        hbox.getChildren().addAll(param.getImageView(), soundAndNoSound.getImageView());
        singlePlayer.setPrefWidth(buttonContainers.getPrefWidth());
        multiPlayer.setPrefWidth(buttonContainers.getPrefWidth());
        retouner.setPrefWidth(buttonContainers.getPrefWidth());
        hbox.setPrefWidth(buttonContainers.getPrefWidth());
        hbox.setAlignment(Pos.CENTER);
        buttonContainers.getChildren().addAll(singlePlayer,multiPlayer,retouner, hbox);

        MenuChoixDifficulté menuChoixDifficulté = new MenuChoixDifficulté(stage, name,menuDuJeuScene);

        soundAndNoSound.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(soundAndNoSound.getPathImage() + "path null");
                switch (soundAndNoSound.getPathImage()){
                    case "./data/Logos/sound.png":
                        soundAndNoSound.setImageView("./data/Logos/nosound.png");
                        break;

                    case "./data/Logos/nosound.png":
                        soundAndNoSound.setImageView("./data/Logos/nosound.png");
                        break;

                    case "./data/Logos/soundhover.png":
                        soundAndNoSound.setImageView("./data/Logos/nosoundhover.png");
                        break;

                    case "./data/Logos/nosoundhover.png":
                        soundAndNoSound.setImageView("./data/Logos/soundhover.png");
                        break;
                }
            }
        });

        soundAndNoSound.getImageView().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(soundAndNoSound.getPathImage().equals("./data/Logos/sound.png")){
                    soundAndNoSound.setImageView("./data/Logos/soundhover.png");
                }
                else{
                    soundAndNoSound.setImageView("./data/Logos/nosoundhover.png");
                }
            }
        });


        soundAndNoSound.getImageView().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(soundAndNoSound.getPathImage().equals("./data/Logos/soundhover.png")){
                    soundAndNoSound.setImageView("./data/Logos/sound.png");
                }
                else{
                    soundAndNoSound.setImageView("./data/Logos/nosound.png");
                }
            }
        });

        param.getImageView().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                param.setImageView("./data/Logos/settingshover.png");
            }
        });

        param.getImageView().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                param.setImageView("./data/Logos/settings.png");

            }
        });
        param.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuParametres parametres = new MenuParametres(stage,menuDuJeuScene);
                stage.setScene(parametres.getScene());
            }
        });


        ImageViewSizePos revenir  = new ImageViewSizePos("./data/Logos/return.png",50,50, new Coordinate(2,2));
        Tooltip tooltip_revenir=new Tooltip("Revenir en arrière");
        tooltip_revenir.setStyle(" -fx-background-color: gray;");
        tooltip_revenir.setShowDelay(new Duration(0));
        Tooltip.install(revenir.getImageView(),tooltip_revenir);

        singlePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(name.toLowerCase().equals("pacman"))
                    stage.setScene(menuChoixDifficulté.getScene());
                else{
                    ImageViewSizePos imageViewSizePos = new ImageViewSizePos("./data/DevPrivate/wip.jpg",screenWidth,screenHeight);
                    pane.getChildren().clear();
                    pane.getChildren().addAll(imageViewSizePos.getImageView(),revenir.getImageView());
                    pane.setAlignment(Pos.TOP_LEFT);
                    stage.setScene(menuDuJeuScene);
                }
            }
        });

        retouner.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuChoixDuJeu menuChoixDuJeu  = new MenuChoixDuJeu(stage);
                stage.setScene(menuChoixDuJeu.getMenuScene());
            }
        });

        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuDuJeu menuDuJeu = new MenuDuJeu(stage,name);
                stage.setScene(menuDuJeu.getMenuDuJeuScene());
            }
        });

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
        pane.getChildren().addAll(buttonContainers);
        buttonContainers.setAlignment(Pos.CENTER);
        menuDuJeuScene.setRoot(pane);
    }

    public Scene getMenuDuJeuScene() {
        return menuDuJeuScene;
    }



}
