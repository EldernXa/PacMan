package Menu;

import GraphicsEngine.MenuChoixDuJeu;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class LancementPacmanTest extends Application {

        @Override
        public void start(Stage stage) throws Exception {

                new MenuChoixDuJeu(stage);
                stage.show();

        }
}