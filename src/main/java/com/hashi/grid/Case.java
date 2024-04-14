package com.hashi.grid;

/**
 * Représente une case de la grille.
 */
public class Case {
    /**
     * La position en x.
     */
    protected int x;

    /**
     * La position en y.
     */
    protected int y;

    /**
     * Référence vers la Grille où cette Case se situe.
     */
    protected Grille grille;

    /**
     * Créer une case sur la grille.
     * 
     * @param x        la position en x.
     * @param y        la position en y.
     * @param laGrille la grille contenant la case.
     */
    public Case(int x, int y, Grille laGrille) {
        this.x = x;
        this.y = y;
        grille = laGrille;
    }

    /**
     * Récupère la position en x.
     * 
     * @return retourne la position en x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Récupère la position en y.
     * 
     * @return retourne la position en y.
     */
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
        return this instanceof Ile;
    }

    /**
     * est-ce que cette Case est un Pont
     * 
     * @return vrai si cette Case est un Pont, faux sinon
     */
    public boolean estPont() {
        return this instanceof Pont;
    }

    // affichage sur terminal
    @Override
    public String toString() {
        return "Case";
    }
}