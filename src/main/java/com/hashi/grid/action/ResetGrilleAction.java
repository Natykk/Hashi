package com.hashi.grid.action;

import java.util.List;

import com.hashi.grid.Grille;
import com.hashi.grid.Pont;

/**
 * Action remettant la grille à zero.
 */
public class ResetGrilleAction implements Action {
    protected List<PontAction> ponts;

    public ResetGrilleAction(List<Pont> ponts) {
        this.ponts = ponts.stream().map(pont -> new PontAction(pont.getIle1(), pont.getIle2(), pont.estDouble()))
                .toList();
    }

    @Override
    public void undo(Grille grille) {
        for (PontAction pont : ponts) {
            grille.ajouterPont(pont.newPont(grille));
        }
    }

    @Override
    public void redo(Grille grille) {
        for (PontAction pont : ponts) {
            grille.retirerPont(pont.getPont(grille));
        }
    }
}