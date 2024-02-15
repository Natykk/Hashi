package com.hashi.grille;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

public class Ile extends Case {
    private int valeur;
    public ArrayList<Pont> listePont;
    private int xAffichage; // coordonnée x pour l'affichage de l'Ile
    private int yAffichage; // coordonnée y pour l'affichage de l'Ile
    private int tailleIle; // taille pour l'affichage
    private Color color;
    private ArrayList<Ile> listeVoisin; // liste des Iles voisines (pas implémenté)

    public Ile(int valeur, int x, int y, Grille lagrille) {
        super(x, y);
        this.valeur = valeur;
        this.listePont = new ArrayList<>();
        this.xAffichage = x;
        this.yAffichage = y;
        this.tailleIle = 20;
        this.color = Color.CYAN;
        this.listeVoisin = new ArrayList<>();
        grille = lagrille;
    }

    public Rectangle getBounds() {
        return new Rectangle(xAffichage - tailleIle / 2, yAffichage - tailleIle / 2, tailleIle, tailleIle);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(xAffichage - 10, yAffichage - 10, 20, 20);
        g.fillOval(xAffichage - 10, yAffichage - 10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(valeur), xAffichage - 5, yAffichage + 5);
    }

    /**
     * ajoute le pont donné à la liste des ponts de cette Ile
     * @param pont le pont à ajouter
     */
    public void ajouterPont(Pont pont) {
        this.listePont.add(pont);
    }

    /**
     * ajoute l'Ile donnée à la liste des voisins de cette Ile
     * @param voisin l'Ile voisine à ajouter
     */
    public void ajouterVoisin(Ile voisin) {
        this.listeVoisin.add(voisin);
    }

    public int getValeur() {
        return this.valeur;
    }

    public int getxAffichage() {
        return xAffichage;
    }

    public void setxAffichage(int xAffichage) {
        this.xAffichage = xAffichage;
    }

    public int getyAffichage() {
        return yAffichage;
    }

    public void setyAffichage(int yAffichage) {
        this.yAffichage = yAffichage;
    }

    /**
     * retirer le pont donné de la liste des ponts de cette Ile
     * @param p le pont à retirer
     */
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
            sum += (p.estDouble() ? 2 : 1);
        }

        return sum;
    }

    // affichage sur terminal
    public String afficher() {
        return String.valueOf(this.valeur);
    }
    

    /**
     * obtenir le nombre d'objet Pont relié à cette Ile
     * (pour une méthode qui fait la distinction entre pont simple et double, voir nbConnexions() )
     * @return le nombre d'objet Pont de la liste de Ponts de cette Ile
     */
    public int getNbPonts() {
        return this.listePont.size();
    }

    /**
     * calculer le nombre de ponts dont cette ile se trouve à "droite"
     * @return le nombre de ponts connectés à cette Ile, où cette Ile est dans le champ ile1
     */
    public int getNbPontDroite() {
        int nbPontDroite = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle1().equals(this)) {
                nbPontDroite++;
            }
        }
        return nbPontDroite;
    }

    /**
     * calculer le nombre de ponts dont cette ile se trouve à "gauche"
     * @return le nombre de ponts connectés à cette Ile, où cette Ile est dans le champ ile2
     */
    public int getNbPontGauche() {
        int nbPontGauche = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle2().equals(this)) {
                nbPontGauche++;
            }
        }
        return nbPontGauche;
    }

    /**
     * récupérer la liste des voisins de cette Ile
     * @return une liste d'Iles
     */
    public ArrayList<Ile> getListeVoisin() {
        return this.listeVoisin;
    }

    /**
     * vérifier si le nombre de Ponts de cette Ile est égal à sa valeur
     * 
     * @return vrai si le nombre de Ponts de cette Ile est égal à sa valeur, faux sinon
     */
    public boolean estComplet() {
        return this.valeur == this.nbConnexions();
    }

    /**
     * vérifier si la valeur de cette Ile est supérieure ou égale à son nombre de Ponts
     * (savoir si une Ile n'est pas valide est utile pour les aides)
     * @return vrai si la valeur de cette Ile est supérieure ou égale à son nombre de Ponts, faux sinon
     */
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
        lesVoisins.add(grille.getVoisinSansPont(this, "haut"));
        lesVoisins.add(grille.getVoisinSansPont(this, "bas"));
        lesVoisins.add(grille.getVoisinSansPont(this, "gauche"));
        lesVoisins.add(grille.getVoisinSansPont(this, "droite"));

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
        if (!estComplet()) {

            // todo: appeler les méthodes d'aide
            // this.techniquePontsForces();
        }
    }

}
