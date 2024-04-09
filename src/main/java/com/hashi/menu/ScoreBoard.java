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
        Label scoreHistoireLabel = new Label("Histoire");
        // Met la police d'écriture Schoolstyle
        scoreHistoireLabel.setFont(new Font("Schoolstyle", Font.PLAIN, 30));
        scoreHistoirePanel.add(scoreHistoireLabel);
        scoreHistoirePanel.add(Box.createVerticalStrut(20));

        // Ajout des scores d'histoire
        for (int i = 0; i < profil.getNbPartieHistoire(); i++) {
            String score = profil.getScoreHistoire(i) + " points";
            Label scoreLabel = new Label(score).setAsRawText();
            scoreLabel.setFont(new Font("Schoolstyle", Font.PLAIN, 20));
            scoreHistoirePanel.add(scoreLabel);
            scoreHistoirePanel.add(Box.createVerticalStrut(10));
        }

        // Création du panneau pour les scores d'arcade
        Panel scoreArcadePanel = new Panel();
        scoreArcadePanel.setLayout(new BoxLayout(scoreArcadePanel, BoxLayout.Y_AXIS));
        scoreArcadePanel.setOpaque(false);

        // Titre des scores d'arcade
        Label scoreArcadeLabel = new Label("Arcade");
        // Met la police d'écriture Schoolstyle
        scoreArcadeLabel.setFont(new Font("Schoolstyle", Font.PLAIN, 30));
        scoreArcadePanel.add(scoreArcadeLabel);
        scoreArcadePanel.add(Box.createVerticalStrut(20));

        // Ajout des scores d'arcade
        for (int i = 0; i < profil.getScoresArcade().size(); i++) {
            Label scoreLabel = new Label("Record "+(i+1)+" : "+profil.getScoresArcade().get(i)).setAsRawText();
            scoreLabel.setFont(new Font("Schoolstyle", Font.PLAIN, 20));
            scoreArcadePanel.add(scoreLabel);
            scoreArcadePanel.add(Box.createVerticalStrut(10));
        }

        // Création du panneau pour les scores
        Panel scorePanel = new Panel(new GridLayout(1,2,50,0));
        scorePanel.setOpaque(false);
        scorePanel.add(scoreHistoirePanel);
        scorePanel.add(scoreArcadePanel);
        add(scorePanel, BorderLayout.CENTER);

        //Création du bouton retour
        Button retourButton = new Button("Retour").setAsRawText();
        retourButton.setPreferredSize(new Dimension(150, 90));
        add(retourButton, BorderLayout.SOUTH);

        // Action du bouton "Retour"
        retourButton.addActionListener(e -> {
            PageManager.changerPage(new HomeMenu());
        });







    }
}