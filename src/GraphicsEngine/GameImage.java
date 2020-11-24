package GraphicsEngine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

/**
 * class for display image.
 */
public class GameImage {
    private Coordinate coordinate;
    private final ImageView imgView;
    private final int valueMove;
    private final String path;
    private final Coordinate coordInit;
    /**
     *
     * @param path image String
     * @param coordinate this position
     * @param valueMove the value of move (speed or other thing)
     */
    public GameImage(String path, Coordinate coordinate, int valueMove){
        coordInit = new Coordinate(coordinate.getX(),coordinate.getY());
        this.path = path;
        this.valueMove = valueMove;
        imgView = new ImageView();
        this.coordinate = coordinate;
        imgView.setX(coordinate.getX());
        imgView.setY(coordinate.getY());
    }

    public Coordinate getCoordInit() {
        return coordInit;
    }

    public void setImage(Image image){
        this.imgView.setImage(image);
    }

    public int getValueMove(){
        return valueMove;
    }

    public ImageView getImgView(){
        return imgView;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }
}
