package GamePlay;

import GraphicsEngine.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ConclusionPacman implements Conclusion {
    private StackPane pane = new StackPane();
    private final Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/4,Screen.getPrimary().getVisualBounds().getHeight()/4);;;
    private final Button rejouer =  new Button("REJOUER");
    //private final Button retourDiff = new Button("RETOURNER AU CHOIX DE DIFFICULTES");
    private final Button retourMenu = new Button("CHOIX DU JEU");
    private final HBox hbox = new HBox(10);
    private final HBox hboxLabel = new HBox();
    private Label titre = new Label();
    Stage stage= new Stage();


    public ConclusionPacman(Stage stageJeu, boolean bool){
        MenuChoixDuJeu menuChoixDuJeu= new MenuChoixDuJeu(stage);
        //clickRetourDiff(stageJeu,game,menuChoixDuJeu);
        clickRejouer(stageJeu);
        clickRetourMenu(stageJeu,menuChoixDuJeu);
        setHbox();
        labelForGame(bool);
        setPane();
        stage.setScene(scene);
        stage.show();


    }
    public void setPane(){
        pane.setStyle("-fx-background-color: black");
        pane.getChildren().addAll(hbox,titre);
    }
    public void labelForGame(boolean bool){
        if(bool){
            titre.setText("Vous avez gagné");
            titre.setTextFill(Color.GREEN);
        }else{
            titre.setText("Vous avez perdu");
            titre.setTextFill(Color.RED);
        }
        hboxLabel.getChildren().add(titre);
        hboxLabel.setAlignment(Pos.TOP_CENTER);


    }
    public void clickRejouer(Stage stageJeu){
        rejouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stageJeu.close();
                Map map = new Map(stage,"./data/Map/PacmanMap.txt");
                stage.setScene(map.getMapScene());
            }
        });
    }/*public void clickRetourDiff(Stage stageJeu,Game game,MenuChoixDuJeu menuChoixDuJeu){
        retourDiff.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuDuJeu menuDuJeu = new MenuDuJeu(stage,game,menuChoixDuJeu.getMenuScene());
                MenuChoixDifficulte menuChoixDifficulte= new MenuChoixDifficulte(stage,game,menuDuJeu.getMenuDuJeuScene());
                stageJeu.close();
                stage.setScene(menuChoixDifficulte.getScene());
            }
        });
    } */
    public void clickRetourMenu(Stage stageJeu,MenuChoixDuJeu menuChoixDuJeu){
        retourMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stageJeu.close();
                stage.setScene(menuChoixDuJeu.getMenuScene());
                stage.setMaximized(true);
            }
        });
    }
    public void setHbox(){
        hbox.getChildren().addAll(rejouer,retourMenu);
        hbox.setAlignment(Pos.TOP_CENTER);
    }


    public Scene getScene() {
        return scene;
    }
}
