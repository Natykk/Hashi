package com.hashi.grille;

import javax.swing.*;
import java.awt.*;

public class MenuGeneral extends JFrame {

    private JPanel mainPanel;
    private JLabel logoLabel;
    private JButton parametresButton;
    private JButton changerProfilButton;
    private JButton reglesButton;
    private JButton quitterButton;
    private JButton arcadeButton;
    private JButton histoireButton;
    private JButton entrainementButton;

    public MenuGeneral() {
        // Définition du titre de la fenêtre
        setTitle("Menu de Sélection");

        // Initialisation du panneau principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        // Ajout du logo
        logoLabel = new JLabel(new ImageIcon("logo.png"));
        // Resize the image to fit the window
        Image img = new ImageIcon("logo.png").getImage();
        Image img2 = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(img2);
        logoLabel.setIcon(logo);

        mainPanel.add(logoLabel, BorderLayout.NORTH);

        // Création des boutons
        parametresButton = new JButton("Paramètres");
        changerProfilButton = new JButton("Changer de profil");
        reglesButton = new JButton("Règles");
        quitterButton = new JButton("Quitter");
        arcadeButton = new JButton("Arcade");
        histoireButton = new JButton("Histoire");
        entrainementButton = new JButton("Entraînement");

        // Définition de la taille des boutons
        parametresButton.setMaximumSize(new Dimension(50, 25));
        changerProfilButton.setMaximumSize(new Dimension(50, 25));
        reglesButton.setMaximumSize(new Dimension(50, 25));
        quitterButton.setMaximumSize(new Dimension(50, 25));
        arcadeButton.setMaximumSize(new Dimension(50, 25));
        histoireButton.setMaximumSize(new Dimension(50, 25));
        entrainementButton.setMaximumSize(new Dimension(50, 25));

        // Ajout d'une image d'arrière-plan a la fenêtre
        ImageIcon bg = new ImageIcon("background.png");
        JLabel background = new JLabel(bg);
        mainPanel.add(background);

        // Ajout des boutons au panneau principal

        // Ajout du groupe de boutons en millieu de la fenêtre contenant les boutons
        // arcade, histoire et entrainement avec les boutons rangé verticalement
        JPanel groupButton = new JPanel();
        groupButton.setLayout(new GridLayout(1, 3, 10, 20));
        groupButton.add(arcadeButton);
        groupButton.add(histoireButton);
        groupButton.add(entrainementButton);
        groupButton.setOpaque(false);
        mainPanel.add(groupButton, BorderLayout.CENTER);

        // Ajout du groupe de boutons en bas de la fenêtre contenant les boutons
        // paramètres, changer de profil, règles et quitter
        JPanel groupButton2 = new JPanel();
        groupButton2.setLayout(new GridLayout(1, 4, 10, 20));
        groupButton2.add(parametresButton);
        groupButton2.add(changerProfilButton);
        groupButton2.add(reglesButton);
        groupButton2.add(quitterButton);
        groupButton2.setOpaque(false);
        // mainPanel.add(groupButton2, BorderLayout.SOUTH);

        // Ajout du panneau principal à la fenêtre
        setContentPane(mainPanel);

        // Rafraîchissement de la fenêtre
        mainPanel.revalidate();
        mainPanel.repaint();

        // Définition de la taille de la fenêtre
        setSize(600, 400);

        // Affichage de la fenêtre
        setVisible(true);

        // Panneau pour le groupe 1
        JPanel groupPanel1 = new JPanel();
        groupPanel1.setLayout(new BorderLayout());
        groupPanel1.add(groupButton, BorderLayout.CENTER);
        groupPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panneau pour le groupe 2
        JPanel groupPanel2 = new JPanel();
        groupPanel2.setLayout(new BorderLayout());
        groupPanel2.add(groupButton2, BorderLayout.CENTER);
        groupPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ajout des panneaux au panneau principal
        mainPanel.add(groupPanel1, BorderLayout.CENTER);
        mainPanel.add(groupPanel2, BorderLayout.SOUTH);

        mainPanel.setPreferredSize(new Dimension(600, 400));

        // Fermeture de l'application lors de la fermeture de la fenêtre
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Action du bouton "Quitter"
        quitterButton.addActionListener(e -> {
            System.exit(0);
        });

        // Action du bouton "Paramètres"
        parametresButton.addActionListener(e -> {
            // Votre code pour le bouton Paramètres
            // ...
        });

        // Action du bouton "Changer de profil"

        changerProfilButton.addActionListener(e -> {
            // Votre code pour le bouton Changer de profil
            // ...
        });

        // Action du bouton "Règles"

        reglesButton.addActionListener(e -> {
            // Votre code pour le bouton Règles
            // ...
        });

        // Action du bouton "Arcade"

        arcadeButton.addActionListener(e -> {
            // Votre code pour le bouton Arcade
            // ...
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
