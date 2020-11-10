package GraphicsEngine;

import javafx.scene.Group;
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
    private final Pane root;
    private final int valueMove;
    private final String path;

    /**
     *
     * @param path image String
     * @param coordinate this position
     * @param valueMove the value of move (speed or other thing)
     * @param root Pane from javafx (we will display the image on it)
     */
    public GameImage(String path, Coordinate coordinate, int valueMove, Pane root){
        this.root = root;
        this.path = path;
        this.valueMove = valueMove;
        imgView = new ImageView(new Image(new File(path).toURI().toString()));
        this.coordinate = coordinate;
        imgView.setX(coordinate.getX());
        imgView.setY(coordinate.getY());

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

    /**
     * Decrease y coordinate for the image and display it
     */
    public void monter(){
        coordinate.setY(imgView.getY()-valueMove);
        imgView.setY(coordinate.getY());
    }

    /**
     * Increase y coordinate for the image and display it.
     */
    public void descendre(){
        coordinate.setY(imgView.getY()+valueMove);
        imgView.setY(coordinate.getY());
    }

    /**
     * Decrease x coordinate for the image and display it.
     */
    public void gauche(){
        coordinate.setX(imgView.getX()-valueMove);
        imgView.setX(coordinate.getX());
    }

    /**
     * Increase x coordinate for the image and display it.
     */
    public void droite(){
        coordinate.setX(imgView.getX()+valueMove);
        imgView.setX(coordinate.getX());
    }
}
