package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class MenuDuJeu {
    StackPane pane = new StackPane();
    Button singlePlayer = new Button("SinglePlayer".toUpperCase());
    Button multiPlayer = new Button("MultiPlayer".toUpperCase());
    VBox buttonContainers = new VBox(10);
    //ImageView imageView = new ImageView(new Image("data/Logos/settings.png"));
    Scene menuDuJeuScene;

    public MenuDuJeu(Stage stage, String pathImage) {

        System.out.println(Screen.getPrimary().getVisualBounds().getWidth());
        System.out.println(Screen.getPrimary().getVisualBounds().getHeight());
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        menuDuJeuScene = new Scene(pane, screenWidth,screenHeight);
        try {
            ImageView fondEcran = new ImageView(new Image(new File(pathImage).toURI().toString()));
            fondEcran.setFitWidth(menuDuJeuScene.getWidth());
            fondEcran.setFitHeight(menuDuJeuScene.getHeight());
            pane.getChildren().add(fondEcran);
        }catch(Exception e){
            e.printStackTrace();
        }
        buttonContainers.getChildren().addAll(singlePlayer,multiPlayer);
        System.out.println(buttonContainers.getAlignment());

        MenuChoixDifficulté menuChoixDifficulté = new MenuChoixDifficulté(stage);
        singlePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(menuChoixDifficulté.getScene());;
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
