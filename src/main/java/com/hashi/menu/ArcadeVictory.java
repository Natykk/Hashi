package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;

import com.hashi.Hashi;
import com.hashi.game.mode.ModeArcade;
import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;

/**
 * La classe `ArcadeVictory` représente un panneau affichant les étoiles
 * obtenues après une victoire.
 * Elle étend la classe `Panel`.
 */
public class ArcadeVictory extends Panel {
    /**
     * Titre du menu.
     */
    private final String TITLE = "title_victory";

    /**
     * Bouton continer.
     */
    private Button continuer;

    /**
     * Bouton retour.
     */
    private Button retour;

    /**
     * Constructeur de la classe `ArcadeVictory`.
     * Initialise le panneau et les boutons.
     */
    public ArcadeVictory() {
        super(new BorderLayout(), "bg-victory.png");

        PageManager.getInstance().setTitle(TITLE);

        continuer = new Button("continue").setFontSize(43);
        retour = new Button("return").setFontSize(50);

        // Affiche le score final de l'utilisateur

        continuer.addActionListener(e -> {
            PageManager.changerPage(new Hashi(new ModeArcade()));
        });

        retour.addActionListener(e -> {
            PageManager.changerPage(new HomeMenu());
        });

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
        groupButton.add(new Label("score").setFontSize(50), gbc);

        gbc.gridx = 1;
        groupButton.add(
                new Label(String.valueOf(PageManager.getProfil().getScoresArcade().get(0))).setAsRawText()
                        .setFontSize(50),
                gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 50, 5, 5);
        groupButton.add(continuer, gbc);
        gbc.gridx = 1;
        groupButton.add(retour, gbc);
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