
import java.awt.Rectangle;
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
    protected List<Pont> Ponts; // Le Tableau des ponts de la grille
    private Case selectedCase; // Case selectionnée par l'utilisateur

    protected Grille(int taille){
        this.table = new Case[taille][taille];
        this.taille = taille;
        this.Iles = new ArrayList<Ile>();
        this.Ponts = new ArrayList<Pont>();
        
        this.initialiserTable();
    }


    public void initialiserTable() {
        for (int i = 0 ; i<taille ; i++) {
            //  ligne
            for (int j = 0 ; j<taille ; j++) {
                //  colonne
                this.table[i][j] = new Case(i, j);

            }
        }
    }
    

    public Case getCase(int x, int y) {
        return this.table[x][y];
    }

    private void setCase(int x, int y,Case new_case) {
        this.table[x][y] = new_case;
    }

    public void ajouterIle(Ile ile) {

        if( this.isInBound(ile.getX(), ile.getY()) ) {

            this.Iles.add(ile);
            
            this.setCase(ile.getX(), ile.getY(), ile);
        }
        else {
            System.err.println("Erreur : Les coordonnées de l'île à ajouter: ("+ile.getX()+","+ile.getY()+") sont en dehors des limites de la grille.\nL'île n'a pas été ajoutée à la Grille");
        }
    }



    /**
     * Ajoute un pont dans la matrice de la Grille (sur chaque case) et dans la liste des ponts
     * @param pont le pont à ajouter
     */
    public void ajouterPont(Pont pont) {

        try {
            // ajouter le pont sur chaque cases de la matrice qui sont entre les deux îles que ce pont relie
        

            if( pont.estHorizontal() ) {
                // l'axe horizontal du pont
                int x = pont.getIle1().getX();
                
                if ( pont.getIle1().getY() < pont.getIle2().getY() ) {
                    // l'île 1 est à la gauche de l'île 2
                    for( int y=pont.getIle1().getY()+1 ; y<pont.getIle2().getY() ; y++ ) {
                        // on parcourt toutes les cases entre ses deux îles (îles exclues)
                        // on ajoute le pont dans chaque case de la matrice, entre les deux îles
                        pont.ajoutCase( this.getCase(x,y) );
                        this.setCase(x,y, pont);
                    }
                    
                } else {
                    // l'île 1 est à la droite de l'île 2
                    for( int y=(pont.getIle1().getY())-1 ; y>=(pont.getIle2().getY()) ; y-- ) {
                        // on parcourt toutes les cases entre ses deux îles (îles exclues)
                        // on ajoute le pont dans chaque case de la matrice, entre les deux îles
                        pont.ajoutCase( this.getCase(x,y) );
                        this.setCase(x, y, pont);
                    }
                }
            }
            else {
                // vertical
                // l'axe vertical du pont
                int y = pont.getIle1().getY();
                
                if ( pont.getIle1().getX() < pont.getIle2().getX() ) {
                    // l'île 1 est au-dessus de l'île 2
                    for( int x=pont.getIle1().getX()+1 ; x<pont.getIle2().getX() ; x++ ) {
                        // on parcourt toutes les cases entre ses deux îles (îles exclues)
                        // on ajoute le pont dans chaque case de la matrice, entre les deux îles
                        this.setCase(x, y, pont);
                    }
                    
                } else {
                    // l'île 1 est en-dessous de l'île 2
                    for( int x=(pont.getIle1().getX())-1 ; x>=(pont.getIle2().getX()) ; x-- ) {
                        // on parcourt toutes les cases entre ses deux îles (îles exclues)
                        // on ajoute le pont dans chaque case de la matrice, entre les deux îles
                        this.setCase(x, y, pont);
                    }
                }
            }
            
        } catch (Exception e) {
            System.err.println("Erreur : Les 2 îles que le pont relie ne sont pas alignées horizontalement ni verticalement");
        }

        // ajouter le pont à la liste de ponts
        this.Ponts.add(pont);
    }

    public void retirerPont(Pont pont) {

        int x,y;

        for (Case c : pont.getListeCase()) {
            // pour toutes les cases où on a mit le pont, dans la matrice
            x = c.getX();
            y = c.getY();
            // on remplace la Case qui contient un Pont par une Case vide
            this.table[x][y] = new Case( x,y );
        }

        // vider la liste de Case du Pont, et le détacher de ses deux Ile
        pont.supprimer();

        // enlever le Pont de la liste des ponts
        this.Ponts.remove(pont);

    }

    public ArrayList<Pont> getListePonts() {
        return (ArrayList<Pont>) this.Ponts;
    }

    public ArrayList<Pont> getListePonts() {
        return (ArrayList<Pont>) this.Ponts;
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

    public Pont getPont(int x, int y) {
        //il faut verifier si le pont existe dans la liste des ponts
        for (Pont pont : this.Ponts) {
            if (pont.getIle1().getX() == x && pont.getIle1().getY() == y) {
                return pont;
            }
        }
        return null;
    }

    public List<Ile> getIles(){
        return this.Iles;
    }

    public List<Pont> getPonts(){
        return this.Ponts;
    }

    public Ile getIleAt(int x, int y) {
        for (Ile ile : this.Iles) {
            if (ile.getX() == x && ile.getY() == y) {
                return ile;
            }
        }
        return null;
    }

    public Case getSelectedCase() {
        return this.selectedCase;
    }

    public void setSelectedCase(Case selectedCase) {
        this.selectedCase = selectedCase;
    }

    public void retirerIle(Ile ile) {
        this.Iles.remove(ile);
    }

    public void retirerCase(int x, int y) {
        this.table[x][y] = null;
    }

    public void retirerPont(int x, int y) {
        for (Pont pont : this.Ponts) {
            if (pont.getIle1().getX() == x && pont.getIle1().getY() == y) {
                this.Ponts.remove(pont);
                break;
            }
        }
    }

    public Pont getPontAt(int x, int y) {
        // le rectangle situé à la position x,y correspond à quel pont ?
        for (Pont pont : this.Ponts) {
            System.out.println(pont.getBounds());
            //si la position de la souris est dans le rectangle du pont
            if (pont.getBounds().contains(x, y)) {
                return pont;
            }
        }
        return null;
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
     * cette fonction appelle chercherVoisin(Ile,int,int), sans qu'on ait à se soucier de mettre les bonnes valeurs pour les entiers
     * @param uneIle l'île dont on cherche un voisin sur son axe cardinal
     * @param sens "haut", "bas", "gauche", "droite" un des quatre sens de l'axe cardinal
     * @return l'île voisine à l'île passée en paramètre par rapport au sens donné. ou null s'il n'y a pas d'île voisine dans ce sens
     * @throws IllegalArgumentException si sens ne vaut pas "haut", "bas", "gauche" ou "droite"
     */
    public Ile getVoisin( Ile uneIle, String sens ) throws IllegalArgumentException {

        switch( sens.toLowerCase() ) {
            case "haut":
                return chercherVoisin(uneIle, 0, -1);
            case "bas":
                return chercherVoisin(uneIle, 0, 1);
            case "gauche":
                return chercherVoisin(uneIle, -1, 0);
            case "droite":
                return chercherVoisin(uneIle, 1, 0);
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
    public Ile chercherVoisin( Ile uneIle, int dx, int dy ) throws UnsupportedOperationException {
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


    
    /**
     * interroge toutes les îles de la grille en appelant leur méthodes de recherche d'aide
     * @param nbDemandeAide le nombre de fois que l'utilisateur a cliqué sur le bouton d'aide sans avoir modifié la grille
     * @return une aide applicable à la grille, dans sa configuraiton actuelle
     */
    public Aide estCeQueQuelquUnAUneAide( int nbDemandeAide ) {
        
        Aide aideTrouve = Aide.RIEN;


        // techniques de démarrage et techniques basiques
        for( Ile uneIle : this.Iles ) {
            
            if( !uneIle.isComplete()
            && aideTrouve == Aide.RIEN ) {
                // on ne s'occupe pas des îles complètes
                aideTrouve = uneIle.techniquePontsForces();
                
            }
        }

        return aideTrouve;
    }
        
} 
