package GraphicsEngine;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;

public class MenuParametres {
    private Scene scene;
    private Scene sceneBack;
    private StackPane pane = new StackPane();
    private Slider volumeSlider = new Slider();
    ImageViewSizePos revenir ;


    public MenuParametres(Stage stage,Scene sceneBack) {
        this.sceneBack = sceneBack;
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/2,Screen.getPrimary().getVisualBounds().getHeight()/2);

        /*ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton("Male");
        button1.setToggleGroup(group);
        button1.setSelected(true);

// Radio 3: Female.
        RadioButton button2 = new RadioButton("Female");
        button2.setToggleGroup(group); */
        volumeSlider.setValue(Musique.mediaPlayer.getVolume() *100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Musique.mediaPlayer.setVolume(volumeSlider.getValue()/100);
            }
        });
        revenir  = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2));

        pane.getChildren().addAll(volumeSlider,revenir.getImageView());

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
        StackPane.setAlignment(revenir.getImageView(),Pos.TOP_LEFT);
        volumeSlider.setMaxSize(100,100);
        volumeSlider.setOrientation(Orientation.VERTICAL);
        StackPane.setAlignment(volumeSlider, Pos.CENTER_RIGHT);
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}
