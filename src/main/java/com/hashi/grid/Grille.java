package com.hashi.grid;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant la grille.
 */
public class Grille extends MouseAdapter {
    /**
     * Coté de la grille.
     */
    private int taille;

    /**
     * La matrice des Case de la grille.
     */
    private Case[][] table;

    /**
     * Le Tableau des Iles de la grille.
     */
    private List<Ile> iles;

    /**
     * Le Tableau des ponts de la grille.
     */
    protected List<Pont> ponts;

    /**
     * Case selectionnée par l'utilisateur.
     */
    private Case selectedCase;

    /**
     * Créer une grille de taille définit.
     * 
     * @param taille la taille.
     */
    public Grille(int taille) {
        this.table = new Case[taille][taille];
        this.taille = taille;
        this.iles = new ArrayList<Ile>();
        this.ponts = new ArrayList<Pont>();

        this.initialiserTable();
    }

    /**
     * Initialiser la matrice Grille.table en le remplissant de Cases vierges.
     */
    public void initialiserTable() {
        for (int i = 0; i < taille; i++) {
            // ligne
            for (int j = 0; j < taille; j++) {
                // colonne
                this.table[i][j] = new Case(i, j, this);

            }
        }
    }

    /**
     * Vérifie si la grille est finie.
     * 
     * @return Retourne si la grille est finie.
     */
    public boolean isGridFinished() {
        for (Ile ile : getIles())
            if (!ile.estComplet())
                return false;

        return true;
    }

    /**
     * Récupère une case.
     * 
     * @param x la position x de la case.
     * @param y la position y de la case.
     * @return retourne la case.
     */
    public Case getCase(int x, int y) {
        return this.table[x][y];
    }

    /**
     * Définit une case.
     * 
     * @param x        la position x de la case.
     * @param y        la position y de la case.
     * @param new_case la case à définir.
     */
    public void setCase(int x, int y, Case new_case) {
        this.table[x][y] = new_case;
    }

    /**
     * ajouter une Ile donnée à la Grille
     * l'Ile est mise dans la matrice à ses propres coordonnées x y
     * l'Ile est aussi ajoutée à la liste d'Iles
     * 
     * @param ile l'Ile à ajouter
     */
    public void ajouterIle(Ile ile) {
        if (this.isInBound(ile.getX(), ile.getY())) {
            this.iles.add(ile);
            this.setCase(ile.getX(), ile.getY(), ile);
        } else {
            System.err.println("Erreur : Les coordonnées de l'Ile à ajouter: (" + ile.getX() + "," + ile.getY()
                    + ") sont en dehors des limites de la grille.\nL'Ile n'a pas été ajoutée à la Grille");
        }
    }

    /**
     * Ajoute un pont dans la matrice de la Grille (sur chaque case) et dans la
     * liste des ponts de cette Grille
     * 
     * @param pont le pont à ajouter
     */
    public void ajouterPont(Pont pont) {

        try {
            // ajouter le pont sur chaque cases de la matrice qui sont entre les deux Iles
            // que ce pont relie

            if (pont.estHorizontal()) {
                // l'axe horizontal du pont
                int y = pont.getIle1().getY();

                if (pont.getIle1().getX() < pont.getIle2().getX()) {
                    // l'Ile 1 est au-dessus de l'Ile 2
                    for (int x = pont.getIle1().getX() + 1; x < pont.getIle2().getX(); x++) {
                        // on parcourt toutes les cases entre ses deux Iles (Iles exclues)
                        // on ajoute le pont dans chaque case de la matrice, entre les deux Iles
                        pont.ajoutCase(this.getCase(x, y));
                        this.setCase(x, y, pont);
                    }

                } else {
                    // l'Ile 1 est en-dessous de l'Ile 2
                    for (int x = (pont.getIle1().getX()) - 1; x > (pont.getIle2().getX()); x--) {
                        // on parcourt toutes les cases entre ses deux Iles (Iles exclues)
                        // on ajoute le pont dans chaque case de la matrice, entre les deux Iles
                        pont.ajoutCase(this.getCase(x, y));
                        this.setCase(x, y, pont);
                    }
                }
            } else {
                // l'axe vertical du pont
                int x = pont.getIle1().getX();

                if (pont.getIle1().getY() < pont.getIle2().getY()) {
                    // l'Ile 1 est à la gauche de l'Ile 2
                    for (int y = pont.getIle1().getY() + 1; y < pont.getIle2().getY(); y++) {
                        // on parcourt toutes les cases entre ses deux Iles (Iles exclues)
                        // on ajoute le pont dans chaque case de la matrice, entre les deux Iles
                        pont.ajoutCase(this.getCase(x, y));
                        this.setCase(x, y, pont);
                    }

                } else {
                    // l'Ile 1 est à la droite de l'Ile 2
                    for (int y = (pont.getIle1().getY()) - 1; y > (pont.getIle2().getY()); y--) {
                        // on parcourt toutes les cases entre ses deux Iles (Iles exclues)
                        // on ajoute le pont dans chaque case de la matrice, entre les deux Iles
                        pont.ajoutCase(this.getCase(x, y));
                        this.setCase(x, y, pont);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(
                    "Erreur : Les 2 Iles que le pont relie ne sont pas alignées horizontalement ni verticalement");
        }

        // ajouter le pont à la liste de ponts
        this.ponts.add(pont);
    }

    /**
     * supprime la présence d'un Pont donné.
     * toutes ses références dans Grille et Ile sont supprimées
     * 
     * @param pont le Pont à supprimer
     */
    public void retirerPont(Pont pont) {
        for (Case c : pont.getListeCase()) {
            // pour toutes les cases où on a mit le pont, dans la matrice
            // on remplace la Case qui contient un Pont par une Case vide
            this.table[c.getX()][c.getY()] = new Case(c.getX(), c.getY(), this);
        }

        // vider la liste de Case du Pont, et le détacher de ses deux Ile
        pont.supprimer();

        // enlever le Pont de la liste des ponts
        this.ponts.remove(pont);
    }

    /**
     * Récupère le nombre de pont de la grille.
     * 
     * @return retourne le nombre de pont de la grille.
     */
    public int getNbPonts() {
        // compte les ponts simple et double
        return this.ponts.size();
    }

    // affichage sur terminal
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.table[j][i].estVide()) {
                    res += "_";
                } else {
                    res += this.table[j][i].toString();
                }

                res += " ";
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Récupère la taille de la grille.
     * 
     * @return retourne la taille de la grille.
     */
    public int getTaille() {
        return this.taille;
    }

    /**
     * Affiche une ile sur terminal.
     * 
     * @return la représentation en chaine de caractère.
     */
    public String afficherIles() {
        String res = "";
        for (Ile ile : this.iles) {
            if (ile != null) {
                res += ile.toString();
            } else {
                res += "*";
            }
        }
        return res;
    }

    /**
     * Affiche un pont sur terminal.
     * 
     * @return la représentation en chaine de caractère.
     */
    public String afficherPonts() {
        String res = "";
        for (Pont pont : this.ponts) {
            res += pont.toString();
        }
        return res;
    }

    /**
     * retourner la liste des Iles de cette Grille
     * 
     * @return la liste des Iles de cette Grille
     */
    public List<Ile> getIles() {
        return this.iles;
    }

    /**
     * retourner la liste des Ponts de cette Grille
     * 
     * @return la liste des Ponts de cette Grille
     */
    public List<Pont> getPonts() {
        return this.ponts;
    }

    /**
     * récupérer une Ile à partir de coordonnées données
     * 
     * @param x coordonnée x sur la matrice
     * @param y coordonnée x sur la matrice
     * @return l'Ile présent aux coordonnées données, ou null s'il n'y en a pas
     */
    public Ile getIleAt(int x, int y) {
        for (Ile ile : this.iles) {
            if (ile.getX() == x && ile.getY() == y) {
                return ile;
            }
        }
        return null;
    }

    /**
     * Récupère la case sélectionner.
     * 
     * @return la case sélectionner.
     */
    public Case getSelectedCase() {
        return this.selectedCase;
    }

    /**
     * Définit la case sélectionner.
     * 
     * @param selectedCase la case à sélectionner.
     */
    public void setSelectedCase(Case selectedCase) {
        this.selectedCase = selectedCase;
    }

    /**
     * retirer une Ile donnée de la liste des Iles de cette Grille
     * 
     * @param ile l'Ile à retirer de la liste
     */
    public void retirerIle(Ile ile) {
        this.iles.remove(ile);
    }

    /**
     * Remplacer la Case aux coordonnées données par une Case vierge
     * 
     * @param x coordonnée x de la matrice
     * @param y coordonnée x de la matrice
     */
    public void retirerCase(int x, int y) {
        this.table[x][y] = new Case(x, y, this);
    }

    /**
     * Récupérer le Pont qui se trouve aux coordonnées données, s'il y en a un
     * 
     * @param xAffichage coordonnée x à l'écran
     * @param yAffichage coordonnée y à l'écran
     * @return le Pont où se situe les coordonnées données, ou null s'il n'y en a
     *         pas
     */
    public Pont getPontAtOnScreen(int xAffichage, int yAffichage) {
        // le rectangle situé à la position x,y correspond à quel pont ?
        for (Pont pont : this.ponts) {

            // si la position de la souris est dans le rectangle du pont
            if (pont.getBounds().contains(xAffichage, yAffichage)) {
                return pont;
            }
        }
        return null;
    }

    /**
     * vérifie si des coordonnées x y sont valides (pas en dehors de la table
     * (matrice))
     * 
     * @param x le numéro de ligne de la matrice
     * @param y le numéro de colonne de la matrice
     * @return vrai si les coordonnées de matrice sont valides, faux sinon
     */
    public boolean isInBound(int x, int y) {
        return 0 <= x && x < taille
                && 0 <= y && y < taille;
    }

    /**
     * regarde les cases sur le même axe cardinal que l'Ile, pour trouver une Ile
     * voisine ou non
     * cette fonction appelle chercherVoisin(Ile,int,int), sans qu'on ait à se
     * soucier de mettre les bonnes valeurs pour les entiers
     * 
     * @param uneIle l'Ile dont on cherche un voisin sur son axe cardinal
     * @param sens   "haut", "bas", "gauche", "droite" un des quatre sens de l'axe
     *               cardinal
     * @return l'Ile voisine à l'Ile passée en paramètre par rapport au sens donné.
     *         ou null s'il n'y a pas d'Ile voisine dans ce sens
     * @throws IllegalArgumentException si sens ne vaut pas "haut", "bas", "gauche"
     *                                  ou "droite"
     */
    public Ile getVoisinSansPont(Ile uneIle, String sens) throws IllegalArgumentException {

        switch (sens.toLowerCase()) {
            case "haut":
                return chercherVoisinSansPont(uneIle, 0, -1);
            case "bas":
                return chercherVoisinSansPont(uneIle, 0, 1);
            case "gauche":
                return chercherVoisinSansPont(uneIle, -1, 0);
            case "droite":
                return chercherVoisinSansPont(uneIle, 1, 0);
            default:
                throw new IllegalArgumentException(
                        "la valeur de sens n'est pas comprise dans \"haut\", \"bas\", \"gauche\", \"droite\"");
        }
    }

    /**
     * regarde les cases sur le même axe cardinal que l'Ile, pour trouver une Ile
     * voisine ou non
     * 
     * @param uneIle l'Ile dont on cherche un voisin sur son axe cardinal
     * @param dx     le déplacement horizontal sur la grille (
     *               -1 : vers la gauche ;
     *               1 : vers la droite ;
     *               0 : aucun déplacement horizontal)
     * @param dy     le déplacement vertical sur la grille (
     *               -1 : vers le haut ;
     *               1 : vers le bas ;
     *               0 : aucun déplacement vertical)
     * @return l'Ile voisine à l'Ile passée en paramètre par rapport au sens donné.
     *         ou null s'il n'y a pas d'Ile voisine dans ce sens
     * @throws UnsupportedOperationException si on sort de la boucle avec un cas
     *                                       logiquement impossible
     */
    private Ile chercherVoisinSansPont(Ile uneIle, int dx, int dy) throws UnsupportedOperationException {
        // récupérer les coordonnées de l'Ile
        int x = uneIle.getX();
        int y = uneIle.getY();

        // décaler d'une case avant de vérifier, pour ne pas se dire que l'Ile dont on
        // démarre est sa propre voisine
        do {
            // avancer d'une case dans le sens donné
            x += dx;
            y += dy;
        } while (isInBound(x, y)
                && this.table[x][y].estVide());

        if (!isInBound(x, y)) {
            // si on sort de la grille, c'est qu'on a rien trouvé, donc il n'y a pas de
            // voisin dans ce sens
            return null;
        }

        if (this.table[x][y].estPont()) {
            // si on trouve un pont, c'est qu'il n'y a pas de voisin dans ce sens
            return null;
        }

        if (this.table[x][y].estIle()) {
            // si on trouve une Ile, c'est que c'est une Ile voisine. on la retourne
            return (Ile) this.table[x][y];
        }

        // logiquement, ça n'arrive jamais ici
        throw new UnsupportedOperationException("sortie de boucle avec un cas non-supporté. logiquement impossible");

    }

    /**
     * interroge toutes les Iles de la grille en appelant leur méthodes de recherche
     * d'aide
     * 
     * @return une liste d'Aide applicable à la grille, dans sa configuraiton
     *         actuelle
     *         si aucune Aide n'a été trouvée, la Liste contient juste Aide.RIEN
     */
    public List<Aide> estCeQueQuelquUnAUneAide() {

        List<Aide> aidesTrouve = new ArrayList<>();

        // techniques de démarrage et techniques basiques
        for (Ile uneIle : this.iles) {

            if (!uneIle.estComplet()) {
                // on ne s'occupe pas des Iles complètes

                // Réeinitialisation de la liste des voisins
                uneIle.resetListeVoisinsConnectes();
                uneIle.resetListeVoisinsPasConnectes();

                // Remplir les listes de voisins une fois, pour ne pas avoir à le recalculer
                uneIle.remplirVoisinsConnectes();
                uneIle.remplirVoisinsPasConnectes();

                try {
                    aidesTrouve.add(uneIle.techniquePontsBloques());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    // System.err.println("Erreur: Attribut -valeur de l'Ile incorrect 1");
                }

                try {
                    aidesTrouve.add(uneIle.techniquePontsForces());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    // System.err.println("Erreur: Attribut -valeur de l'Ile incorrect 2");
                }

                try {
                    aidesTrouve.add(uneIle.techniqueIsolation());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    // System.err.println("Erreur: Attribut -valeur de l'Ile incorrect 3");
                }
            }
        }

        // enlever tous les Aide.RIEN de la List
        aidesTrouve.remove(Aide.RIEN);

        if (aidesTrouve.isEmpty()) {
            // si on a trouvé aucune aide, on renvoit une List qui contient seulement
            // Aide.RIEN
            aidesTrouve.add(Aide.RIEN);
        }

        return aidesTrouve;
    }

    /**
     * Récupère un pont à partir des iles de début et de fin.
     * 
     * @param selectedIle ile de départ.
     * @param clickedIle  ile de fin.
     * @return retourne le pont à cette position.
     */
    public Pont getPont(Ile selectedIle, Ile clickedIle) {
        for (Pont pont : this.ponts) {
            if ((pont.getIle1() == selectedIle && pont.getIle2() == clickedIle)
                    || (pont.getIle1() == clickedIle && pont.getIle2() == selectedIle)) {
                return pont;
            }
        }

        return null;
    }

}
