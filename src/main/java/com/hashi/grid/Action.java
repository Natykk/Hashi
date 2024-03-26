package com.hashi.grid;

/**
 * Interface Action permettant l'implementation des méthodes undo (retour en arrière) et redo (retour en avant)
 */
public interface Action {
    void undo();

    void redo();
}