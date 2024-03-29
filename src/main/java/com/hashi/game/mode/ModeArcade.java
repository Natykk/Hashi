package com.hashi.game.mode;

import java.util.ArrayList;
import java.util.List;

import com.hashi.grid.Jeu;
import com.hashi.grid.action.Action;
import com.hashi.menu.PageManager;
import com.hashi.style.Panel;
import com.hashi.grid.Grille;

public class ModeArcade extends Mode {
    private int[] scores = new int[0];

    public ModeArcade(Panel returnPanel, Grille grille, int numGrille, boolean charger) {
        super(returnPanel, grille, numGrille, charger);
    }

    @Override
    public void sauvegarder(List<Action> actions) {
        PageManager.getProfil().setPartieArcade(numGrille, actions);
    }

    @Override
    public List<Action> getActions() {
        if (charger) {
            return PageManager.getProfil().getPartieArcade(numGrille);
        }

        PageManager.getProfil().setPartieArcade(numGrille, new ArrayList<>());

        return new ArrayList<>();
    }

    @Override
    public void setScore(int temps) {
        PageManager.getProfil().setScoreArcade(0);
    }

}
