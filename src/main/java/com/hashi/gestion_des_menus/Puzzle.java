package com.hashi.gestion_des_menus;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.hashi.style.Button;
import com.hashi.style.Panel;

//il me reste de faire fonctionner les boutons 
public class Puzzle extends Panel {
    private Button retour;
    private Button[][] boutons;

    public Puzzle() {
        super(new BorderLayout(), "bg-entrainement.png");

        PageManager.getInstance().setTitle("title_puzzle");

        retour = new Button("return").setFontSize(50);
        boutons = new Button[3][6]; // 3 niveaux de puzzle chaque niveau avec 6 boutons

        positionnerBoutons3();

        retour.addActionListener(e -> {
            PageManager.changerPage(new MenuEntrainement());
        });
    }

    private void positionnerBoutons3() {
        Panel boutonsHaut = new Panel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT));
        boutonsHaut.add(retour);
        Panel contenu = new Panel(new GridLayout(3, 1));
        contenu.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 120));

        // Boucle pour créer les boutons pour chaque niveau de puzzle
        for (int i = 0; i < 3; i++) {
            Panel panelNiveau = new Panel(new FlowLayout(FlowLayout.CENTER));
            panelNiveau.setBorder(BorderFactory.createEmptyBorder(55, 0, 0, 0));

            for (int j = 0; j < 6; j++) {
                boutons[i][j] = new Button(String.valueOf(j + 1)).setAsRawText().setFontSize(70);
                boutons[i][j].setPreferredSize(new Dimension(90, 90));
                panelNiveau.add(boutons[i][j]);
            }

            contenu.add(panelNiveau);
        }

        // Ajout du contenu à ce panneau Puzzle
        add(contenu, BorderLayout.CENTER);
        add(boutonsHaut, BorderLayout.NORTH);
    }
}