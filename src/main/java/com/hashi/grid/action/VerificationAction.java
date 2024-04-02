package com.hashi.grid.action;

import java.util.List;

import com.hashi.grid.Grille;

/**
 * Action supprimant tous les ponts incorrectes.
 */
public class VerificationAction extends ResetGrilleAction {

    public VerificationAction(Grille grille, List<PontAction> solution) {
        super(grille.getPonts().stream().filter(pont -> {
            for (PontAction pont_ : solution) {
                if (pont_.getPont(grille) != null) {
                    return pont_.estDouble != pont.estDouble();
                }
            }

            return true;
        }).toList());
    }

}
