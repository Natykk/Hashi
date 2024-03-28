package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;

import com.hashi.LanguageManager;
import com.hashi.style.Button;
import com.hashi.style.ComboBox;
import com.hashi.style.Label;
import com.hashi.style.Panel;
import com.hashi.style.SchoolStyle;
import com.hashi.style.Style;
import com.hashi.style.StyleManager;
import com.hashi.style.SummerStyle;

/**
 * La classe `Parameter` représente la page des paramètres dans l'application.
 * Elle étend la classe.
 */
public class Parameter extends Panel {

    /**
     * Constructeur de la classe `Parameter`.
     * @param returnPanel Le panneau à retourner après la modification des paramètres.
     * @param returnTitle Le titre de la page à retourner.
     */
    public Parameter(Panel returnPanel, String returnTitle) {
        super(new BorderLayout(), "bg-parameter.png");

        PageManager.getInstance().setTitle("title_parameters");

        // Créer un panel pour contenir les boutons
        Panel buttonsPanel = new Panel(new GridBagLayout());

        GridBagConstraints gbc = createGbc(0, 1);
        buttonsPanel.add(new Label("select_theme").setFontSize(50), gbc);
        gbc.gridx++;
        String[] themes = { "School", "Summer" };
        List<String> themes_key = List.of("school", "summer");
        Style[] themes_style = { new SchoolStyle(), new SummerStyle() };
        ComboBox<String> themeBox = new ComboBox<>(themes).setFontSize(50);
        themeBox.setSelectedIndex(themes_key.indexOf(StyleManager.getInstance().getName()));
        themeBox.addActionListener(e -> {
            StyleManager.setStyle(themes_style[themeBox.getSelectedIndex()]);
            PageManager.changerPage(new Parameter(returnPanel, returnTitle));
        });
        buttonsPanel.add(themeBox, gbc);

        gbc = createGbc(0, 2);
        buttonsPanel.add(new Label("select_language").setFontSize(50), gbc);
        gbc.gridx++;
        String[] languages = { "Francais", "English" };
        List<String> languages_key = List.of("fr", "en");
        ComboBox<String> languagesBox = new ComboBox<>(languages).setFontSize(50);
        languagesBox.setSelectedIndex(languages_key.indexOf(LanguageManager.getLanguage()));
        languagesBox.addActionListener(e -> {
            LanguageManager.setLanguage(languages_key.get(languagesBox.getSelectedIndex()));
            PageManager.changerPage(new Parameter(returnPanel, returnTitle));
        });
        buttonsPanel.add(languagesBox, gbc);
        add(buttonsPanel, BorderLayout.CENTER);

        Button validerButton = new Button("validate").setFontSize(50);
        validerButton.setPreferredSize(new Dimension(250, 100));
        validerButton.addActionListener(e -> {
            PageManager.changerPage(returnPanel);
            PageManager.getInstance().setTitle(returnTitle);
        });
        Panel bottomPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 130));
        bottomPanel.add(validerButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Crée une contrainte pour le positionnement des composants dans un panneau de type `GridBagLayout`.
     * @param x Position horizontale du composant dans la grille
     * @param y Position verticale du composant dans la grille
     * @return GridBagConstraints pour le positionnement du composant dans la grille
     */
    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        return gbc;
    }
}
