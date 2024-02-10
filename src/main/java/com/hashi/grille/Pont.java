package com.hashi.grille;

import java.awt.*;
import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

public class Pont extends Case {
    private Ile ile1;
    private Ile ile2;
    private int nbPont;
    private boolean estDouble;
    private ArrayList<Case> listeCase; // Liste des cases par lesquelles passe l'Pont
    private boolean estClique;
    public int nb_pont;

    public Pont(Ile ile1, Ile ile2) {
        super(0, 0);
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.nbPont = 1;
        this.estDouble = false;
        this.estClique = false;
        this.listeCase = new ArrayList<>();

        this.ile1.ajouterPont(this);
        this.ile2.ajouterPont(this);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        // efface tout les ponts

        // Dessine un pont simple si le nombre de ponts est 0
        System.out.println("nbPont : " + this.nbPont);
        if (this.nbPont == 0) {
            g.drawLine(ile1.x - 5, ile1.y - 5, ile2.x - 5, ile2.y - 5);
            g.drawLine(ile1.x + 5, ile1.y + 5, ile2.x + 5, ile2.y + 5);

        } else {
            // Dessine 2 ponts l'un a coté de l'autre si le nombre de ponts est 2
            g.drawLine(ile1.x, ile1.y, ile2.x, ile2.y);

        }

    }

    public Rectangle getBounds() {
        int x = (ile1.x + ile2.x) / 2 - 5;
        int y = (ile1.y + ile2.y) / 2 - 5;
        return new Rectangle(x, y, 20, 20);
    }

    public void effacer() {
        if (this.nbPont > 0) {
            this.nbPont = 0;
            this.ile1.retirerPont(this);
            this.ile2.retirerPont(this);
        }
    }

    public boolean isEffacable() {
        return nbPont == 0;
    }

    public Ile getIleDep() {

        return this.ile1;
    }

    public Ile getIleArr() {
        return this.ile2;
    }

    public ArrayList<Case> getListeCase() {
        return this.listeCase;
    }

    public int getNbPont() {
        return this.nbPont;
    }

    public boolean estValide() {
        if (this.ile1.equals(this.ile2)) {
            return false;
        }

        int deltaX = Math.abs(ile1.x - ile2.x);
        int deltaY = Math.abs(ile1.y - ile2.y);

        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        if (this.nbPont == 2 || this.ile1.nb_connexion() >= this.ile1.getValeur() ||
                this.ile2.nb_connexion() >= this.ile2.getValeur()) {
            return false;
        }

        return true;
    }

    public void ajoutCase(Case c) {
        this.listeCase.add(c);
    }

    public boolean EstDouble() {
        return this.estDouble;
    }

    /**
     * est-ce que le Pont est horizontal ou vertical
     * 
     * @return vrai si le Pont est horizontal, faux si il est vertical
     * @throws InvalidAttributeValueException si les 2 îles que le pont relie ne
     *                                        sont pas alignées horizontalement ni
     *                                        verticalement
     */
    public boolean estHorizontal() throws InvalidAttributeValueException {

        if (this.ile1.getX() == this.ile2.getX()) {
            // si les 2 îles que le pont relie sont sur le même axe X (horizontal)
            // c'est un pont horizontal
            return true;
        } else if (this.ile1.getY() == this.ile2.getY()) {
            // si les 2 îles que le pont relie sont sur le même axe Y (vertical)
            // c'est un pont vertical
            return false;
        } else {
            throw new InvalidAttributeValueException();
        }
    }

    public void supprimer() {
        while (!this.listeCase.isEmpty()) {
            this.listeCase.remove(0);
        }

        // this.ile1.getPosition().getGrille().retirerPont(this);
        this.ile1.retirerPont(this);
        this.ile2.retirerPont(this);
    }

    public Ile getCaseDepart() {
        return this.ile1;
    }

    public Ile getCaseArrivee() {
        return this.ile2;
    }

    public void ajouterPont() {
        this.nbPont++;
    }

    public void retirerPont() {
        this.nbPont--;
    }

    public boolean estDouble() {
        return estDouble;
    }

    public void setEstDouble(boolean estDouble) {
        this.estDouble = estDouble;
    }

    public boolean estClique() {
        return estClique;
    }

    public void setEstClique(boolean estClique) {
        this.estClique = estClique;
    }

    public Ile getIle1() {
        return ile1;
    }

    public Ile getIle2() {
        return ile2;
    }

    public void setNbPont(int nbPont) {
        this.nbPont = nbPont;
    }
}