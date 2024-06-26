package com.hashi.menu;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.hashi.game.mode.ModeHistoire;
import com.hashi.style.Button;
import com.hashi.style.Panel;

/**
 * La classe `HistoryLoadGame` représente un panneau pour afficher les options
 * de chargement de jeu.
 * Elle étend la classe `Panel`.
 */
public class HistoryLoadGame extends Panel {
    /**
     * Titre du menu.
     */
    private final String TITLE = "title_history_load_game";

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
     * Constructeur de la classe `HistoryLoadGame`.
     * Initialise le panneau et les boutons.
     */
    public HistoryLoadGame() {
        super(new BorderLayout(), "bg-history-load-game.png");

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
            PageManager.changerPage(new ModeHistoire(new HomeMenu(), false).getNextPanel());
        });
        charger.addActionListener(e -> {
            PageManager.changerPage(new ModeHistoire(new HomeMenu(), true).getNextPanel());
        });
        parametre.addActionListener(e -> {
            PageManager.changerPage(new Parameter(this, TITLE));
        });
        changerProfil.addActionListener(e -> {
            PageManager.changerPage(new StartScreen());
        });
        retour.addActionListener(e -> {
            PageManager.changerPage(new HomeMenu());
        });
    }

    /**
     * Positionne les boutons dans le panneau.
     */
    private void positionnerBoutons() {

        Panel boutonsHaut = new Panel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alignement à droite
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
