package GraphicsEngine;

//import ReadFile.ReadFileMapPacman;
import GraphicsEngine.Maps.Map;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

        stage.setMaximized(true);

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
            stage.setMaximized(false);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    stage.setFullScreen(false);
                    Map currentMap = new Map(stage,"./data/Map/");
                    Musique.mediaPlayer.stop();
                    stage.setScene(currentMap.getMapScene());
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

                stage.setScene(sceneBack);
            }
        });
        StackPane.setAlignment(revenir.getImageView(),Pos.TOP_LEFT);
    }

}
