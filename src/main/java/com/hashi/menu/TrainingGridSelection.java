package com.hashi.menu;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import java.awt.BorderLayout;

import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;

/**
 * La classe TrainingGridSelection représente la page permettant à l'utilisateur
 * de sélectionner un niveau
 * pour l'entraînement.
 */
public class TrainingGridSelection extends Panel {
    private Button retour;
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
            Panel panelNiveau = new Panel(new GridLayout(2, 6));
            panelNiveau.setBorder(BorderFactory.createEmptyBorder(55, 250, 0, 150));

            for (int j = 0; j < 6; j++) {
                final int column = j;
                Button bouton = new Button(String.valueOf(j + 1)).setAsRawText().setFontSize(45);
                bouton.addActionListener(e -> {
                    PageManager.changerPage(new TrainingLoadGame(this, typeTaille, row, column));
                });
                panelNiveau.add(bouton);
            }

            for (int j = 0; j < 6; j++) {
                int score = PageManager.getProfil().getScoreEntrainement(j + 6 * i + 18 * typeTaille);

                panelNiveau.add(new Label(score > -1 ? String.valueOf(score) : "")
                        .setAsRawText().setFontSize(30));
            }

            contenu.add(panelNiveau);
        }

        add(contenu, BorderLayout.CENTER);
        add(boutonsHaut, BorderLayout.NORTH);
    }
}