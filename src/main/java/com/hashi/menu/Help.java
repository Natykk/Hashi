package com.hashi.menu;

import com.hashi.grid.Aide;
import com.hashi.grid.Grille;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.io.*;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.hashi.LanguageManager;
import com.hashi.style.Panel;
import com.hashi.style.StyleManager;

public class Help extends JFrame {
    public static int numeroAide = -1;

    public Help(Grille grille) {

        super(LanguageManager.getString("title_help"));

        Aide aideChoisie = choisirAide(grille);

        Panel panel = new Panel(new BorderLayout(), "bg-help.png");
        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setOpaque(false);
        text.setForeground(StyleManager.getInstance().getFgColor());
        text.setFont(StyleManager.getInstance().getFont().deriveFont(0, 25));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFocusable(false);

        // Ajout des techniques dans la zone de texte
        if (aideChoisie != null) {

            // ce texte n'est pas très clair pour l'utilisateur, pas la peine de l'afficher
            // text.setText(LanguageManager.getString(aideChoisie.toString()));
            // text.append("\n\n");

            System.out.println("Aide choisie : " + aideChoisie);

            // Ajout du titre d'explication si la technique 2 est affichée
            String imageName1 = "";
            String imagename2 = "";
            switch (aideChoisie) {
                case FORCE8:
                case FORCE6:
                    text.append(LanguageManager.getString("technique1"));
                    imageName1 = "tech1_1.png";
                    imagename2 = "tech1_2.png";
                    break;
                case FORCE1:
                case FORCE2:
                    text.append(LanguageManager.getString("technique2"));
                    imageName1 = "tech2_1.png";
                    imagename2 = "tech2_2.png";
                    break;
                case FORCE3:
                case FORCE5:
                case FORCE7:
                    text.append(LanguageManager.getString("technique3"));
                    imageName1 = "tech3_1.png";
                    imagename2 = "tech3_2.png";
                    break;
                case BLOQUE3:
                    text.append(LanguageManager.getString("technique4"));
                    imageName1 = "tech4_1.png";
                    imagename2 = "tech4_2.png";
                    break;
                case BLOQUE42:
                    text.append(LanguageManager.getString("technique5"));
                    imageName1 = "tech5_1.png";
                    imagename2 = "tech5_2.png";
                    break;
                case ISOLE1:
                case ISOLE2:
                case ISOLE22:
                    text.append(LanguageManager.getString("technique7"));
                    imageName1 = "tech7_1.png";
                    imagename2 = "tech7_2.png";
                    break;
                default:
                    // Ajoutez ici le code à exécuter si aucun cas ne correspond
                    break;
            }

            // Redimensionner l'image
            Panel imagePanel1 = new Panel().setImage(imageName1);
            imagePanel1.setPreferredSize(new Dimension(150, 150));
            Panel imagePanel2 = new Panel().setImage(imagename2);
            imagePanel2.setPreferredSize(new Dimension(150, 150));
            Panel imagePanel = new Panel(new FlowLayout(FlowLayout.CENTER));

            // Ajout des images au panneau
            imagePanel.add(imagePanel1);
            imagePanel.add(imagePanel2);

            // Ajout du panneau d'images au panneau principal
            panel.add(imagePanel, BorderLayout.SOUTH);

        } else {
            text.setText("");
            System.out.println("Aucune aide utilisable trouvée.");
        }
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(270, 270, 0, 270));
        scrollPane.getViewport().setViewPosition(new Point(0, 0));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Configure le comportement de la fenêtre
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1000, 720);
        setResizable(true);
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
