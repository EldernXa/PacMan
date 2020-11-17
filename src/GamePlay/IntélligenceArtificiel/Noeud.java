package GamePlay.IntélligenceArtificiel;



public class Noeud {

    private int id;
    private int axeX ;
    private int axeY ;

    public Noeud(int axeX, int axeY,int id) {

        this.axeX = axeX;
        this.axeY = axeY;
        this.id = id ;
    }




    public int getAxeX() {
        return axeX;
    }

    public int getAxeY() {
        return axeY;
    }

    public int getId() {
        return id;
    }

}
