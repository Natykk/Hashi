package com.hashi.menu;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.hashi.style.Button;
import com.hashi.style.Panel;

/**
 * La classe TrainingGridSelection représente la page permettant à l'utilisateur
 * de sélectionner un niveau
 * pour l'entraînement.
 */
public class TrainingGridSelection extends Panel {
    private Button retour;
    private Button[][] boutons;
    private int typeTaille;

    /**
     * Constructeur de la classe TrainingGridSelection.
     * 
     * @param numTaille Le numéro de la taille du puzzle sélectionné.
     */
    public TrainingGridSelection(int numTaille) {

        super(new BorderLayout(), "bg-training-grid-selection.png");
        this.typeTaille = numTaille;
        PageManager.getInstance().setTitle("title_training_grid_selection");

        retour = new Button("return").setFontSize(50);
        boutons = new Button[3][6];

        positionnerBoutons3();

        retour.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSizeSelection());
        });
    }

    /**
     * Méthode pour positionner les boutons représentant les niveaux de puzzle.
     */
    private void positionnerBoutons3() {
        Panel boutonsHaut = new Panel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));
        boutonsHaut.add(retour);
        Panel contenu = new Panel(new GridLayout(3, 1));
        contenu.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 120));
        int i = 0;
        // Boucle pour créer les boutons pour chaque niveau de puzzle

        for (i = 0; i < 3; i++) {
            final int row = i;
            Panel panelNiveau = new Panel(new FlowLayout(FlowLayout.CENTER));
            panelNiveau.setBorder(BorderFactory.createEmptyBorder(55, 0, 0, 0));

            for (int j = 0; j < 6; j++) {
                final int column = j;
                boutons[i][j] = new Button(String.valueOf(j + 1)).setAsRawText().setFontSize(60);
                boutons[i][j].setPreferredSize(new Dimension(90, 90));

                boutons[i][j].addActionListener(e -> {

                    PageManager.changerPage(new TrainingLoadGame(this, typeTaille, row, column));
                });
                panelNiveau.add(boutons[i][j]);
            }

            contenu.add(panelNiveau);
        }

        add(contenu, BorderLayout.CENTER);
        add(boutonsHaut, BorderLayout.NORTH);
    }
}