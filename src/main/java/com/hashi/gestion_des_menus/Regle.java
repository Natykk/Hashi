package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

public class Regle extends JPanel {

    private JLabel titleLabel;
    private JTextArea text;
    private JButton validerButton;

    public Regle() {
        PageManager.setPageTitle("Hashi - Règle");

        setLayout(new BorderLayout());
        setOpaque(false);

        // Création du titre
        titleLabel = new JLabel("Règles");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titleLabel.setOpaque(false);
        add(titleLabel, BorderLayout.NORTH);

        // Création de la zone de texte
        text = new JTextArea();
        text.setEditable(false);
        text.setOpaque(false);
        text.setFont(new Font("Arial", Font.PLAIN, 14));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        // Ajout des règles dans la zone de texte
        text.setText(
                "Le Hashi est un jeu de réflexion qui consiste à relier des îles entre elles avec des ponts en suivants les règles suivantes :\n\n"
                        +
                        "1. Les ponts doivent être horizontaux ou verticaux et ils ne se croisent pas.\n" +
                        "2. Il peut y avoir au plus deux ponts entre deux îles données.\n" +
                        "3. Chaque île se voit attribuer un chiffre entre 1 et 8 représentant le nombre de ponts auquel elle doit être connectée.\n"
                        +
                        "On doit pouvoir relier toutes les îles entre elles grâce aux ponts.");

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        validerButton = new JButton("Valider");
        validerButton.addActionListener(e -> {
            // j'ai pas penser encore comment faire
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(validerButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        titleLabel.setFont(new Font("Arial", Font.BOLD, getHeight() / 20));
        text.setFont(new Font("Arial", Font.PLAIN, getHeight() / 40));
    }
}
