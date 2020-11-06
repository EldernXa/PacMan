package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MenuChoixDifficulté {
    private Pane pane = new Pane();
    ImageViewSizePos facile;
    ImageViewSizePos moyen;
    ImageViewSizePos difficile;
    ImageViewSizePos expert;
    ImageViewSizePos revenir ;
    private Scene scene;

    public MenuChoixDifficulté(Stage stage) {
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());

        revenir  = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2));

        Coordinate coordFacile = new Coordinate(scene.getWidth()/2-700,scene.getHeight()/2-150);
        RectanglePos rectFacile = new RectanglePos(180,270, Color.LIGHTBLUE);
        facile = new ImageViewSizePos("./data/Font/facile.png",250,170);
        List<Node> facileList = Arrays.asList(rectFacile.getRectangle(),facile.getImageView());
        ObjectsInPane facileObj = new ObjectsInPane(facileList, coordFacile,rectFacile.getRectangle().getWidth(),rectFacile.getRectangle().getHeight());

        Coordinate coordMoyen = new Coordinate(scene.getWidth()/2-350,scene.getHeight()/2-150);
        RectanglePos rectMoyen = new RectanglePos(180,270,Color.ORANGE);
        moyen = new ImageViewSizePos("./data/Font/moyen.png",250,170);
        List<Node> moyenList = Arrays.asList(rectMoyen.getRectangle(),moyen.getImageView());
        ObjectsInPane moyenObj = new ObjectsInPane(moyenList, coordMoyen,rectMoyen.getRectangle().getWidth(),rectMoyen.getRectangle().getHeight());

        Coordinate coordDifficile = new Coordinate(scene.getWidth()/2,scene.getHeight()/2-150);
        RectanglePos rectDifficile = new RectanglePos(180,270,Color.RED);
        difficile = new ImageViewSizePos("./data/Font/difficile.png",250,170);
        List<Node> difficileList = Arrays.asList(rectDifficile.getRectangle(),difficile.getImageView());
        ObjectsInPane difficileObj = new ObjectsInPane(difficileList, coordDifficile,rectDifficile.getRectangle().getWidth(),rectDifficile.getRectangle().getHeight());

        Coordinate coordExpert = new Coordinate(scene.getWidth()/2+350,scene.getHeight()/2-150);
        RectanglePos rectExpert = new RectanglePos(180,270,Color.BLACK);
        expert = new ImageViewSizePos("./data/Font/expert.png",250,170);
        List<Node> expertList = Arrays.asList(rectExpert.getRectangle(),expert.getImageView());
        ObjectsInPane expertObj = new ObjectsInPane(expertList, coordExpert,rectExpert.getRectangle().getWidth(),rectExpert.getRectangle().getHeight());

        pane.getChildren().addAll(facileObj.getPane(),moyenObj.getPane(),difficileObj.getPane(),expertObj.getPane());

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Pane root = new Pane();//Creation groupe
                root.setMinSize(0, 0);
                Scene scene = new Scene(root,getScene().getWidth(),getScene().getHeight());//Creation fenetre de taille 400 sur 400 pixels
                VisualObject visualObject = new VisualObject("./data/pacmanOuvert2.png", new Coordinate(0, 0), scene, root, new Coordinate(getScene().getWidth(),getScene().getHeight()));
                root.getChildren().add(visualObject.getImageView());
                root.setStyle("-fx-background-color: black;");
                stage.setScene(scene);
            }
        });

        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuDuJeu menuDuJeu = new MenuDuJeu(stage,true); //a revoir lier avec le menu du choix du jeu

                stage.setScene(menuDuJeu.getMenuDuJeuScene());
            }
        });
        stage.setScene(scene);
    }

    public ImageViewSizePos getFacile() {
        return facile;
    }

    public ImageViewSizePos getMoyen() {
        return moyen;
    }

    public ImageViewSizePos getDifficile() {
        return difficile;
    }

    public ImageViewSizePos getExpert() {
        return expert;
    }

    public Scene getScene() {
        return scene;
    }
}
