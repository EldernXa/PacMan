package GamePlay;

import GraphicsEngine.*;
import GraphicsEngine.Maps.Map;
import GraphicsEngine.Maps.MapPacman;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class
ConclusionPacman implements Conclusion {
    private StackPane pane = new StackPane();
    private final Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/4,Screen.getPrimary().getVisualBounds().getHeight()/4);;;
    private final Button rejouer =  new Button("REJOUER");
    //private final Button retourDiff = new Button("RETOURNER AU CHOIX DE DIFFICULTES");
    private final Button retourMenu = new Button("CHOIX DU JEU");
    private final HBox hbox = new HBox(10);
    private final HBox hboxLabel = new HBox();
    private Label titre = new Label();
    private Stage stage= new Stage();
    private Button exit = new Button("Quitter");


    public ConclusionPacman(Stage stageJeu, boolean bool,int nbPoints){
        MenuChoixDuJeu menuChoixDuJeu= new MenuChoixDuJeu(stage);
        VisualObject.stopTimelineParallel();
        VisualObject.clearTimelineParallel();
        clickRejouer(stageJeu);
        clickRetourMenu(stageJeu,menuChoixDuJeu);
        setHbox();
        labelForGame(bool,nbPoints);
        setPane();
        clickExit();
        stage.setScene(scene);
        stage.show();

    }

    public void setPane(){
        pane.setStyle("-fx-background-color: black");
        pane.getChildren().addAll(hbox,titre);
    }
    public void labelForGame(boolean bool, int nbPoints){
        if(bool){
            titre.setText("Vous avez gagné ! \nVous avez obtenu : "  +nbPoints +" Points.");
            titre.setTextFill(Color.GREEN);
        }else{
            titre.setText("Vous avez perdu! \nVous avez obtenu : "  +nbPoints +" Points.");
            titre.setTextFill(Color.RED);
        }
        titre.setFont(Font.font("Arial",20));
        hboxLabel.getChildren().add(titre);
        hboxLabel.setAlignment(Pos.TOP_CENTER);


    }
    public void clickRejouer(Stage stageJeu){
        rejouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
                Map.visualObjects.clear();
                Map map = new MapPacman(stageJeu,"./data/Map/");
                stageJeu.setScene(map.getMapScene());
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
                Map.visualObjects.clear();
                stage.setScene(menuChoixDuJeu.getMenuScene());
                stage.setMaximized(true);
            }
        });
    }
    public void clickExit(){
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    public void setHbox(){
        hbox.getChildren().addAll(rejouer,retourMenu,exit);
        hbox.setAlignment(Pos.TOP_CENTER);
    }


    public Scene getScene() {
        return scene;
    }
}
