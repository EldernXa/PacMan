import GraphicsEngine.*;
import ReadFile.ReadFileCommandes;
import ReadFile.ReadFileMap2Pacman;
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
//        ReadFileMap2Pacman readFileMap2Pacman = new ReadFileMap2Pacman("./data/Map/PacmanMap.txt");
       /* ReadFileCommandes test = new ReadFileCommandes("./data/Controles/Pacman/controlesPac.txt");
        test.write("a",'a',1,1); */
        launch(args);
    }
}
