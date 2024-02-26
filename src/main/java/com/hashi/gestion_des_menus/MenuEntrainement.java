package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;


import com.hashi.style.Panel;



public class MenuEntrainement extends Panel {
    private final String TITLE = "title";
    private JButton retour;
    private JButton parametre;
    private JButton changerProfil;
    private JButton regles;
    private JButton quitter;
    private JButton sept;
    private JButton dix;
    private JButton vignt;

    public MenuEntrainement() {

        super(new BorderLayout(), "bg-selection-grille.png");
        PageManager.getInstance().setTitle(TITLE);
        
        retour = new JButton("Retour");
        parametre = new JButton("Parametres");
        changerProfil = new JButton("Changer de profil");
        regles = new JButton("Règles");
        quitter = new JButton("Quitter le jeu");
        sept=new JButton("7X7");
        dix=new JButton("10X10");
        vignt=new JButton("25X25");

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
        JPanel boutonsHaut = new JPanel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alignement à droite
        boutonsHaut.add(retour);

        JPanel boutonsCentre = new JPanel(new GridBagLayout());
        boutonsCentre.add(sept);
        boutonsCentre.add(dix);
        boutonsCentre.add(vignt);

        JPanel boutonsBas = new JPanel();
        boutonsBas.setLayout(new GridLayout(1, 3, 5, 5));
        boutonsBas.add(parametre);
        boutonsBas.add(changerProfil);
        boutonsBas.add(Box.createHorizontalStrut(10));
        boutonsBas.add(regles);
        boutonsBas.add(quitter);

        JPanel contenu = new JPanel(new BorderLayout());
        contenu.add(boutonsHaut, BorderLayout.NORTH);
        contenu.add(boutonsCentre, BorderLayout.CENTER);
        contenu.add(boutonsBas, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(contenu, BorderLayout.CENTER);
    }


}