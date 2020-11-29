package GamePlay;
import GraphicsEngine.*;
import MusicEngine.Musique;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.Objects;

import static GraphicsEngine.Menu.getMenuLevel;

public class MenuDuJeu {
    private final StackPane pane = new StackPane();
    private final Button singlePlayer = new Button("SinglePlayer".toUpperCase());
    private final Button multiPlayer = new Button("MultiPlayer".toUpperCase());
    private final VBox buttonContainers = new VBox(15);
    private final Button retouner = new Button("Retourner au choix du jeu".toUpperCase());
    private final ImageViewSizePos param = new ImageViewSizePos("./data/Logos/settings.png",40, 40);
    private final Game jeu;
    private final ImageViewSizePos soundAndNoSound = new ImageViewSizePos("./data/Logos/sound.png",40,40);;
    private final HBox hbox = new HBox(20);
    private final ImageViewSizePos revenir  = new ImageViewSizePos("./data/Logos/return.png",50,50, new Coordinate(2,2));
    private final Stage stage;
    private final double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    private final double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
    private final Scene menuDuJeuScene= new Scene(pane, screenWidth,screenHeight);


    public MenuDuJeu(Stage stage,Game game,Scene sceneBack) {
        this.stage = stage;
        jeu = game;

        menuDuJeuScene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());

        if(!game.getListMusiques().isEmpty()) {
            game.getListMusiques().get(0).lancerMusique();
        }

        ImageViewSizePos fondEcran = new ImageViewSizePos("./data/Jeux/" + jeu.getName() + "/menudujeu.jpg",menuDuJeuScene.getWidth(),menuDuJeuScene.getHeight());

        pane.getChildren().add(fondEcran.getImageView());
        setButtonContainers();
        clickSound();
        enterSound();
        exitSound();
        enterParam();
        exitParam();
        clickParam(game);
        setmusic(game);
        setTooltip();
        enterRevenir();
        clickRetourner(sceneBack);
        clickRevenir();
        clickSingle();
        exitRevenir();

        pane.getChildren().add(buttonContainers);
    }



    public Scene getMenuDuJeuScene() {
        return menuDuJeuScene;
    }


    public void clickRetourner(Scene sceneBack){
        retouner.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(!jeu.getListMusiques().isEmpty()){
                    jeu.getListMusiques().get(0).stopMusique();
                }
                stage.setScene(sceneBack);
            }
        });
    }

    public void clickSingle(){
        singlePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(Menu.getMenuLevel() || !verifGameFinish(jeu)) {
                    MenuChoixDifficulte menuChoixDifficulte = new MenuChoixDifficulte(stage, jeu, menuDuJeuScene);
                    diff(jeu,stage, menuChoixDifficulte,revenir,screenWidth,screenHeight);
                }
                else{
                    String nameFileMap = "Map" + jeu.getName();
                    if(Musique.mediaPlayer!=null)
                        Musique.mediaPlayer.stop();
                    try {
                        Class <?>classMap = Class.forName("GamePlay." + jeu.getName() + "." + nameFileMap);
                        Class<?>[] parameters = new Class[]{Stage.class, String.class};
                        Constructor<?> constructor = classMap.getConstructor(parameters);
                        Map map = (Map)constructor.newInstance(stage, "./data/Map/");
                        stage.setScene(map.getMapScene());
                        System.out.println("test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void enterRevenir(){
        revenir.getImageView().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                revenir.setImageView("./data/Logos/returnhover.png");
            }
        });
    }
    public void exitRevenir(){
        revenir.getImageView().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                revenir.setImageView("./data/Logos/return.png");

            }
        });
    }
    public void clickRevenir(){
        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setScene(menuDuJeuScene);
            }
        });
    }

    public void enterParam(){
        param.getImageView().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                param.setImageView("./data/Logos/settingshover.png");
            }
        });
    }
    public void exitParam(){
        param.getImageView().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                param.setImageView("./data/Logos/settings.png");

            }
        });
    }
    public void clickParam(Game game){
        param.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuParametres parametres = new MenuParametres(game);


            }
        });
    }

    public void exitSound(){
        soundAndNoSound.getImageView().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(soundAndNoSound.getPathImage().equals("./data/Logos/soundhover.png")){
                    soundAndNoSound.setImageView("./data/Logos/sound.png");
                }
                else{
                    soundAndNoSound.setImageView("./data/Logos/nosound.png");
                }
            }
        });
    }

    public void enterSound(){
        soundAndNoSound.getImageView().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(soundAndNoSound.getPathImage().equals("./data/Logos/sound.png")){
                    soundAndNoSound.setImageView("./data/Logos/soundhover.png");
                }
                else{
                    soundAndNoSound.setImageView("./data/Logos/nosoundhover.png");
                }
            }
        });
    }
    public void clickSound(){
        soundAndNoSound.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!jeu.getListMusiques().isEmpty()) {
                    switch (soundAndNoSound.getPathImage()) {
                        case "./data/Logos/sound.png":

                            soundAndNoSound.setImageView("./data/Logos/nosound.png");
                            break;

                        case "./data/Logos/nosound.png":
                            soundAndNoSound.setImageView("./data/Logos/sound.png");
                            break;

                        case "./data/Logos/soundhover.png":
                            jeu.getListMusiques().get(0).mute(true);
                            soundAndNoSound.setImageView("./data/Logos/nosoundhover.png");
                            break;

                        case "./data/Logos/nosoundhover.png":
                            jeu.getListMusiques().get(0).mute(false);
                            soundAndNoSound.setImageView("./data/Logos/soundhover.png");
                            break;
                    }
                }
            }
        });
    }
    public void setTooltip(){
        Tooltip tooltip_revenir=new Tooltip("Revenir en arrière");
        tooltip_revenir.setStyle(" -fx-background-color: gray;");
        tooltip_revenir.setShowDelay(new Duration(0));
        Tooltip.install(revenir.getImageView(),tooltip_revenir);
    }

    public void setButtonContainers() {
        buttonContainers.setPrefWidth(400);
        hbox.getChildren().addAll(param.getImageView(), soundAndNoSound.getImageView());
        singlePlayer.setPrefWidth(buttonContainers.getPrefWidth());
        multiPlayer.setPrefWidth(buttonContainers.getPrefWidth());
        retouner.setPrefWidth(buttonContainers.getPrefWidth());
        hbox.setPrefWidth(buttonContainers.getPrefWidth());
        hbox.setAlignment(Pos.CENTER);
        buttonContainers.getChildren().addAll(singlePlayer,multiPlayer);
        if(Menu.getMenuChoiceGame())
            buttonContainers.getChildren().add(retouner);
        buttonContainers.getChildren().add(hbox);

        buttonContainers.setAlignment(Pos.CENTER);
    }

    public void setmusic(Game game){
        File directoryPath = new File("./data/Jeux/"+ game.getName()+"/");
        String contents[] = directoryPath.list();
        boolean music = false;
        for(String content :contents){
            if(content.contains("musique")){
                music = true;
            }
        }
        if(!music){
            soundAndNoSound.setImageView("./data/Logos/nosound.png");
            soundAndNoSound.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Label label = new Label("Il n'y a pas encore de musique disponible");
                    label.setFont(Font.font("Arial", 42));
                    label.setTextFill(Color.WHITE);
                    label.setStyle("-fx-background-color: black");
                    pane.setAlignment(label,Pos.TOP_CENTER);
                    if(!pane.getChildren().contains(label)) {
                        pane.getChildren().add(label);
                    }
                }
            });
        }

    }
    public void diff(Game game, Stage stage, MenuChoixDifficulte menuChoixDifficulte, ImageViewSizePos revenir, double screenWidth, double screenHeight){
            File directoryPath = new File("./data/Jeux/"+ game.getName()+"/");
            String contents[] = directoryPath.list();
            boolean bool = false;
            for(String content :contents){
                if(content.equals("notyet.txt")){
                    StackPane newPane = new StackPane();
                    Scene scenetemp = new Scene(newPane,Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());
                    ImageViewSizePos imageViewSizePos = new ImageViewSizePos("./data/DevPrivate/wip.jpg",screenWidth,screenHeight);
                    newPane.getChildren().addAll(imageViewSizePos.getImageView(),revenir.getImageView());
                    newPane.setAlignment(Pos.TOP_LEFT);
                    stage.setScene(scenetemp);
                    bool = true;
                }

            }
            if(!bool)
                stage.setScene(menuChoixDifficulte.getScene());

        }

    public boolean verifGameFinish(Game game){
        File directoryPath = new File("./data/Jeux/"+ game.getName()+"/");
        boolean bool = false;
        for(String content : Objects.requireNonNull(directoryPath.list())){
            if(content.equals("notyet.txt"))
                bool = true;
        }
        return !bool;
    }


    public Game getJeu() {
        return jeu;
    }

    public Stage getStage() {
        return stage;
    }


}

