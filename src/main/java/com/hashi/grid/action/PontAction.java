package com.hashi.grid.action;

import java.io.Serializable;

import com.hashi.grid.Grille;
import com.hashi.grid.Ile;
import com.hashi.grid.Pont;

/**
 * Classe permettant la serialization des ponts, pour la sauvegarde des actions.
 */
public class PontAction implements Serializable {
    /**
     * Abscisse de l'ile de départ.
     */
    public int ile1X;

    /**
     * Ordonnée de l'ile de départ.
     */
    public int ile1Y;

    /**
     * Abscisse de l'ile de fin.
     */
    public int ile2X;

    /**
     * Ordonnée de l'ile de fin.
     */
    public int ile2Y;

    /**
     * Le pont est double ou simple ?
     */
    public boolean estDouble;

    /**
     * Créer un pont sérializable simple ou double à partir de coordonnées.
     * 
     * @param ile1X     abscisse de l'ile de départ.
     * @param ile1Y     ordonnée de l'ile de départ.
     * @param ile2X     abscisse de l'ile de fin.
     * @param ile2Y     ordonnée de l'ile de fin.
     * @param estDouble si le pont est double ou simple.
     */
    public PontAction(int ile1X, int ile1Y, int ile2X, int ile2Y, boolean estDouble) {
        this.ile1X = ile1X;
        this.ile1Y = ile1Y;
        this.ile2X = ile2X;
        this.ile2Y = ile2Y;
        this.estDouble = estDouble;
    }

    /**
     * Créer un pont sérializable simple ou double.
     * 
     * @param ile1      l'ile de départ.
     * @param ile2      l'ile de fin.
     * @param estDouble si le pont est double ou simple.
     */
    public PontAction(Ile ile1, Ile ile2, boolean estDouble) {
        this.ile1X = ile1.getX();
        this.ile1Y = ile1.getY();
        this.ile2X = ile2.getX();
        this.ile2Y = ile2.getY();
        this.estDouble = estDouble;
    }

    /**
     * Créer un pont sérializable simple.
     * 
     * @param ile1 l'ile de départ.
     * @param ile2 l'ile de fin.
     */
    public PontAction(Ile ile1, Ile ile2) {
        this(ile1, ile2, false);
    }

    /**
     * Récupère le pont correspondant sur la grille.
     * 
     * @param grille la grille où récupérer le pont.
     * @return retourne le pont récupérer.
     */
    public Pont getPont(Grille grille) {
        Ile ile1 = grille.getIleAt(ile1X, ile1Y);
        Ile ile2 = grille.getIleAt(ile2X, ile2Y);

        return grille.getPont(ile1, ile2);
    }

    /**
     * Génère un pont à partir des données stocker.
     * 
     * @param grille la grille permettant de récupérer les iles de départ et de fin
     *               du pont.
     * @return retourne le nouveau pont.
     */
    public Pont newPont(Grille grille) {
        Ile ile1 = grille.getIleAt(ile1X, ile1Y);
        Ile ile2 = grille.getIleAt(ile2X, ile2Y);
        Pont pont = new Pont(ile1, ile2, grille);

        pont.setEstDouble(estDouble);

        return pont;
    }
}
