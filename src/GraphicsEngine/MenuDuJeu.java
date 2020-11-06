package GraphicsEngine;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class MenuDuJeu {
    Pane pane = new Pane();
    Button singlePlayer = new Button("SinglePlayer");
    Button multiPlayer = new Button("MultiPlayer");
    //ImageView imageView = new ImageView(new Image("data/Logos/settings.png"));
    Scene menuDuJeuScene;

    public MenuDuJeu(Stage stage, String pathImage) {
        try {
            ImageView fondEcran = new ImageView(new Image(new File(pathImage).toURI().toString()));
            pane.getChildren().add(fondEcran);
        }catch(Exception e){
            e.printStackTrace();
        }
        menuDuJeuScene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());

        menuDuJeuScene.setRoot(pane);
    }

    public Scene getMenuDuJeuScene() {
        return menuDuJeuScene;
    }
}
