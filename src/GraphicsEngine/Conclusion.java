package GraphicsEngine;

import GameEngine.Game;
import GamePlay.MenuChoixDuJeu;
import GamePlay.MenuDuJeu;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;


public abstract class Conclusion {

    private final StackPane pane;
    private final Scene scene;
    private final Button rejouer;
    private final Button retourMenuChoix;
    private final Button retourMenu;
    private final HBox hbox;
    private final HBox hboxLabel;
    private final Label titre;
    private final Stage stage;
    private final Button exit;
    private final boolean bool;

    public Conclusion(Stage stageJeu, boolean bool){
        this.bool = bool;
        pane = new StackPane();
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/4, Screen.getPrimary().getVisualBounds().getHeight()/4);
        rejouer = new Button("REJOUER");
        retourMenuChoix = new Button("CHOIX DU JEU");
        retourMenu = new Button("MENU DU JEU");
        hbox = new HBox(10);
        hboxLabel = new HBox();
        titre = new Label();
        hboxLabel.getChildren().add(titre);
        hboxLabel.setAlignment(Pos.TOP_CENTER);
        stage = new Stage();
        exit = new Button("QUITTER");

        for(EventHandler<KeyEvent> eventHandler: Map.getListEventHandler()){
            stageJeu.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
        }
        VisualObject.stopTimelineParallel();
        VisualObject.clearTimelineParallel();
        clickRejouer(stageJeu);
        clickRetourMenuChoix(stageJeu);
        clickRetourMenu(stageJeu);
        setHbox();
        setPane();
        clickExit();
        stage.setScene(scene);
        stage.show();
    }

    public boolean getBool(){
        return bool;
    }

    public void labelForGame(){
        initLabel(titre);
        titre.setFont(Font.font("Arial",20));
    }

    public abstract void initLabel(Label lbl);

    public Scene getScene() {
        return scene;
    }

    public void clickExit(){
        exit.setOnMouseClicked(mouseEvent->{
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     *
     * @param stageJeu new stage for new window
     */
    public void clickRetourMenu(Stage stageJeu){
        retourMenu.setOnMouseClicked(mouseEvent->{
            Game game = initGame();
            MenuDuJeu menuDuJeu = new MenuDuJeu(stage, game, null);
            stageJeu.close();
            Map.visualObjects.clear();
            stage.setScene(menuDuJeu.getMenuDuJeuScene());
            stage.setMaximized(true);
        });
    }

    public abstract Game initGame();

    /**
     *
     * @param stageJeu new stage for new window
     */
    public void clickRetourMenuChoix(Stage stageJeu){
        retourMenuChoix.setOnMouseClicked(mouseEvent -> {
            stageJeu.close();
            Map.visualObjects.clear();
            stage.setScene(new MenuChoixDuJeu(stage).getMenuScene());
            stage.setMaximized(true);
        });
    }

    public void setPane(){
        pane.setStyle("-fx-background-color: black");
        pane.getChildren().addAll(hbox,titre);
    }

    /**
     *
     * @param stageJeu new stage for new window
     */
    public void clickRejouer(Stage stageJeu){
        rejouer.setOnMouseClicked(mouseEvent -> {
            stage.close();
            Map.getVisualObjects().clear();
            Map map = initMap(stageJeu);
        });
    }

    public abstract Map initMap(Stage stageJeu);

    /**
     * set the buttons
     */
    public void setHbox(){
        hbox.getChildren().addAll(rejouer);
        rejouer.setVisible(true);
        if(Menu.getMenuJeu())
            hbox.getChildren().add(retourMenu);
        if(Menu.getMenuChoiceGame())
            hbox.getChildren().add(retourMenuChoix);

        hbox.getChildren().add(exit);
        hbox.setAlignment(Pos.TOP_CENTER);
    }
}
