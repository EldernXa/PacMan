package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Conclusion {
    private StackPane pane = new StackPane();
    private final Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/2,Screen.getPrimary().getVisualBounds().getHeight()/2);;;
    private final Button rejouer =  new Button("REJOUER");
    private final Button retourDiff = new Button("RETOURNER AU CHOIX DE DIFFICULTES");
    private final Button retourMenu = new Button("RETOURNER AU MENU DU CHOIX DU JEU");
    private final HBox hbox = new HBox(10);
    private Label titre = new Label();


    public Conclusion(Stage stage, Scene sceneBack, Game game,boolean bool){
        MenuChoixDuJeu menuChoixDuJeu= new MenuChoixDuJeu(stage);
        clickRetourDiff(stage,game,menuChoixDuJeu);
        clickRejouer(stage,sceneBack);
        clickRetourMenu(stage,menuChoixDuJeu);
        setHbox();
        labelForGame(bool);
        setPane();


    }
    public void setPane(){
        pane.setStyle("-fx-background-color: black");
        pane.getChildren().addAll(hbox,titre);
    }
    public void labelForGame(boolean bool){
        if(bool){
            titre.setText("Vous avez gagné");
        }else{
            titre.setText("Vous avez perdu");
        }
        titre.setAlignment(Pos.TOP_CENTER);
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
    public void setHbox(){
        hbox.getChildren().addAll(rejouer,retourDiff,retourMenu);
        hbox.setAlignment(Pos.CENTER);
    }

    public Scene getScene() {
        return scene;
    }
}
