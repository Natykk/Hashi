package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;

import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;

// il me reste l'affichage des etoiles 
public class HistoryVictory extends Panel {
    private final String TITLE = "title_victory";
    private Button continuer;
    private Button quitter;
    private int score = 1;

    public HistoryVictory() {
        super(new BorderLayout(), "bg-victory.png");

        PageManager.getInstance().setTitle(TITLE);

        continuer = new Button("continue").setFontSize(43);
        quitter = new Button("quit").setFontSize(50);

        continuer.addActionListener(e -> {
            // grille suivante ( histoire )
        });

        quitter.addActionListener(e -> {
            PageManager.changerPage(new HomeMenu());
        });

        positionnerBoutons();
    }

    private void positionnerBoutons() {
        Panel starsGroup = new Panel();

        for (int i = 0; i < 3; i++) {
            Panel star = new Panel().setImage(score >= (i + 1) ? "star.png" : "empty-star.png");

            star.setPreferredSize(new Dimension(75, 70));
            starsGroup.add(star);
        }

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

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5); // Marge
        return gbc;
    }
}