package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;

import com.hashi.game.mode.ModeHistoire;
import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;

/**
 * La classe `HistoryVictory` représente un panneau affichant les étoiles
 * obtenues après une victoire.
 * Elle étend la classe `Panel`.
 */
public class HistoryVictory extends Panel {
    /**
     * Titre du menu.
     */
    private final String TITLE = "title_victory";

    /**
     * Bouton continer.
     */
    private Button continuer;

    /**
     * Bouton quitter.
     */
    private Button quitter;

    /**
     * Panneau contenant les étoiles du score.
     */
    private Panel starsGroup;

    /**
     * Constructeur de la classe `HistoryVictory`.
     * Initialise le panneau et les boutons.
     * 
     * @param mode  le mode histoire.
     * @param score le score à afficher.
     */
    public HistoryVictory(ModeHistoire mode, int score) {
        super(new BorderLayout(), "bg-victory.png");

        PageManager.getInstance().setTitle(TITLE);

        continuer = new Button("continue").setFontSize(43);
        quitter = new Button("quit").setFontSize(50);

        continuer.addActionListener(e -> {
            PageManager.changerPage(mode.getNextPanel());
        });

        quitter.addActionListener(e -> {
            PageManager.changerPage(new HomeMenu());
        });

        starsGroup = new Panel();

        for (int i = 0; i < 3; i++) {
            Panel star = new Panel().setImage(score >= (i + 1) ? "star.png" : "empty-star.png");

            star.setPreferredSize(new Dimension(75, 70));
            starsGroup.add(star);
        }

        positionnerBoutons();
    }

    /**
     * Positionne les boutons et les étoiles dans le panneau.
     */
    private void positionnerBoutons() {
        Panel groupButton = new Panel();
        groupButton.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        groupButton.add(new Label("stars").setFontSize(50), gbc);

        gbc.gridx = 1;
        groupButton.add(starsGroup, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 50, 5, 5);
        groupButton.add(continuer, gbc);
        gbc.gridx = 1;
        groupButton.add(quitter, gbc);
        groupButton.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        Panel contenu = new Panel(new BorderLayout());
        contenu.add(groupButton, BorderLayout.CENTER);

        add(contenu, BorderLayout.CENTER);
    }

    /**
     * Crée une contrainte pour le positionnement des composants dans un panneau de
     * type `GridBagLayout`.
     * 
     * @param x Position horizontale du composant dans la grille
     * @param y Position verticale du composant dans la grille
     * @return GridBagConstraints pour le positionnement du composant dans la grille
     */
    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5); // Marge
        return gbc;
    }
}