package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Conclusion {
    private StackPane pane;
    private Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/2,Screen.getPrimary().getVisualBounds().getHeight()/2);;;
    private Button rejouer =  new Button("REJOUER");
    private Button retourDiff = new Button("RETOURNER AU CHOIX DE DIFFICULTES");
    private Button retourMenu = new Button("RETOURNER AU MENU DU CHOIX DU JEU");
    HBox hbox = new HBox(10);


    public Conclusion(Stage stage, Scene sceneBack, Game game){
        MenuChoixDuJeu menuChoixDuJeu= new MenuChoixDuJeu(stage);
        clickRetourDiff(stage,game,menuChoixDuJeu);
        clickRejouer(stage,sceneBack);
        clickRetourMenu(stage,menuChoixDuJeu);


    }

    public void clickRejouer(Stage stage, Scene sceneBack){
        rejouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(sceneBack);
            }
        });
    }
    public void clickRetourDiff(Stage stage,Game game,MenuChoixDuJeu menuChoixDuJeu){
        retourDiff.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuDuJeu menuDuJeu = new MenuDuJeu(stage,game,menuChoixDuJeu.getMenuScene());
                MenuChoixDifficulte menuChoixDifficulte= new MenuChoixDifficulte(stage,game,menuDuJeu.getMenuDuJeuScene());
                stage.setScene(menuChoixDifficulte.getScene());
            }
        });
    }
    public void clickRetourMenu(Stage stage,MenuChoixDuJeu menuChoixDuJeu){
        retourMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

               stage.setScene(menuChoixDuJeu.getMenuScene());
            }
        });
    }

}
