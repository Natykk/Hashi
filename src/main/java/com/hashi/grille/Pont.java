package com.hashi.grille;

import java.awt.*;
import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

import com.hashi.style.StyleManager;

public class Pont extends Case {
    private Ile ile1;
    private Ile ile2;
    private boolean estDouble;
    private ArrayList<Case> listeCase; // Liste des cases par lesquelles passe le pont
    private boolean estClique;

    public Pont(Ile ile1, Ile ile2) {
        super(0, 0);
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.estDouble = false;
        this.estClique = false;
        this.listeCase = new ArrayList<>();

        this.ile1.ajouterPont(this);
        this.ile2.ajouterPont(this);
    }

    public void draw(Graphics g) {
        try {
            g.setColor(StyleManager.getInstance().getFgColor());

            int decalage = estDouble ? 10 : 0;

            if (estHorizontal()) {
                g.drawLine(ile1.x, ile1.y - decalage, ile2.x, ile2.y - decalage);

                if (estDouble)
                    g.drawLine(ile1.x, ile1.y + decalage, ile2.x, ile2.y + decalage);
            } else {
                g.drawLine(ile1.x - decalage, ile1.y, ile2.x - decalage, ile2.y);

                if (estDouble)
                    g.drawLine(ile1.x + decalage, ile1.y, ile2.x + decalage, ile2.y);
            }
        } catch (InvalidAttributeValueException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds() {
        boolean horizontal = true;

        try {
            horizontal = estHorizontal();
        } catch (InvalidAttributeValueException e) {
            e.printStackTrace();
        }

        Ile ile1 = this.ile1;
        Ile ile2 = this.ile2;

        if (ile1.x > ile2.x || ile1.y > ile2.y) {
            ile1 = this.ile2;
            ile2 = this.ile1;
        }

        if (horizontal)
            return new Rectangle(ile1.x + ile1.getTaille() / 2, ile1.y - 15,
                    ile2.x - ile1.x - (ile1.getTaille() + ile2.getTaille()) / 2, 30);
        else
            return new Rectangle(ile1.x - 15, ile1.y + ile1.getTaille() / 2, 30,
                    ile2.y - ile1.y - (ile1.getTaille() + ile2.getTaille()) / 2);
    }

    public void effacer() {
        this.ile1.retirerPont(this);
        this.ile2.retirerPont(this);
    }

    public boolean isEffacable() {
        return this.ile1.nb_connexion() == 0 && this.ile2.nb_connexion() == 0;
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
        return this.estDouble ? 2 : 1;
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

        if (this.estDouble || this.ile1.nb_connexion() >= this.ile1.getValeur() ||
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
            // si les 2 îles que le pont relie ont le même Y (vertical)
            // c'est un pont vertical
            return false;
        } else if (this.ile1.getY() == this.ile2.getY()) {
            // si les 2 îles que le pont relie ont le même X (horizontal)
            // c'est un pont horizontal
            return true;
        }

        // si les 2 îles que le pont relie ne sont pas alignées horizontalement ni
        // verticalement
        throw new InvalidAttributeValueException(
                "Les 2 îles que le pont relie ne sont pas alignées horizontalement ni verticalement");
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
        this.estDouble = true;
    }

    public void retirerPont() {
        this.estDouble = false;
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

    public void DejaUneIle() {

    }
}