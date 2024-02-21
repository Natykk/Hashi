package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

public class MenuGeneral2 extends JPanel {

    private JButton parametresButton;
    private JButton changerProfilButton;
    private JButton reglesButton;
    private JButton quitterButton;
    private JButton arcadeButton;
    private JButton histoireButton;
    private JButton entrainementButton;

    public MenuGeneral2() {
        // Initialisation du panneau principal
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        // Création des boutons
        parametresButton = new JButton("Paramètres");
        changerProfilButton = new JButton("Changer de profil");
        reglesButton = new JButton("Règles");
        quitterButton = new JButton("Quitter");
        arcadeButton = new JButton("Arcade");
        histoireButton = new JButton("Histoire");
        entrainementButton = new JButton("Entraînement");

        // Définition de la taille des boutons
        parametresButton.setMaximumSize(new Dimension(20, 25));
        changerProfilButton.setMaximumSize(new Dimension(20, 25));
        reglesButton.setMaximumSize(new Dimension(20, 25));
        quitterButton.setMaximumSize(new Dimension(20, 25));
        arcadeButton.setMaximumSize(new Dimension(20, 25));
        histoireButton.setMaximumSize(new Dimension(20, 25));
        entrainementButton.setMaximumSize(new Dimension(20, 25));

        // Ajout des boutons au panneau principal

        // Ajout du groupe de boutons en millieu de la fenêtre contenant les boutons
        // arcade, histoire et entrainement avec les boutons rangé verticalement
        JPanel groupButton = new JPanel();
        groupButton.setLayout(new GridLayout(1, 3, 5, 5));
        groupButton.add(arcadeButton);
        groupButton.add(histoireButton);
        groupButton.add(entrainementButton);
        groupButton.setOpaque(false);
        this.add(groupButton, BorderLayout.CENTER);

        // Ajout du groupe de boutons en bas de la fenêtre contenant les boutons
        // paramètres, changer de profil, règles et quitter
        JPanel groupButton2 = new JPanel();
        groupButton2.setLayout(new GridLayout(1, 4, 5, 5));
        groupButton2.add(parametresButton);
        groupButton2.add(changerProfilButton);
        groupButton2.add(reglesButton);
        groupButton2.add(quitterButton);
        groupButton2.setOpaque(false);
        this.add(groupButton2, BorderLayout.SOUTH);

        // Panneau pour le groupe 1
        JPanel groupPanel1 = new JPanel();
        groupPanel1.setLayout(new BorderLayout());
        groupPanel1.add(groupButton, BorderLayout.CENTER);
        groupPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panneau pour le groupe 2
        JPanel groupPanel2 = new JPanel();
        groupPanel2.setLayout(new BorderLayout());
        groupPanel2.add(groupButton2, BorderLayout.CENTER);
        groupPanel2.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Ajout des panneaux au panneau principal
        this.add(groupPanel1, BorderLayout.CENTER);
        this.add(groupPanel2, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(5, 2));

        // Fermeture de l'application lors de la fermeture de la fenêtre

        // Action du bouton "Quitter"
        quitterButton.addActionListener(e -> {
            System.exit(0);
        });

        // Action du bouton "Paramètres"
        parametresButton.addActionListener(e -> {
            // changement de page -> parametre
            PageManager.changerPage(new Parametre());
        });

        // Action du bouton "Changer de profil"

        changerProfilButton.addActionListener(e -> {
            // changement de page -> changer profil
            PageManager.changerPage(new EcranLancement());
        });

        // Action du bouton "Règles"

        reglesButton.addActionListener(e -> {
            // changement de page -> Aide
            // ...
        });

        // Action du bouton "Arcade"

        arcadeButton.addActionListener(e -> {
            // changement de page -> Mode Arcade
            PageManager.changerPage(new PageMode());
        });

        // Action du bouton "Histoire"

        histoireButton.addActionListener(e -> {
            // Votre code pour le bouton Histoire
            // ...
        });

        // Action du bouton "Entraînement"

        entrainementButton.addActionListener(e -> {
            // Votre code pour le bouton Entraînement
            // ...
        });
    }
}
