package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hashi.LanguageManager;
import com.hashi.style.Button;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;

/**
 * La classe `Rule` représente la page des règles dans le jeu.
 * Elle étend la classe `Panel`.
 */
public class Rule extends Panel {

    /**
     * Constructeur de la classe `Rule`.
     * 
     * @param returnPanel Le panneau à retourner après la consultation des règles.
     * @param returnTitle Le titre de la page à retourner.
     */
    public Rule(Panel returnPanel, String returnTitle) {
        super(new BorderLayout(), "bg-rule.png");

        PageManager.getInstance().setTitle("title_rules");

        // Création de la zone de texte
        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setOpaque(false);
        text.setForeground(StyleManager.getInstance().getFgColor());
        text.setFont(StyleManager.getInstance().getFont().deriveFont(0, 25));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFocusable(false);

        // Ajout des règles dans la zone de texte
        text.setText(LanguageManager.getString("rules"));

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(270, 270, 0, 270));
        scrollPane.getViewport().setViewPosition(new Point(0, 0));
        add(scrollPane, BorderLayout.CENTER);

        Button retourButton = new Button("return").setFontSize(50);
        retourButton.addActionListener(e -> {
            PageManager.changerPage(returnPanel);
            PageManager.getInstance().setTitle(returnTitle);
        });

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(retourButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
