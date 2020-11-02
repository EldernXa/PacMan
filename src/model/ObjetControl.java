package model;

import java.util.ArrayList;

public class ObjetControl {
    private final int id; // identifiant de l'objet
    private double coordX; // coordonée x de l'objet
    private double coordY; // coordonée y de l'objet
    private ArrayList<Double> direction ; /***tableau de taille si la première case est postive alors déplacement
     croissant dans les x , aucun sur les x si égale à 0 et déplacement décroissant dans les x si négatifs .
     Même fonctionnement pour les y***/

    private double radius;

    public ObjetControl(int id) {
        this.id = id;
        this.coordX = 0.0;
        this.coordY = 0.0;
        this.direction = null;
        this.radius = 0.0;
    }

    public ObjetControl(int id, double coordX, double coordY, ArrayList<Double> direction, double radius) {
        this.id = id;
        this.coordX = coordX;
        this.coordY = coordY;
        this.direction = direction;
        this.radius = radius;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public void setDirection(ArrayList<Double> direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public ArrayList<Double> getDirection() {
        return direction;
    }
}
