import GraphicsEngine.Coordinate;
import GraphicsEngine.MenuDuJeu;
import GraphicsEngine.VisualObject;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import GraphicsEngine.MenuChoixDuJeu;

import javafx.scene.Scene;


public class Main extends Application {
    private final int xMax = 800;
    private final int yMax = 500;


    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();//Creation groupe
        root.setMaxHeight(yMax);
        root.setMaxWidth(xMax);
        root.setMinSize(0, 0);
        Scene scene = new Scene(root,xMax,yMax);//Creation fenetre de taille 400 sur 400 pixels
        /*Circle b = new Circle();
        b.setCenterX(400);
        b.setCenterY(250);
        b.setRadius(10);
        b.setFill(Color.BLUE);
        Controller a = new Controller(b,scene,root,primaryStage);
        a.controle();*/
        //Menu menu = new Menu();
        VisualObject visualObject = new VisualObject("./data/pacmanOuvert2.png", new Coordinate(0, 0), scene, root, new Coordinate(xMax, yMax));
        root.getChildren().add(visualObject.getImageView());
        root.setStyle("-fx-background-color: black;");
        System.out.println(scene.heightProperty());
        primaryStage.setScene(scene);
        MenuChoixDuJeu menuChoixDuJeu = new MenuChoixDuJeu(primaryStage);

        MenuDuJeu menuPacMan = new MenuDuJeu(primaryStage, "./data/Logos/pacmanmenudujeu.jpg");
        MenuDuJeu menuCasseBrique = new MenuDuJeu(primaryStage,"./data/Logos/cassebriquemenudujeu.jpg");

        menuChoixDuJeu.getImageJeu1().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menuChoixDuJeu.changerScene(menuCasseBrique.getMenuDuJeuScene());
            }
        });
        menuChoixDuJeu.getImageJeu2().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menuChoixDuJeu.changerScene(menuPacMan.getMenuDuJeuScene());
            }
        });
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
