package GamePlay.IntélligenceArtificiel;

import java.util.ArrayList;

public class Noeud {
    private ArrayList<Noeud> noeudSuiv;
    private int axeX ;
    private int axeY ;

    public Noeud(int axeX, int axeY) {
        this.noeudSuiv = new ArrayList<>();
        this.axeX = axeX;
        this.axeY = axeY;
    }

    public void addNoeud(){

    }
    public ArrayList<Noeud> getNoeudSuiv() {
        return noeudSuiv;
    }

    public int getAxeX() {
        return axeX;
    }



    public int getAxeY() {
        return axeY;
    }


}
