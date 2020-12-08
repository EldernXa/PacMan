package ReadFile;

import java.io.File;
import java.util.ArrayList;

/**
 * Classe servant à la lecture d'un fichier map et au stockage des principal données de la map
 */
public abstract class ReadFileMap {

    private ArrayList<PosMursAssocies> tabMurFctCoord = new ArrayList<>();
    private double abscMax = 0;
    private double ordMax = 0;

    /**
     * Constructeur vide car les constructeurs des classes filles qui héritent de cette classe font le principal du travail
     */
    public ReadFileMap(){

    }

    /**
     * Récupère la les des PosMursAssocies
     * @return
     */
    public ArrayList<PosMursAssocies> getTabMurFctCoord() {
        return tabMurFctCoord;
    }

    /**
     * Permet d'affecter une valeur à la liste des PosMursAssocies
     * @param tabMurFctCoord
     */
    public void setTabMurFctCoord(ArrayList<PosMursAssocies> tabMurFctCoord) {
        this.tabMurFctCoord = tabMurFctCoord;
    }

    /**
     * Récupère l'abscisse maximal de la map
     * @return
     */
    public double getAbscMax() {
        return abscMax;
    }

    /**
     * Permet d'affecter l'abscisse maximale de la map
     * @param abscMax
     */
    public void setAbscMax(double abscMax) {
        this.abscMax = abscMax;
    }

    /**
     * Récupère l'ordonnée mximale de la map
     * @return
     */
    public double getOrdMax() {
        return ordMax;
    }

    /**
     * Permet d'affecter l'ordonnée maximale de la map
     * @param ordMax
     */
    public void setOrdMax(double ordMax) {
        this.ordMax = ordMax;
    }
}
