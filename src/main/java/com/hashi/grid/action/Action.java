package com.hashi.grid.action;

import java.io.Serializable;

import com.hashi.grid.Grille;

/**
 * Interface Action permettant l'implementation des méthodes undo (retour en
 * arrière) et redo (retour en avant)
 */
public interface Action extends Serializable {
    void undo(Grille grille);

    void redo(Grille grille);
}