package com.hashi.menu;

import com.hashi.Profil;
import com.hashi.style.Panel;
import com.hashi.style.Button;
import com.hashi.style.Label;

import java.awt.*;
import javax.swing.*;

public class ScoreBoard extends Panel {
    private static final String TITLE = "title";

    public ScoreBoard() {

        super(new BorderLayout(), "bg-home-menu.png");
        PageManager.getInstance().setTitle(TITLE);

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

        // Création du bouton retour
        Button retourButton = new Button("Retour").setAsRawText();
        retourButton.setPreferredSize(new Dimension(150, 90));
        add(retourButton, BorderLayout.SOUTH);

        // Action du bouton "Retour"
        retourButton.addActionListener(e -> {
            PageManager.changerPage(new HomeMenu());
        });

    }
}