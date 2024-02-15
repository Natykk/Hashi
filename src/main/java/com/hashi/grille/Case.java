package com.hashi.grille;

public class Case {
    protected int x; // coordonnée x pour la matrice de Grille
    protected int y; // coordonnée y pour la matrice de Grille
    static Grille grille; // référence vers la Grille où cette Case se situe

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setGrille(Grille new_grille) {
        grille = new_grille;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * est-ce que cette Case n'est ni une Ile, ni un Pont
     * @return vrai si cette Case n'est ni une Ile, ni un Pont. faux sinon
     */
    public boolean estVide() {
        return !estIle() && !estPont();
    }

    /**
     * est-ce que cette Case est une Ile
     * @return vrai si cette Case est une Ile, faux sinon
     */
    public boolean estIle() {
        return grille.getCase(x, y) instanceof Ile;
    }

    /**
     * est-ce que cette Case est un Pont
     * @return vrai si cette Case est un Pont, faux sinon
     */
    public boolean estPont() {
        return grille.getCase(x, y) instanceof Pont;
    }

    // affichage sur terminal
    public String afficher() {
        if (this.estVide()) {
            return " ";
        } else {
            return grille.getCase(x, y).afficher();
        }
    }

    /**
     * récupérer la valeur de l'Ile qui est cette Case
     * @return la valeur de l'Ile, ou -1 si ce n'est pas une Ile
     */
    public int getValeur() {
        if (this.estIle()) {
            return ((Ile) grille.getCase(x, y)).getValeur();
        } else {
            return -1;
        }
    }

}