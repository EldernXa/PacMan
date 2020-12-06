package GraphicsEngine;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public abstract class Score {
    private final ArrayList<Node> listNode;
    private final Stage stage;
    private final HBox score;
    public Score(Stage stage){
        this.score = new HBox();
        this.stage = stage;
        this.listNode = new ArrayList<>();
    }

    public Stage getStage(){
        return stage;
    }

    public void addNode(Node node){
        listNode.add(node);
    }

    public void setHBox(){
        score.getChildren().addAll(listNode);
    }

    public HBox getScore(){
        return score;
    }

    public abstract void modifyRealTimeValue(VisualObject visualObject, Stage stage);

}
