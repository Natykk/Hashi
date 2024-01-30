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



    /**
     * vérifie si des coordonnées x y sont valides (pas en dehors de la table)
     * @param x le numéro de ligne
     * @param y le numéro de colonne
     * @return vrai si les coordonnées sont valides, faux sinon
     */
    public boolean isInBound(int x, int y) {
        return 0 <= x && x < taille
            && 0 <= y && y < taille;
    }


    /**
     * regarde les cases sur le même axe cardinal que l'île, pour trouver une île voisine ou non
     * @param uneIle l'île dont on cherche un voisin sur son axe cardinal
     * @param dx le déplacement horizontal sur la grille (-1 : vers la gauche ; 1 : vers la droite)
     * @param dy le déplacement vertical sur la grille (-1 : vers le haut ; 1 : vers le bas)
     * @return retourne l'île voisine par rapport à la direction donnée, à l'île passée en paramètre. ou null s'il n'y a pas d'île voisine dans cette direction
     */
    public Ile getVoisin( Ile uneIle, int dx, int dy ) {
        return null;
    }


} 
