package com.hashi.gestion_des_menus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hashi.Language;
import com.hashi.style.Button;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;

public class Regle extends Panel {

    private JTextArea text;
    private Button retourButton;

    public Regle(Panel returnPanel, String returnTitle) {
        super(new BorderLayout(), "bg-regle.png");

        PageManager.getInstance().setTitle("title_rules");

        // Création de la zone de texte
        text = new JTextArea();
        text.setEditable(false);
        text.setOpaque(false);
        text.setForeground(StyleManager.getInstance().getFgColor());
        text.setFont(StyleManager.getInstance().getFont().deriveFont(0, 25));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        // Ajout des règles dans la zone de texte
        text.setText(Language.getString("rules"));

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(270, 270, 0, 270));
        add(scrollPane, BorderLayout.CENTER);

        retourButton = new Button("return").setFontSize(50);
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
