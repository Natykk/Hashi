package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;

// il reste a ajouter le score et peut etre regler l'emplacement du label et boutton
public class Victory extends Panel {
    private final String TITLE = "title_victory";
    private Button retour;
    private String txtTemp;

    public Victory(String temps) {
        super(new BorderLayout(), "bg-victory.png");
        PageManager.getInstance().setTitle(TITLE);
        txtTemp = temps;
        retour = new Button("return").setFontSize(50);

        retour.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSizeSelection());
        });

        positionnerBoutons();
    }

    private void positionnerBoutons() {
        Panel groupButton = new Panel();
        groupButton.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        groupButton.add(new Label("scores").setFontSize(50), gbc);
        gbc.gridx = 1;

        groupButton.add(new Label(txtTemp).setAsRawText().setFontSize(50), gbc);

        gbc.gridy = 1;
        groupButton.add(retour, gbc);

        Panel contenu = new Panel(new BorderLayout());
        contenu.add(groupButton, BorderLayout.CENTER);

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
