package com.hashi.menu;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.hashi.Hashi;
import com.hashi.style.Button;
import com.hashi.style.Panel;

import com.hashi.grid.Grille;
import com.hashi.grid.Jeu;

/**
 * La classe TrainingLoadGame représente la page de chargement du jeu pour l'entraînement.
 */
public class TrainingLoadGame extends Panel {
    private final String TITLE = "title_training_load_game";
    private Button retour;
    private Button nouvellePartie;
    private Button charger;
    private Button parametre;
    private Button changerProfil;
    private Button regles;
    private Button quitter;

    /**
     * Constructeur de la classe TrainingLoadGame.
     * @param TypeTaille Le type de taille de grille.
     * @param row Le numéro de la ligne de la grille.
     * @param column Le numéro de la colonne de la grille.
     */
    public TrainingLoadGame(int TypeTaille, int row, int column) {
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
            Jeu j = new Jeu();
            /*j.genererGrilleDepuisFichier("grille.txt");
            Grille grille = j.listeGrille.get(column + row);
            PageManager.changerPage(new Hashi(grille));*/

            // Fais 3 switch imbriqués pour déterminer la taille de la grille / la difficulté / le niveau
            // puis appelle la méthode de Hashi pour générer la grille
            // et enfin appelle la méthode de PageManager pour changer de page
            StringBuilder sb = new StringBuilder();
            switch (TypeTaille) {
                case 0:
                    sb.append("7x7/");
                    
                    break;
                case 1:
                    sb.append("10x10/");
                    break;
                case 2:
                    sb.append("25x25/");
                    break;
                default:
                    sb.append("7x7/");
                    break;
            }

            switch (row) {
                case 0:
                    sb.append("Facile/GF.txt");
                    break;
                case 1:
                    sb.append("Moyen/GM.txt");
                    break;
                case 2:
                    sb.append("Difficile/GD.txt");
                    break;
                default:
                    sb.append("Facile/GF.txt");
                    break;
            }

            j.genererGrilleDepuisFichier(sb.toString());

            Grille grille = j.listeGrille.get(column);
            PageManager.changerPage(new Hashi(grille));
            
        });
        charger.addActionListener(e -> {

        });
        parametre.addActionListener(e -> {
            PageManager.changerPage(new Parameter(this, TITLE));
        });
        changerProfil.addActionListener(e -> {
            PageManager.changerPage(new StartScreen());
        });
        retour.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSelection(TypeTaille));
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
     * Méthode utilitaire pour créer un GridBagConstraints avec des valeurs prédéfinies.
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
