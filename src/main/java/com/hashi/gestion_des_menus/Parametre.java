package com.hashi.gestion_des_menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;

import com.hashi.Language;
import com.hashi.style.Button;
import com.hashi.style.ComboBox;
import com.hashi.style.Label;
import com.hashi.style.Panel;
import com.hashi.style.SchoolStyle;
import com.hashi.style.Style;
import com.hashi.style.StyleManager;
import com.hashi.style.SummerStyle;

public class Parametre extends Panel {

    public Parametre(Panel returnPanel) {
        super(new BorderLayout(), "bg-parametre.png");

        PageManager.getInstance().setTitle("Hashi - Paramètre");

        // Créer un panel pour contenir les boutons
        Panel buttonsPanel = new Panel(new GridBagLayout());

        GridBagConstraints gbc = createGbc(0, 1);
        buttonsPanel.add(new Label("select_theme").setFontSize(50), gbc);
        gbc.gridx++;
        String[] themes = { "School", "Summer" };
        Style[] themes_key = { new SchoolStyle(), new SummerStyle() };
        ComboBox<String> themeBox = new ComboBox<>(themes).setFontSize(50);
        themeBox.addActionListener(e -> {
            StyleManager.setStyle(themes_key[themeBox.getSelectedIndex()]);
            PageManager.changerPage(this);
        });
        buttonsPanel.add(themeBox, gbc);

        gbc = createGbc(0, 2);
        buttonsPanel.add(new Label("select_language").setFontSize(50), gbc);
        gbc.gridx++;
        String[] languages = { "Francais", "English" };
        String[] languages_key = { "fr", "en" };
        ComboBox<String> languagesBox = new ComboBox<>(languages).setFontSize(50);
        languagesBox.addActionListener(e -> {
            Language.setLanguage(languages_key[languagesBox.getSelectedIndex()]);
            PageManager.changerPage(this);
        });
        buttonsPanel.add(languagesBox, gbc);
        add(buttonsPanel, BorderLayout.CENTER);

        Button validerButton = new Button("validate").setFontSize(50);
        validerButton.setPreferredSize(new Dimension(250, 100));
        validerButton.addActionListener(e -> {
            PageManager.changerPage(returnPanel);
        });
        Panel bottomPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 130));
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
