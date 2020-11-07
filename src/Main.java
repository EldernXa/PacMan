import GraphicsEngine.Coordinate;
import GraphicsEngine.MenuDuJeu;
import GraphicsEngine.VisualObject;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import GraphicsEngine.MenuChoixDuJeu;

import javafx.scene.Scene;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MenuChoixDuJeu menuChoixDuJeu = new MenuChoixDuJeu(primaryStage);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
