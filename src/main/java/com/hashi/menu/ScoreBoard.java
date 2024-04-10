package com.hashi.menu;

import com.hashi.style.Panel;
import com.hashi.style.Button;

import java.awt.*;
import javax.swing.*;

public class ScoreBoard extends Panel {
    private static final String TITLE = "title_score";
    private Button scoreArcade;
    private Button scoreHistoire;
    private Button retourButton;

    public ScoreBoard() {
        super(new BorderLayout(), "bg-home-menu.png");

        PageManager.getInstance().setTitle(TITLE);

        retourButton = new Button("return").setFontSize(50);
        retourButton.addActionListener(e -> {
            PageManager.changerPage(new HomeMenu());
        });

        Dimension size = new Dimension(250, 100);

        scoreHistoire = new Button("scoreHistoire").setFontSize(30);
        scoreArcade = new Button("scoreArcade").setFontSize(30);
        scoreHistoire.setPreferredSize(size);
        scoreArcade.setPreferredSize(size);
        positionnerBoutons1();

        scoreHistoire.addActionListener(e -> {
            PageManager.changerPage(new ScoreBoardHistoire());
        });

        scoreArcade.addActionListener(e -> {
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
        contenu.add(buttonPanel, BorderLayout.SOUTH);
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