package com.hashi.grille;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import com.hashi.style.StyleManager;

public class Ile extends Case {
    private int valeur;
    public List<Pont> listePont;
    private int xAffichage;
    private int yAffichage;
    private int tailleIle; // taille pour l'affichage
    private List<Ile> listeVoisin; // liste des Iles voisines (pas implémenté)

    public Ile(int valeur, int x, int y, Grille lagrille) {
        super(x, y, lagrille);
        this.valeur = valeur;
        this.listePont = new ArrayList<>();
        this.xAffichage = x;
        this.yAffichage = y;
        this.tailleIle = 35;
        this.listeVoisin = new ArrayList<>();
        grille = lagrille;
    }

    public Rectangle getBounds() {
        return new Rectangle(xAffichage - tailleIle / 2, yAffichage - tailleIle / 2, tailleIle, tailleIle);
    }

    public void draw(Graphics g) {
        if (valeur == getNbConnexion()) {
            g.setColor(StyleManager.getInstance().getFgColor());
            g.fillOval(xAffichage - tailleIle / 2, yAffichage - tailleIle / 2, tailleIle, tailleIle);
        } else {
            g.setColor(StyleManager.getInstance().getBgColor());
            g.fillOval(xAffichage - tailleIle / 2, yAffichage - tailleIle / 2, tailleIle, tailleIle);
        }

        g.setColor(StyleManager.getInstance().getFgColor());
        g.drawOval(xAffichage - tailleIle / 2, yAffichage - tailleIle / 2, tailleIle, tailleIle);

        if (valeur == getNbConnexion())
            g.setColor(StyleManager.getInstance().getBgColor());
        else
            g.setColor(StyleManager.getInstance().getFgColor());

        // on ecrie la valeur de l'ile dans le cercle de l'ile au centre
        String text = String.valueOf(valeur);

        g.setFont(StyleManager.getInstance().getFont().deriveFont(0, 20));
        g.drawString(
                text,
                xAffichage - g.getFontMetrics().stringWidth(text) / 2,
                yAffichage - g.getFontMetrics().getHeight() / 2 + g.getFontMetrics().getAscent());
    }

    /**
     * ajoute le pont donné à la liste des ponts de cette Ile
     * 
     * @param pont le pont à ajouter
     */
    public void ajouterPont(Pont pont) {
        this.listePont.add(pont);
    }

    /**
     * ajoute l'Ile donnée à la liste des voisins de cette Ile
     * 
     * @param voisin l'Ile voisine à ajouter
     */
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

    /**
     * Donne la taille en pixel de l'{@link com.hashi.grille.Ile}
     * 
     * @return la taille en pixel
     */
    public int getTaille() {
        return tailleIle;
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
     * 
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
     * (pour une méthode qui fait la distinction entre pont simple et double, voir
     * nbConnexions() )
     * 
     * @return le nombre d'objet Pont de la liste de Ponts de cette Ile
     */
    public int getNbPonts() {
        return this.listePont.size();
    }

    /**
     * calculer le nombre de ponts dont cette ile se trouve à "droite"
     * 
     * @return le nombre de ponts connectés à cette Ile, où cette Ile est dans le
     *         champ ile1
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
     * 
     * @return le nombre de ponts connectés à cette Ile, où cette Ile est dans le
     *         champ ile2
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
     * 
     * @return une liste d'Iles
     */
    public List<Ile> getListeVoisin() {
        return this.listeVoisin;
    }

    /**
     * vérifier si le nombre de Ponts de cette Ile est égal à sa valeur
     * 
     * @return vrai si le nombre de Ponts de cette Ile est égal à sa valeur, faux
     *         sinon
     */
    public boolean estComplet() {
        return this.valeur == this.nbConnexions();
    }

    /**
     * vérifier si la valeur de cette Ile est supérieure ou égale à son nombre de
     * Ponts
     * (savoir si une Ile n'est pas valide est utile pour les aides)
     * 
     * @return vrai si la valeur de cette Ile est supérieure ou égale à son nombre
     *         de Ponts, faux sinon
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

    /*
     * retourne vrai si l'île a plus d'un pont dans une direction
     * 
     * @return vrai si l'île a plus d'un pont dans une direction
     * 
     */
    public boolean estDouble() {
        return this.getNbPontBas() > 1 || this.getNbPontHaut() > 1;
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
    public List<Ile> getVoisins() {

        List<Ile> lesVoisins = new ArrayList<>();

        // récupération des îles voisines dans les quatre sens
        lesVoisins.add(grille.getVoisinSansPont(this, "haut"));
        lesVoisins.add(grille.getVoisinSansPont(this, "bas"));
        lesVoisins.add(grille.getVoisinSansPont(this, "gauche"));
        lesVoisins.add(grille.getVoisinSansPont(this, "droite"));

        // la méthode getVoisinSansPont ne permet pas de récupérer les îles voisines qui
        // sont
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
        List<Ile> lesVoisins = this.getVoisins();

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
                // et qui a 2 voisins libres, car certains sont complétés
                // et qui a moins de 3 Ponts dans des sens différents (?)

                /*
                 * prend en compte des cas qui ne sont pas BLOQUE41
                 * if (this.nbVoisinsLibres() == 2
                 * && this.getNbPonts() < 3 ) {
                 * // création d'une Ile qui a comme valeur le nombre de ponts restants à
                 * connecter à cette Ile-ci
                 * IlePlusFaible = new Ile( this.pontRestants() ,this.x,this.y, grille);
                 * 
                 * if( IlePlusFaible.techniquePontsForces() == Aide.FORCE3) {
                 * // si, dans cette situation où l'Ile créée peut appliquer une technique,
                 * // c'est que cette technique peut être appliquée à cette Ile-ci
                 * return Aide.BLOQUE41;
                 * }
                 * }
                 */

                /*
                 * // une île qui a besoin de 4 ponts,
                 * // qui a 2 voisins libres
                 * // et qui a moins de 3 ponts dans des sens différents (?)
                 * if (this.nbVoisinsLibres() == 2
                 * && this.getNbPonts() < 3) {
                 * 
                 * this.nbPontsPossibles();
                 * 
                 * 
                 * return Aide.BLOQUE41;
                 * }
                 * 
                 * // une île de valeur 4 qui a 1 voisin libres et 1 pont avec 2 voisins qui
                 * sont
                 * // complétés = on peut compléter l'île avec 2 ponts sur son 3e et dernier
                 * voisin
                 * if(this.nbVoisinsLibres() == 1
                 * && this.valeur == 2) {
                 * 
                 * return Aide.BLOQUE42;
                 * }
                 */
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

    /**
     * calcule combien de Ponts manquent à cette Ile
     * 
     * @return la valeur de l'Ile moins son nombre de connexions
     */
    private int pontRestants() {
        return this.valeur - this.nbConnexions();
    }

    /**
     * recherche le Pont qui relie cette Ile avec son voisin passé en paramètre
     * 
     * @param unVoisin une Ile voisine à cette Ile
     * @return le Pont qui relie ces deux Iles,
     *         ou null s'il n'y en pas, ou si ces Iles ne sont pas voisines
     */
    public Pont getPontEntreIles(Ile unVoisin) {

        for (Pont p : this.listePont) {
            // on parcourt les Ponts reliés à cette Ile
            if ((p.getIle1() == this && p.getIle1() == unVoisin)
                    || (p.getIle2() == this && p.getIle2() == unVoisin)) {
                // si les deux Iles que ce Pont relient sont cette Ile et ce voisin, c'est le
                // Pont qu'on recherche
                return p;
            }
        }

        return null;
    }

    /**
     * compte le nombre maximum de positions de ponts(connexions) possibles qu'a
     * cette Ile actuellement, en respectant la valeur de ses voisins et les Ponts
     * déjà placés
     * 
     * @return nombre maximum de ponts supplémentaires que cette Ile peut recevoir
     */
    public int nbPontsPossibles() {

        int nbPontsPossibles = 0; // nombre de Ponts max qu'on peut placer sur une Ile, en respectant le nombre de
                                  // Ponts restants de ses voisins
        Pont unPont;

        for (Ile unVoisin : this.getVoisins()) {
            // pour chaque voisin
            if (!unVoisin.estComplet()) {
                // si il n'est pas complété

                if ((unPont = getPontEntreIles(unVoisin)) == null) {
                    // s'il n'existe pas de Pont entre cette Ile et ce voisin,
                    if (unVoisin.pontRestants() >= 2) {
                        // et qu'il peut encore recevoir au moins 2 ponts
                        // le nombre de ponts qu'on peut avoir avec ce voisin est donc 2
                        nbPontsPossibles += 2;
                    } else if (unVoisin.pontRestants() == 1) {
                        // et qu'il ne peut plus avoir qu'un pont avec une Ile
                        // le nombre de ponts qu'on peut avoir avec ce voisin est donc 1
                        nbPontsPossibles += 1;
                    }
                } else if (!unPont.estDouble()) {
                    // s'il existe un Pont entre cette Ile et ce voisin
                    // et qu'il n'est pas double
                    // le nombre de ponts supplémentaires qu'on peut avoir avec ce voisin est donc 1
                    nbPontsPossibles += 1;
                }
            }
        }

        return nbPontsPossibles;
    }

}
