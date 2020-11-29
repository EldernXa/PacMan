package Menu;

import GamePlay.MenuChoixDuJeu;
import javafx.application.Application;
import javafx.stage.Stage;

public class LancementPacmanTest extends Application {

        @Override
        public void start(Stage stage) throws Exception {

                new MenuChoixDuJeu(stage);
                stage.show();

        }
}