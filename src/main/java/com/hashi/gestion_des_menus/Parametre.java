package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

public class Parametre extends JPanel {
    private PageManager pageManager;

    public Parametre(PageManager pageManager) {
        this.pageManager = pageManager;
        pageManager.setTitle("Paramètre");

        setLayout(new BorderLayout()); // Utiliser BorderLayout pour organiser les composants verticalement

        // Créer un panel pour contenir les boutons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS)); // Utiliser BoxLayout pour aligner les composants verticalement

        // Ajouter un espace vertical au-dessus des boutons pour les centrer
        buttonsPanel.add(Box.createVerticalGlue());

        // Création d'un bouton déroulant pour les thèmes
        String[] themes = {"Thème 1", "Thème 2", "Thème 3"}; // Exemple de thèmes
        JComboBox<String> themeBox = new JComboBox<>(themes);
        themeBox.setPreferredSize(new Dimension(150, 30)); // Ajuster la taille du bouton déroulant
        themeBox.setMaximumSize(new Dimension(150, 30)); // Définir une taille maximale
        themeBox.setMinimumSize(new Dimension(150, 30)); // Définir une taille minimale
        buttonsPanel.add(new JLabel("Thèmes: "));
        buttonsPanel.add(themeBox);

        // Ajouter un espace vertical entre les boutons
        buttonsPanel.add(Box.createVerticalStrut(20)); // Espacement de 20 pixels

        // Création d'un autre bouton déroulant pour les options
        String[] options = {"Option 1", "Option 2", "Option 3"}; // Exemple d'options
        JComboBox<String> optionsBox = new JComboBox<>(options);
        optionsBox.setPreferredSize(new Dimension(150, 30)); // Ajuster la taille du bouton déroulant
        optionsBox.setMaximumSize(new Dimension(150, 30)); // Définir une taille maximale
        optionsBox.setMinimumSize(new Dimension(150, 30)); // Définir une taille minimale
        buttonsPanel.add(new JLabel("Options: "));
        buttonsPanel.add(optionsBox);

        // Ajouter un espace vertical entre les boutons et le bouton "Valider"
        buttonsPanel.add(Box.createVerticalStrut(20)); // Espacement de 20 pixels

        // Ajouter le bouton "Valider"
        JButton validerButton = new JButton("Valider");
        buttonsPanel.add(validerButton);

        // Ajouter un espace vertical en dessous des boutons pour les centrer
        buttonsPanel.add(Box.createVerticalGlue());

        // Ajouter le panel des boutons au centre de la fenêtre
        add(buttonsPanel, BorderLayout.CENTER);
    }
}







