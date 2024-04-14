package com.hashi.grid.action;

import java.io.Serializable;

import com.hashi.grid.Grille;

/**
 * Interface Action permettant l'implementation des méthodes undo (retour en
 * arrière) et redo (retour en avant)
 */
public interface Action extends Serializable {
    /**
     * Cette fonction retoure en arrière sur l'action et reviens à l'état précédent
     * de la grille.
     * 
     * @param grille la grille sur laquel appliquer les modifications.
     */
    void undo(Grille grille);

    /**
     * Cette fonction rétablit l'action et reviens à l'état courant de la grille.
     * 
     * @param grille la grille sur laquel appliquer les modifications.
     */
    void redo(Grille grille);
}