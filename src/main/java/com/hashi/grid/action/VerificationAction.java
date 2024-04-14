package com.hashi.grid.action;

import java.util.List;

import com.hashi.grid.Grille;

/**
 * Action supprimant tous les ponts incorrectes.
 */
public class VerificationAction extends ResetGrilleAction {

    /**
     * Créer une instance de l'action vérifiant la grille.
     * 
     * @param grille   la grille pour la vérification.
     * @param solution la solution contre laquel vérifier.
     */
    public VerificationAction(Grille grille, List<PontAction> solution) {
        super(grille.getPonts().stream().filter(pont -> {
            for (PontAction pont_ : solution) {
                if (pont_.getPont(grille) == pont && pont_.estDouble == pont.estDouble()) {
                    return false;
                }
            }

            return true;
        }).toList());
    }

}
