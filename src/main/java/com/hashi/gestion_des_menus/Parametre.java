package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

public class Parametre extends JPanel {

    public Parametre() {
        PageManager.setPageTitle("Hashi - Paramètre");

        setLayout(new BorderLayout());

        // Créer un JLabel pour le titre "Paramètre"
        JLabel titleLabel = new JLabel("Paramètre");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Créer un panel pour contenir les boutons
        JPanel buttonsPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = createGbc(0, 1);
        buttonsPanel.add(new JLabel("Thèmes: "), gbc);
        gbc.gridx++;
        String[] themes = { "Thème 1", "Thème 2", "Thème 3" };
        JComboBox<String> themeBox = new JComboBox<>(themes);
        themeBox.setPreferredSize(new Dimension(150, 30));
        buttonsPanel.add(themeBox, gbc);

        gbc = createGbc(0, 2);
        buttonsPanel.add(new JLabel("Options: "), gbc);
        gbc.gridx++;
        String[] options = { "Option 1", "Option 2", "Option 3" };
        JComboBox<String> optionsBox = new JComboBox<>(options);
        optionsBox.setPreferredSize(new Dimension(150, 30));
        buttonsPanel.add(optionsBox, gbc);
        add(buttonsPanel, BorderLayout.CENTER);

        JButton validerButton = new JButton("Valider");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(validerButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }
}
