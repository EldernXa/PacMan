import GraphicsEngine.*;
import ReadFile.ReadFileCommandes;
import ReadFile.ReadFileOptions;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Menu menu = new Menu(primaryStage, true, true, "Pacman");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
//        ReadFileMap2Pacman readFileMap2Pacman = new ReadFileMap2Pacman("./data/Map/FACILE_PacmanMap.txt");
/*        ReadFileCommandes test = new ReadFileCommandes("./data/Controles/Pacman/controles.txt",true);
        System.out.println(test.getDirectionMulti());
        System.out.println(test.getToucheMulti());

        test.writeMulti("monter",'p');*/

/*        ReadFileOptions test = new ReadFileOptions("./data/Jeux/Pacman/options.txt");

        System.out.println(test.isState("music"));*/

        launch(args);
    }
}
