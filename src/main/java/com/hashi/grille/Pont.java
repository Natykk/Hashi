package com.hashi.grille;

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
    private boolean estClique; // si le Pont est cliqué

    public Pont(Ile ile1, Ile ile2) {
        // grille est static
        super(0, 0, grille);
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
     * 
     * @param g
     */
    public void draw(Graphics g) {
        try {
            g.setColor(StyleManager.getInstance().getFgColor());

            int decalage = estDouble ? 10 : 0;

            if (estHorizontal()) {
                g.drawLine(ile1.getxAffichage(), ile1.getyAffichage() - decalage, ile2.getxAffichage(),
                        ile2.getyAffichage() - decalage);

                if (estDouble)
                    g.drawLine(ile1.getxAffichage(), ile1.getyAffichage() + decalage, ile2.getxAffichage(),
                            ile2.getyAffichage() + decalage);
            } else {
                g.drawLine(ile1.getxAffichage() - decalage, ile1.getyAffichage(), ile2.getxAffichage() - decalage,
                        ile2.getyAffichage());

                if (estDouble)
                    g.drawLine(ile1.getxAffichage() + decalage, ile1.getyAffichage(), ile2.getxAffichage() + decalage,
                            ile2.getyAffichage());
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

        if (ile1.getxAffichage() > ile2.getxAffichage() || ile1.getyAffichage() > ile2.getyAffichage()) {
            ile1 = this.ile2;
            ile2 = this.ile1;
        }

        if (horizontal)
            return new Rectangle(ile1.getxAffichage() + ile1.getTaille() / 2, ile1.getyAffichage() - 15,
                    ile2.getxAffichage() - ile1.getxAffichage() - (ile1.getTaille() + ile2.getTaille()) / 2, 30);
        else
            return new Rectangle(ile1.getxAffichage() - 15, ile1.getyAffichage() + ile1.getTaille() / 2, 30,
                    ile2.getyAffichage() - ile1.getyAffichage() - (ile1.getTaille() + ile2.getTaille()) / 2);
    }
/* 
    public void effacer() {
        this.ile1.retirerPont(this);
        this.ile2.retirerPont(this);
    }
*/
    public boolean isEffacable() {
        return this.ile1.nbConnexions() == 0 && this.ile2.nbConnexions() == 0;
    }

    public Ile getIleDep() {

        return this.ile1;
    }

    public Ile getIleArr() {
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
     * Savoir si le Pont est simple ou double
     * @return 1 si le Pont est simple, 2 s'il est double
     */
    public int getNbPont() {
        return this.estDouble ? 2 : 1;
    }

    public boolean estValide() {
        if (this.ile1.equals(this.ile2)) {
            return false;
        }

        int deltaX = Math.abs(ile1.getxAffichage() - ile2.getxAffichage());
        int deltaY = Math.abs(ile1.getxAffichage() - ile2.getyAffichage());

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

    public void ajouterPont() {
        this.estDouble = true;
    }

    public void retirerPont() {
        this.estDouble = false;
    }

    /**
     * est-ce que le Pont est double ou simple
     * retourne l'attribut estDouble
     * 
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
     * 
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