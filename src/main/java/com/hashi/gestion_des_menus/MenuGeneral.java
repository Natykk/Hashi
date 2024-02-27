package com.hashi.gestion_des_menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;

import com.hashi.style.Button;
import com.hashi.style.Panel;

public class MenuGeneral extends Panel {
    private static final String TITLE = "title";
    private Button parametresButton;
    private Button changerProfilButton;
    private Button reglesButton;
    private Button quitterButton;
    private Button arcadeButton;
    private Button histoireButton;
    private Button entrainementButton;

    public MenuGeneral() {
        super(new BorderLayout(), "bg-principal.png");

        PageManager.getInstance().setTitle(TITLE);

        // Création des boutons
        parametresButton = new Button().setImage("btn-option.png");
        changerProfilButton = new Button().setImage("btn-switch-profil.png");
        reglesButton = new Button().setImage("btn-aide.png");
        quitterButton = new Button().setImage("btn-quitter.png");
        arcadeButton = new Button("arcade").setFontSize(35);
        histoireButton = new Button("history").setFontSize(35);
        entrainementButton = new Button("training").setFontSize(35);

        Dimension size = new Dimension(150, 90);

        parametresButton.setPreferredSize(size);
        changerProfilButton.setPreferredSize(size);
        reglesButton.setPreferredSize(size);
        quitterButton.setPreferredSize(size);

        size = new Dimension(250, 100);

        arcadeButton.setPreferredSize(size);
        histoireButton.setPreferredSize(size);
        entrainementButton.setPreferredSize(size);

        positionnerBoutons1();

        // Action du bouton "Quitter"
        quitterButton.addActionListener(e -> {
            System.exit(0);
        });

        // Action du bouton "Paramètres"
        parametresButton.addActionListener(e -> {
            // changement de page -> parametre
            PageManager.changerPage(new Parametre(this, TITLE));
        });

        // Action du bouton "Changer de profil"

        changerProfilButton.addActionListener(e -> {
            // changement de page -> changer profil
            PageManager.changerPage(new EcranLancement());
        });

        // Action du bouton "Règles"

        reglesButton.addActionListener(e -> {
            // changement de page -> Aide
            PageManager.changerPage(new Regle(this, TITLE));
        });

        // Action du bouton "Arcade"

        arcadeButton.addActionListener(e -> {
            // changement de page -> Mode Arcade
        });

        // Action du bouton "Histoire"

        histoireButton.addActionListener(e -> {
            // Votre code pour le bouton Histoire
            PageManager.changerPage(new PageMode(this, TITLE));
        });

        // Action du bouton "Entraînement"

        entrainementButton.addActionListener(e -> {
            // Votre code pour le bouton Entraînement
            PageManager.changerPage(new MenuEntrainement());
        });
    }

    private void positionnerBoutons1() {
        Panel groupButton = new Panel();
        groupButton.setLayout(new GridBagLayout()); // Utilisation de GridBagLayout
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        groupButton.add(arcadeButton, gbc);
        gbc.gridx = 1;
        groupButton.add(histoireButton, gbc);
        gbc.gridx = 2;
        groupButton.add(entrainementButton, gbc);
        groupButton.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        // Ajout du groupe de boutons en bas de la fenêtre contenant les boutons
        // paramètres, changer de profil, règles et quitter

        Panel groupButton2 = new Panel(new GridBagLayout());
        gbc.gridx = 0;
        groupButton2.add(parametresButton, gbc);
        gbc.gridx = 1;
        groupButton2.add(changerProfilButton, gbc);
        gbc.gridx = 2;
        groupButton2.add(reglesButton, gbc);
        gbc.gridx = 3;
        groupButton2.add(quitterButton, gbc);

        Panel contenu = new Panel(new BorderLayout());
        contenu.add(groupButton, BorderLayout.CENTER);
        contenu.add(groupButton2, BorderLayout.SOUTH);

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
