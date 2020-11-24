package ReadFile;

import java.io.File;
import java.util.ArrayList;

public abstract class ReadFileMap {

    private ArrayList<PosMursAssocies> tabMurFctCoord = new ArrayList<>();
    private double abscMax = 0;
    private double ordMax = 0;

    public ReadFileMap(){

    }

    public ArrayList<PosMursAssocies> getTabMurFctCoord() {
        return tabMurFctCoord;
    }

    public void setTabMurFctCoord(ArrayList<PosMursAssocies> tabMurFctCoord) {
        this.tabMurFctCoord = tabMurFctCoord;
    }

    public double getAbscMax() {
        return abscMax;
    }

    public void setAbscMax(double abscMax) {
        this.abscMax = abscMax;
    }

    public double getOrdMax() {
        return ordMax;
    }

    public void setOrdMax(double ordMax) {
        this.ordMax = ordMax;
    }
}
