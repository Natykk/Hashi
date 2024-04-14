package com.hashi.menu;

import com.hashi.Profil;
import com.hashi.style.Panel;
import com.hashi.style.Button;
import com.hashi.style.StyleManager;
import java.awt.*;
import javax.swing.*;

/**
 * Menu d'affichage des scores en mode Histoire.
 */
public class ScoreBoardHistoire extends Panel {
    private static final String TITLE = "title_score_history";

    /**
     * Créer une instance du menu.
     */
    public ScoreBoardHistoire() {
        super(new BorderLayout(), "bg-start-screen.png");
        PageManager.getInstance().setTitle(TITLE);

        // Récupération du profil
        Profil profil = PageManager.getProfil();

        // Création du panneau pour les scores d'histoire
        Panel scoreHistoirePanel = new Panel(new GridBagLayout());
        scoreHistoirePanel.setBorder(BorderFactory.createEmptyBorder(250, 0, 10, 0));

        // Ajout des titres de niveau (Level 1 à Level 12)
        int row = 0;
        int column = 0;
        for (int i = 1; i <= 12; i++) {
            // Création du label pour le niveau
            JLabel levelLabel = new JLabel("Level " + i + " :");
            levelLabel.setFont(StyleManager.getInstance().getFont().deriveFont(Font.BOLD, 25));
            levelLabel.setForeground(StyleManager.getInstance().getFgColor());

            // Création du label pour le score
            JLabel scoreLabel = new JLabel(String.valueOf(profil.getScoreHistoire(i - 1)));
            scoreLabel.setFont(StyleManager.getInstance().getFont().deriveFont(0, 25));
            scoreLabel.setForeground(StyleManager.getInstance().getFgColor());

            // Création des contraintes pour le niveau
            GridBagConstraints gbcLevel = createGbc(column * 2, row);
            gbcLevel.anchor = GridBagConstraints.LINE_START;
            gbcLevel.insets = new Insets(10, 10, 10, 10);

            // Création des contraintes pour le score
            GridBagConstraints gbcScore = createGbc(column * 2 + 1, row);
            gbcScore.anchor = GridBagConstraints.LINE_START;
            gbcScore.insets = new Insets(0, 0, 10, 50);

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
