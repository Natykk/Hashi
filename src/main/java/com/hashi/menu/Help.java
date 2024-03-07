package com.hashi.menu;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;

public class Help extends Panel {
    private static final String TITLE = "title_help";
    private Button fermer;

    public Help() {
        super(new BorderLayout(), "bg-help.png");
        PageManager.getInstance().setTitle(TITLE);

        fermer = new Button("close").setFontSize(50);
        fermer.addActionListener(e -> {
            System.exit(0);
        });
        Panel contenu = new Panel(new BorderLayout());

        // Ajout du texte au milieu de la page
        contenu.add(createMiddleTextPanel(), BorderLayout.CENTER);

        // Ajout des images en bas de la page
        // contenu.add(createBottomImagesPanel(), BorderLayout.SOUTH);

        add(contenu, BorderLayout.CENTER);

        Panel boutonsHaut = new Panel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));
        boutonsHaut.add(fermer);

        add(boutonsHaut, BorderLayout.NORTH);
    }

    private Panel createMiddleTextPanel() {
        Panel middleTextPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        middleTextPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 20, 0));

        // Ajout du texte au milieu de la page
        Label middleTextLabel = new Label("aide");
        middleTextLabel.setFontSize(30);
        middleTextPanel.add(middleTextLabel);

        return middleTextPanel;
    }
}
