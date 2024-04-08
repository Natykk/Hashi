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
            PageManager.getProfil().setAvancementHistoire(1);

        this.numGrille = PageManager.getProfil().getAvancementHistoire();

        loadNextGrid();
    }

    private void loadNextGrid() {
        String fichierGrille = Mode.getGrilleToPlay(1, this.numGrille / 6, this.numGrille % 6);

        this.grille = Jeu.genererGrilleDepuisFichier(fichierGrille).get(this.numGrille % 6);
        this.solution = Jeu.genererSolutionDepuisFichier(fichierGrille).get(this.numGrille % 6);
    }

    public Panel getNextPanel() {
        if (numGrille == 5 || numGrille == 8)
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

        int time = (int) timer.tempsEcoule() / 1000;
        int score = Score.calculScoreHistoire(numGrille, time);

        PageManager.getProfil().setScoreHistoire(numGrille, score);

        numGrille++;

        PageManager.getProfil().setAvancementHistoire(numGrille);

        loadNextGrid();

        return new HistoryVictory(this, score);
    }

    @Override
    public void startTimer(Label label) {
        timer = new TimerManager(label, PageManager.getProfil().getTempsHistoire(numGrille), false);
        timer.addActionListener(
                e -> PageManager.getProfil().setTempsHistoire(numGrille, (int) timer.tempsEcoule() / 1000));
    }

    @Override
    public void sauvegarder(List<Action> actions) {
        PageManager.getProfil().setPartieHistoire(numGrille, actions);
    }

    @Override
    public List<Action> getActions() {
        if (charger) {
            charger = false;

            return PageManager.getProfil().getPartieHistoire(numGrille);
        }

        PageManager.getProfil().setPartieHistoire(numGrille, new ArrayList<>());

        return new ArrayList<>();
    }
}
