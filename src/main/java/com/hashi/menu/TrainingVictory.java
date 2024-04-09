package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;

/**
 * La classe Victory représente la page affichée lorsqu'une victoire est
 * atteinte dans le jeu.
 */
public class TrainingVictory extends Panel {
    private final String TITLE = "title_victory";
    private Button retour;
    private String txtTemp;

    /**
     * Constructeur de la classe Victory.
     * 
     * @param temps Le temps écoulé pour la victoire.
     */
    public TrainingVictory(int temps, int numTaille) {
        super(new BorderLayout(), "bg-victory.png");
        PageManager.getInstance().setTitle(TITLE);
        txtTemp = String.valueOf(temps);
        retour = new Button("return").setFontSize(50);

        retour.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSelection(numTaille));
        });

        positionnerBoutons();
    }

    /**
     * Méthode pour positionner les boutons sur la page.
     */
    private void positionnerBoutons() {
        Panel groupButton = new Panel();
        groupButton.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        groupButton.add(new Label("score").setFontSize(50), gbc);
        gbc.gridx = 1;

        groupButton.add(new Label(txtTemp).setAsRawText().setFontSize(50), gbc);

        gbc.gridy = 1;
        groupButton.add(retour, gbc);

        Panel contenu = new Panel(new BorderLayout());
        contenu.add(groupButton, BorderLayout.CENTER);

        add(contenu, BorderLayout.CENTER);
    }

    /**
     * Méthode utilitaire pour créer un GridBagConstraints avec des valeurs
     * prédéfinies.
     * 
     * @param x La position en x.
     * @param y La position en y.
     * @return Le GridBagConstraints créé.
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
