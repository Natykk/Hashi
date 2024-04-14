package com.hashi.menu;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.hashi.Hashi;
import com.hashi.game.mode.ModeEntrainement;
import com.hashi.style.Button;
import com.hashi.style.Panel;

/**
 * La classe TrainingLoadGame représente la page de chargement du jeu pour
 * l'entraînement.
 */
public class TrainingLoadGame extends Panel {
    /**
     * Titre du menu.
     */
    private final String TITLE = "title_training_load_game";

    /**
     * Bouton retour.
     */
    private Button retour;

    /**
     * Bouton nouvelle partie.
     */
    private Button nouvellePartie;

    /**
     * Bouton charger.
     */
    private Button charger;

    /**
     * Bouton paramètre.
     */
    private Button parametre;

    /**
     * Bouton changer de profil.
     */
    private Button changerProfil;

    /**
     * Bouton règle.
     */
    private Button regles;

    /**
     * Bouton quitter.
     */
    private Button quitter;

    /**
     * Constructeur de la classe TrainingLoadGame.
     * 
     * @param returnPanel le menu de retour.
     * @param typeTaille  le type de taille de grille.
     * @param row         le numéro de la ligne de la grille.
     * @param column      le numéro de la colonne de la grille.
     */
    public TrainingLoadGame(Panel returnPanel, int typeTaille, int row, int column) {
        super(new BorderLayout(), "bg-training-load-game.png");

        PageManager.getInstance().setTitle(TITLE);

        retour = new Button("return").setFontSize(50);
        nouvellePartie = new Button("new_game").setFontSize(40);
        charger = new Button("load_game").setFontSize(40);
        parametre = new Button().setImage("btn-option.png");
        changerProfil = new Button().setImage("btn-switch-profil.png");
        regles = new Button().setImage("btn-rule.png");
        quitter = new Button().setImage("btn-quit.png");

        Dimension size = new Dimension(150, 90);

        parametre.setPreferredSize(size);
        changerProfil.setPreferredSize(size);
        regles.setPreferredSize(size);
        quitter.setPreferredSize(size);

        positionnerBoutons();

        quitter.addActionListener(e -> {
            System.exit(0);
        });
        regles.addActionListener(e -> {
            PageManager.changerPage(new Rule(this, TITLE));
        });
        nouvellePartie.addActionListener(e -> {
            PageManager.changerPage(new Hashi(new ModeEntrainement(returnPanel, typeTaille, row, column, false)));
        });
        charger.addActionListener(e -> {
            PageManager.changerPage(new Hashi(new ModeEntrainement(returnPanel, typeTaille, row, column, true)));
        });
        parametre.addActionListener(e -> {
            PageManager.changerPage(new Parameter(this, TITLE));
        });
        changerProfil.addActionListener(e -> {
            PageManager.changerPage(new StartScreen());
        });
        retour.addActionListener(e -> {
            PageManager.changerPage(returnPanel);
        });
    }

    /**
     * Méthode pour positionner les boutons sur la page.
     */
    private void positionnerBoutons() {

        Panel boutonsHaut = new Panel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));
        boutonsHaut.add(retour);

        Panel boutonsCentre = new Panel(new GridBagLayout());
        boutonsCentre.add(nouvellePartie, createGbc(0, 0));
        boutonsCentre.add(charger, createGbc(0, 1));

        Panel boutonsBas = new Panel(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        boutonsBas.add(parametre, gbc);
        gbc.gridx = 1;
        boutonsBas.add(changerProfil, gbc);
        gbc.gridx = 2;
        boutonsBas.add(regles, gbc);
        gbc.gridx = 3;
        boutonsBas.add(quitter, gbc);

        Panel contenu = new Panel(new BorderLayout());
        contenu.add(boutonsHaut, BorderLayout.NORTH);
        contenu.add(boutonsCentre, BorderLayout.CENTER);
        contenu.add(boutonsBas, BorderLayout.SOUTH);

        add(contenu, BorderLayout.CENTER);
    }

    /**
     * Méthode utilitaire pour créer un GridBagConstraints avec des valeurs
     * prédéfinies.
     * 
     * @param x La position en x.
     * @param y La position en y.
     * @return Le GridBagConstraints créé.
     */
    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }
}
