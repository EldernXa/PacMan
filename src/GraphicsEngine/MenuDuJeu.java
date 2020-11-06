package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    //ImageView imageView = new ImageView(new Image("data/Logos/settings.png"));
    Scene menuDuJeuScene;

    public MenuDuJeu(Stage stage,boolean bool) {

        buttonContainers.setPrefWidth(500);
        System.out.println(Screen.getPrimary().getVisualBounds().getWidth());
        System.out.println(Screen.getPrimary().getVisualBounds().getHeight());
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        menuDuJeuScene = new Scene(pane, screenWidth,screenHeight);
        menuDuJeuScene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());
        try {
            ImageView fondEcran;
            if(bool) {
                fondEcran = new ImageView(new Image(new File("./data/Logos/pacmanmenudujeu.jpg").toURI().toString()));
            }
            else{
                fondEcran = new ImageView(new Image(new File("./data/Logos/cassebriquemenudujeu.jpg").toURI().toString()));
            }
            fondEcran.setFitWidth(menuDuJeuScene.getWidth());
            fondEcran.setFitHeight(menuDuJeuScene.getHeight());
            pane.getChildren().add(fondEcran);
        }catch(Exception e){
            e.printStackTrace();
        }
        singlePlayer.setPrefWidth(buttonContainers.getPrefWidth());
        multiPlayer.setPrefWidth(buttonContainers.getPrefWidth());
        retouner.setPrefWidth(buttonContainers.getPrefWidth());

        buttonContainers.getChildren().addAll(singlePlayer,multiPlayer,retouner);

        System.out.println(buttonContainers.getAlignment());

        MenuChoixDifficulté menuChoixDifficulté = new MenuChoixDifficulté(stage);

        singlePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(bool)
                    stage.setScene(menuChoixDifficulté.getScene());
                else{
                    ImageViewSizePos imageViewSizePos = new ImageViewSizePos("./data/DevPrivate/wip.jpg",(int)screenWidth,(int)screenHeight);
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
