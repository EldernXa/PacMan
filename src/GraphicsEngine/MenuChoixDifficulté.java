package GraphicsEngine;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
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

    public MenuChoixDifficulté(Stage stage, String name) {
        scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        revenir  = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2));


        ImageViewSizePos fond = new ImageViewSizePos("./data/Logos/"+ name + "menudujeu.jpg",(int)scene.getWidth(),(int)scene.getHeight());
        RectanglePos rectFacile = new RectanglePos(180,270, Color.LIGHTBLUE);
        Coordinate coordFacile = new Coordinate(scene.getWidth()/8-(rectFacile.getRectangle().getWidth()/2),scene.getHeight()/2-(rectFacile.getRectangle().getHeight()/2));
        facile = new ImageViewSizePos("./data/Font/facile.png",250,170);
        List<Node> facileList = Arrays.asList(rectFacile.getRectangle(),facile.getImageView());
        ObjectsInPane facileObj = new ObjectsInPane(facileList, coordFacile,rectFacile.getRectangle().getWidth(),rectFacile.getRectangle().getHeight());

        RectanglePos rectMoyen = new RectanglePos(180,270,Color.ORANGE);
        Coordinate coordMoyen = new Coordinate((3*scene.getWidth()/8)-(rectMoyen.getRectangle().getWidth()/2),scene.getHeight()/2-(rectMoyen.getRectangle().getHeight()/2));
        moyen = new ImageViewSizePos("./data/Font/moyen.png",250,170);
        List<Node> moyenList = Arrays.asList(rectMoyen.getRectangle(),moyen.getImageView());
        ObjectsInPane moyenObj = new ObjectsInPane(moyenList, coordMoyen,rectMoyen.getRectangle().getWidth(),rectMoyen.getRectangle().getHeight());

        RectanglePos rectDifficile = new RectanglePos(180,270,Color.RED);
        Coordinate coordDifficile = new Coordinate((5*scene.getWidth()/8)-(rectDifficile.getRectangle().getWidth()/2),scene.getHeight()/2-(rectDifficile.getRectangle().getHeight()/2));
        difficile = new ImageViewSizePos("./data/Font/difficile.png",250,170);
        List<Node> difficileList = Arrays.asList(rectDifficile.getRectangle(),difficile.getImageView());
        ObjectsInPane difficileObj = new ObjectsInPane(difficileList, coordDifficile,rectDifficile.getRectangle().getWidth(),rectDifficile.getRectangle().getHeight());

        RectanglePos rectExpert = new RectanglePos(180,270,Color.BLACK);
        Coordinate coordExpert = new Coordinate((7*scene.getWidth()/8)-(rectExpert.getRectangle().getWidth()/2),scene.getHeight()/2-(rectExpert.getRectangle().getHeight()/2));
        expert = new ImageViewSizePos("./data/Font/expert.png",250,170);
        List<Node> expertList = Arrays.asList(rectExpert.getRectangle(),expert.getImageView());
        ObjectsInPane expertObj = new ObjectsInPane(expertList, coordExpert,rectExpert.getRectangle().getWidth(),rectExpert.getRectangle().getHeight());

        pane.getChildren().addAll(fond.getImageView(),facileObj.getPane(),moyenObj.getPane(),difficileObj.getPane(),expertObj.getPane(), revenir.getImageView());


        facileObj.getPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                MenuDuJeu menuDuJeu = new MenuDuJeu(stage,name ,true); //a revoir lier avec le menu du choix du jeu

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
