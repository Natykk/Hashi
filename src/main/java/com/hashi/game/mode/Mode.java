package com.hashi.game.mode;

import java.util.List;

import com.hashi.grid.Jeu;
import com.hashi.grid.action.Action;
import com.hashi.style.Panel;
import com.hashi.grid.Grille;

public abstract class Mode {
    protected Panel returnPanel;
    protected Grille grille;
    protected int numGrille;
    protected boolean charger;

    /**
     * Créer une instance de {@link com.hashi.game.mode.Mode} représentant le mode
     * jeu.
     * 
     * @param returnPanel le menu pour le bouton retour.
     * @param grille     la grille.
     * @param numGrille le numéro de la grille.
     * @param charger     vrai si on doit charger l'ancien partie.
     */
    public Mode(Panel returnPanel, Grille grille, int numGrille, boolean charger) {
        this.returnPanel = returnPanel;
        this.grille = grille;
        this.numGrille = numGrille;
        this.charger = charger;
    }

    /**
     * Récupère la grille.
     * 
     * @return la grille.
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Récupère le menu pour le bouton retour.
     * 
     * @return le menu pour le bouton retour.
     */
    public Panel getReturnPanel() {
        return returnPanel;
    }

    /**
     * Sauvegarde la liste d'action de la partie.
     * 
     * @param actions
     */
    public abstract void sauvegarder(List<Action> actions);

    /**
     * Charge la liste d'action de la précédente partie.
     * 
     * @return la liste d'action.
     */
    public abstract List<Action> getActions();

    /**
     * Définit le score pour la partie.
     * 
     * @param temps permettant de calculer le score.
     */
    public abstract void setScore(int temps);


    public static String getGrilleToPlay(int typeTaille, int row, int column) {
        StringBuilder sb = new StringBuilder();
        switch (typeTaille) {
            case 0:
                sb.append("7x7/");
                break;
            case 1:
                sb.append("10x10/");
                break;
            case 2:
                sb.append("25x25/");
                break;
            default:
                sb.append("7x7/");
                break;
        }

        switch (row) {
            case 0:
                sb.append("Facile/GF.txt");
                break;
            case 1:
                sb.append("Moyen/GM.txt");
                break;
            case 2:
                sb.append("Difficile/GD.txt");
                break;
            default:
                sb.append("Facile/GF.txt");
                break;
        }


        return sb.toString();
    }
}
