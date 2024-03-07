package com.hashi.menu;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.hashi.style.Button;
import com.hashi.style.Panel;

public class TrainingGridSelection extends Panel {
    private Button retour;
    private Button[][] boutons;

    public TrainingGridSelection() {
        super(new BorderLayout(), "bg-training-grid-selection.png");

        PageManager.getInstance().setTitle("title_training_grid_selection");

        retour = new Button("return").setFontSize(50);
        boutons = new Button[3][6];

        positionnerBoutons3();

        retour.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSizeSelection());
        });
    }

    private void positionnerBoutons3() {
        Panel boutonsHaut = new Panel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));
        boutonsHaut.add(retour);
        Panel contenu = new Panel(new GridLayout(3, 1));
        contenu.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 120));

        // Boucle pour créer les boutons pour chaque niveau de puzzle
        for (int i = 0; i < 3; i++) {
            Panel panelNiveau = new Panel(new FlowLayout(FlowLayout.CENTER));
            panelNiveau.setBorder(BorderFactory.createEmptyBorder(55, 0, 0, 0));

            for (int j = 0; j < 6; j++) {
                boutons[i][j] = new Button(String.valueOf(j + 1)).setAsRawText().setFontSize(70);
                boutons[i][j].setPreferredSize(new Dimension(90, 90));
                boutons[i][j].addActionListener(e -> {
                    PageManager.changerPage(new TrainingLoadGame());
                });
                panelNiveau.add(boutons[i][j]);
            }

            contenu.add(panelNiveau);
        }

        add(contenu, BorderLayout.CENTER);
        add(boutonsHaut, BorderLayout.NORTH);
    }
}