package com.hashi.grid.action;

import com.hashi.grid.Grille;
import com.hashi.grid.Ile;
import com.hashi.grid.Pont;

/**
 * Action ajoutant un pont.
 */
public class AddPontAction implements Action {
    /**
     * Pont a ajouter à la grille.
     */
    protected PontAction pont;

    /**
     * Créer une instance de l'action ajoutant un pont.
     * 
     * @param ile1 l'ile de départ du pont.
     * @param ile2 l'ile de fin du pont.
     */
    public AddPontAction(Ile ile1, Ile ile2) {
        this.pont = new PontAction(ile1, ile2);
    }

    @Override
    public void undo(Grille grille) {
        Pont pont = this.pont.getPont(grille);

        if (pont.estDouble()) {
            pont.setEstDouble(false);
        } else {
            grille.retirerPont(pont);
        }
    }

    @Override
    public void redo(Grille grille) {
        Pont pont = this.pont.getPont(grille);

        if (pont == null) {
            grille.ajouterPont(this.pont.newPont(grille));
        } else {
            pont.setEstDouble(true);
        }
    }
}
