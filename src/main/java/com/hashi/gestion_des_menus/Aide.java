package com.hashi.gestion_des_menus;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;

import com.hashi.gestion_des_menus.PageManager;
import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;

// je vais la changer en frame
public class Aide extends Panel {
    private static final String TITLE = "title_aide";
    private Button fermer;

    public Aide() {
        super(new BorderLayout(), "bg-aide1.png");
        PageManager.getInstance().setTitle(TITLE);

        fermer = new Button("fermer").setFontSize(50);
        fermer.addActionListener(e -> {
            System.exit(0); 
        });
        Panel contenu = new Panel(new BorderLayout());
        
        // Ajout du texte au milieu de la page
        contenu.add(createMiddleTextPanel(), BorderLayout.CENTER);

        // Ajout des images en bas de la page
        //contenu.add(createBottomImagesPanel(), BorderLayout.SOUTH);

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
