package GraphicsEngine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageViewSizePos {
    private ImageView imageView = new ImageView();
    private Coordinate coordinate;
    String pathImage;

    public ImageViewSizePos(){
    }

    public ImageViewSizePos(String pathImage, Coordinate coordinate){
        this.pathImage = pathImage;
        this.coordinate = coordinate;
        imageView.setImage(new Image(new File(pathImage).toURI().toString()));
        setCoordinate(coordinate);
    }

    public ImageViewSizePos(String pathImage, double width, double height, Coordinate coordinate){
        this.pathImage = pathImage;
        this.coordinate = coordinate;
        imageView.setImage(new Image(new File(pathImage).toURI().toString()));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        setCoordinate(coordinate);
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public ImageViewSizePos(String pathImage, double width, double height){
        this.pathImage = pathImage;
        imageView.setImage(new Image(new File(pathImage).toURI().toString()));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        imageView.setTranslateX(coordinate.getX());
        imageView.setTranslateY(coordinate.getY());
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(String pathImage) {
        this.pathImage = pathImage;
        this.imageView.setImage(new Image(new File(pathImage).toURI().toString()));
    }
}
