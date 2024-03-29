package com.hashi.menu;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.hashi.Hashi;
import com.hashi.game.mode.Mode;
import com.hashi.game.mode.ModeArcade;
import com.hashi.game.mode.ModeEntrainement;
import com.hashi.grid.Grille;
import com.hashi.grid.Jeu;
import com.hashi.style.Button;
import com.hashi.style.Panel;

/**
 * La classe `ArcadeLoadGame` représente un panneau pour afficher les options
 * de chargement de jeu.
 * Elle étend la classe `Panel`.
 */
public class ArcadeLoadGame extends Panel {
    private final String TITLE = "title_training_load_game";
    private Button retour;
    private Button nouvellePartie;
    private Button charger;
    private Button parametre;
    private Button changerProfil;
    private Button regles;
    private Button quitter;

    /**
     * Constructeur de la classe `ArcadeLoadGame`.
     * Initialise le panneau et les boutons.
     */
    public ArcadeLoadGame(Panel returnPanel) {
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

            int typeTaille = (int) (Math.random() * 3);
            int row = (int) (Math.random() * 3);
            int column = (int) (Math.random() * 7);


            Jeu j = new Jeu();

            j.genererGrilleDepuisFichier(Mode.getGrilleToPlay(typeTaille, row, column));

            Grille grille = j.listeGrille.get(column);
            int numGrille = typeTaille * 18 + row * 6 + column;
            Mode.getGrilleToPlay(typeTaille, row, column);
            PageManager.changerPage(new Hashi(new ModeArcade(returnPanel,grille,numGrille,false)));
        });
        charger.addActionListener(e -> {
            // grille sauvegarder
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
