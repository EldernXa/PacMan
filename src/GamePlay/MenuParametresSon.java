package GamePlay;

import GraphicsEngine.Coordinate;
import GraphicsEngine.Game;
import GraphicsEngine.ImageViewSizePos;
import MusicEngine.Musique;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuParametresSon {
    private final StackPane pane = new StackPane();
    private final Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/2,Screen.getPrimary().getVisualBounds().getHeight()/2);
    private final Slider volumeSlider = new Slider();
    private final HBox son = new HBox(10);
    private final ImageViewSizePos revenir = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2));
    private final Label labelSon = new Label("Régler le niveau du son :");


    public MenuParametresSon(Stage stage, Scene sceneBack, Game game) {
        pane.setStyle("-fx-background-color: lightgray");
        setVolumeSlider();
        styleLabelSon();
        son.getChildren().addAll(labelSon,volumeSlider);
        son.setAlignment(Pos.CENTER_LEFT);
        pane.getChildren().add(son);
        setRevenir(stage,sceneBack);
        setdisable(game);
        pane.getChildren().add(revenir.getImageView());
        StackPane.setAlignment(revenir.getImageView(),Pos.TOP_LEFT);
    }


    public void setdisable(Game game){
        if(game.getListMusiques().isEmpty()){
            getVolumeSlider().setValue(0);
            getVolumeSlider().setDisable(true);
        }
    }
    public void setVolumeSlider(){
        volumeSlider.setValue(Musique.mediaPlayer.getVolume() *100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Musique.mediaPlayer.setVolume(volumeSlider.getValue()/100);
            }
        });
        volumeSlider.setMaxSize(70,70);
        volumeSlider.setOrientation(Orientation.HORIZONTAL);
        volumeSlider.getStyleClass().add("slider");
        StackPane.setAlignment(volumeSlider, Pos.CENTER_RIGHT);

    }
    public Slider getVolumeSlider() {
        return volumeSlider;
    }
    public void styleLabelSon(){
        labelSon.setFont(Font.font("Arial",15));
        labelSon.setStyle("-fx-font-weight: bold");
        labelSon.setTextFill(Color.BLACK);
    }


    public Scene getScene() {
        return scene;
    }


    public void setRevenir(Stage stage, Scene sceneBack){
        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                stage.setScene(sceneBack);
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
    }


}
