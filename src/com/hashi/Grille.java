import java.util.ArrayList;
import java.util.List;


public class Grille {
    private int taille; // coté de la grille² 
    private Case[][] table; // La matrice des Case de la grille
    private List<Ile> Iles; // Le Tableau des Iles de la grille
    private List<Pont> Ponts; // Le Tableau des ponts de la grille

    protected Grille(String cheminFichier,int taille){
        this.table = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.table[i][j] = new Case(i, j);
            }
        }
        this.Iles = new ArrayList<Ile>();
        this.Ponts = new ArrayList<Pont>();
    }
} 
