package com.hashi.menu;

import com.hashi.Profil;
import com.hashi.style.Panel;
import com.hashi.style.Button;
import com.hashi.style.Label;

import java.awt.*;
import javax.swing.*;

public class ScoreBoard extends Panel {
    private static final String TITLE = "title";
    private Button scoreArcade;
    private Button scoreHistoire;
    private Button retourButton;
    public ScoreBoard() {

        super(new BorderLayout(), "bg-home-menu.png");

        
        retourButton = new Button("return").setFontSize(50);

        // Récupération du profil
        Profil profil = PageManager.getProfil();

        // Création du panneau pour les scores d'histoire
        Panel scoreHistoirePanel = new Panel();
        scoreHistoirePanel.setLayout(new BoxLayout(scoreHistoirePanel, BoxLayout.Y_AXIS));
        scoreHistoirePanel.setOpaque(false);

        // Titre des scores d'histoire
        Label scoreHistoireLabel = new Label("Histoire").setAsRawText();
        scoreHistoirePanel.add(scoreHistoireLabel);
        scoreHistoirePanel.add(Box.createVerticalStrut(20));

        // Ajout des scores d'histoire
        boolean histoireScoreExist = false;
        for (int i = 0; i < profil.getNbPartieHistoire(); i++) {
            int scoreValue = profil.getScoreHistoire(i);
            if (scoreValue > 0) {
                histoireScoreExist = true;
                String score = "Chapitre " + (i + 1) + " : " + scoreValue + " points";
                Label scoreLabel = new Label(score).setAsRawText();
                scoreHistoirePanel.add(scoreLabel);
                scoreHistoirePanel.add(Box.createVerticalStrut(10));
            }
        }
        if (!histoireScoreExist) {
            Label noHistoryScoreLabel = new Label("Aucun score enregistre").setAsRawText();
            scoreHistoirePanel.add(noHistoryScoreLabel);
            scoreHistoirePanel.add(Box.createVerticalStrut(10));
        }

        // Création du panneau pour les scores d'arcade
        Panel scoreArcadePanel = new Panel();
        scoreArcadePanel.setLayout(new BoxLayout(scoreArcadePanel, BoxLayout.Y_AXIS));
        scoreArcadePanel.setOpaque(false);

        // Titre des scores d'arcade
        Label scoreArcadeLabel = new Label("Arcade").setAsRawText();
        scoreArcadePanel.add(scoreArcadeLabel);
        scoreArcadePanel.add(Box.createVerticalStrut(20));

        // Ajout des scores d'arcade
        boolean arcadeScoreExist = false;
        for (int i = 0; i < profil.getScoresArcade().size(); i++) {
            int scoreValue = profil.getScoresArcade().get(i);
            if (scoreValue > 0) {
                arcadeScoreExist = true;
                Label scoreLabel = new Label("Record " + (i + 1) + " : " + scoreValue).setAsRawText();
                scoreArcadePanel.add(scoreLabel);
                scoreArcadePanel.add(Box.createVerticalStrut(10));
            }
        }
        if (!arcadeScoreExist) {
            Label noArcadeScoreLabel = new Label("Aucun score enregistre").setAsRawText();
            scoreArcadePanel.add(noArcadeScoreLabel);
            scoreArcadePanel.add(Box.createVerticalStrut(10));
        }

        // Création du panneau principal pour les scores
        Panel scoresContainer = new Panel(new GridBagLayout());
        scoresContainer.setOpaque(false);

        // Ajout du panneau pour les scores d'histoire
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Permet au panneau de remplir l'espace horizontal
        gbc.anchor = GridBagConstraints.CENTER;
        scoresContainer.add(scoreHistoirePanel, gbc);

        // Ajout du panneau pour les scores d'arcade
        gbc.gridx = 1;
        scoresContainer.add(scoreArcadePanel, gbc);

        // Ajout du panneau principal des scores au centre de ScoreBoard
        add(scoresContainer, BorderLayout.CENTER);
        // Action du bouton "Retour"

        retourButton.addActionListener(e -> {
            PageManager.changerPage(new HomeMenu());
        });


        Dimension size = new Dimension(300, 100);

        scoreHistoire = new Button("scoreHistoire").setFontSize(30);
        scoreArcade = new Button("scoreArcade").setFontSize(30);
        scoreHistoire.setPreferredSize(size);
        scoreArcade.setPreferredSize(size);
        positionnerBoutons1();

        scoreHistoire.addActionListener(e->{
            PageManager.changerPage(new ScoreBoardHistoire());
        });

        scoreArcade.addActionListener(e->{
            PageManager.changerPage(new ScoreBoardArcade());
        });
       
        




    }
    private void positionnerBoutons1() {
        Panel groupButton = new Panel();
        groupButton.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        groupButton.add(scoreHistoire, gbc);
        gbc.gridx = 1;
        groupButton.add(scoreArcade, gbc);
        gbc.gridx = 2;
        groupButton.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(retourButton);

        Panel contenu = new Panel(new BorderLayout());
        contenu.add(groupButton, BorderLayout.CENTER);
        contenu.add(buttonPanel,BorderLayout.SOUTH);
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