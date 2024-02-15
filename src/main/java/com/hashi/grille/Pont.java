package com.hashi.grille;

import java.awt.*;
import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

public class Pont extends Case {
    private Ile ile1; // Ile que le Pont raccorde
    private Ile ile2; // autre Ile que le Pont raccorde
    private boolean estDouble; // le Pont peut être simple ou double
    private ArrayList<Case> listeCase; // Liste des Cases par lesquelles passe le Pont
    private boolean estClique; // si le Pont est cliqué

    public Pont(Ile ile1, Ile ile2) {
        super(0, 0);
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.estDouble = false;
        this.estClique = false;
        this.listeCase = new ArrayList<>();

        // ajouter le Pont nouvellement créé aux 2 Iles qu'il relie
        this.ile1.ajouterPont(this);
        this.ile2.ajouterPont(this);
    }

    /**
     * dessine le pont à l'écran ?
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        // efface tout les ponts

        if (!this.estDouble) {
            // Dessine un pont simple si le nombre de ponts est 0
            System.out.println("Pont est Simple");
            g.drawLine(ile1.getxAffichage() - 5, ile1.getyAffichage() - 5, ile2.getxAffichage() - 5, ile2.getyAffichage() - 5);
            g.drawLine(ile1.getxAffichage() + 5, ile1.getyAffichage() + 5, ile2.getxAffichage() + 5, ile2.getyAffichage() + 5);
        } 
        else {
            // Dessine 2 ponts l'un a coté de l'autre si le nombre de ponts est 2
            System.out.println("Pont est Double");
            g.drawLine(ile1.getxAffichage(), ile1.getyAffichage(), ile2.getxAffichage(), ile2.getyAffichage());

        }

    }

    public Rectangle getBounds() {
        int xAffichage = (ile1.getxAffichage() + ile2.getxAffichage()) / 2 - 5;
        int yAffichage = (ile1.getyAffichage() + ile2.getyAffichage()) / 2 - 5;
        return new Rectangle(xAffichage, yAffichage, 20, 20);
    }

    /**
     * Retourner la liste des Cases de la matrice qui contiennent ce Pont
     * @return la liste des Cases
     */
    public ArrayList<Case> getListeCase() {
        return this.listeCase;
    }

    public boolean estValide() {
        if (this.ile1.equals(this.ile2)) {
            return false;
        }

        int deltaX = Math.abs(ile1.getxAffichage() - ile2.getxAffichage());
        int deltaY = Math.abs(ile1.getyAffichage() - ile2.getyAffichage());

        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        if (this.estDouble || this.ile1.nbConnexions() >= this.ile1.getValeur() ||
                this.ile2.nbConnexions() >= this.ile2.getValeur()) {
            return false;
        }

        return true;
    }

    /**
     * ajouter une Case donnée à la liste des Cases de ce Pont
     * ce sont les Cases dans la matrice où passe ce Pont
     * @param c
     */
    public void ajoutCase(Case c) {
        this.listeCase.add(c);
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

    /**
     * supprime les éléments liés à ce Pont
     * - vide sa liste de Cases
     * - appelle retirerPont() sur les deux Iles que le pont reliait
     * méthode appelée dans Grille
     */
    public void supprimer() {
        while (!this.listeCase.isEmpty()) {
            this.listeCase.remove(0);
        }

        this.ile1.retirerPont(this);
        this.ile2.retirerPont(this);
    }

    /**
     * est-ce que le Pont est double ou simple
     * retourne l'attribut estDouble
     * @return vrai si le Pont est double, faux s'il est simple
     */
    public boolean estDouble() {
        return estDouble;
    }

    public void setEstDouble(boolean estDouble) {
        this.estDouble = estDouble;
    }

    /**
     * est-ce que le Pont est cliqué
     * retourne l'attribut EstClique
     * @return vrai si le Pont est cliqué, faux sinon
     */
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

}