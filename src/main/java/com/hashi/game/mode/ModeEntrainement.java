package com.hashi.game.mode;

import java.util.ArrayList;
import java.util.List;

import com.hashi.grid.action.Action;
import com.hashi.grid.Jeu;
import com.hashi.menu.PageManager;
import com.hashi.style.Panel;

public class ModeEntrainement extends Mode {

    public ModeEntrainement(Panel returnPanel, int typeTaille, int row, int column, boolean charger) {
        super(returnPanel, null, 0, charger);

        Jeu j = new Jeu();

        j.genererGrilleDepuisFichier(Mode.getGrilleToPlay(typeTaille, row, column));

        grille = j.listeGrille.get(column);
        this.numGrille = typeTaille * 18 + row * 6 + column;
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
    public void setScore(int temps) {
        PageManager.getProfil().setScoreEntrainement(numGrille, temps);
    }

}
