package GraphicsEngine;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class MenuChoixDuJeu {
    ArrayList<Game> gameList = new ArrayList<>();

    private Pane pane = new Pane();
    private Stage stage;
    private Scene menuScene;
    private Button buttonExit = new Button("Quitter le menu");
    private ImageViewSizePos imageJeu1;
    private ImageViewSizePos imageJeu2;

    private ImageViewSizePos previousGame;
    private ImageViewSizePos currentGame;
    private ImageViewSizePos nextGame;

    private ImageViewSizePos imageFond;
    Label choixDuJeuLabel = new Label("Choisissez votre Jeu :");
    ArrayList<Game> listJeux = new ArrayList<>();


    public MenuChoixDuJeu(Stage stage) {

        remplirListGame();
        remplirGamesAttributs();
        System.out.println("hey");

        System.out.println("width " + Screen.getPrimary().getVisualBounds().getWidth()/4);
        menuScene = new Scene(pane,Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        menuScene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());
        choixDuJeuLabel.setFont(Font.font("Avenir Next", 45));
        choixDuJeuLabel.setUnderline(true);
        choixDuJeuLabel.setTranslateX(menuScene.getWidth()/2-220);
        choixDuJeuLabel.setTranslateY(70);
        choixDuJeuLabel.setStyle("-fx-border-color: black;");
        choixDuJeuLabel.setPadding(new Insets(7));
        choixDuJeuLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));

        imageFond =  new ImageViewSizePos("./data/Logos/menuchoixdujeu.jpg",menuScene.getWidth(),menuScene.getHeight());

        imageJeu1 = new ImageViewSizePos("./data/Jeux/Pacman/menuchoixdujeu.jpg",500,250);
        Coordinate coordImageJeu1 = new Coordinate(menuScene.getWidth()/2-(imageJeu1.getImageView().getFitWidth()/2)-(menuScene.getWidth()/4),menuScene.getHeight()/2-(imageJeu1.getImageView().getFitHeight()/2));
        imageJeu1.setCoordinate(coordImageJeu1);
        imageJeu2 = new ImageViewSizePos("./data/Logos/cassebriquemenuchoixdujeu.jpg",500,250);
        Coordinate coordImageJeu2 = new Coordinate(menuScene.getWidth()/2-(imageJeu2.getImageView().getFitWidth()/2)+(menuScene.getWidth()/4),menuScene.getHeight()/2-(imageJeu2.getImageView().getFitHeight()/2));
        imageJeu2.setCoordinate(coordImageJeu2);




        recupJeux();
        imageJeu1.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuDuJeu menuPacMan = new MenuDuJeu(stage,"Pacman",new Musique("./data/Jeux/Pacman/musiquemenu" +
                        ".wav"));
                changerScene(menuPacMan.getMenuDuJeuScene());
            }
        });

        imageJeu2.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuDuJeu menuCasseBrique = new MenuDuJeu(stage,"Casse-Brique",new Musique("./data/Jeux/Pacman/musiquemenu" +
                        ".wav"));
                changerScene(menuCasseBrique.getMenuDuJeuScene());
            }
        });
        buttonExit.setTranslateX(menuScene.getWidth()/2-100);
        buttonExit.setTranslateY(menuScene.getHeight()/2 + 250);
        buttonExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
                System.exit(0);
            }
        });

        pane.getChildren().addAll(imageFond.getImageView(),imageJeu1.getImageView(), imageJeu2.getImageView(), choixDuJeuLabel,buttonExit);

        stage.setScene(menuScene);
        this.stage = stage ;
    }

    private void remplirListGame(){
        File gameDirectoryPath = new File("./data/Jeux");
        String listGameDirectory[] = gameDirectoryPath.list();
        for(String gameName : gameDirectoryPath.list()){
            gameList.add(new Game(gameName));
        }
    }

    private void remplirGamesAttributs(){
        File gameDirectoryPath = new File("./data/Jeux");
        for(Game game : gameList){
            game.setImageJeu(new ImageViewSizePos("./data/Jeux/"+game.getName() +"/menuchoixdujeu",500,250));
            for(String string : new File("./data/Jeux/"+game.getName()).list()){
                if(string.substring(0,7).equals("musique")){
                    System.out.println(game.getName());
                    System.out.println(string);
                    game.getListMusiques().add(new Musique("./data/Jeux/"+game.getName()+"/"+string));
                }
            }
        }

        for (Game game : gameList){
            System.out.println(game.getName() + " a pour image : " + game.getImageJeu().getPathImage());
            System.out.println("Et pour liste de musiques : ");
            for(Musique musique : game.getListMusiques()){
                System.out.println("- " + musique.getPath());
            }
        }
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void recupJeux(){
        File directoryPath = new File("./data/Jeux");
        String contents[] = directoryPath.list();
        for(String content :contents){
            listJeux.add(new Game(content));
        }



    }
    public void afficherListJeux(){

    }

    public ImageView getImageJeu1() {
        return imageJeu1.getImageView();
    }

    public ImageView getImageJeu2() {
        return imageJeu2.getImageView();
    }

    public Stage getStage() {
        return stage;
    }

    public void changerScene(Scene scene){
        stage.setScene(scene);
    }

    public Scene getMenuScene() {
        return menuScene;
    }

    public Pane getPane() {
        return pane;
    }
}
