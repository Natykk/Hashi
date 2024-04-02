package com.hashi.game.mode;

import java.util.ArrayList;
import java.util.List;

import com.hashi.Profil;
import com.hashi.grid.Grille;
import com.hashi.grid.Jeu;
import com.hashi.grid.action.Action;
import com.hashi.grid.action.PontAction;
import com.hashi.menu.HistoryVictory;
import com.hashi.menu.PageManager;
import com.hashi.menu.TrainingVictory;
import com.hashi.style.Label;
import com.hashi.style.Panel;

public class ModeHistoire extends Mode {
    protected Grille grille;
    protected List<PontAction> solution;
    protected int numGrille;
    protected int typeTaille;

    public ModeHistoire(Panel returnPanel, boolean charger) {
        super(returnPanel, charger);

        Profil profil = PageManager.getProfil();
        int num = profil.getAvancementHistoire();
        String fichierGrille = Mode.getGrilleToPlay(1, 2,num);

        this.grille = Jeu.genererGrilleDepuisFichier(fichierGrille).get(num);
        this.solution = Jeu.genererSolutionDepuisFichier(fichierGrille.replace("G", "SG")).get(num);
        this.numGrille = 18 + num * 6 + num;
        this.typeTaille = 1;
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
        return new HistoryVictory();
    }

    @Override
    public void startTimer(Label label) {
        throw new UnsupportedOperationException("Unimplemented method 'startTimer'");
    }

    @Override
    public void sauvegarder(List<Action> actions) {
        PageManager.getProfil().setPartieHistoire(numGrille, actions);
    }

    @Override
    public List<Action> getActions() {
        if (charger) {
            return PageManager.getProfil().getPartieHistoire(numGrille);
        }

        PageManager.getProfil().setPartieHistoire(numGrille, new ArrayList<>());

        return new ArrayList<>();
    }

    @Override
    public void callAide() {
    }

}
