package GamePlay.IntélligenceArtificiel;

import java.util.*;

public class Graphe {

    private boolean adjMatrix[][];
    private int taille ;
    private ArrayList<Noeud> noeuds;

    // Initialize the matrix
    public Graphe(int taille) {
        this.noeuds = new ArrayList<>();
        this.taille = taille;
        adjMatrix = new boolean[taille][taille];
    }


    public void addNoeud(Noeud noeudDépart, Noeud noeudDestination) {
        adjMatrix[noeudDépart.getId()][noeudDestination.getId()] = true;
        adjMatrix[noeudDestination.getId()][noeudDépart.getId()] = true;
        if(!noeuds.contains(noeudDépart)){
            noeuds.add(noeudDépart);
        }
        if(!noeuds.contains(noeudDestination)){
            noeuds.add(noeudDestination);
        }
    }


    public void enleverNoeud(Noeud noeudDépart, Noeud noeudDestination) {
        adjMatrix[noeudDépart.getId()][noeudDestination.getId()] = false;
        adjMatrix[noeudDestination.getId()][noeudDépart.getId()] = false;

    }

    public Noeud getNoeud(int id){
        for(int i = 0 ; i < noeuds.size(); i++){
            if(id == noeuds.get(i).getId()){
                return noeuds.get(i);
            }
        }
        return null;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < taille; i++) {
            s.append(i + ": ");
            for (boolean j : adjMatrix[i]) {
                s.append((j ? 1 : 0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

        public static void main(String args[]) {
            Noeud noeud1 = new Noeud(30,40,0);
            Noeud noeud2 = new Noeud(55,75,1);
            Noeud noeud3 = new Noeud(13,57,2);
            Noeud noeud4 = new Noeud(7,18,3);
            Noeud noeud5 = new Noeud(24,19,4);
            Graphe g = new Graphe(5);

            g.addNoeud(noeud1, noeud2);
            g.addNoeud(noeud1, noeud5);
            g.addNoeud(noeud5, noeud4);
            g.addNoeud(noeud2, noeud3);


            System.out.println(g.adjMatrix[1][2]);
            //System.out.println(g.toString());
            System.out.println(g.getNoeud(2).getAxeX()+" "+g.getNoeud(2).getAxeY());
        }
    }

