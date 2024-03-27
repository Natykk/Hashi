package com.hashi.game.mode;

import java.util.List;

import com.hashi.grid.action.Action;
import com.hashi.grid.Grille;

public class ModeHistoire extends Mode {

    public ModeHistoire(Grille grille, int numGrille, boolean charger) {
        super(grille, numGrille, charger);
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
