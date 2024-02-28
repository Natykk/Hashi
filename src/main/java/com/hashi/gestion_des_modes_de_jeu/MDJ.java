package com.hashi.gestion_des_modes_de_jeu;

/**
 * Represente une interface pour les modes de jeu.
 */
public interface MDJ {
    int calculScore();

    void afficherUnePartie();

    void sauvegarde();
}