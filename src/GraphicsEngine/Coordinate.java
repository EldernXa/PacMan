package GraphicsEngine;

/**
 * Class who represent position of all things in the game.
 */
public class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void affichageCoord(){
        System.out.print("(" + x + "," + y + ")");
    }

    public boolean compare(Coordinate coordinate){
        if((this.x == coordinate.getX())&&(this.y == coordinate.getY())){
            return true;
        }
        else{
            return false;
        }
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
