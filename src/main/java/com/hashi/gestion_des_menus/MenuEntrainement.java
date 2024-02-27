package com.hashi.gestion_des_menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.hashi.style.Button;
import com.hashi.style.Panel;

public class MenuEntrainement extends Panel {
    private final String TITLE = "title";
    private Button retour;
    private Button parametre;
    private Button changerProfil;
    private Button regles;
    private Button quitter;
    private Button sept;
    private Button dix;
    private Button vignt;

    public MenuEntrainement() {
        super(new BorderLayout(), "bg-selection-grille.png");

        PageManager.getInstance().setTitle(TITLE);

        retour = new Button("return").setFontSize(50);
        parametre = new Button().setImage("btn-option.png");
        changerProfil = new Button().setImage("btn-switch-profil.png");
        regles = new Button().setImage("btn-aide.png");
        quitter = new Button().setImage("btn-quitter.png");
        sept = new Button("grid_size_7").setFontSize(50);
        dix = new Button("grid_size_10").setFontSize(50);
        vignt = new Button("grid_size_25").setFontSize(50);

        Dimension size = new Dimension(150, 90);

        parametre.setPreferredSize(size);
        changerProfil.setPreferredSize(size);
        regles.setPreferredSize(size);
        quitter.setPreferredSize(size);

        size = new Dimension(250, 100);

        sept.setPreferredSize(size);
        dix.setPreferredSize(size);
        vignt.setPreferredSize(size);

        positionnerBoutons2();

        retour.addActionListener(e -> {
            PageManager.changerPage(new MenuGeneral());
        });
        quitter.addActionListener(e -> {
            System.exit(0);
        });
        regles.addActionListener(e -> {
            PageManager.changerPage(new Regle(this, TITLE));
        });
        parametre.addActionListener(e -> {
            PageManager.changerPage(new Parametre(this, TITLE));
        });
        changerProfil.addActionListener(e -> {
            PageManager.changerPage(new EcranLancement());
        });
        sept.addActionListener(e -> {
            PageManager.changerPage(new Puzzle(this, TITLE));
        });
        dix.addActionListener(e -> {
            PageManager.changerPage(new Puzzle(this, TITLE));
        });
        vignt.addActionListener(e -> {
            PageManager.changerPage(new Puzzle(this, TITLE));
        });

    }

    private void positionnerBoutons2() {
        Panel boutonsHaut = new Panel(new FlowLayout(FlowLayout.RIGHT));
        boutonsHaut.add(retour);

        Panel boutonsCentre = new Panel(new GridBagLayout());
        boutonsCentre.add(sept, createGbc(0, 0));
        boutonsCentre.add(dix, createGbc(1, 0));
        boutonsCentre.add(vignt, createGbc(2, 0));

        Panel boutonsBas = new Panel(new GridBagLayout());
        GridBagConstraints gbc = createGbc(0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        boutonsBas.add(parametre, gbc);
        gbc.gridx = 1;
        boutonsBas.add(changerProfil, gbc);
        gbc.gridx = 2;
        boutonsBas.add(regles, gbc);
        gbc.gridx = 3;
        boutonsBas.add(quitter, gbc);

        Panel contenu = new Panel(new BorderLayout());
        contenu.add(boutonsHaut, BorderLayout.NORTH);
        contenu.add(boutonsCentre, BorderLayout.CENTER);
        contenu.add(boutonsBas, BorderLayout.SOUTH);

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