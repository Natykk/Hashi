package com.hashi.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import com.hashi.LanguageManager;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;

public class Help extends JFrame {
    private JTextArea text;

    public Help() {
        //super(new BorderLayout(), "bg-help.png");
        Panel panel = new Panel();

        panel.setImage("bg-help.png");

        text = new JTextArea();
        text.setEditable(false);
        text.setOpaque(false);
        text.setForeground(StyleManager.getInstance().getFgColor());
        text.setFont(StyleManager.getInstance().getFont().deriveFont(0, 25));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        // Ajout des techniques dans la zone de texte
        text.setText(LanguageManager.getString("help"));
        
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        Panel content= new Panel().setImage("tech.png");

        content.setPreferredSize(new Dimension(1,1));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(270, 270, 0, 270));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(Box.createVerticalGlue());
        panel.add(content);
        panel.add(Box.createVerticalGlue());

        add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setVisible(true);
    }

}

