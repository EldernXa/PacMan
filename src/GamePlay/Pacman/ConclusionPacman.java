package GamePlay.Pacman;

import GamePlay.MenuChoixDifficulte;
import GamePlay.MenuChoixDuJeu;
import GamePlay.MenuDuJeu;
import GraphicsEngine.*;
import GraphicsEngine.Map;
import MusicEngine.Musique;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class
ConclusionPacman implements Conclusion {
    private StackPane pane = new StackPane();
    private final Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/4,Screen.getPrimary().getVisualBounds().getHeight()/4);;;
    private final Button rejouer =  new Button("REJOUER");
    //private final Button retourDiff = new Button("RETOURNER AU CHOIX DE DIFFICULTES");
    private final Button retourMenuChoix = new Button("CHOIX DU JEU");
    private final Button retourMenu = new Button("MENU DU JEU");
    private final HBox hbox = new HBox(10);
    private final HBox hboxLabel = new HBox();
    private Label titre = new Label();
    private Stage stage= new Stage();
    private Button exit = new Button("QUITTER");
    private final PacMan pacMan;


    public ConclusionPacman(Stage stageJeu, boolean bool,int nbPoints, PacMan pacMan){
        MenuChoixDuJeu menuChoixDuJeu= new MenuChoixDuJeu(stage);

        this.pacMan = pacMan;
        for(EventHandler<KeyEvent> eventHandler : Map.getListEventHandler()){
            stageJeu.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
        }
        VisualObject.stopTimelineParallel();
        VisualObject.clearTimelineParallel();
        clickRejouer(stageJeu);
        clickRetourMenuChoix(stageJeu,menuChoixDuJeu);
        clickRetourMenu(stageJeu);
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
                Map map = new MapPacman(stageJeu,"./data/Map/"+ Map.diff.getName()+"_");
                stageJeu.setScene(map.getMapScene());
            }
        });
    }
    /*public void clickRetourDiff(Stage stageJeu,Game game,MenuChoixDuJeu menuChoixDuJeu){
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
    public void clickRetourMenuChoix(Stage stageJeu, MenuChoixDuJeu menuChoixDuJeu){
        retourMenuChoix.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stageJeu.close();
                Map.visualObjects.clear();
                stage.setScene(menuChoixDuJeu.getMenuScene());
                stage.setMaximized(true);
            }
        });
    }

    public void clickRetourMenu(Stage stageJeu){
        retourMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Game game = new Game("Pacman");
                game.setImageJeu(new ImageViewSizePos("./data/Jeux/"+game.getName() +"/menuchoixdujeu.jpg", 500, 250));
                for(String string : Objects.requireNonNull(new File("./data/Jeux/" + game.getName()).list())){
                    if(string.substring(0,7).equals("musique")){
                        game.getListMusiques().add(new Musique("./data/Jeux/"+game.getName()+"/"+string));
                    }
                }
                MenuDuJeu menuDuJeu = new MenuDuJeu(stage,game,null);
                stageJeu.close();
                Map.visualObjects.clear();
                stage.setScene(menuDuJeu.getMenuDuJeuScene());
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
        hbox.getChildren().addAll(rejouer);
        if(Menu.getMenuJeu())
            hbox.getChildren().add(retourMenu);
        if(Menu.getMenuChoiceGame())
            hbox.getChildren().add(retourMenuChoix);

        hbox.getChildren().add(exit);
        hbox.setAlignment(Pos.TOP_CENTER);
    }


    public Scene getScene() {
        return scene;
    }
}
