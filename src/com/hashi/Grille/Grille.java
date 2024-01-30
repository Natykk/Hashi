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

    public Case getCase(int x, int y) {
        return this.table[x][y];
    }

    public void setCase(int x, int y,Case new_case) {
        this.table[x][y] = new_case;
    }

    public void ajouterIle(Ile ile) {
        this.Iles.add(ile);
    }

    public void ajouterPont(Pont pont) {
        this.Ponts.add(pont);
    }

    public void retirerPont(Pont pont) {
        this.Ponts.remove(pont);
    }
    

    public void afficher() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.table[i][j] == null) {
                    System.out.print(" ");
                } else {
                    this.table[i][j].afficher();
                }
            }
            System.out.println();
        }
    }

    public void afficherIles() {
        for (Ile ile : this.Iles) {
            ile.afficher();
        }
    }

    public void afficherPonts() {
        for (Pont pont : this.Ponts) {
            pont.afficher();
        }
    }

    public void afficherGrille() {
        this.afficher();
        this.afficherIles();
        this.afficherPonts();
    }

    public void setGrille() {

    }


} 
