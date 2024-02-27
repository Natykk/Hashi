package com.hashi.grille;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import com.hashi.style.StyleManager;

public class Ile extends Case {
    private int valeur;
    public ArrayList<Pont> listePont;
    public int x;
    public int y;
    private int tailleIle;
    private ArrayList<Ile> listeVoisin;

    public Ile(int valeur, int x, int y, Grille lagrille) {
        super(x, y);
        this.valeur = valeur;
        this.listePont = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.tailleIle = 35;
        this.listeVoisin = new ArrayList<>();
        grille = lagrille;
    }

    public Rectangle getBounds() {
        return new Rectangle(x - tailleIle / 2, y - tailleIle / 2, tailleIle, tailleIle);
    }

    public void draw(Graphics g) {
        if (valeur == getNbConnexion()) {
            g.setColor(StyleManager.getInstance().getFgColor());
            g.fillOval(x - tailleIle / 2, y - tailleIle / 2, tailleIle, tailleIle);
        } else {
            g.setColor(StyleManager.getInstance().getBgColor());
            g.fillOval(x - tailleIle / 2, y - tailleIle / 2, tailleIle, tailleIle);
        }

        g.setColor(StyleManager.getInstance().getFgColor());
        g.drawOval(x - tailleIle / 2, y - tailleIle / 2, tailleIle, tailleIle);

        if (valeur == getNbConnexion())
            g.setColor(StyleManager.getInstance().getBgColor());
        else
            g.setColor(StyleManager.getInstance().getFgColor());

        // on ecrie la valeur de l'ile dans le cercle de l'ile au centre
        String text = String.valueOf(valeur);

        g.setFont(StyleManager.getInstance().getFont().deriveFont(0, 20));
        g.drawString(
                text,
                x - g.getFontMetrics().stringWidth(text) / 2,
                y - g.getFontMetrics().getHeight() / 2 + g.getFontMetrics().getAscent());
    }

    public void ajouterPont(Pont pont) {
        this.listePont.add(pont);
    }

    public void ajouterVoisin(Ile voisin) {
        this.listeVoisin.add(voisin);
    }

    public int getNbConnexion() {
        int tot = 0;
        for (Pont pont : listePont) {
            tot += pont.getNbPont();
        }
        return tot;
    }

    public boolean supprimerPont(Pont pont) {
        if (this.listePont.contains(pont)) {
            int k = listePont.indexOf(pont);
            if (listePont.get(k).equals(pont)) {
                this.listePont.remove(pont);
                return false;
            } else {
                // Pont p1 = listePont.get(k);
                // p1.setNbPont(p1.getNbPont() - 1);
                this.listePont.remove(pont);
                // this.listePont.add(p1);
            }
        }
        return true;
    }

    public boolean ileComplete() {
        return listePont.size() == this.valeur;
    }

    public int getValeur() {
        return this.valeur;
    }

    public void retirerPont(Pont p) {
        this.listePont.remove(p);
    }

    /**
     * retourne le nombre de ponts reliés à cette île EN PRENANT EN COMPTE LES PONTS
     * DOUBLES
     * 
     * @return le nombre de ponts en comptant les ponts doubles pour 2 reliés à
     *         cette île
     */
    public int nbConnexions() {
        int sum = 0;

        for (Pont p : listePont) {
            // si le pont ests double, il compte pour 2
            sum += (p.EstDouble() ? 2 : 1);
        }

        return sum;
    }

    /**
     * vérifie si le nombre de ponts de l'île est égal à sa valeur
     * 
     * @return vrai si le nombre de ponts de l'île est égal à sa valeur, faux sinon
     */
    public boolean isComplete() {
        return this.nbConnexions() == valeur;
    }

    public String afficher() {
        return String.valueOf(this.valeur);
    }

    public int getNbPonts() {
        return this.listePont.size();
    }

    public int getNbPontDroite() {
        int nbPontDroite = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle1().equals(this)) {
                nbPontDroite++;
            }
        }
        return nbPontDroite;
    }

    public int getNbPontGauche() {
        int nbPontGauche = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle2().equals(this)) {
                nbPontGauche++;
            }
        }
        return nbPontGauche;
    }

    public ArrayList<Ile> getListeVoisin() {
        return this.listeVoisin;
    }

    public boolean estComplet() {
        return this.valeur == this.getNbPonts();
    }

    public boolean estValide() {
        return this.valeur >= this.getNbPonts();
    }

    public int getNbPontBas() {
        int nbPontBas = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle1().equals(this)) {
                nbPontBas++;
            }
        }
        return nbPontBas;
    }

    public int getNbPontHaut() {
        int nbPontHaut = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle2().equals(this)) {
                nbPontHaut++;
            }
        }
        return nbPontHaut;
    }

    /*
     * retourne vrai si l'île a plus d'un pont dans une direction
     * 
     * @return vrai si l'île a plus d'un pont dans une direction
     * 
     */
    public boolean estDouble() {
        return this.getNbPontBas() > 1 || this.getNbPontHaut() > 1;
    }

    public int nb_connexion() {
        return this.listePont.size();
    }

    /*
     * retourne la position de l'île
     * 
     * @return la position de l'île
     */
    public int getPosition() {
        return this.x;
    }

    /**
     * vérifie si le nombre de ponts de l'île est inférieur à sa valeur
     * 
     * @return vrai si le nombre de ponts de l'île est inférieur à sa valeur, faux
     *         sinon
     */
    public boolean estLibre() {
        return this.nbConnexions() < valeur;
    }

    /**
     * récupère les îles voisines (les îles sur le même axe cardinal que cette île,
     * sans être bloqué par un pont)
     * 
     * @return une liste d'îles qui sont les îles voisines, ou null si l'île n'a
     *         aucune voisine
     */
    public ArrayList<Ile> getVoisins() {

        ArrayList<Ile> lesVoisins = new ArrayList<>();

        // récupération des îles voisines dans les quatre sens
        lesVoisins.add(grille.getVoisin(this, "haut"));
        lesVoisins.add(grille.getVoisin(this, "bas"));
        lesVoisins.add(grille.getVoisin(this, "gauche"));
        lesVoisins.add(grille.getVoisin(this, "droite"));

        // la méthode getVoisin ne permet pas de récupérer les îles voisines qui sont
        // déjà reliées par un pont, donc
        List<Pont> sesPonts = this.listePont;

        if (!sesPonts.isEmpty()) {

            for (Pont unPont : sesPonts) {
                // pour chaque pont, on cherche l'île qui n'est pas uneIle
                if (unPont.getIle1() == this) {
                    // si cette île est dans l'attribut -ile1, c'est qu'elle a une voisine dans
                    // -île2
                    lesVoisins.add(unPont.getIle2());
                } else {
                    // et, logiquement, cette île est donc dans l'attribut -ile2, elle a une voisine
                    // dans -île1
                    lesVoisins.add(unPont.getIle1());
                }
            }
        }

        // enlever les valeurs null, s'il y en a
        while (lesVoisins.remove(null))
            ;

        return lesVoisins;
    }

    /**
     * compte le nombre d'île voisines de cette île qui n'ont pas encore tous leurs
     * ponts de placés
     * 
     * @return le nombre d'îles voisines qui n'ont pas encore tous leurs ponts de
     *         placés
     */
    public int nbVoisinsLibres() {
        // récupérer la liste de voisins de l'île
        ArrayList<Ile> lesVoisins = this.getVoisins();

        // pas besoin de faire toutes les étapes en-dessous si la liste est vide
        if (lesVoisins.isEmpty()) {
            return 0;
        }

        // à partir de la liste de ses voisins,
        // compte le nombre d'îles qui satisfont la méthode estLibre()
        return (int) getVoisins().stream().filter(e -> e.estLibre()).count();
    }

    /**
     * vérifie les techniques pour quand une île a besoin de beaucoup de ponts, mais
     * qu'elle a peu de voisins
     * la technique spécifique sera décidée par l'attribut -valeur de l'île dans une
     * autre fonction
     * 
     * @return une aide applicable à la grille dans sa configuraiton actuelle
     */
    public Aide techniquePontsForces() {

        switch (this.valeur) {
            case 1:
                // une île qui a besoin d'un pont,
                // n'en a actuellement aucun
                // et qui n'a qu'un seul voisin libre
            case 2:
                // une île qui a besoin de 2 ponts,
                // en a actuellement moins de 2
                // et qui n'a qu'un seul voisin libre
                if (this.nbVoisinsLibres() == 1
                        && this.nbConnexions() < this.valeur) {
                    // la même condition peut s'appliquer pour le cas 1 et 2
                    // renvoie FORCE1 si la valeur de l'île est 1. respectivement FORCE2 et 2
                    return this.valeur == 1 ? Aide.FORCE1 : Aide.FORCE2;
                }
                break;
            case 3:
                // une île qui a besoin de 3 ponts,
                // en a actuellement moins de 2 dans des sens différents
                // et qui n'a que 2 voisins libres
                if (this.nbVoisinsLibres() == 2
                        && this.listePont.size() < 2) {
                    return Aide.FORCE3;
                }
                break;
            case 4:
                // une île qui a besoin de 4 ponts,
                // en a actuellement moins de 4
                // et qui n'a que 2 voisins libres
                if (this.nbVoisinsLibres() == 2
                        && this.nbConnexions() < this.valeur) {
                    return Aide.FORCE4;
                }
                break;
            case 5:
                // une île qui a besoin de 5 ponts,
                // en a actuellement moins de 3 dans des sens différents
                // et qui n'a que 3 voisins libres
                if (this.nbVoisinsLibres() == 3
                        && this.listePont.size() < 3) {
                    return Aide.FORCE5;
                }
                break;
            case 6:
                // une île qui a besoin de 6 ponts,
                // en a actuellement moins de 6
                // et qui n'a que 3 voisins libres
                if (this.nbVoisinsLibres() == 3
                        && this.nbConnexions() < this.valeur) {
                    return Aide.FORCE6;
                }
                break;
            case 7:
                // une île qui a besoin de 7 ponts
                // et en a actuellement moins de 4 dans des sens différents
                if (this.listePont.size() < 4) {
                    return Aide.FORCE7;
                }
                break;
            case 8:
                // une île qui a besoin de 8 ponts
                // et en a actuellement moins de 8
                if (this.nbConnexions() < 8) {
                    return Aide.FORCE8;
                }
                break;
        }
        // si la valeur de l'île est fausse, on ne s'en occupe pas ici

        return Aide.RIEN;
    }

    /**
     * vérifie les techniques pour quand une île a un voisin qui lui fournit 1/2
     * ponts et qui est complété
     * 
     * @return une aide applicable à la grille dans sa configuraiton actuelle
     * @throws InvalidAttributeValueException si l'attribut -valeur de l'île n'est
     *                                        pas compris dans [1,8]
     */
    public Aide techniquePontsBloques() throws InvalidAttributeValueException {

        switch (this.valeur) {
            case 3:
                // une île qui a besoin de 3 ponts,
                // en a actuellement moins de 3
                // et qui n'a plus qu'un voisin libre
                if (this.nbVoisinsLibres() == 1
                        && this.nbConnexions() < this.valeur) {
                    return Aide.BLOQUE3;
                }
                break;
            case 4:
                // une île qui a besoin de 4 ponts,
                // en a actuellement moins de 3 dans des sens différents
                // et qui a 2 voisins libres
                if (this.nbVoisinsLibres() == 1
                        && this.nbConnexions() < this.valeur) {

                    return Aide.BLOQUE3;
                }
                break;
            case 5:

            case 6:

            case 7:

            default:
                throw new InvalidAttributeValueException("erreur techniquePontsForces(): l'attribut -valeur de " + this
                        + " n'est pas compris dans [1,8]");
        }

        return Aide.RIEN;
    }

    /**
     * appelle toutes les méthodes d'aide pour cette île, si nécessaire
     */
    public void chercherAide() {

        // déjà, on fait rien sur les îles complètes
        if (!isComplete()) {

            // todo: appeler les méthodes d'aide
            // this.techniquePontsForces();
        }
    }

    public Component getListePont() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getListePont'");
    }

}
