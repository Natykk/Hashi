package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

public class PageMode extends JPanel{
    private JButton retour;
    private JButton nouvellePartie;
    private JButton charger;
    private JButton parametre;
    private JButton changerProfil;
    private JButton regles;
    private JButton quitter;

    private PageManager pageManager;
    public PageMode(PageManager pageManager){
        this.pageManager=pageManager;

        
        retour = new JButton("Retour au menu");
        nouvellePartie= new JButton("NOUVELLE PARTIE");
        charger= new JButton("CHARGER");
        parametre=new JButton("Parametres");
        changerProfil= new JButton("Changer de profil");
        regles=new JButton("Règles");
        quitter=new JButton("Quitter le jeu");

        JPanel groupButton = new JPanel();
        groupButton.setLayout(new GridLayout(1, 3, 5, 5));
        groupButton.add(parametre);
        groupButton.add(changerProfil);
        groupButton.add(regles);
        groupButton.add(quitter);
        groupButton.setOpaque(false);
        this.add(groupButton, BorderLayout.SOUTH);

        JPanel groupPanel1 = new JPanel();
        groupPanel1.setLayout(new BorderLayout());
        groupPanel1.add(groupButton, BorderLayout.SOUTH);
        groupPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel group=new JPanel();
        group.setLayout(new GridLayout(1, 3, 5, 5));
        group.add(retour);
        this.add(group,BorderLayout.NORTH);

        JPanel groupCentre=new JPanel();
        groupCentre.setLayout(new GridLayout(1, 3, 5, 5));
        groupCentre.add(nouvellePartie);
        groupCentre.add(charger);
        this.add(groupCentre,BorderLayout.CENTER);


        this.add(groupPanel1, BorderLayout.SOUTH);



        quitter.addActionListener(e -> {
            System.exit(0);
        });
        regles.addActionListener(e->{
            //pass
        });
        nouvellePartie.addActionListener(e->{
            //pass
        });
        charger.addActionListener(e -> {
            //pass
        });
        parametre.addActionListener(e -> {
            pageManager.changerPage(new Parametre(pageManager));
        });
        changerProfil.addActionListener(e -> {
            pageManager.changerPage(new EcranLancement(pageManager));
        });
        retour.addActionListener(e -> {
            pageManager.changerPage(new MenuGeneral2(pageManager));
        });
    }
}
