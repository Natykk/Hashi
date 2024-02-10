package com.hashi.grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MenuAccueil extends JFrame {

    private JPanel mainPanel;
    private JLabel logoLabel;
    private JTextField profilTextField;
    private JButton validerButton;
    private JTextArea messageTextArea;
    private JComboBox<String> menuDeroulantComboBox;

    public MenuAccueil() {
        setTitle("Interface Swing");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        // Panneau principal
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // Logo
        logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon("logo.png")); // Remplacer "logo.png" par l'image de votre logo
        logoLabel.setBackground(Color.BLUE);
        mainPanel.add(logoLabel, BorderLayout.NORTH);

        // Profil
        JPanel profilPanel = new JPanel();
        profilPanel.setLayout(new FlowLayout());
        mainPanel.add(profilPanel, BorderLayout.CENTER);

        JLabel profilLabel = new JLabel("Profil : ");
        profilPanel.add(profilLabel);

        profilTextField = new JTextField(20);

        profilPanel.add(profilTextField);

        // Bouton Valider
        validerButton = new JButton("Valider");
        validerButton.setBackground(Color.BLUE);
        profilPanel.add(validerButton);

        // Message
        messageTextArea = new JTextArea();
        messageTextArea.setBackground(Color.RED);
        messageTextArea.setEditable(false);
        mainPanel.add(messageTextArea, BorderLayout.SOUTH);

        // Menu déroulant
        menuDeroulantComboBox = new JComboBox<>();
        menuDeroulantComboBox.setBackground(Color.GREEN);
        mainPanel.add(menuDeroulantComboBox, BorderLayout.NORTH);

        // Remplissage du menu déroulant
        menuDeroulantComboBox.addItem("Nouvelle Utilisateur");

        // Action du bouton Valider
        validerButton.addActionListener(e -> {
            // Votre code pour le bouton Valider
            menuDeroulantComboBox.addItem(profilTextField.getText());
        });

        // ItemListener pour détecter les changements de sélection
        menuDeroulantComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selected = (String) e.getItem();
                    if (selected.equals("Nouvelle Utilisateur")) {
                        profilTextField.setVisible(true);
                    } else {
                        profilTextField.setVisible(false);
                    }
                }
            }
        });

        setVisible(true);
    }
}