package GraphicsEngine;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class MenuDuJeu {
    StackPane pane = new StackPane();
    Button singlePlayer = new Button("SinglePlayer".toUpperCase());
    Button multiPlayer = new Button("MultiPlayer".toUpperCase());
    VBox buttonContainers = new VBox(15);
    Button retouner = new Button("Retourner au choix du jeu".toUpperCase());
    ImageViewSizePos param;
    ImageViewSizePos sound;
    ImageViewSizePos nosound;
    Scene menuDuJeuScene;
    HBox hbox = new HBox(20);

    public MenuDuJeu(Stage stage,String name) {
        buttonContainers.setPrefWidth(400);
        System.out.println(Screen.getPrimary().getVisualBounds().getWidth());
        System.out.println(Screen.getPrimary().getVisualBounds().getHeight());
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        menuDuJeuScene = new Scene(pane, screenWidth,screenHeight);
        menuDuJeuScene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());

        param =new ImageViewSizePos("data/Logos/settings.png",40, 40);
        sound = new ImageViewSizePos("data/Logos/sound.png",40,40);
        nosound = new ImageViewSizePos("data/Logos/nosound.png",40,40);
        ImageViewSizePos fondEcran = new ImageViewSizePos("./data/Logos/" + name + "menudujeu.jpg",menuDuJeuScene.getWidth(),menuDuJeuScene.getHeight());
        pane.getChildren().add(fondEcran.getImageView());

        hbox.getChildren().addAll(param.getImageView(),sound.getImageView());
        singlePlayer.setPrefWidth(buttonContainers.getPrefWidth());
        multiPlayer.setPrefWidth(buttonContainers.getPrefWidth());
        retouner.setPrefWidth(buttonContainers.getPrefWidth());
        hbox.setPrefWidth(buttonContainers.getPrefWidth());
        hbox.setAlignment(Pos.CENTER);
        buttonContainers.getChildren().addAll(singlePlayer,multiPlayer,retouner, hbox);

        System.out.println(buttonContainers.getAlignment());

        MenuChoixDifficulté menuChoixDifficulté = new MenuChoixDifficulté(stage, name);

        sound.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hbox.getChildren().remove(sound.getImageView());
                hbox.getChildren().add(nosound.getImageView());
            }
        });
        nosound.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hbox.getChildren().remove(nosound.getImageView());
                hbox.getChildren().add(sound.getImageView());
            }
        });
        singlePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(name.equals("pacman"))
                    stage.setScene(menuChoixDifficulté.getScene());
                else{
                    ImageViewSizePos imageViewSizePos = new ImageViewSizePos("./data/DevPrivate/wip.jpg",screenWidth,screenHeight);
                    pane.getChildren().clear();
                    pane.getChildren().addAll(imageViewSizePos.getImageView(),retouner);
                    stage.setScene(menuDuJeuScene);
                }
            }
        });
        retouner.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuChoixDuJeu menuChoixDuJeu  = new MenuChoixDuJeu(stage);
                stage.setScene(menuChoixDuJeu.getMenuScene());
            }
        });
        pane.getChildren().addAll(buttonContainers);
        buttonContainers.setAlignment(Pos.CENTER);
        menuDuJeuScene.setRoot(pane);
    }

    public Scene getMenuDuJeuScene() {
        return menuDuJeuScene;
    }



}
