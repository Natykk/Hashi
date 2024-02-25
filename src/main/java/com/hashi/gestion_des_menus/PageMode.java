package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

public class PageMode extends JPanel {
    private JButton retour;
    private JButton nouvellePartie;
    private JButton charger;
    private JButton parametre;
    private JButton changerProfil;
    private JButton regles;
    private JButton quitter;

    public PageMode() {
        PageManager.getInstance().setTitle("Hashi - Sélection du mode de jeu");

        retour = new JButton("Retour au menu");
        nouvellePartie = new JButton("NOUVELLE PARTIE");
        charger = new JButton("CHARGER");
        parametre = new JButton("Parametres");
        changerProfil = new JButton("Changer de profil");
        regles = new JButton("Règles");
        quitter = new JButton("Quitter le jeu");

        positionnerBoutons();

        quitter.addActionListener(e -> {
            System.exit(0);
        });
        regles.addActionListener(e -> {
            PageManager.changerPage(new Regle());
        });
        nouvellePartie.addActionListener(e -> {
            // pass
        });
        charger.addActionListener(e -> {
            // pass
        });
        parametre.addActionListener(e -> {
            PageManager.changerPage(new Parametre());
        });
        changerProfil.addActionListener(e -> {
            PageManager.changerPage(new EcranLancement());
        });
        retour.addActionListener(e -> {
            PageManager.changerPage(new MenuGeneral());
        });
    }

    private void positionnerBoutons() {

        JPanel boutonsHaut = new JPanel();
        boutonsHaut.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alignement à droite
        boutonsHaut.add(retour);

        JPanel boutonsCentre = new JPanel(new GridBagLayout());
        boutonsCentre.add(nouvellePartie, createGbc(0, 0));
        boutonsCentre.add(charger, createGbc(0, 1));

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

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5); // Marge
        return gbc;
    }
}
