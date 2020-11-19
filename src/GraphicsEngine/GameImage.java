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

    /**
     *
     * @param path image String
     * @param coordinate this position
     * @param valueMove the value of move (speed or other thing)
     */
    public GameImage(String path, Coordinate coordinate, int valueMove){
        this.path = path;
        this.valueMove = valueMove;
        imgView = new ImageView();
        this.coordinate = coordinate;
        imgView.setX(coordinate.getX());
        imgView.setY(coordinate.getY());
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

    public void move(double x, double y){
        coordinate.setX(x);
        coordinate.setY(y);
        imgView.setX(coordinate.getX());
        imgView.setY(coordinate.getY());
    }

   /* *//**
     * Decrease y coordinate for the image and display it
     *//*
    public void monter(){
        coordinate.setY(imgView.getY()-valueMove);
        imgView.setY(coordinate.getY());
    }

    *//**
     * Increase y coordinate for the image and display it.
     *//*
    public void descendre(){
        coordinate.setY(imgView.getY()+valueMove);
        imgView.setY(coordinate.getY());
    }

    *//**
     * Decrease x coordinate for the image and display it.
     *//*
    public void gauche(){
        coordinate.setX(imgView.getX()-valueMove);
        imgView.setX(coordinate.getX());
    }

    *//**
     * Increase x coordinate for the image and display it.
     *//*
    public void droite(){
        coordinate.setX(imgView.getX()+valueMove);
        imgView.setX(coordinate.getX());
    }*/
}
