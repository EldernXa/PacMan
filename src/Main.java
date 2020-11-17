import GraphicsEngine.*;
import ReadFile.ReadFileMap2Pacman;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MenuChoixDuJeu menuChoixDuJeu = new MenuChoixDuJeu(primaryStage);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        File file = new File("./data/Map/PacmanMap.txt");
        ReadFileMap2Pacman readFileMap2Pacman = new ReadFileMap2Pacman(file);
        launch(args);
    }
}
