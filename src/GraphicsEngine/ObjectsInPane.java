package GraphicsEngine;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.List;

public class ObjectsInPane {
    StackPane pane = new StackPane();
    List<Node> nodeList;

    public ObjectsInPane(List<Node> nodeList, Coordinate coordinate, double width, double height){
        this.nodeList = nodeList;
        for (Node node : nodeList){
            pane.getChildren().add(node);
        }
        pane.setAlignment(Pos.CENTER);
        pane.setTranslateX(coordinate.getX());
        pane.setTranslateY(coordinate.getY());
        pane.setMaxSize(width,height);
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public Pane getPane() {
        return pane;
    }
}
