package com.hashi.grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import com.hashi.style.StyleManager;

public class Pont extends Case {
    private Ile ile1; // Ile que le Pont raccorde
    private Ile ile2; // autre Ile que le Pont raccorde
    private boolean estDouble; // le Pont peut être simple ou double
    private List<Case> listeCase; // Liste des Cases par lesquelles passe le Pont

    public Pont(Ile ile1, Ile ile2, Grille grille) {
        // grille est static
        super(0, 0, grille);
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.estDouble = false;
        this.listeCase = new ArrayList<>();

        // ajouter le Pont nouvellement créé aux 2 Iles qu'il relie
        this.ile1.ajouterPont(this);
        this.ile2.ajouterPont(this);
    }

    /**
     * Dessine le pont à l'écran.
     * 
     * @param g
     */
    public void draw(Graphics g) {
        try {
            g.setColor(StyleManager.getInstance().getFgColor());

            int decalage = estDouble ? 10 : 0;

            if (estHorizontal()) {
                g.drawLine(ile1.getXAffichage(), ile1.getYAffichage() - decalage, ile2.getXAffichage(),
                        ile2.getYAffichage() - decalage);

                if (estDouble)
                    g.drawLine(ile1.getXAffichage(), ile1.getYAffichage() + decalage, ile2.getXAffichage(),
                            ile2.getYAffichage() + decalage);
            } else {
                g.drawLine(ile1.getXAffichage() - decalage, ile1.getYAffichage(), ile2.getXAffichage() - decalage,
                        ile2.getYAffichage());

                if (estDouble)
                    g.drawLine(ile1.getXAffichage() + decalage, ile1.getYAffichage(), ile2.getXAffichage() + decalage,
                            ile2.getYAffichage());
            }
        } catch (InvalidAttributeValueException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zone cliquable du pont (zone entre deux iles)
     * 
     * @return
     */
    public Rectangle getBounds() {
        boolean horizontal = true;

        try {
            horizontal = estHorizontal();
        } catch (InvalidAttributeValueException e) {
            e.printStackTrace();
        }

        Ile ile1 = this.ile1;
        Ile ile2 = this.ile2;

        if (ile1.getXAffichage() > ile2.getXAffichage() || ile1.getYAffichage() > ile2.getYAffichage()) {
            ile1 = this.ile2;
            ile2 = this.ile1;
        }

        if (horizontal)
            return new Rectangle(ile1.getXAffichage() + ile1.getTaille() / 2, ile1.getYAffichage() - 15,
                    ile2.getXAffichage() - ile1.getXAffichage() - (ile1.getTaille() + ile2.getTaille()) / 2, 30);
        else
            return new Rectangle(ile1.getXAffichage() - 15, ile1.getYAffichage() + ile1.getTaille() / 2, 30,
                    ile2.getYAffichage() - ile1.getYAffichage() - (ile1.getTaille() + ile2.getTaille()) / 2);
    }

    public Ile getIle1() {

        return this.ile1;
    }

    public Ile getIle2() {
        return this.ile2;
    }

    /**
     * Retourner la liste des Cases de la matrice qui contiennent ce Pont
     * 
     * @return la liste des Cases
     */
    public List<Case> getListeCase() {
        return this.listeCase;
    }

    /**
     * ajouter une Case donnée à la liste des Cases de ce Pont
     * ce sont les Cases dans la matrice où passe ce Pont
     * 
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

    public void setEstDouble(boolean estDouble) {
        this.estDouble = estDouble;
    }

    public boolean estDouble() {
        return this.estDouble;
    }

    @Override
    public String toString() {
        return "Pont<double: " + estDouble() + ">";
    }
}