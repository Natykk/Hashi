package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;

import com.hashi.Hashi;
import com.hashi.game.mode.ModeArcade;
import com.hashi.style.Button;
import com.hashi.style.Panel;

/**
 * La classe `HomeMenu` représente le menu principal de l'application.
 * Elle étend la classe `Panel`.
 */
public class HomeMenu extends Panel {
    private static final String TITLE = "title";
    private Button parametresButton;
    private Button changerProfilButton;
    private Button reglesButton;
    private Button quitterButton;
    private Button arcadeButton;
    private Button histoireButton;
    private Button entrainementButton;
    private Button scoreboardButton;

    /**
     * Constructeur de la classe `HomeMenu`.
     * Initialise le menu principal et les boutons.
     */
    public HomeMenu() {
        super(new BorderLayout(), "bg-home-menu.png");

        PageManager.getInstance().setTitle(TITLE);

        // Création des boutons
        parametresButton = new Button().setImage("btn-option.png");
        changerProfilButton = new Button().setImage("btn-switch-profil.png");
        reglesButton = new Button().setImage("btn-rule.png");
        quitterButton = new Button().setImage("btn-quit.png");
        arcadeButton = new Button("arcade").setFontSize(30);
        histoireButton = new Button("history").setFontSize(30);
        entrainementButton = new Button("training").setFontSize(30);
        scoreboardButton = new Button("scoreBoard").setFontSize(20);

        Dimension size = new Dimension(150, 90);

        parametresButton.setPreferredSize(size);
        changerProfilButton.setPreferredSize(size);
        reglesButton.setPreferredSize(size);
        quitterButton.setPreferredSize(size);

        size = new Dimension(260, 100);

        arcadeButton.setPreferredSize(size);
        histoireButton.setPreferredSize(size);
        entrainementButton.setPreferredSize(size);

        size = new Dimension(150, 60);

        scoreboardButton.setPreferredSize(size);

        positionnerBoutons1();

        // Action du bouton "Quitter"
        quitterButton.addActionListener(e -> {
            System.exit(0);
        });

        // Action du bouton "Paramètres"
        parametresButton.addActionListener(e -> {
            // changement de page -> parametre
            PageManager.changerPage(new Parameter(this, TITLE));
        });

        // Action du bouton "Changer de profil"

        changerProfilButton.addActionListener(e -> {
            // changement de page -> changer profil
            PageManager.changerPage(new StartScreen());
        });

        // Action du bouton "Règles"

        reglesButton.addActionListener(e -> {
            // changement de page -> Aide
            PageManager.changerPage(new Rule(this, TITLE));
        });

        // Action du bouton "Arcade"

        arcadeButton.addActionListener(e -> {
            // changement de page -> Mode Arcade
            PageManager.changerPage(new Hashi(new ModeArcade()));
        });

        // Action du bouton "Histoire"

        histoireButton.addActionListener(e -> {
            // Votre code pour le bouton Histoire
            PageManager.changerPage(new HistoryLoadGame());
        });

        // Action du bouton "Entraînement"

        entrainementButton.addActionListener(e -> {
            // Votre code pour le bouton Entraînement
            PageManager.changerPage(new TrainingGridSizeSelection());
        });

        // Action du bouton "Scoreboard"
        scoreboardButton.addActionListener(e -> {
            // Votre code pour le bouton Scoreboard
            PageManager.changerPage(new ScoreBoard());
        });

    }

    /**
     * Positionne les boutons dans le menu.
     */
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
        gbc.gridx = 1;
        gbc.gridy = 1;
        groupButton.add(scoreboardButton, gbc);
        groupButton.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));

        // Ajout du groupe de boutons en bas de la fenêtre contenant les boutons
        // paramètres, changer de profil, règles et quitter

        Panel groupButton2 = new Panel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
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

    /**
     * Crée une contrainte pour le positionnement des composants dans un panneau de
     * type `GridBagLayout`.
     * 
     * @param x Position horizontale du composant dans la grille
     * @param y Position verticale du composant dans la grille
     * @return GridBagConstraints pour le positionnement du composant dans la grille
     */
    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5); // Marge
        return gbc;
    }
}