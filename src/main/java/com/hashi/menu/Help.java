package com.hashi.menu;

import java.awt.BorderLayout;

import javax.swing.*;

import com.hashi.LanguageManager;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;

public class Help extends Panel {
    private JTextArea text;

    public Help() {
        super(new BorderLayout(), "bg-help.png");
        Panel panel = new Panel();

        text = new JTextArea();
        text.setEditable(false);
        text.setOpaque(false);
        text.setForeground(StyleManager.getInstance().getFgColor());
        text.setFont(StyleManager.getInstance().getFont().deriveFont(0, 25));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        // Ajout des techniques dans la zone de texte
        text.setText(LanguageManager.getString("help"));

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(270, 270, 0, 270));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setImage("bg-help.png");

        add(panel);

        //setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        //setResizable(false);
        setVisible(true);
    }

}