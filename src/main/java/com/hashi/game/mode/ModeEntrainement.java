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

public class ModeEntrainement extends Mode {
    protected Grille grille;
    protected List<PontAction> solution;
    protected int numGrille;
    protected int typeTaille;

    public ModeEntrainement(Panel returnPanel, int typeTaille, int row, int column, boolean charger) {
        super(returnPanel, charger);

        String fichierGrille = Mode.getGrilleToPlay(typeTaille, row, column);

        this.grille = Jeu.genererGrilleDepuisFichier(fichierGrille).get(column);
        this.solution = Jeu.genererSolutionDepuisFichier(fichierGrille.replace(".txt", "_soluce.txt")).get(column);
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

    @Override
    public void callAide() {
    }

}
