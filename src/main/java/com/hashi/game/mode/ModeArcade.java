package com.hashi.game.mode;

import java.util.List;

import com.hashi.grid.action.Action;
import com.hashi.style.Panel;
import com.hashi.grid.Grille;

public class ModeArcade extends Mode {

    public ModeArcade(Panel returnPanel, Grille grille, int numGrille, boolean charger) {
        super(returnPanel, grille, numGrille, charger);
    }

    @Override
    public void sauvegarder(List<Action> actions) {
        throw new UnsupportedOperationException("Unimplemented method 'sauvegarder'");
    }

    @Override
    public List<Action> getActions() {
        throw new UnsupportedOperationException("Unimplemented method 'charger'");
    }

    @Override
    public void setScore(int temps) {
        throw new UnsupportedOperationException("Unimplemented method 'setScore'");
    }

}
