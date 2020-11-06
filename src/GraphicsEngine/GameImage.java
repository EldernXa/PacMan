package GraphicsEngine;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class GameImage {
    private Coordinate coordinate;
    private final ImageView imgView;
    private final Pane root;
    private final int valueMove;
    private final String path;

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

    public void monter(){
        coordinate.setY(imgView.getY()-valueMove);
        imgView.setY(coordinate.getY());
    }

    public void descendre(){
        coordinate.setY(imgView.getY()+valueMove);
        imgView.setY(coordinate.getY());
    }

    public void gauche(){
        coordinate.setX(imgView.getX()-valueMove);
        imgView.setX(coordinate.getX());
    }

    public void droite(){
        coordinate.setX(imgView.getX()+valueMove);
        imgView.setX(coordinate.getX());
    }
}
