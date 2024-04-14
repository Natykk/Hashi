package com.hashi.grid.action;

import com.hashi.grid.Grille;
import com.hashi.grid.Pont;

/**
 * Action supprimant un pont.
 */
public class RemovePontAction implements Action {
    /**
     * Pont à supprimer.
     */
    protected PontAction pont;

    /**
     * Créer une instance de l'action supprimant un pont.
     * 
     * @param pont le pont à supprimer.
     */
    public RemovePontAction(Pont pont) {
        this.pont = new PontAction(pont.getIle1(), pont.getIle2(), pont.estDouble());
    }

    @Override
    public void undo(Grille grille) {
        grille.ajouterPont(this.pont.newPont(grille));
    }

    @Override
    public void redo(Grille grille) {
        grille.retirerPont(this.pont.getPont(grille));
    }
}
