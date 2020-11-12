package GraphicsEngine;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class MenuParametres {
    private Scene scene;
    private StackPane pane = new StackPane();
    private Slider volumeSlider = new Slider();
    ImageViewSizePos revenir ;
    private Stage stage = new Stage();


    public MenuParametres() {

        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/2,Screen.getPrimary().getVisualBounds().getHeight()/2);
        Label titre = new Label("Parametres");
        titre.setStyle("-fx-font-size: 30px");
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
        pane.setStyle("-fx-background-color: gray");

        pane.getChildren().addAll(volumeSlider,revenir.getImageView(),titre);

        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                stage.close();
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
        StackPane.setAlignment(titre,Pos.TOP_CENTER);

        stage.setScene(scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }
}
