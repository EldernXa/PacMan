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
    private Scene scene;

    public MenuChoixDifficulté(Stage stage) {
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());







        facile = new ImageView(new Image(new File("./data/Font/facile.png").toURI().toString()));
        moyen = new ImageView(new Image(new File("./data/Font/moyen.png").toURI().toString()));
        difficile = new ImageView(new Image(new File("./data/Font/difficile.png").toURI().toString()));
        expert = new ImageView(new Image(new File("./data/Font/expert.png").toURI().toString()));
        Coordinate coordFacile = new Coordinate(scene.getWidth()/2-600,scene.getHeight()/2-150);
        Coordinate coordMoyen = new Coordinate(scene.getWidth()/2-300,scene.getHeight()/2-150);
        Coordinate coordDifficile = new Coordinate(scene.getWidth()/2,scene.getHeight()/2-150);
        Coordinate coordExptert = new Coordinate(scene.getWidth()/2+300,scene.getHeight()/2-150);
        RectanglePos rectFacile = new RectanglePos(170,240, Color.LIGHTBLUE,coordFacile);
        RectanglePos rectMoyen = new RectanglePos(170,240,Color.ORANGE,coordMoyen);
        RectanglePos rectDifficile = new RectanglePos(170,240,Color.RED,coordDifficile);
        RectanglePos rectExpert = new RectanglePos(170,240,Color.BLACK,coordExptert);
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
        paneFacile.getChildren().addAll(rectFacile.getRectangle(),facile);
        pane.getChildren().addAll(paneFacile,rectMoyen.getRectangle(),rectDifficile.getRectangle(),rectExpert.getRectangle(),moyen,difficile,expert);

        paneFacile.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
