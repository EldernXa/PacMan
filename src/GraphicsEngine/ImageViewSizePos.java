package GraphicsEngine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageViewSizePos {
    private ImageView imageView;
    private Coordinate coordinate;

    public ImageViewSizePos(String pathImage, double width, double height, Coordinate coordinate){
        this.coordinate = coordinate;
        imageView = new ImageView(new Image(new File(pathImage).toURI().toString()));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        setCoordinate(coordinate);
    }

    public ImageViewSizePos(String pathImage, double width, double height){
        imageView = new ImageView(new Image(new File(pathImage).toURI().toString()));
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

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
