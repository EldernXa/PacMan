package GraphicsEngine;

import ReadFile.ReadFileCommandes;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class MenuParametresCommandes {
    private final StackPane pane = new StackPane();
    private final Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/2,Screen.getPrimary().getVisualBounds().getHeight()/2);;
    VBox vbox = new VBox(10);
    private final ImageViewSizePos revenir = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2));
    ReadFileCommandes readFileCommandes;
    Label label1 = new Label("Cliquez sur un bouton et appuyez sur une touche.");


    public MenuParametresCommandes(Stage stage,Scene sceneBack, Game game) {
        scene.getStylesheets().add(new File("./ressources/style.css").toURI().toString());
        pane.setStyle("-fx-background-color: lightgray");
        readFileCommandes = new ReadFileCommandes("./data/Controles/" +game.getName() +"/controles.txt" ,true);
        label1.setFont(Font.font("Arial",20));
        setCommandes();
        setRevenir(stage,sceneBack);
        pane.getChildren().add(revenir.getImageView());
        pane.getChildren().add(label1);
        StackPane.setAlignment(revenir.getImageView(),Pos.TOP_LEFT);
        label1.setAlignment(Pos.CENTER);
        StackPane.setAlignment(label1,Pos.CENTER);
    }


    public Scene getScene() {
        return scene;
    }

    public void setCommandes(){
        for(int i = readFileCommandes.getDirectionSolo().size()-1; i>=0; i--){
            HBox hbox = new HBox(10);
            Label label = new Label(readFileCommandes.getDirectionSolo().get(i));
            Button button = new Button(readFileCommandes.getToucheSolo().get(i));
            label.setFont(Font.font("Arial",20));
            button.getStyleClass().add("controle");
            switch (button.getText()){
                case "&" :
                    button.setText("↑");
                    break;
                case "%" :
                    button.setText("←");
                    break;
                case "(":
                    button.setText("↓");
                    break;
                case "'":
                    button.setText("→");
                    break;
                default:
                    button.setText(button.getText());
                    break;
            }
            hbox.getChildren().addAll(label,button);
            vbox.getChildren().add(hbox);
            setChangeCommande(label,button);
        }
        vbox.setAlignment(Pos.CENTER_LEFT);
        pane.getChildren().add(vbox);

    }

    synchronized public void setChangeCommande(Label label,Button button){

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String text = button.getText();
                label1.setText("Cliquez sur un bouton et appuyez sur une touche.");
                button.setText("");
                button.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        int code = keyEvent.getCode().getCode();
                        if(readFileCommandes.getToucheSolo().contains(keyEvent.getCode().getChar().toLowerCase())&& !keyEvent.getCode().getChar().toLowerCase().equals(text)){
                            label1.setText("Ce caractère est deja utilisé");
                            button.setText(text);
                        }else{

                            switch (keyEvent.getCode().getChar()){
                                case "&" :
                                    button.setText("↑");
                                    break;
                                case "%" :
                                    button.setText("←");
                                    break;
                                case "(":
                                    button.setText("↓");
                                    break;
                                case "'":
                                    button.setText("→");
                                    break;
                                default:
                                    if((code <=110 && code >=97) || code ==10 || code == 20 || code == 9 || code ==0){

                                    }else{
                                        button.setText(keyEvent.getCode().getChar().toLowerCase());
                                    }

                                    break;
                            }

                            if((code <=110 && code >=97) || code ==10 || code == 20 || code == 9 || code ==0){
                                label1.setText("Caractère non correct");
                            }else{
                                readFileCommandes.writeSolo(label.getText(),keyEvent.getCode().getChar().toLowerCase().charAt(0));

                            }
                        }

                        button.setOnKeyPressed(null);
                        scene.setOnMouseClicked(null);

                    }
                });

                scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {

                        System.out.println(text);
                        button.setText(text);
                        button.setOnKeyPressed(null);
                        scene.setOnMouseClicked(null);
                    }
                });

            }

        });

    }
    public void setRevenir(Stage stage, Scene scene){
        revenir.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                stage.setScene(scene);
            }
        });
        revenir.getImageView().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                revenir.setImageView("./data/Logos/returnhover.png");
            }
        });

        revenir.getImageView().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                revenir.setImageView("./data/Logos/return.png");

            }
        });
    }


}
