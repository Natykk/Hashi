package com.hashi.menu;
import com.hashi.grid.Aide;
import com.hashi.grid.Grille;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.*;

import com.hashi.LanguageManager;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;

public class Help extends JFrame {

    public Help(Grille grille) {
        
        
        super(LanguageManager.getString("title_help"));
        grille.FillListVoisins();
        List<Aide> aides ;
        
        aides = grille.estCeQueQuelquUnAUneAide();
        System.out.println("aides = " + aides);
        Panel panel = new Panel(new BorderLayout(), "bg-help.png");
        JTextArea text = new JTextArea();
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

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setVisible(true);
        getContentPane().add(panel);
    }

}