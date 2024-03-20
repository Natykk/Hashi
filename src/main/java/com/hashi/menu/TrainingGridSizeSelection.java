package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import com.hashi.style.Button;
import com.hashi.style.Panel;

public class TrainingGridSizeSelection extends Panel {
    private final String TITLE = "title_training_grid_size_selection";
    private Button retour;
    private Button parametre;
    private Button changerProfil;
    private Button regles;
    private Button quitter;
    private Button sept;
    private Button dix;
    private Button vignt;

    public TrainingGridSizeSelection() {
        super(new BorderLayout(), "bg-training-grid-size-selection.png");

        PageManager.getInstance().setTitle(TITLE);

        retour = new Button("return").setFontSize(50);
        parametre = new Button().setImage("btn-option.png");
        changerProfil = new Button().setImage("btn-switch-profil.png");
        regles = new Button().setImage("btn-rule.png");
        quitter = new Button().setImage("btn-quit.png");
        sept = new Button("7x7").setAsRawText().setFontSize(50);
        dix = new Button("10x10").setAsRawText().setFontSize(50);
        vignt = new Button("25x25").setAsRawText().setFontSize(50);

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
            PageManager.changerPage(new HomeMenu());
        });
        quitter.addActionListener(e -> {
            System.exit(0);
        });
        regles.addActionListener(e -> {
            PageManager.changerPage(new Rule(this, TITLE));
        });
        parametre.addActionListener(e -> {
            PageManager.changerPage(new Parameter(this, TITLE));
        });
        changerProfil.addActionListener(e -> {
            PageManager.changerPage(new StartScreen());
        });
        sept.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSelection(0));
        });
        dix.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSelection(1));
        });
        vignt.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSelection(2));
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