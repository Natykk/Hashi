package com.hashi.grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.management.InvalidAttributeValueException;

import com.hashi.style.StyleManager;

/**
 * Classe représentant une ile sur la grille.
 */
public class Ile extends Case {
    /**
     * Le nombre de connexion maximale de l'ile.
     */
    private int valeur;

    /**
     * Liste des ponts liés à l'ile.
     */
    public List<Pont> listePont;

    /**
     * Coordonnée x pour l'affichage de l'Ile.
     */
    private int xAffichage;

    /**
     * Coordonnée y pour l'affichage de l'Ile.
     */
    private int yAffichage;

    /**
     * Taille pour l'affichage.
     */
    private int tailleAffichage;

    /**
     * Liste des Iles voisines connectées par un Pont à cette Ile (rempli et utilisé
     * lors de la demande d'aide).
     */
    private List<Ile> listeVoisinsConnectes;

    /**
     * Liste des Iles voisines pas connectées par un Pont à cette Ile (rempli et
     * utilisé lors de la demande d'aide).
     */
    private List<Ile> listeVoisinsPasConnectes;

    /**
     * Créer une ile sur la grille.
     * 
     * @param valeur   le nombre de connexion maximale de l'ile.
     * @param x        position en x sur la grille.
     * @param y        position en y sur la grille.
     * @param lagrille la grille où ajouter l'ile.
     */
    public Ile(int valeur, int x, int y, Grille lagrille) {
        super(x, y, lagrille);
        this.valeur = valeur;
        this.listePont = new ArrayList<>();
        this.xAffichage = x;
        this.yAffichage = y;
        this.listeVoisinsConnectes = new ArrayList<>();
        this.listeVoisinsPasConnectes = new ArrayList<>();
        grille = lagrille;
    }

    /**
     * Change la taille d'affichage
     * 
     * @param tailleAffichage la taille.
     */
    public void setTailleAffichage(int tailleAffichage) {
        this.tailleAffichage = tailleAffichage;
    }

    /**
     * Récupère la taille d'affichage de l'ile.
     * 
     * @return retourne la taille d'affichage de l'ile.
     */
    private int getTailleAffichage() {
        return (int) (this.tailleAffichage * ((grille.getSelectedCase() == this) ? 1.25 : 1));
    }

    /**
     * Zone cliquable de l'ile.
     * 
     * @return retourne le rectangle de l'ile.
     */
    public Rectangle getBounds() {
        int tailleAffichage = getTailleAffichage();

        return new Rectangle(xAffichage - tailleAffichage / 2, yAffichage - tailleAffichage / 2, tailleAffichage,
                tailleAffichage);
    }

    /**
     * Dessine l'ile à l'écran.
     * 
     * @param g {@link java.awt.Graphics2D} permettant de dessiner l'élément.
     */
    public void draw(Graphics g) {
        int tailleAffichage = getTailleAffichage();

        if (estComplet()) {
            g.setColor(StyleManager.getInstance().getFgColor());
            g.fillOval(xAffichage - tailleAffichage / 2, yAffichage - tailleAffichage / 2, tailleAffichage,
                    tailleAffichage);
        } else {
            g.setColor(StyleManager.getInstance().getBgColor());
            g.fillOval(xAffichage - tailleAffichage / 2, yAffichage - tailleAffichage / 2, tailleAffichage,
                    tailleAffichage);
        }

        g.setColor(StyleManager.getInstance().getFgColor());
        g.drawOval(xAffichage - tailleAffichage / 2, yAffichage - tailleAffichage / 2, tailleAffichage,
                tailleAffichage);

        if (estComplet())
            g.setColor(StyleManager.getInstance().getBgColor());
        else
            g.setColor(StyleManager.getInstance().getFgColor());

        // on ecrie la valeur de l'ile dans le cercle de l'ile au centre
        String text = String.valueOf(valeur);

        g.setFont(StyleManager.getInstance().getFont().deriveFont(0, (int) (tailleAffichage * 0.9)));
        g.drawString(
                text,
                xAffichage - g.getFontMetrics().stringWidth(text) / 2,
                yAffichage - g.getFontMetrics().getHeight() / 2 + g.getFontMetrics().getAscent());
    }

    /**
     * ajoute le pont donné à la liste des ponts de cette Ile.
     * 
     * @param pont le pont à ajouter.
     */
    public void ajouterPont(Pont pont) {
        if (!listePont.contains(pont))
            listePont.add(pont);
    }

    /**
     * Donne la taille en pixel de l'{@link com.hashi.grid.Ile}
     * 
     * @return la taille en pixel.
     */
    public int getTaille() {
        return tailleAffichage;
    }

    /**
     * Récupère le nombre de connexion maximale de l'ile.
     * 
     * @return retourne le nombre de connexion maximale de l'ile.
     */
    public int getValeur() {
        return this.valeur;
    }

    /**
     * Récupère la position en x pour l'affichage.
     * 
     * @return retourne la position.
     */
    public int getXAffichage() {
        return xAffichage;
    }

    /**
     * Définit la position en x pour l'affichage.
     * 
     * @param xAffichage la position en x.
     */
    public void setXAffichage(int xAffichage) {
        this.xAffichage = xAffichage;
    }

    /**
     * Récupère la position en y pour l'affichage.
     * 
     * @return retourne la position.
     */
    public int getYAffichage() {
        return yAffichage;
    }

    /**
     * Définit la position en y pour l'affichage.
     * 
     * @param yAffichage la position en y.
     */
    public void setYAffichage(int yAffichage) {
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
     * retourne le nombre de ponts reliés à cette Ile EN PRENANT EN COMPTE LES PONTS
     * DOUBLES
     * 
     * @return le nombre de ponts en comptant les ponts doubles pour 2 reliés à
     *         cette Ile
     */
    public int getNbConnexion() {
        int sum = 0;

        for (Pont p : listePont) {
            // si le pont ests double, il compte pour 2
            sum += (p.estDouble() ? 2 : 1);
        }

        return sum;
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
     * récupérer la liste des voisins connectés par un Pont à cette Ile
     * 
     * @return une liste d'Iles
     */
    public List<Ile> getListeVoisinsConnectes() {
        return this.listeVoisinsConnectes;
    }

    /**
     * récupérer la liste des voisins pas connectés par un Pont à cette Ile
     * 
     * @return une liste d'Iles
     */
    public List<Ile> getListeVoisinsPasConnectes() {
        return this.listeVoisinsPasConnectes;
    }

    /**
     * vérifier si le nombre de Ponts de cette Ile est égal à sa valeur
     * 
     * @return vrai si le nombre de Ponts de cette Ile est égal à sa valeur, faux
     *         sinon
     */
    public boolean estComplet() {
        return this.valeur == this.getNbConnexion();
    }

    /**
     * Récupère le nombre de pont bas.
     * 
     * @return retourne le nombre de pont bas.
     */
    public int getNbPontBas() {
        int nbPontBas = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle1().equals(this)) {
                nbPontBas++;
            }
        }
        return nbPontBas;
    }

    /**
     * Récupère le nombre de pont haut.
     * 
     * @return retourne le nombre de pont haut.
     */
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
     * retourne vrai si l'Ile a plus d'un pont dans une direction
     * 
     * @return vrai si l'Ile a plus d'un pont dans une direction
     * 
     */
    public boolean estDouble() {
        return this.getNbPontBas() > 1 || this.getNbPontHaut() > 1;
    }

    /**
     * vérifie si le nombre de ponts de l'Ile est inférieur à sa valeur
     * 
     * @return vrai si le nombre de ponts de l'Ile est inférieur à sa valeur, faux
     *         sinon
     */
    public boolean estLibre() {
        return this.getNbConnexion() < valeur;
    }

    /**
     * vérifie si l'Ile est relié à son voisin par un Pont simple
     * 
     * @param voisin l'ile voisine.
     * @return vrai si le Pont entre cette Ile et son voisin est simple,
     *         faux sinon
     */
    public boolean estConnecteParUnPontSimple(Ile voisin) {
        Pont p;
        if ((p = this.getPontEntreIles(voisin)) != null) {
            return !p.estDouble();
        }
        System.err.println("Erreur estConnecteParUnPontSimple(): l'Ile passée en paramètre n'est pas un voisin");
        return false;
    }

    /**
     * vérifie si l'Ile est relié à son voisin par un Pont double
     * 
     * @param voisin l'ile voisine.
     * @return vrai si le Pont entre cette Ile et son voisin est double,
     *         faux sinon
     */
    public boolean estConnecteParUnPontDouble(Ile voisin) {
        Pont p;
        if ((p = this.getPontEntreIles(voisin)) != null) {
            return p.estDouble();
        }
        // cas erreur
        System.err.println("Erreur estConnecteParUnPontDouble(): l'Ile passée en paramètre n'est pas un voisin");
        return false;
    }

    /**
     * remplit la liste des voisins (listeVoisinsConnectes) qui ONT un pont simple
     * ou double de relié avec
     * cette Ile
     * (les Iles sur le même axe cardinal que cette Ile, sans être bloqué par un
     * pont)
     */
    public void remplirVoisinsConnectes() {

        if (!this.listePont.isEmpty()) {

            for (Pont unPont : this.listePont) {
                // pour chaque pont, on cherche l'Ile qui n'est pas uneIle
                if (unPont.getIle1() == this) {
                    // si cette Ile est dans l'attribut -ile1, c'est qu'elle a une voisine dans
                    // -Ile2
                    listeVoisinsConnectes.add(unPont.getIle2());
                } else {
                    // et, logiquement, cette Ile est donc dans l'attribut -ile2, elle a une voisine
                    // dans -Ile1
                    listeVoisinsConnectes.add(unPont.getIle1());
                }
            }
        }

        // enlever les valeurs null, s'il y en a
        while (listeVoisinsConnectes.remove(null))
            ;
    }

    /**
     * remplit la liste des voisins (listeVoisinsPasConnectes) qui n'ont PAS de pont
     * de relié avec cette Ile
     * (les Iles sur le même axe cardinal que cette Ile, sans être bloqué par un
     * pont)
     */
    public void remplirVoisinsPasConnectes() {

        // récupération des Iles voisines dans les quatre sens
        // (la méthode getVoisinSansPont ne permet pas de récupérer les Iles voisines
        // qui
        // sont déjà reliées par un pont)
        listeVoisinsPasConnectes.add(grille.getVoisinSansPont(this, "haut"));
        listeVoisinsPasConnectes.add(grille.getVoisinSansPont(this, "bas"));
        listeVoisinsPasConnectes.add(grille.getVoisinSansPont(this, "gauche"));
        listeVoisinsPasConnectes.add(grille.getVoisinSansPont(this, "droite"));

        // enlever les valeurs null, s'il y en a
        while (listeVoisinsPasConnectes.remove(null))
            ;
    }

    /**
     * récupère toutes Iles voisines, connectées à cette Ile ou non
     * (concatène juste listeVoisinsConnectes avec listeVoisinsPasConnectes)
     * 
     * @return une liste d'Iles qui sont les Iles voisines,
     *         ou une liste vide si l'Ile n'a aucun voisin
     */
    public List<Ile> getVoisins() {
        // java 8
        // return Stream.concat(this.listeVoisinsConnectes.stream(),
        // this.listeVoisinsPasConnectes.stream()).collect(Collectors.toList());

        // java 16+
        return Stream.concat(this.listeVoisinsConnectes.stream(), this.listeVoisinsPasConnectes.stream()).toList();
    }

    /**
     * prend une Liste d'Iles voisines à cette Ile, et en retire toutes les Iles qui
     * ne valident pas la méthode (predicat) passée en paramètre
     * 
     * @param sesVoisins la liste d'Ile qui va se faire appliquer la méthode
     * @param predicat   le filtre
     * @return une liste de voisins, dont certains ont été retirés selon un critère,
     *         ou pas,
     *         ou une liste vide s'ils ont tous été enlévés
     */
    public List<Ile> filtreDeVoisins(List<Ile> sesVoisins, Predicate<? super Ile> predicat) {
        return sesVoisins.stream().filter(predicat).toList();
    }

    /**
     * donne la liste des voisins qui ONT un pont simple ou double de relié avec
     * cette Ile
     * ET qui sont complets
     * 
     * @return la liste des voisins complets connectés à cette Ile,
     *         ou une liste vide si l'Ile n'a aucun voisin complets connectés à elle
     */
    public List<Ile> getVoisinsCompletsConnectes() {
        return filtreDeVoisins(getListeVoisinsConnectes(), e -> e.estComplet());
    }

    /**
     * donne la liste des voisins qui ont un Pont simple de relié avec cette Ile
     * ET qui sont complets
     * car c'est ce qui nous intéresse vraiment
     * 
     * @return la liste des voisins complets connectés à cette Ile par un Pont
     *         simple,
     *         ou une liste vide si l'Ile n'a aucun voisin complets connectés à elle
     *         par un Pont simple
     */
    public List<Ile> getVoisinsCompletsConnectesParUnPontSimple() {
        return filtreDeVoisins(getVoisinsCompletsConnectes(), e -> e.estConnecteParUnPontSimple(this));
    }

    /**
     * donne la liste des voisins qui ont un Pont double de relié avec cette Ile
     * car c'est ce qui nous intéresse vraiment
     * 
     * @return la liste des voisins connectés à cette Ile par un Pont double,
     *         ou une liste vide si l'Ile n'a aucun voisin connectés à elle par un
     *         Pont double
     */
    public List<Ile> getVoisinsConnectesParUnPontDouble() {
        return filtreDeVoisins(getListeVoisinsConnectes(), e -> e.estConnecteParUnPontDouble(this));
    }

    /**
     * donne la liste des voisins qui ONT un pont simple ou double de relié avec
     * cette Ile
     * ET qui sont libres
     * 
     * @return la liste des voisins libres connectés à cette Ile,
     *         ou une liste vide si l'Ile n'a aucun voisin libres connectés à elle
     */
    public List<Ile> getVoisinsLibresConnectes() {
        return filtreDeVoisins(getListeVoisinsConnectes(), e -> e.estLibre());
    }

    /**
     * donne la liste des voisins qui ont un Pont simple de relié avec cette Ile
     * ET qui sont libres
     * 
     * @return la liste des voisins libres connectés à cette Ile par un Pont simple,
     *         ou une liste vide si l'Ile n'a aucun voisin libres connectés à elle
     *         par un Pont simple
     */
    public List<Ile> getVoisinsLibresConnectesParUnPontSimple() {
        return filtreDeVoisins(getVoisinsLibresConnectes(), e -> e.estConnecteParUnPontSimple(this));
    }

    /**
     * donne la liste des voisins qui n'ont PAS de pont de relié avec cette Ile
     * ET qui sont libres (on s'en fiche des Iles complétées pas reliées à nous)
     * 
     * @return la liste des voisins libres qui ne sont pas connectés à cette Ile,
     *         ou une liste vide si l'Ile n'a aucun voisin libres pas connectés à
     *         elle
     */
    public List<Ile> getVoisinsLibresPasConnectes() {
        return filtreDeVoisins(getListeVoisinsPasConnectes(), e -> e.estLibre());
    }

    /**
     * donne les Iles voisines à cette Ile qui n'ont pas encore tous leurs
     * ponts de placés
     * 
     * @return la liste des Iles voisines qui n'ont pas encore tous leurs ponts de
     *         placés,
     *         ou une liste vide si l'Ile n'a aucun voisin libres
     */
    public List<Ile> getVoisinsLibres() {
        return filtreDeVoisins(getVoisins(), e -> e.estLibre());
    }

    /**
     * vérifie les techniques pour quand une Ile a besoin de beaucoup de ponts, mais
     * qu'elle a peu de voisins
     * la technique spécifique sera décidée par l'attribut -valeur de l'Ile dans une
     * autre fonction
     * 
     * @return une aide applicable à la grille dans sa configuraiton actuelle
     * @throws InvalidAttributeValueException si l'attribut -valeur de l'Ile n'est
     *                                        pas compris dans [1,8]
     */
    public Aide techniquePontsForces() throws InvalidAttributeValueException {

        /*
         * la condition "est-ce que l'Ile n'a pas encore tous les ponts est implicite,
         * car cette méthode n'est pas appelée sur des Iles complètes"
         */

        switch (this.valeur) {
            case 1:
                // une Ile qui a besoin d'un pont,
            case 2:
                // ou une Ile qui a besoin de 2 ponts
                // et qui n'a qu'un seul voisin qui doit être libre (s'il n'est pas libre, c'est
                // une erreur du joueur)
                if (this.getVoisinsLibres().size() == 1) {
                    // la même condition peut s'appliquer pour le cas 1 et 2
                    // renvoie FORCE1 si la valeur de l'Ile est 1. respectivement FORCE2 et 2
                    return this.valeur == 1 ? Aide.FORCE1 : Aide.FORCE2;
                }

                break;
            case 3:
                /*
                 * une Ile qui a besoin de 3 ponts,
                 * en a actuellement moins de 2 dans des sens différents
                 * et qui n'a que 2 voisins, qui sont libres
                 */
                if (this.getVoisinsConnectesParUnPontDouble().size() == 0
                        && this.getNbPonts() < 2
                        && ((this.getVoisinsLibresPasConnectes().size()
                                + this.getVoisinsLibresConnectesParUnPontSimple().size()) == 2)) {
                    return Aide.FORCE3;
                }
                break;
            case 4:
                /*
                 * une Ile qui a besoin de 4 ponts,
                 * et qui n'a que 2 voisins
                 * 
                 * le nombre d'objet ponts
                 * (pas présent
                 * + pont simple où on peut en rajouter
                 * + pont double)
                 * est de 2
                 */

                /*
                 * if (this.getVoisinsLibresPasConnectes().size()
                 * + this.getVoisinsConnectesParUnPontDouble().size()
                 * + this.getVoisinsLibresConnectesParUnPontSimple().size() == 2) {
                 * return Aide.FORCE4;
                 * }
                 */

                // autre manière de faire la condition
                if (this.getVoisinsCompletsConnectesParUnPontSimple().size() == 0
                        && (this.getVoisinsLibres().size()
                                + this.getVoisinsCompletsConnectes().size()) == 2) {
                    return Aide.FORCE6;
                }
                break;
            case 5:
                /*
                 * une Ile qui a besoin de 5 ponts,
                 * en a actuellement moins de 3 dans des sens différents
                 * et qui n'a que 3 voisins libres
                 * 
                 * 
                 * si elle a exactement 3 voisins libres
                 * OU
                 * si elle a un voisin complet connecté à elle
                 * et que le pont entre elle et ce voisin est double
                 */
                if (this.getNbPonts() < 3
                        && (((this.getVoisinsLibresPasConnectes().size()
                                + this.getVoisinsLibresConnectesParUnPontSimple().size()) == 3)
                                ||
                                (this.getVoisinsConnectesParUnPontDouble().size() == 1
                                        && ((this.getVoisinsLibresPasConnectes().size()
                                                + this.getVoisinsLibresConnectesParUnPontSimple().size()) == 2)))) {
                    return Aide.FORCE5;
                }
                break;
            case 6:
                /*
                 * une Ile qui a besoin de 6 ponts,
                 * en a actuellement moins de 6
                 * et qui n'a que 3 voisins libres
                 * 
                 * le nombre d'objet ponts
                 * (pas présent
                 * + pont simple où on peut en rajouter
                 * + pont double)
                 * est de 3
                 */

                /*
                 * if (this.getVoisinsLibresPasConnectes().size()
                 * + this.getVoisinsConnectesParUnPontDouble().size()
                 * + this.getVoisinsLibresConnectesParUnPontSimple().size() == 3) {
                 * return Aide.FORCE6;
                 * }
                 */

                // autre manière de faire la condition
                if (this.getVoisinsCompletsConnectesParUnPontSimple().size() == 0
                        && (this.getVoisinsLibres().size()
                                + this.getVoisinsCompletsConnectes().size()) == 3) {
                    return Aide.FORCE6;
                }
                break;
            case 7:
                /*
                 * une Ile qui a besoin de 7 ponts
                 * et en a actuellement moins de 4 dans des sens différents
                 */
                if (this.getNbPonts() < 4) {
                    return Aide.FORCE7;
                }
                break;
            case 8:
                /*
                 * une Ile qui a besoin de 8 ponts
                 * et en a actuellement moins de 8
                 * la condition (this.getNbConnexion() < 8) est implicite
                 */
                return Aide.FORCE8;
            // break;
            default:
                throw new InvalidAttributeValueException("erreur techniquePontsForces():  l'attribut -valeur de " + this
                        + " n'est pas compris dans [1,8]");
        }

        return Aide.RIEN;
    }

    /**
     * vérifie les techniques pour quand une Ile a un voisin qui lui fournit 1/2
     * ponts et qui est complété
     * 
     * @return une aide applicable à la grille dans sa configuraiton actuelle
     * @throws InvalidAttributeValueException si l'attribut -valeur de l'Ile n'est
     *                                        pas compris dans [1,8]
     */
    public Aide techniquePontsBloques() throws InvalidAttributeValueException {

        /*
         * List<Ile> desVoisins;
         * int count=0;
         */

        switch (this.valeur) {
            case 3:
                /*
                 * une Ile qui a besoin de 3 ponts,
                 * en a actuellement moins de 3 (implicite)
                 * et qui n'a plus qu'un voisin libre
                 * 
                 * cette Ile est relié à un voisin complet par un Pont simple
                 * et elle n'a qu'un seul autre voisin, qui n'a pas le choix que d'être libre
                 */
                if ((this.getVoisinsCompletsConnectesParUnPontSimple().size() == 1
                        && this.getVoisinsLibres().size() == 1)
                        ||
                        (this.getVoisinsConnectesParUnPontDouble().size() == 1
                                && this.getVoisinsLibresPasConnectes().size() == 1)) {
                    return Aide.BLOQUE3;
                }
                break;
            case 4:
                /*
                 * une Ile qui a besoin de 4 ponts,
                 * qui est relié à un voisin complet par un Pont simple
                 * et qui a moins de 3 Ponts dans des sens différents (?)
                 * 
                 * pont simple d'un voisin complété: 1
                 * et
                 * pont double
                 * + pont simple d'un voisin pas complété
                 * + pas de pont
                 * = 2
                 */
                if (this.getVoisinsCompletsConnectesParUnPontSimple().size() == 1
                        && ((this.getVoisinsConnectesParUnPontDouble().size()
                                + this.getVoisinsLibresConnectes().size()
                                + this.getVoisinsLibresPasConnectes().size()) == 2)) {
                    return Aide.BLOQUE41;
                }

                /*
                 * une Ile qui a besoin de 4 ponts,
                 * qui est relié à DEUX voisins complet par un Pont simple
                 * et qui a moins de 3 Ponts dans des sens différents (?)
                 * 
                 * pont simple d'un voisin complété: 2
                 * et
                 * pont double
                 * + pont simple d'un voisin pas complété
                 * + pas de pont
                 * = 2
                 */
                if (this.getVoisinsCompletsConnectesParUnPontSimple().size() == 2
                        && ((this.getVoisinsConnectesParUnPontDouble().size()
                                + this.getVoisinsLibresConnectes().size()
                                + this.getVoisinsLibresPasConnectes().size()) == 1)) {
                    return Aide.BLOQUE42;
                }

                // précédents essais:
                /*
                 * // retirer le cas d'une Ile avec 2 voisins
                 * if (this.techniquePontsForces() == Aide.FORCE4) {
                 * break;
                 * }
                 */

                /*
                 * //prend en compte des cas qui ne sont pas BLOQUE41
                 * if (this.getVoisinsLibres().size() == 2
                 * && this.getNbPonts() < 3
                 * && this.nbPontsPossibles() > 2) {
                 * // création d'une Ile qui a comme valeur le nombre de ponts restants à
                 * // connecter à cette Ile-ci
                 * Ile IlePlusFaible = new Ile(this.pontRestants(), this.x, this.y, grille);
                 * 
                 * if (IlePlusFaible.techniquePontsForces() == Aide.FORCE3) {
                 * // si, dans cette situation où l'Ile créée peut appliquer une technique,
                 * // c'est que cette technique peut être appliquée à cette Ile-ci
                 * return Aide.BLOQUE41;
                 * }
                 * }
                 */

                /*
                 * // une Ile qui a besoin de 4 ponts,
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
                 * // une Ile de valeur 4 qui a 1 voisin libre et 1 pont avec 2 voisins qui
                 * sont
                 * // complétés = on peut compléter l'Ile avec 2 ponts sur son 3e et dernier
                 * voisin
                 * if(this.nbVoisinsLibres() == 1
                 * && this.valeur == 2) {
                 * 
                 * return Aide.BLOQUE42;
                 * }
                 */
                break;
            case 5:
                /*
                 * une Ile qui a besoin de 5 ponts,
                 * et qui a, soit:
                 * -un voisin connecté complet par un Pont simple
                 * ou
                 * -deux voisins connectés par un Pont double chacun
                 * ou encore
                 * -deux voisins connectés, l'un complet par un Pont simple, l'autre par un Pont
                 * double
                 */
                if (this.getVoisinsCompletsConnectesParUnPontSimple().size() == 1
                        ||
                        this.getVoisinsConnectesParUnPontDouble().size() == 2) {
                    return Aide.BLOQUE5;
                }

                // précédents essais:
                /*
                 * if( (desVoisins = this.getVoisinsCompletsConnectes()).size() > 0 ) {
                 * // pour chaque voisin complet connecté
                 * for (Ile chaqueVoisin : desVoisins) {
                 * 
                 * if (chaqueVoisin.getPontEntreIles(this).getNbPont() == 1) {
                 * // si y'en a un avec un Pont simple
                 * return Aide.BLOQUE5;
                 * }
                 * }
                 * }
                 * 
                 * // faire la même chose pour les voisins connectés, pas nécessairement complet
                 * // car une Ile peut avoir un pont double, et ne pas encore être complet
                 * if ((desVoisins = this.getVoisinsConnectes()).size() > 0) {
                 * // pour chaque voisin connecté
                 * for (Ile chaqueVoisin : desVoisins) {
                 * 
                 * if (chaqueVoisin.getPontEntreIles(this).getNbPont() == 2) {
                 * // compter les Ponts doubles
                 * count += 1;
                 * }
                 * }
                 * 
                 * if (count == 2) {
                 * // si y'a 2 Ponts doubles
                 * return Aide.BLOQUE5;
                 * }
                 * }
                 */
                break;
            case 6:
                /*
                 * une Ile qui a besoin de 6 Ponts,
                 * qui a 4 voisins
                 * et qui a un voisin de valeur 1
                 * on met un pont entre chaque voisin qui n'est pas celle de 1
                 * 
                 */
                if (this.getVoisinsCompletsConnectesParUnPontSimple().size() == 1
                        && ((this.getVoisinsConnectesParUnPontDouble().size()
                                + this.getVoisinsLibresConnectes().size()
                                + this.getVoisinsLibresPasConnectes().size()) == 3)) {
                    return Aide.BLOQUE6;
                }
                break;
            case 7:
                /*
                 * une Ile qui a besoin de 7 ponts,
                 * et qui a un voisin connecté complet par un Pont simple
                 */
                if (this.getVoisinsCompletsConnectesParUnPontSimple().size() == 1) {
                    return Aide.BLOQUE7;
                }
                break;

            case 1:
            case 2:
            case 8:
                // cas non-utilisé
                // il n'y a pas de techniques où un pont est bloqué pour les Iles avec ces
                // valeurs
                break;

            default:
                throw new InvalidAttributeValueException("erreur techniquePontsBloques(): l'attribut -valeur de " + this
                        + " n'est pas compris dans [1,8]");
        }

        return Aide.RIEN;
    }

    /**
     * vérifie les techniques d'isolation, pour quand une Ile de valeur 1 a une
     * autre Ile de valeur 1 comme voisin
     * idem pour les Iles de valeur 2
     * 
     * @return une aide applicable à la grille dans sa configuraiton actuelle
     * @throws InvalidAttributeValueException si l'attribut -valeur de l'Ile n'est
     *                                        pas compris dans [1,8]
     */
    public Aide techniqueIsolation() throws InvalidAttributeValueException {
        List<Ile> sesVoisins;

        switch (this.valeur) {
            case 1:
                // comme c'est une Ile de valeur 1, elle a forcément aucun pont
                // une Ile de valeur 1,
                // qui a deux voisins libres
                if ((sesVoisins = getVoisinsLibresPasConnectes()).size() == 2) {
                    // pour ses deux Iles voisines
                    for (Ile v : sesVoisins) {
                        if (v.getValeur() == 1) {
                            // si une des deux a une valeur de 1,
                            // on est sûr qu'on peut mettre un pont avec son autre voisin libre
                            return Aide.ISOLE1;
                        }
                    }
                }
                break;

            case 2:
                // une Ile de valeur 2,
                // qui a deux voisins libres
                if ((sesVoisins = getVoisinsLibres()).size() == 2) {

                    if (sesVoisins.get(0).getValeur() == 2
                            && sesVoisins.get(1).getValeur() == 2) {
                        // si ses DEUX voisins sont des Iles de valeur 2
                        return Aide.ISOLE22;
                    } else if ((sesVoisins.get(0).getValeur() == 2
                            && getVoisinsLibresConnectes().contains(sesVoisins.get(1))) ||
                            (sesVoisins.get(1).getValeur() == 2
                                    && getVoisinsLibresConnectes().contains(sesVoisins.get(0)))) {
                        // si la première Ile a une valeur de 2,
                        // ET que la 2e Ile EST connecté à cette Ile (fait partie de
                        // getVoisinsLibresConnectes() )
                        // (et inversement, 2e Ile, première Ile)
                        // alors Aide.ISOLE2 est déjà appliqué
                        break;
                        // il faut faire cette vérification avant la 3e
                    } else if (sesVoisins.get(0).getValeur() == 2
                            || sesVoisins.get(1).getValeur() == 2) {
                        // si un deux deux voisins a une valeur de 2,
                        // on est sûr qu'on peut mettre au moins un pont avec son autre voisin libre
                        return Aide.ISOLE2;
                    }
                }
                break;

            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                // cas non-implémentés
                break;

            default:
                throw new InvalidAttributeValueException("erreur techniqueIsolation():    l'attribut -valeur de " + this
                        + " n'est pas compris dans [1,8]");
        }

        return Aide.RIEN;
    }

    /**
     * calcule combien de Ponts manquent à cette Ile
     * 
     * @return la valeur de l'Ile moins son nombre de connexions
     */
    private int pontRestants() {
        return this.valeur - this.getNbConnexion();
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
            if ((p.getIle1() == this && p.getIle2() == unVoisin)
                    || (p.getIle2() == this && p.getIle1() == unVoisin)) {
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

    /**
     * Remet à zéro la liste des voisins connecté de l'ile.
     */
    public void resetListeVoisinsConnectes() {
        this.listeVoisinsConnectes.clear();
    }

    /**
     * Remet à zéro la liste des voisins non connecté de l'ile.
     */
    public void resetListeVoisinsPasConnectes() {
        this.listeVoisinsPasConnectes.clear();
    }

    // affichage sur terminal
    @Override
    public String toString() {
        return "Ile<value: " + valeur + ", x: " + getX() + ", y: " + getY() + ">";
    }
}
