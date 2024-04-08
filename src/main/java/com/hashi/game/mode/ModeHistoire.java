package com.hashi.game.mode;

import java.util.ArrayList;
import java.util.List;

import com.hashi.Hashi;
import com.hashi.game.Score;
import com.hashi.grid.Grille;
import com.hashi.grid.Jeu;
import com.hashi.grid.TimerManager;
import com.hashi.grid.action.Action;
import com.hashi.grid.action.PontAction;
import com.hashi.menu.Chapitre;
import com.hashi.menu.HistoryVictory;
import com.hashi.menu.PageManager;
import com.hashi.style.Label;
import com.hashi.style.Panel;

public class ModeHistoire extends Mode {
    protected Grille grille;
    protected List<PontAction> solution;
    protected int numGrille;

    public ModeHistoire(Panel returnPanel, boolean charger) {
        super(returnPanel, charger);

        if (!charger)
            PageManager.getProfil().resetHistoire();

        this.numGrille = PageManager.getProfil().getAvancementHistoire();

        loadNextGrid();
    }

    private void loadNextGrid() {
        int tempNumGrille = numGrille - 1;
        String fichierGrille = Mode.getGrilleToPlay(1, tempNumGrille / 6, tempNumGrille % 6);

        grille = Jeu.genererGrilleDepuisFichier(fichierGrille).get(tempNumGrille % 6);
        solution = Jeu.genererSolutionDepuisFichier(fichierGrille).get(tempNumGrille % 6);
    }

    public Panel getNextPanel() {
        if (numGrille == 5 || numGrille == 8) // les fois on joue 2 parties de suite
            return new Hashi(this);

        return new Chapitre(this, numGrille);
    }

    @Override
    public Grille getGrille() {
        return this.grille;
    }

    @Override
    public List<PontAction> getSolution() {
        return this.solution;
    }

    @Override
    public Panel gameFinishedGetVictoryPanel() {
        timer.stopTimer();

        int tempNumGrille = numGrille - 1;
        int time = (int) timer.tempsEcoule() / 1000;
        int score = Score.calculScoreHistoire(tempNumGrille, time);

        PageManager.getProfil().setScoreHistoire(tempNumGrille, score);

        numGrille++;

        PageManager.getProfil().setAvancementHistoire(numGrille);

        loadNextGrid();

        return new HistoryVictory(this, score);
    }

    @Override
    public void startTimer(Label label) {
        timer = new TimerManager(label, PageManager.getProfil().getTempsHistoire(numGrille - 1), false);
        timer.addActionListener(
                e -> PageManager.getProfil().setTempsHistoire(numGrille - 1, (int) timer.tempsEcoule() / 1000));
    }

    @Override
    public void sauvegarder(List<Action> actions) {
        PageManager.getProfil().setPartieHistoire(numGrille - 1, actions);
    }

    @Override
    public List<Action> getActions() {
        if (charger) {
            charger = false;

            return PageManager.getProfil().getPartieHistoire(numGrille - 1);
        }

        PageManager.getProfil().setPartieHistoire(numGrille - 1, new ArrayList<>());

        return new ArrayList<>();
    }
}
