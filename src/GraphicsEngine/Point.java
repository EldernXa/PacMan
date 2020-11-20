package GraphicsEngine;

import javafx.scene.Scene;

import java.util.ArrayList;

public class Point extends UnmouvingObj{

    public Point(Coordinate coordinate, Scene scene) {
        super("./data/Point&Fruit/point.png", coordinate, scene);
    }

    @Override
    public boolean effectCollision(ArrayList<VisualObject> visualObjects) {
        super.getImageView().setVisible(false);
        return false;
    }
}
