package com.hashi.game.mode;

import java.util.List;

import com.hashi.grid.Grille;
import com.hashi.grid.action.Action;
import com.hashi.style.Label;
import com.hashi.style.Panel;

public class ModeHistoire extends Mode {

    public ModeHistoire(Panel returnPanel, boolean charger) {
        super(returnPanel, charger);
    }

    @Override
    public Grille getGrille() {
        throw new UnsupportedOperationException("Unimplemented method 'getGrille'");
    }

    @Override
    public Panel gameFinishedGetVictoryPanel() {
        throw new UnsupportedOperationException("Unimplemented method 'getVictoryPanel'");
    }

    @Override
    public void startTimer(Label label) {
        throw new UnsupportedOperationException("Unimplemented method 'startTimer'");
    }

    @Override
    public void sauvegarder(List<Action> actions) {
        throw new UnsupportedOperationException("Unimplemented method 'sauvegarder'");
    }

    @Override
    public List<Action> getActions() {
        throw new UnsupportedOperationException("Unimplemented method 'getActions'");
    }

}
