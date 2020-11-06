package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class MenuChoixDifficulté {
    private Pane pane = new Pane();
    ImageView facile;
    ImageView moyen;
    ImageView difficile;
    ImageView expert;
    ImageViewSizePos revenir ;
    private Scene scene;

    public MenuChoixDifficulté(Stage stage) {
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());






        revenir  = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2));
        facile = new ImageView(new Image(new File("./data/Font/facile.png").toURI().toString()));
        moyen = new ImageView(new Image(new File("./data/Font/moyen.png").toURI().toString()));
        difficile = new ImageView(new Image(new File("./data/Font/difficile.png").toURI().toString()));
        expert = new ImageView(new Image(new File("./data/Font/expert.png").toURI().toString()));
        Coordinate coordFacile = new Coordinate(scene.getWidth()/2-700,scene.getHeight()/2-150);
        Coordinate coordMoyen = new Coordinate(scene.getWidth()/2-350,scene.getHeight()/2-150);
        Coordinate coordDifficile = new Coordinate(scene.getWidth()/2,scene.getHeight()/2-150);
        Coordinate coordExptert = new Coordinate(scene.getWidth()/2+350,scene.getHeight()/2-150);
        RectanglePos rectFacile = new RectanglePos(210,270, Color.LIGHTBLUE,new Coordinate(coordFacile.getX()-10,coordFacile.getY()));
        RectanglePos rectMoyen = new RectanglePos(210,270,Color.ORANGE,new Coordinate(coordMoyen.getX()-10,coordMoyen.getY()));
        RectanglePos rectDifficile = new RectanglePos(210,270,Color.RED,new Coordinate(coordDifficile.getX()-10,coordDifficile.getY()));
        RectanglePos rectExpert = new RectanglePos(210,270,Color.BLACK,new Coordinate(coordExptert.getX()-10,coordExptert.getY()));
        facile.setTranslateX(coordFacile.getX());
        facile.setTranslateY(coordFacile.getY());
        facile.setFitWidth(250);
        facile.setFitHeight(200);

        moyen.setTranslateX(coordMoyen.getX());
        moyen.setTranslateY(coordMoyen.getY());
        moyen.setFitWidth(250);
        moyen.setFitHeight(200);

        difficile.setTranslateX(coordDifficile.getX());
        difficile.setTranslateY(coordDifficile.getY());
        difficile.setFitWidth(250);
        difficile.setFitHeight(200);

        expert.setTranslateX(coordExptert.getX());
        expert.setTranslateY(coordExptert.getY());
        expert.setFitWidth(250);
        expert.setFitHeight(200);

        Pane paneFacile = new Pane();
        pane.setMaxSize(270,210);
        paneFacile.getChildren().addAll(rectFacile.getRectangle(),facile);
        pane.getChildren().addAll(paneFacile,rectMoyen.getRectangle(),rectDifficile.getRectangle(),rectExpert.getRectangle(),moyen,difficile,expert,revenir.getImageView());

        facile.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                MenuDuJeu menuDuJeu = new MenuDuJeu(stage,true);

                stage.setScene(menuDuJeu.getMenuDuJeuScene());
            }
        });
        stage.setScene(scene);
    }

    public ImageView getFacile() {
        return facile;
    }

    public ImageView getMoyen() {
        return moyen;
    }

    public ImageView getDifficile() {
        return difficile;
    }

    public ImageView getExpert() {
        return expert;
    }

    public Scene getScene() {
        return scene;
    }
}
