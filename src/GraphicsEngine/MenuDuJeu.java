package GraphicsEngine;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    Game jeu;
    ImageViewSizePos soundAndNoSound;
    Scene menuDuJeuScene;
    HBox hbox = new HBox(20);

    public MenuDuJeu(Stage stage,Game game,Scene sceneBack) {
        jeu = game;
        if(!game.getListMusiques().isEmpty()) {

            game.getListMusiques().get(0).lancerMusique();
        }

        //music.lancerMusique();
        buttonContainers.setPrefWidth(400);
        System.out.println(Screen.getPrimary().getVisualBounds().getWidth());
        System.out.println(Screen.getPrimary().getVisualBounds().getHeight());
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        menuDuJeuScene = new Scene(pane, screenWidth,screenHeight);
        menuDuJeuScene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());

        param = new ImageViewSizePos("./data/Logos/settings.png",40, 40);
        soundAndNoSound = new ImageViewSizePos("./data/Logos/sound.png",40,40);

        ImageViewSizePos fondEcran = new ImageViewSizePos("./data/Jeux/" + jeu.getName() + "/menudujeu.jpg",menuDuJeuScene.getWidth(),menuDuJeuScene.getHeight());
        pane.getChildren().add(fondEcran.getImageView());

        hbox.getChildren().addAll(param.getImageView(), soundAndNoSound.getImageView());
        singlePlayer.setPrefWidth(buttonContainers.getPrefWidth());
        multiPlayer.setPrefWidth(buttonContainers.getPrefWidth());
        retouner.setPrefWidth(buttonContainers.getPrefWidth());
        hbox.setPrefWidth(buttonContainers.getPrefWidth());
        hbox.setAlignment(Pos.CENTER);
        buttonContainers.getChildren().addAll(singlePlayer,multiPlayer,retouner, hbox);

        MenuChoixDifficulté menuChoixDifficulté = new MenuChoixDifficulté(stage, jeu,menuDuJeuScene);

        soundAndNoSound.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(soundAndNoSound.getPathImage() + "path null");
                if (!game.getListMusiques().isEmpty()) {
                    switch (soundAndNoSound.getPathImage()) {
                        case "./data/Logos/sound.png":

                            soundAndNoSound.setImageView("./data/Logos/nosound.png");
                            break;

                        case "./data/Logos/nosound.png":
                            soundAndNoSound.setImageView("./data/Logos/sound.png");
                            break;

                        case "./data/Logos/soundhover.png":
                            game.getListMusiques().get(0).mute(true);
                            soundAndNoSound.setImageView("./data/Logos/nosoundhover.png");
                            break;

                        case "./data/Logos/nosoundhover.png":
                            game.getListMusiques().get(0).mute(false);
                            soundAndNoSound.setImageView("./data/Logos/soundhover.png");
                            break;
                    }
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
                MenuParametres parametres = new MenuParametres();

            }
        });

        setmusic(game);
        ImageViewSizePos revenir  = new ImageViewSizePos("./data/Logos/return.png",50,50, new Coordinate(2,2));
        Tooltip tooltip_revenir=new Tooltip("Revenir en arrière");
        tooltip_revenir.setStyle(" -fx-background-color: gray;");
        tooltip_revenir.setShowDelay(new Duration(0));
        Tooltip.install(revenir.getImageView(),tooltip_revenir);

        singlePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                diff(game,stage,menuChoixDifficulté,revenir,screenWidth,screenHeight);
            }
        });


        retouner.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(!game.getListMusiques().isEmpty()){
                    game.getListMusiques().get(0).stopMusique();
                }
                stage.setScene(sceneBack);
            }
        });
        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(menuDuJeuScene);
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
    public void setmusic(Game game){
        File directoryPath = new File("./data/Jeux/"+ game.getName()+"/");
        String contents[] = directoryPath.list();
        boolean music = false;
        for(String content :contents){
            if(content.contains("musique")){
                music = true;
            }
        }
        if(!music){
            System.out.println("on est dedans");
            soundAndNoSound.setImageView("./data/Logos/nosound.png");
            soundAndNoSound.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Label label = new Label("Il n'y a pas encore de musique disponible");
                    label.setFont(Font.font("Arial", 42));
                    label.setTextFill(Color.WHITE);
                    label.setStyle("-fx-background-color: black");
                    pane.setAlignment(label,Pos.TOP_CENTER);
                    if(!pane.getChildren().contains(label)) {
                        pane.getChildren().add(label);
                    }
                }
            });
        }

    }
    public void diff(Game game, Stage stage,MenuChoixDifficulté menuChoixDifficulté, ImageViewSizePos revenir, double screenWidth, double screenHeight){
            File directoryPath = new File("./data/Jeux/"+ game.getName()+"/");
            String contents[] = directoryPath.list();
            boolean bool = false;
            for(String content :contents){
                if(content.equals("notyet.txt")){
                    StackPane newPane = new StackPane();
                    Scene scenetemp = new Scene(newPane,Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
                    ImageViewSizePos imageViewSizePos = new ImageViewSizePos("./data/DevPrivate/wip.jpg",screenWidth,screenHeight);
                    newPane.getChildren().addAll(imageViewSizePos.getImageView(),revenir.getImageView());
                    newPane.setAlignment(Pos.TOP_LEFT);
                    stage.setScene(scenetemp);
                    bool = true;
                }

            }
            if(!bool)
                stage.setScene(menuChoixDifficulté.getScene());

        }
    }

