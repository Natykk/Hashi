
import java.awt.event.MouseAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
//package com.hashi.grille;

import org.w3c.dom.events.MouseEvent;



public class Grille extends MouseAdapter {
    private int taille; // coté de la grille² 
    private Case[][] table; // La matrice des Case de la grille
    private List<Ile> Iles; // Le Tableau des Iles de la grille
    private List<Pont> Ponts; // Le Tableau des ponts de la grille
    private Case selectedCase; // Case selectionnée par l'utilisateur

    protected Grille(int taille){
        this.table = new Case[taille][taille];
        this.taille = taille;
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

    public ArrayList<Pont> getListePonts() {
        return (ArrayList<Pont>) this.Ponts;
    }
    

    public String afficher() {
        String res = "";
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.table[i][j] == null) {
                    res += " ";
                } else {
                    res += this.table[i][j].afficher();
                }
            }
            res += "\n";
        }
        return res;
    }

    public int getTaille() {
        return this.taille;
    }

    public String afficherIles() {
        String res = "";
        for (Ile ile : this.Iles) {
            if(ile!=null){
                res += ile.afficher();
            }else{
                res += "*";
            }
        }
        return res;
    }

    public String afficherPonts() {
        String res = "";
        for (Pont pont : this.Ponts) {
            res += pont.afficher();
        }
        return res;
    }

    public void afficherGrille() {
        this.afficher();
    }


    public int[][] getGridAsArray(){
        int[][] res = new int[this.taille][this.taille];
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.table[i][j] == null) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = this.table[i][j].getValeur();
                }
            }
        }
        return res;
    }


    // Methode qui verifie si le pont a créer est valide ( les ponts ne se croisent pas, les ponts ne sont pas en diagonale, les ponts ne sont pas superposés)
    public boolean pontValide(Pont pont) {
        if (pont.getIle1().getX() == pont.getIle2().getX()) {
            int x = pont.getIle1().getX();
            int y1 = pont.getIle1().getY();
            int y2 = pont.getIle2().getY();
            if (y1 < y2) {
                for (int i = y1 + 1; i < y2; i++) {
                    if (this.table[x][i] != null) {
                        return false;
                    }
                }
            } else {
                for (int i = y2 + 1; i < y1; i++) {
                    if (this.table[x][i] != null) {
                        return false;
                    }
                }
            }
        } else if (pont.getIle1().getY() == pont.getIle2().getY()) {
            int y = pont.getIle1().getY();
            int x1 = pont.getIle1().getX();
            int x2 = pont.getIle2().getX();
            if (x1 < x2) {
                for (int i = x1 + 1; i < x2; i++) {
                    if (this.table[i][y] != null) {
                        return false;
                    }
                }
            } else {
                for (int i = x2 + 1; i < x1; i++) {
                    if (this.table[i][y] != null) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
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
     * cette fonction appelle getVoisin(Ile,int,int), sans qu'on ait à se soucier de mettre les bonnes valeurs pour les entiers
     * @param uneIle l'île dont on cherche un voisin sur son axe cardinal
     * @param sens "haut", "bas", "gauche", "droite" un des quatre sens de l'axe cardinal
     * @return l'île voisine à l'île passée en paramètre par rapport au sens donné. ou null s'il n'y a pas d'île voisine dans ce sens
     * @throws IllegalArgumentException si sens ne vaut pas "haut", "bas", "gauche" ou "droite"
     */
    public Ile getVoisin( Ile uneIle, String sens ) throws IllegalArgumentException {

        switch( sens.toLowerCase() ) {
            case "haut":
                return getVoisin(uneIle, 0, -1);
            case "bas":
                return getVoisin(uneIle, 0, 1);
            case "gauche":
                return getVoisin(uneIle, -1, 0);
            case "droite":
                return getVoisin(uneIle, 1, 0);
            default:
                throw new IllegalArgumentException("la valeur de sens n'est pas comprise dans \"haut\", \"bas\", \"gauche\", \"droite\"");
        }
    }



    /**
     * regarde les cases sur le même axe cardinal que l'île, pour trouver une île voisine ou non
     * @param uneIle l'île dont on cherche un voisin sur son axe cardinal
     * @param dx le déplacement horizontal sur la grille (-1 : vers la gauche ; 1 : vers la droite)
     * @param dy le déplacement vertical sur la grille (-1 : vers le haut ; 1 : vers le bas)
     * @return l'île voisine à l'île passée en paramètre par rapport au sens donné. ou null s'il n'y a pas d'île voisine dans ce sens
     * @throws UnsupportedOperationException si on sort de la boucle avec un cas logiquement impossible
     */
    public Ile getVoisin( Ile uneIle, int dx, int dy ) throws UnsupportedOperationException {
        // récupérer les coordonnées de l'île
        int x = uneIle.getX();
        int y = uneIle.getY();
        
        // décaler d'une case avant de vérifier, pour ne pas se dire que l'île dont on démarre est sa propre voisine
        do {
            // avancer d'une case dans le sens donné
            x += dx;
            y += dy;
        } while( isInBound(x, y) 
            &&  this.table[x][y].estVide() );
        

        if( !isInBound(x, y) ) {
            // si on sort de la grille, c'est qu'on a rien trouvé, donc il n'y a pas de voisin dans ce sens
            return null;
        }

        if( this.table[x][y].estPont() ) {
            // si on trouve un pont, c'est qu'il n'y a pas de voisin dans ce sens
            return null;
        }
        
        if( this.table[x][y].estIle() ) {
            // si on trouve une île, c'est que c'est une île voisine. on la retourne
            return (Ile) this.table[x][y];
        }


        // logiquement, ça n'arrive jamais ici
        throw new  UnsupportedOperationException("sortie de boucle avec un cas non-supporté. logiquement impossible");

    }


    

        
} 
