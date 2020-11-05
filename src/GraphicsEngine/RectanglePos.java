package GraphicsEngine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class RectanglePos {
    private Rectangle rectangle = new Rectangle();
    private Coordinate coord;

    public RectanglePos(int hauteur, int largeur, Color color, Coordinate coord) {
        this.rectangle.setTranslateX(coord.getX());
        this.rectangle.setTranslateY(coord.getY());
        this.coord = coord;
        this.rectangle.setFill(color);
        this.rectangle.setHeight(hauteur);
        this.rectangle.setWidth(largeur);
        this.rectangle.setStroke(Color.BLACK);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Coordinate getCoord() {
        return coord;
    }
}
