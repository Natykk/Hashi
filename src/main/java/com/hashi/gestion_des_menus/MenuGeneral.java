package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

public class MenuGeneral extends JPanel {

    private JButton parametresButton;
    private JButton changerProfilButton;
    private JButton reglesButton;
    private JButton quitterButton;
    private JButton arcadeButton;
    private JButton histoireButton;
    private JButton entrainementButton;

    public MenuGeneral() {
        PageManager.getInstance().setTitle("Hashi");

        // Création des boutons
        parametresButton = new JButton("Paramètres");
        changerProfilButton = new JButton("Changer de profil");
        reglesButton = new JButton("Règles");
        quitterButton = new JButton("Quitter");
        arcadeButton = new JButton("Arcade");
        histoireButton = new JButton("Histoire");
        entrainementButton = new JButton("Entraînement");

        positionnerBoutons1();

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
            PageManager.changerPage(new Regle());
        });

        // Action du bouton "Arcade"

        arcadeButton.addActionListener(e -> {
            // changement de page -> Mode Arcade
        });

        // Action du bouton "Histoire"

        histoireButton.addActionListener(e -> {
            // Votre code pour le bouton Histoire
            PageManager.changerPage(new PageMode());
        });

        // Action du bouton "Entraînement"

        entrainementButton.addActionListener(e -> {
            // Votre code pour le bouton Entraînement
            // ...
        });
    }

    private void positionnerBoutons1() {

        JPanel groupButton = new JPanel();
        groupButton.setLayout(new GridBagLayout()); // Utilisation de GridBagLayout
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        groupButton.add(arcadeButton, gbc);
        gbc.gridx = 1;
        groupButton.add(histoireButton, gbc);
        gbc.gridx = 2;
        groupButton.add(entrainementButton, gbc);

        // Ajout du groupe de boutons en bas de la fenêtre contenant les boutons
        // paramètres, changer de profil, règles et quitter
        JPanel groupButton2 = new JPanel();
        groupButton2.setLayout(new GridLayout(1, 4, 5, 5));
        groupButton2.add(parametresButton);
        groupButton2.add(changerProfilButton);
        groupButton2.add(reglesButton);
        groupButton2.add(quitterButton);

        JPanel contenu = new JPanel(new BorderLayout());
        contenu.add(groupButton, BorderLayout.CENTER);
        contenu.add(groupButton2, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(contenu, BorderLayout.CENTER);

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
