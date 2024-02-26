package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

import com.hashi.style.Panel;

//il me reste de faire fonctionner les boutons 
public class Puzzle extends Panel {

    private JButton retour;
    private JButton[][] boutons;

    public Puzzle(Panel returnPanel, String returnTitle) {
        retour = new JButton("Retour");

        boutons = new JButton[3][6]; // 3 niveaux de puzzle chaque niveau avec 6 boutons

        positionnerBoutons3();

        retour.addActionListener(e -> {
            PageManager.changerPage(returnPanel);
            PageManager.getInstance().setTitle(returnTitle);
        });
    }

    private void positionnerBoutons3() {
        JPanel boutonsHaut = new JPanel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));
        boutonsHaut.add(retour);
        JPanel contenu = new JPanel(new GridLayout(3, 1));
        String[] labels = { "Facile", "Moyen", "Difficile" };

        // Boucle pour créer les boutons pour chaque niveau de puzzle
        for (int i = 0; i < 3; i++) {
            JPanel panelNiveau = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelNiveau.add(new JLabel(labels[i]));

            for (int j = 0; j < 6; j++) {
                boutons[i][j] = new JButton("Bouton " + (j + 1));
                panelNiveau.add(boutons[i][j]);

                contenu.add(panelNiveau);
            }

            // Ajout du contenu à ce panneau Puzzle
            this.setLayout(new BorderLayout());
            this.add(contenu, BorderLayout.CENTER);
            this.add(boutonsHaut, BorderLayout.NORTH);
        }
    }
}