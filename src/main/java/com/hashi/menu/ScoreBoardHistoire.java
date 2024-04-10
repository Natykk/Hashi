package com.hashi.menu;
import com.hashi.Profil;
import com.hashi.style.Panel;
import com.hashi.style.Button;
import com.hashi.style.StyleManager;
import com.hashi.LanguageManager;
import java.awt.*;
import javax.swing.*;

public class ScoreBoardHistoire extends Panel {
    private static final String TITLE = "title";

    public ScoreBoardHistoire() {
        super(new BorderLayout(), "bg-home-menu.png");
        PageManager.getInstance().setTitle(TITLE);

        // Récupération du profil
        Profil profil = PageManager.getProfil();

        JLabel titleLabel = new JLabel("Scores d'Histoire");
        titleLabel.setFont(StyleManager.getInstance().getFont().deriveFont(Font.BOLD, 30));
        titleLabel.setForeground(StyleManager.getInstance().getFgColor());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(200, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH); 
        


        // Création du panneau pour les scores d'histoire
        Panel scoreHistoirePanel = new Panel(new GridBagLayout());
        scoreHistoirePanel.setOpaque(false);

        // Ajout des titres de niveau (Level 1 à Level 12)
        int row = 0;
        int column = 0;
        for (int i = 1; i <= 12; i++) {
            // Création du label pour le niveau
            JLabel levelLabel = new JLabel("Level " + i);
            levelLabel.setFont(StyleManager.getInstance().getFont().deriveFont(Font.BOLD, 25));
            levelLabel.setForeground(StyleManager.getInstance().getFgColor());

            // Création du label pour le score
            String score = profil.getScoreHistoire(i - 1) + " points";
            JLabel scoreLabel = new JLabel(score);
            scoreLabel.setFont(StyleManager.getInstance().getFont().deriveFont(0, 25));
            scoreLabel.setForeground(StyleManager.getInstance().getFgColor());

            // Création des contraintes pour le niveau
            GridBagConstraints gbcLevel = createGbc(column * 2, row);
            gbcLevel.anchor = GridBagConstraints.LINE_START;
            gbcLevel.insets = new Insets(10, 10, 10, 30);

            // Création des contraintes pour le score
            GridBagConstraints gbcScore = createGbc(column * 2 + 1, row);
            gbcScore.anchor = GridBagConstraints.LINE_START;
            gbcScore.insets = new Insets(0, 0, 10, 10); 

            // Ajout des composants au panneau
            scoreHistoirePanel.add(levelLabel, gbcLevel);
            scoreHistoirePanel.add(scoreLabel, gbcScore);

            // Passage à la prochaine colonne après chaque troisième niveau
            if (i % 2 == 0) {
                row++;
                column = 0;
            } else {
                column++;
            }
        }
        add(scoreHistoirePanel, BorderLayout.CENTER);

        Button retourButton = new Button("return").setFontSize(50);
        retourButton.addActionListener(e -> {
            PageManager.changerPage(new ScoreBoard());
        });

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(retourButton);
        add(buttonPanel, BorderLayout.SOUTH);
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
