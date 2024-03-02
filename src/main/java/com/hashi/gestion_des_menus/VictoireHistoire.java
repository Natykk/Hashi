package com.hashi.gestion_des_menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.hashi.gestion_des_menus.MenuEntrainement;
import com.hashi.gestion_des_menus.MenuGeneral;
import com.hashi.gestion_des_menus.PageManager;
import com.hashi.style.Button;
import com.hashi.style.Label;
import com.hashi.style.Panel;


// il me reste l'affichage des etoiles 
public class VictoireHistoire extends Panel {
    private final String TITLE = "title_victoire";
    private Button continuer;
    private Button quitter;

    public VictoireHistoire(){
        super(new BorderLayout(), "bg-victoire.png");

        PageManager.getInstance().setTitle(TITLE);

        continuer = new Button("continuer").setFontSize(43);
        quitter = new Button("quitter").setFontSize(50);
 
        continuer.addActionListener(e -> {
            // grille suivante ( histoire )
        });

        quitter.addActionListener(e -> {
            PageManager.changerPage(new MenuGeneral());
        });

        positionnerBoutons(); 
    }

    private void positionnerBoutons() {
        Panel groupButton = new Panel();
        groupButton.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        groupButton.add(new Label("Etoiles").setFontSize(50), gbc); 
        gbc.gridy = 1; 
        
        gbc.gridx = 1; 
        gbc.insets = new Insets(20, 50, 5, 5);         
        groupButton.add(continuer, gbc);
        gbc.gridx = 2;
        groupButton.add(quitter, gbc);
        groupButton.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
    
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