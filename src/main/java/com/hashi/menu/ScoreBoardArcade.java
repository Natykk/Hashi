package com.hashi.menu;

import com.hashi.Profil;
import com.hashi.style.Panel;
import com.hashi.style.Button;
import com.hashi.style.StyleManager;

import java.awt.*;
import javax.swing.*;

public class ScoreBoardArcade extends Panel {
    private static final String TITLE = "title_score_arcade";

    public ScoreBoardArcade() {
        super(new BorderLayout(), "bg-start-screen.png");
        PageManager.getInstance().setTitle(TITLE);

        // Récupération du profil
        Profil profil = PageManager.getProfil();

        // Création du panneau pour les scores d'arcade
        Panel scoreArcadePanel = new Panel();
        scoreArcadePanel.setLayout(new BoxLayout(scoreArcadePanel, BoxLayout.Y_AXIS));
        scoreArcadePanel.setBorder(BorderFactory.createEmptyBorder(310, 0, 10, 0));

        // Ajout des scores d'arcade
        for (int i = 0; i < profil.getScoresArcade().size(); i++) {
            JLabel scoreLabel = new JLabel("Record " + (i + 1) + " : " + profil.getScoresArcade().get(i));
            scoreLabel.setFont(StyleManager.getInstance().getFont().deriveFont(0, 30));
            scoreLabel.setForeground(StyleManager.getInstance().getFgColor());
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer horizontalement
            scoreArcadePanel.add(scoreLabel);
            scoreArcadePanel.add(Box.createVerticalStrut(10));
        }

        // Ajout du panneau de scores au centre
        add(scoreArcadePanel, BorderLayout.CENTER);

        // Ajout du bouton de retour au centre
        Button retourButton = new Button("return").setFontSize(50);
        retourButton.addActionListener(e -> {
            PageManager.changerPage(new ScoreBoard());
        });

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(retourButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
