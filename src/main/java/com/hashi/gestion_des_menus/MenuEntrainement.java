package com.hashi.gestion_des_menus;

import com.hashi.Hashi;
import com.hashi.grille.Grille;
import com.hashi.grille.Jeu;

import javax.swing.*;
import java.awt.*;

public class MenuEntrainement extends JFrame {

    public MenuEntrainement() {
        super("Menu de Sélection de Niveau - Hashi");

        // Bouton Retour en haut à droite
        JButton retourButton = new JButton("Retour");

        // Création des boutons de niveaux
        JButton[][] niveauButtons = new JButton[3][6];
        String[] niveaux = { "Facile", "Moyen", "Difficile" };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                niveauButtons[i][j] = new JButton(niveaux[i] + " " + (j + 1));
            }
        }

        // Création du panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Création du panel pour les boutons de niveaux
        JPanel niveauPanel = new JPanel(new GridLayout(3, 6));

        // Ajout des boutons de niveaux au panel
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                niveauPanel.add(niveauButtons[i][j]);
            }
        }

        // Ajout des panels au panel principal

        mainPanel.add(retourButton, BorderLayout.NORTH);
        mainPanel.add(niveauPanel, BorderLayout.CENTER);

        // Ajout du panel principal à la fenêtre
        add(mainPanel);

        // Configuration de la fenêtre
        setSize(800, 600);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        // si on clique sur le bouton retour, on ferme la fenêtre
        retourButton.addActionListener(e -> dispose());

        // si on clique sur un bouton de niveau, on lance le niveau correspondant
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                int finalI = i;
                int finalJ = j;
                niveauButtons[i][j].addActionListener(e -> {
                    dispose();
                    Jeu jd = new Jeu();
                    jd.genererGrilleDepuisFichier("grille.txt");
                    Grille grille;
                    if (finalI == 0) {
                        grille = jd.listeGrille.get(finalJ);
                    } else {
                        grille = jd.listeGrille.get(finalJ * finalI);
                    }

                    // affiche la grille dans une fenêtre
                    Hashi hashi = new Hashi(grille);
                });
            }
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuEntrainement());
    }
}