import GraphicsEngine.*;
import javafx.application.Application;
import javafx.stage.Stage;


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
