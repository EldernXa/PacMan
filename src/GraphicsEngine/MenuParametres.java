package GraphicsEngine;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;

public class MenuParametres {
    private Scene scene;
    private Scene sceneBack;
    private Pane pane = new Pane();
    private Slider volumeSlider = new Slider();


    public MenuParametres(Stage stage,Scene sceneBack) {
        this.sceneBack = sceneBack;
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
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

        pane.getChildren().add(volumeSlider);
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}
