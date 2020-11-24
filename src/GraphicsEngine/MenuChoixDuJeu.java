package GraphicsEngine;

import MusicEngine.Musique;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class MenuChoixDuJeu {
    ArrayList<Game> gameList = new ArrayList<>();

    private Pane pane = new Pane();
    private Stage stage;
    private Scene menuScene;
    private TextField zoneDeRecherche = new TextField();
    private Button buttonExit = new Button("Quitter le menu");

    private Game previousGame = new Game();
    private Game currentGame = new Game();
    private Game nextGame = new Game();
    Coordinate coordPreviousGame;
    Coordinate coordCurrentGame;
    Coordinate coordNextGame;
    private double imageWidth = 500;
    private double imageHeight = 250;
    int firstGameIndex = 0;

    private ImageViewSizePos imageFond;
    Label choixDuJeuLabel = new Label("Choisissez votre Jeu :");
    ArrayList<Game> listJeux = new ArrayList<>();


    public MenuChoixDuJeu(Stage stage) {

        remplirListGame();
        remplirGamesAttributs();

        menuScene = new Scene(pane,Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
        menuScene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());

        creationZoneDeRecherche();

        creationDuJeuLabel();

        imageFond =  new ImageViewSizePos("./data/Logos/menuchoixdujeu.jpg",menuScene.getWidth(),menuScene.getHeight());

        pane.getChildren().add(imageFond.getImageView());

        setCoordGames();

        setCurrentGame(firstGameIndex);

        initialisationAllGamesImages();

        buttonExit.setTranslateX(menuScene.getWidth()/2-100);
        buttonExit.setTranslateY(menuScene.getHeight()/2 + 250);
        buttonExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
        pane.getChildren().addAll(currentGame.getImageJeu().getImageView(), choixDuJeuLabel,zoneDeRecherche,buttonExit);
        stage.setScene(menuScene);
        this.stage = stage ;
    }

    public void creationZoneDeRecherche(){
        zoneDeRecherche.setPromptText("Rentrez le nom du jeu");
        zoneDeRecherche.setFocusTraversable(false);
        zoneDeRecherche.setTranslateX(3*(menuScene.getWidth()/4));
        zoneDeRecherche.setTranslateY(menuScene.getHeight()/16);
        zoneDeRecherche.setMinSize(300,35);
    }

    public void propositionJeu(){
        zoneDeRecherche.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                
            }
        });
    }

    public void creationDuJeuLabel(){
        choixDuJeuLabel.setFont(Font.font("Avenir Next", 45));
        choixDuJeuLabel.setUnderline(true);
        choixDuJeuLabel.setTranslateX(menuScene.getWidth()/2-220);
        choixDuJeuLabel.setTranslateY(70);
        choixDuJeuLabel.setStyle("-fx-border-color: black;");
        choixDuJeuLabel.setPadding(new Insets(7));
        choixDuJeuLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
    }

    public void setCoordGames(){
        coordPreviousGame = new Coordinate(menuScene.getWidth() / 6 - (imageWidth / 4), menuScene.getHeight() / 2 - (imageHeight / 2));
        previousGame.getImageJeu().setCoordinate(coordPreviousGame);
        coordCurrentGame = new Coordinate(menuScene.getWidth() / 2 - (imageWidth / 2), menuScene.getHeight() / 2 - (imageHeight / 4));
        currentGame.getImageJeu().setCoordinate(coordCurrentGame);
        coordNextGame = new Coordinate(5 * (menuScene.getWidth() / 6) - 3*(imageWidth / 4), menuScene.getHeight() / 2 - (imageHeight / 2));
        nextGame.getImageJeu().setCoordinate(coordNextGame);
    }

    public void initialisationAllGamesImages(){
        if(currentGame != null) {
            currentGame.setImageJeu(new ImageViewSizePos(gameList.get(firstGameIndex).getImageJeu().getPathImage(), 500, 250));
            currentGame.getImageJeu().setCoordinate(coordCurrentGame);
            currentGame.getImageJeu().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    MenuDuJeu currentGameMenu = new MenuDuJeu(stage,currentGame,menuScene);
                    changerScene(currentGameMenu.getMenuDuJeuScene());
                }
            });
        }

        if(previousGame != null) {
            previousGame.setImageJeu(new ImageViewSizePos(gameList.get(calculFollowingCurrentIndex(-1, firstGameIndex)).getImageJeu().getPathImage(), 500, 250));
            previousGame.getImageJeu().setCoordinate(coordPreviousGame);
            previousGame.getImageJeu().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    firstGameIndex = calculFollowingCurrentIndex(-1, firstGameIndex);
                    setCurrentGame(firstGameIndex);
                }
            });
            pane.getChildren().add(previousGame.getImageJeu().getImageView());
        }

        if(nextGame != null) {
            nextGame.setImageJeu(new ImageViewSizePos(gameList.get(calculFollowingCurrentIndex(1, firstGameIndex)).getImageJeu().getPathImage(), 500, 250));
            nextGame.getImageJeu().setCoordinate(coordNextGame);
            nextGame.getImageJeu().getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    firstGameIndex = calculFollowingCurrentIndex(1, firstGameIndex);
                    setCurrentGame(firstGameIndex);
                }
            });
            pane.getChildren().add(nextGame.getImageJeu().getImageView());
        }
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public Game getPreviousGame() {
        return previousGame;
    }

    public Game getNextGame() {
        return nextGame;
    }

    public static void game1SetGame2Attributs(Game game1, Game game2){
        game1.setName(game2.getName());
        game1.setListDifficultesFromString(game2.getName());
        game1.setListMusiques(game2.getListMusiques());
        game1.setImage(game2.getImageJeu());
        game1.getImageJeu().setPathImage(game2.getImageJeu().getPathImage());
        game1.getImageJeu().getImageView().setFitWidth(game2.getImageJeu().getImageView().getFitWidth());
        game1.getImageJeu().getImageView().setFitHeight(game2.getImageJeu().getImageView().getFitHeight());
    }

    public void setCurrentGame(int index) {
        game1SetGame2Attributs(currentGame,gameList.get(index));
        if(gameList.size() < 3){
            if(index == 0){
                this.previousGame = null;
                //mettre une image noire pour previous
            }
            else{
                game1SetGame2Attributs(previousGame,gameList.get(calculFollowingCurrentIndex(-1,index)));
            }
            if(index == gameList.size()-1){
                this.nextGame = null;
                //mettre une image noir pour next
            }
            else{
                game1SetGame2Attributs(nextGame,gameList.get(calculFollowingCurrentIndex(1,index)));

            }
        }
        else {
            if (index == 0) {
                if ((!gameList.get(gameList.size()-1).getName().equals(this.nextGame.getName())) && (!gameList.get(gameList.size()-1).getName().equals(this.currentGame.getName()))) {
                    game1SetGame2Attributs(previousGame,gameList.get(calculFollowingCurrentIndex(-1,index)));
                    game1SetGame2Attributs(nextGame,gameList.get(calculFollowingCurrentIndex(1,index)));
                } else {
                    //mettre une image noire
                }
            } else if (index == gameList.size() - 1) {
                if ((!gameList.get(0).getName().equals(this.previousGame.getName())) && (!gameList.get(0).getName().equals(this.currentGame.getName()))) {
                    game1SetGame2Attributs(nextGame,gameList.get(calculFollowingCurrentIndex(1,index)));
                    game1SetGame2Attributs(previousGame,gameList.get(calculFollowingCurrentIndex(-1,index)));
                } else {
                    //mettre une image noire
                }
            } else {
                game1SetGame2Attributs(previousGame,gameList.get(calculFollowingCurrentIndex(-1,index)));
                game1SetGame2Attributs(nextGame,gameList.get(calculFollowingCurrentIndex(1,index)));
            }
        }
    }

    public int calculFollowingCurrentIndex(int plusoumoins1, int currentIndex){
        if(plusoumoins1 == 1){
            if(currentIndex == gameList.size()-1){
                return 0;
            }
            return currentIndex+1;
        }
        else{
            if(currentIndex == 0){
                return gameList.size()-1;
            }
            return currentIndex-1;
        }
    }

    private void remplirListGame(){
        File gameDirectoryPath = new File("./data/Jeux");
        for(String gameName : gameDirectoryPath.list()){
            gameList.add(new Game(gameName));
        }
    }

    private void remplirGamesAttributs(){
        File gameDirectoryPath = new File("./data/Jeux");
        for(Game game : gameList){
            game.setImageJeu(new ImageViewSizePos("./data/Jeux/"+game.getName() +"/menuchoixdujeu.jpg", imageWidth, imageHeight));
            for(String string : new File("./data/Jeux/"+game.getName()).list()){
                if(string.substring(0,7).equals("musique")){
                    game.getListMusiques().add(new Musique("./data/Jeux/"+game.getName()+"/"+string));
                }
            }
        }
    }

    public ArrayList<Game> getGameList() {
        return gameList;
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
}
