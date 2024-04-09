package com.hashi.game.mode;

import java.util.List;

import com.hashi.grid.action.Action;
import com.hashi.grid.action.PontAction;
import com.hashi.style.Label;
import com.hashi.style.Panel;
import com.hashi.grid.Grille;
import com.hashi.grid.TimerManager;

public abstract class Mode {
    protected Panel returnPanel;
    protected boolean charger;
    protected TimerManager timer;

    /**
     * Créer une instance de {@link com.hashi.game.mode.Mode} représentant le mode
     * jeu.
     * 
     * @param returnPanel le menu pour le bouton retour.
     * @param charger     vrai si on doit charger l'ancien partie.
     */
    public Mode(Panel returnPanel, boolean charger) {
        this.returnPanel = returnPanel;
        this.charger = charger;
    }

    /**
     * Récupère la grille.
     * 
     * @return la grille.
     */
    public abstract Grille getGrille();

    /**
     * Récupère la solution.
     * 
     * Cette fonction doit toujours être appeler après getGrille()
     * 
     * @return la solution.
     */
    public abstract List<PontAction> getSolution();

    /**
     * Démarre le timer.
     * 
     * @param label étiquette pour l'affichage du temps.
     */
    public abstract void startTimer(Label label);

    /**
     * Récupère le menu de victoire et finie la partie.
     * 
     * @return le menu de victoire.
     */
    public abstract Panel gameFinishedGetVictoryPanel();

    /**
     * Récupère le menu pour le bouton retour.
     * 
     * @return le menu pour le bouton retour.
     */
    public Panel getReturnPanel() {
        if (timer != null)
            timer.stopTimer();

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

    protected static String getGrilleToPlay(int typeTaille, int row, int column) {
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

    /**
     * Fonction appeler quand le bouton aide est cliquer
     */
    public void callAide() {

    }

}
