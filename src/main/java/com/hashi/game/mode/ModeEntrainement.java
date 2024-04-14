package com.hashi.game.mode;

import java.util.ArrayList;
import java.util.List;

import com.hashi.grid.action.Action;
import com.hashi.grid.action.PontAction;
import com.hashi.grid.Grille;
import com.hashi.grid.Jeu;
import com.hashi.grid.TimerManager;
import com.hashi.menu.PageManager;
import com.hashi.menu.TrainingVictory;
import com.hashi.style.Label;
import com.hashi.style.Panel;

/**
 * Classe gérant le mode entrainement.
 */
public class ModeEntrainement extends Mode {
    /**
     * La grille actuellement charger.
     */
    protected Grille grille;

    /**
     * La solution associer à la grille.
     */
    protected List<PontAction> solution;

    /**
     * Le numéro de la grille.
     */
    protected int numGrille;

    /**
     * Taille de la grille.
     */
    protected int typeTaille;

    /**
     * Créer une instance du mode entrainement.
     * 
     * @param returnPanel le menu pour le bouton retour.
     * @param typeTaille  taille de la grille.
     * @param row         ligne de la grille au niveau de l'affichage dans le menu.
     * @param column      colonne de la grille au niveau de l'affichage dans le
     *                    menu.
     * @param charger     si l'on doit charger l'ancienne partie.
     */
    public ModeEntrainement(Panel returnPanel, int typeTaille, int row, int column, boolean charger) {
        super(returnPanel, charger);

        String fichierGrille = Mode.getGrilleToPlay(typeTaille, row, column);

        this.grille = Jeu.genererGrilleDepuisFichier(fichierGrille).get(column);
        this.solution = Jeu.genererSolutionDepuisFichier(fichierGrille.replace("G", "SG")).get(column);
        this.numGrille = typeTaille * 18 + row * 6 + column;
        this.typeTaille = typeTaille;
    }

    @Override
    public Grille getGrille() {
        return grille;
    }

    @Override
    public List<PontAction> getSolution() {
        return solution;
    }

    @Override
    public Panel gameFinishedGetVictoryPanel() {
        timer.stopTimer();

        int time = (int) timer.tempsEcoule() / 1000;

        PageManager.getProfil().setScoreEntrainement(numGrille, time);

        return new TrainingVictory(time, typeTaille);
    }

    @Override
    public void startTimer(Label label) {
        timer = new TimerManager(label, PageManager.getProfil().getTempsEntrainement(numGrille), false);
        timer.addActionListener(
                e -> PageManager.getProfil().setTempsEntrainement(numGrille, (int) timer.tempsEcoule() / 1000));
    }

    @Override
    public void sauvegarder(List<Action> actions) {
        PageManager.getProfil().setPartieEntrainement(numGrille, actions);
    }

    @Override
    public List<Action> getActions() {
        if (charger) {
            return PageManager.getProfil().getPartieEntrainement(numGrille);
        }

        PageManager.getProfil().setPartieEntrainement(numGrille, new ArrayList<>());

        return new ArrayList<>();
    }

}
