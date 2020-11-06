package GraphicsEngine;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.List;

public class ObjectsInPane {
    Pane pane = new Pane();
    List<Node> nodeList;

    public ObjectsInPane(List<Node> nodeList, Coordinate coordinate, double width, double height){
        this.nodeList = nodeList;
        for (Node node : nodeList){
            pane.getChildren().add(node);
        }
        pane.setTranslateX(coordinate.getX());
        pane.setTranslateY(coordinate.getY());
        pane.setMaxSize(width,height);
        //pane.setCenterShape(true);
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public Pane getPane() {
        return pane;
    }
}
