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

    private Game previousGame;
    private Game currentGame;
    private Game nextGame;
    int index;

    private ImageViewSizePos imageFond;
    Label choixDuJeuLabel = new Label("Choisissez votre Jeu :");
    ArrayList<Game> listJeux = new ArrayList<>();


    public MenuChoixDuJeu(Stage stage) {

        index = 0;
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

        pane.getChildren().add(imageFond.getImageView());

        setCurrentGame(index);


        if(currentGame != null) {
            currentGame.setImageJeu(new ImageViewSizePos(gameList.get(index).getImageJeu().getPathImage(), 500, 250));
            Coordinate coordCurrentGame = new Coordinate(menuScene.getWidth() / 2 - (currentGame.getImageJeu().getImageView().getFitWidth() / 2), menuScene.getHeight() / 2 - (currentGame.getImageJeu().getImageView().getFitHeight() / 2));
            currentGame.getImageJeu().setCoordinate(coordCurrentGame);

            currentGame.getImageJeu().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    MenuDuJeu currentGameMenu = new MenuDuJeu(stage,currentGame,new Musique("./data/Jeux/"+currentGame.getName()+"/musiquemenu.wav"),menuScene);
                    changerScene(currentGameMenu.getMenuDuJeuScene());
                }
            });
        }

        if(previousGame != null) {
            previousGame.setImageJeu(new ImageViewSizePos(gameList.get(Math.abs(index - 1) % gameList.size()).getImageJeu().getPathImage(), 500, 250));
            Coordinate coordPreviousGame = new Coordinate(menuScene.getWidth() / 6 - (currentGame.getImageJeu().getImageView().getFitWidth() / 2), menuScene.getHeight() / 2 - (currentGame.getImageJeu().getImageView().getFitHeight() / 2));
            previousGame.getImageJeu().setCoordinate(coordPreviousGame);

            previousGame.getImageJeu().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    setCurrentGame((Math.abs(index-1))%gameList.size());
                }
            });

            pane.getChildren().add(previousGame.getImageJeu().getImageView());
        }

        if(nextGame != null) {
            nextGame.setImageJeu(new ImageViewSizePos(gameList.get((index + 1) % gameList.size()).getImageJeu().getPathImage(), 500, 250));
            Coordinate coordNextGame = new Coordinate(5 * (menuScene.getWidth() / 6) - (currentGame.getImageJeu().getImageView().getFitWidth() / 2), menuScene.getHeight() / 2 - (currentGame.getImageJeu().getImageView().getFitHeight() / 2));
            nextGame.getImageJeu().setCoordinate(coordNextGame);


            nextGame.getImageJeu().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("current image link = " + currentGame.getImageJeu().getPathImage());
                    System.out.println("next image link = " + nextGame.getImageJeu().getPathImage());
                    setCurrentGame((index+1)%gameList.size());
                    System.out.println("Click next");
                    System.out.println("current image link = " + currentGame.getImageJeu().getPathImage());
                    //System.out.println("next image link = " + nextGame.getImageJeu().getPathImage());
                    System.out.println("previous image link = " + previousGame.getImageJeu().getPathImage());


                }
            });

            pane.getChildren().add(nextGame.getImageJeu().getImageView());
        }

        recupJeux();

        buttonExit.setTranslateX(menuScene.getWidth()/2-100);
        buttonExit.setTranslateY(menuScene.getHeight()/2 + 250);
        buttonExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
                System.exit(0);
            }
        });

        System.out.println(currentGame.getImageJeu().getPathImage());
        pane.getChildren().addAll(currentGame.getImageJeu().getImageView(), choixDuJeuLabel,buttonExit);
        printArrows();
        stage.setScene(menuScene);
        this.stage = stage ;
    }

    public void setCurrentGame(int index) {
        this.currentGame = gameList.get(index);
        if(gameList.size() < 3){
            if(index == 0){
                this.previousGame = null;
                //mettre une image noire pour previous
            }
            else{
                this.previousGame = gameList.get(Math.abs(index-1)%gameList.size());
            }
            if(index == gameList.size()-1){
                this.nextGame = null;
                //mettre une image noir pour next
            }
            else{
                this.nextGame = gameList.get((index+1)%gameList.size());

            }
        }
        else{
            if(index == 0){
                if((!gameList.get(gameList.size()).getName().equals(this.nextGame.getName()) )&&(!gameList.get(gameList.size()).getName().equals(this.currentGame.getName()))){
                    this.previousGame = gameList.get(gameList.size());
                }
                else{
                    //mettre une image noire
                }
            }
            else if(index == gameList.size()-1){
                if((!gameList.get(0).getName().equals(this.previousGame.getName()))&&(!gameList.get(0).getName().equals(this.currentGame.getName()))){
                    this.nextGame = gameList.get(0);
                }
                else{
                    //mettre une image noire
                }
            }
            else{
                this.previousGame = gameList.get(index-1);
                this.nextGame = gameList.get(index+1);
            }
        }
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
            game.setImageJeu(new ImageViewSizePos("./data/Jeux/"+game.getName() +"/menudujeu.jpg",500,250));
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
    public void printArrows(){
        ImageViewSizePos rightArrow = new ImageViewSizePos("./data/Logos/right-arrow.png",75,75,new Coordinate(currentGame.getImageJeu().getCoordinate().getX()+currentGame.getImageJeu().getImageView().getFitWidth(),currentGame.getImageJeu().getCoordinate().getY()+currentGame.getImageJeu().getImageView().getFitHeight()/2 - 30));
        ImageViewSizePos leftArrow = new ImageViewSizePos("./data/Logos/left-arrow.png",75,75,new Coordinate(currentGame.getImageJeu().getCoordinate().getX()-100,currentGame.getImageJeu().getCoordinate().getY()+currentGame.getImageJeu().getImageView().getFitHeight()/2-30));
        pane.getChildren().addAll(rightArrow.getImageView(),leftArrow.getImageView());
        rightArrow.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCurrentGame(index+1);
            }
        });
        leftArrow.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCurrentGame(index-1);
            }
        });

    }


}
