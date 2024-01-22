class grille {
  // Partie variables d instance

  //longueur	-> Longueur de la grille (0->X)
  //largeur		-> Largeur de la grille (0vY)
  //table		-> La matrice des Case de la grille
  //sommets		-> Le Tableau des sommets de la grille
  //aretes		-> Le Tableau des arêtes de la grille
  //undo -> Pile de mouvement.

    private int longueur;
    private int largeur;
    private Case[][] table;
    private ile[] sommets;
    private pont[] ponts;
    private Pile undo;

    // Partie constructeur

    public grille(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.table = new Case[longueur][largeur];
        this.iles = new ile[longueur * largeur];
        this.ponts = new pont[longueur * largeur * 2];
        this.undo = new Pile();
        int i, j;
        for (i = 0; i < longueur; i++) {
            for (j = 0; j < largeur; j++) {
                this.table[i][j] = new Case(i, j);
            }
        }
        for (i = 0; i < longueur; i++) {
            for (j = 0; j < largeur; j++) {
                if (i != longueur - 1) {
                    this.ponts[i * largeur + j] = new pont(this.table[i][j], this.table[i + 1][j]);
                }
                if (j != largeur - 1) {
                    this.ponts[longueur * largeur + i * (largeur - 1) + j] = new pont(this.table[i][j], this.table[i][j + 1]);
                }
            }
        }
        for (i = 0; i < longueur; i++) {
            for (j = 0; j < largeur; j++) {
                this.iles[i * largeur + j] = new ile(this.table[i][j], this.ponts);
            }
        }
    }

    // Partie accesseurs

    public int getLongueur() {
        return this.longueur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public Case[][] getTable() {
        return this.table;
    }

    public ile[] getSommets() {
        return this.sommets;
    }

    public pont[] getAretes() {
        return this.ponts;
    }

    public Pile getUndo() {
        return this.undo;
    }

    // Partie méthodes

    public void afficher() {
        int i, j;
        for (i = 0; i < this.longueur; i++) {
            for (j = 0; j < this.largeur; j++) {
                System.out.print(this.table[i][j].getValeur() + " ");
            }
            System.out.println();
        }
    }

    public void afficherSommets() {
        int i;
        for (i = 0; i < this.longueur * this.largeur; i++) {
            System.out.println(this.sommets[i].toString());
        }
    }

    public void afficherAretes() {
        int i;
        for (i = 0; i < this.longueur * this.largeur * 2; i++) {
            System.out.println(this.ponts[i].toString());
        }
    }


    static public void main(String[] args) {
        grille g = new grille(5, 5);
        g.afficher();
        g.afficherSommets();
        g.afficherAretes();
    }

}