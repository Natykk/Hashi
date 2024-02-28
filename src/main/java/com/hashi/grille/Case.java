package com.hashi.grille;

public class Case {
    protected int x; // coordonnée x pour l'affichage de l'Ile
    protected int y; // coordonnée y pour l'affichage de l'Ile
    static Grille grille; // référence vers la Grille où cette Case se situe

    public Case(int x, int y, Grille laGrille) {
        this.x = x;
        this.y = y;
        grille = laGrille;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * est-ce que cette Case n'est ni une Ile, ni un Pont
     * 
     * @return vrai si cette Case n'est ni une Ile, ni un Pont. faux sinon
     */
    public boolean estVide() {
        return !estIle() && !estPont();
    }

    /**
     * est-ce que cette Case est une Ile
     * 
     * @return vrai si cette Case est une Ile, faux sinon
     */
    public boolean estIle() {
        return grille.getCase(x, y) instanceof Ile;
    }

    /**
     * est-ce que cette Case est un Pont
     * 
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
}