package com.hashi.menu;

import com.hashi.grid.Aide;
import com.hashi.grid.Grille;

import java.awt.BorderLayout;
import java.io.*;
import java.util.*;

import javax.swing.*;

import java.io.FileReader;

import com.hashi.LanguageManager;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;

public class Help extends JFrame {
    public static int numeroAide = -1;
    public Help(Grille grille) {

        super(LanguageManager.getString("title_help"));


        grille.fillListVoisins();


        Aide aideChoisie = choisirAide(grille);


        Panel panel = new Panel(new BorderLayout(), "bg-help.png");
        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setOpaque(false);
        text.setForeground(StyleManager.getInstance().getFgColor());
        text.setFont(StyleManager.getInstance().getFont().deriveFont(0, 25));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        // Ajout des techniques dans la zone de texte
        if (aideChoisie != null) {
            text.setText(LanguageManager.getString(aideChoisie.toString()));
            System.out.println("Aide choisie : " + aideChoisie);
        } else {

            text.setText(LanguageManager.getString("help"));
            System.out.println("Aucune aide utilisable trouvée.");
        }
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(270, 270, 0, 270));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Configure le comportement de la fenêtre
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setVisible(true);
        getContentPane().add(panel);
    }



    // Méthode pour charger les descriptions d'aide depuis une source de données
    private void chargerDescriptionsAide(Map<Aide, String> descriptionsAide) {
        try {
            FileReader fileReader = new FileReader("aide.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    Aide aide = Aide.fromString(parts[0]);
                    descriptionsAide.put(aide, parts[1]);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Aide choisirAide(Grille grille) {
        List<Aide> aides = grille.estCeQueQuelquUnAUneAide();
        Set<Aide> aidesUtilisables = new HashSet<>();
        Map<Aide, String> descriptionsAide = new HashMap<>();

        chargerDescriptionsAide(descriptionsAide);

        for (Aide aide : aides) {
            if (aide != Aide.RIEN && descriptionsAide.containsKey(aide)) {
                aidesUtilisables.add(aide);
            }
        }

        if (!aidesUtilisables.isEmpty()) {
            numeroAide = (numeroAide + 1) % aidesUtilisables.size();
            Iterator<Aide> iterator = aidesUtilisables.iterator();
            for (int i = 0; i < numeroAide; i++) {
                iterator.next();
            }
            return iterator.next();
        } else {
            return null;
        }
    }

}