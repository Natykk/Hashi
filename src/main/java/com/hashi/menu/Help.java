package com.hashi.menu;

import com.hashi.grid.Aide;
import com.hashi.grid.Grille;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.*;
import java.util.*;

import javax.swing.*;

import com.hashi.LanguageManager;
import com.hashi.style.Image;
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
            // Ajout du titre d'explication si la technique 2 est affichée
            Image imageComponent1 = new Image(panel);
            Image imageComponent2 = new Image(panel);
            switch (aideChoisie) {
                case FORCE8:
                case FORCE6:
                    text.append("\n\n" + LanguageManager.getString("technique1"));
                    imageComponent1.setImage("tech1_1.png");
                    imageComponent2.setImage("tech1_2.png");
                    break;
                case FORCE1:
                case FORCE2:
                    text.append("\n\n" + LanguageManager.getString("technique2"));
                    imageComponent1.setImage("tech2_1.png");
                    imageComponent2.setImage("tech2_2.png");
                    break;
                case FORCE3:
                case FORCE5:
                case FORCE7:
                    text.append("\n\n" + LanguageManager.getString("technique3"));
                    imageComponent1.setImage("tech3_1.png");
                    imageComponent2.setImage("tech3_2.png");
                    break;
                case BLOQUE3:
                    text.append("\n\n" + LanguageManager.getString("technique4"));
                    imageComponent1.setImage("tech4_1.png");
                    imageComponent2.setImage("tech4_2.png");
                    break;
                case BLOQUE42:
                    text.append("\n\n" + LanguageManager.getString("technique5"));
                    imageComponent1.setImage("tech5_1.png");
                    imageComponent2.setImage("tech5_2.png");
                    break;
                case ISOLE1:
                case ISOLE2:
                case ISOLE22:
                    text.append("\n\n" + LanguageManager.getString("technique7"));
                    imageComponent1.setImage("tech7_1.png");
                    imageComponent2.setImage("tech7_2.png");
                    break;
                default:
                    // Ajoutez ici le code à exécuter si aucun cas ne correspond
                    break;
            }

            // Redimensionner l'image
            ImageIcon originalIcon = new ImageIcon(imageComponent1.getImage());
            ImageIcon originalIcon1 = new ImageIcon(imageComponent2.getImage());
            ImageIcon resizedIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
            ImageIcon resizedIcon1 = new ImageIcon(originalIcon1.getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
            JLabel imageLabel1 = new JLabel(resizedIcon);
            JLabel imageLabel2 = new JLabel(resizedIcon1);
            Panel imagePanel = new Panel(new FlowLayout(FlowLayout.CENTER));

            // Ajout des images au panneau
            imagePanel.add(imageLabel1);
            imagePanel.add(imageLabel2);

            // Ajout du panneau d'images au panneau principal
            panel.add(imagePanel, BorderLayout.SOUTH);




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
            return (Aide) aidesUtilisables.toArray()[numeroAide];
        } else {
            return null;
        }
    }

}
