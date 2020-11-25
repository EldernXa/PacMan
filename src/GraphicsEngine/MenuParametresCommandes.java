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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MenuParametresCommandes {
    private final StackPane pane = new StackPane();
    private final Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth()/2,Screen.getPrimary().getVisualBounds().getHeight()/2);;
    VBox vbox = new VBox(10);
    private final ImageViewSizePos revenir = new ImageViewSizePos("./data/Logos/return.png",50,50,new Coordinate(2,2));
    ReadFileCommandes readFileCommandes;

    public MenuParametresCommandes(Stage stage,Scene sceneBack, Game game) {

        readFileCommandes = new ReadFileCommandes("./data/Controles/" +game.getName() +"/controles.txt" );
        setCommandes();
        setRevenir(stage,sceneBack);
        pane.getChildren().add(revenir.getImageView());
        StackPane.setAlignment(revenir.getImageView(),Pos.TOP_LEFT);
    }


    public Scene getScene() {
        return scene;
    }
    public void setCommandes(){
        for(int i = readFileCommandes.getDirection().size()-1;i>=0;i--){
            HBox hbox = new HBox(10);
            Label label = new Label(readFileCommandes.getDirection().get(i));
            Button button = new Button(readFileCommandes.getTouche().get(i));
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

    public void setChangeCommande(Label label,Button button){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        System.out.println(keyEvent.getCode().getChar());
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
                                button.setText(keyEvent.getCode().getChar().toLowerCase());
                                break;
                        }
                        readFileCommandes.write(label.getText(),keyEvent.getCode().getChar().toLowerCase().charAt(0));
                        button.setOnKeyPressed(null);


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
