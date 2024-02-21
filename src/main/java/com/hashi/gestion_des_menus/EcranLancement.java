package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class EcranLancement extends JPanel {

    private JComboBox<String> profilBox;
    private ArrayList<String> profils;
    private JPanel panel1, panel2;
    // private JLabel logoLabel;
    private PageManager pageManager;
    private MenuGeneral2 Menu;

    public EcranLancement(PageManager pageManager) {
        JButton bouton = new JButton("Valider");
        this.pageManager = pageManager;
        // Charger les profils depuis le fichier "profils.txt"
        chargerprofils();

        // Ajout d'une action lorsque le bouton valider (dans la première page) est
        // cliqué
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String profilChoisi = profilBox.getItemAt(profilBox.getSelectedIndex());
                // Vérifier si "Nouveau profil" est sélectionné
                if (profilChoisi.equals("Nouveau profil")) {
                    PageNouveauProfil();
                } else {
                    System.out.println("Vous avez choisi : " + profilChoisi);
                    // Changement du page => Menu
                    pageManager.changerPage(new MenuGeneral2(pageManager));
                }
            }
        });

        // Convertir l'ArrayList en tableau de chaînes
        String[] profilsArray = profils.toArray(new String[profils.size() + 1]);
        // Ajouter "Nouveau profil" à la fin du tableau
        profilsArray[profils.size()] = "Nouveau profil";

        profilBox = new JComboBox<>(profilsArray);
        panel1 = new JPanel(new GridBagLayout());
        panel1.add(new JLabel("Profil:"), createGbc(0, 0));
        panel1.add(profilBox, createGbc(1, 0));
        panel1.add(bouton, createGbc(2, 2));

        // à regler (logo)
        /*
         * logoLabel = new JLabel(new ImageIcon("logo.png"));
         * // Resize the image to fit the window
         * Image img = new ImageIcon("logo.png").getImage();
         * Image img2 = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
         * ImageIcon logo = new ImageIcon(img2);
         * logoLabel.setIcon(logo);
         * panel1.add(logoLabel);
         */

        // Initialiser panel2 avec un champ JTextField vide
        panel2 = new JPanel(new GridBagLayout());
        Menu = new MenuGeneral2(pageManager);
        add(panel1);
    }

    // vérifier si la page est vide
    private boolean estPageVide() {
        return panel2.getComponentCount() == 0;
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5); // Marge
        return gbc;
    }

    private void PageNouveauProfil() {
        // si elle est vide on va creer la page du nouveau profil
        if (estPageVide()) {
            // à regler (logo)
            /*
             * logoLabel = new JLabel(new ImageIcon("logo.png"));
             * // Resize the image to fit the window
             * Image img = new ImageIcon("logo.png").getImage();
             * Image img2 = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
             * ImageIcon logo = new ImageIcon(img2);
             * logoLabel.setIcon(logo);
             * panel2.add(logoLabel);
             */

            JTextField nouveauprofilField;
            panel2.add(new JLabel("Créer un nouveau profil : "), createGbc(0, 0));
            panel2.add(nouveauprofilField = new JTextField(8), createGbc(1, 0));

            JButton validerNouveauprofil = new JButton("Valider");
            validerNouveauprofil.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nouveauprofil = nouveauprofilField.getText();
                    // si on saisie rien il affiche un message d'erreur
                    if (nouveauprofil.trim().isEmpty()) {
                        pageManager.MessageErreur("Veuillez entrer un nom du profil valide.",
                                "Erreur");
                        // si on saisie un message déjà exsistant on affiche un message d'erreur
                    } else if (profilExisteDeja(nouveauprofil)) {
                        pageManager.MessageErreur("Ce profil existe déja",
                                "Erreur");
                    } else {
                        System.out.println("Nouveau profil créé : " + nouveauprofil);
                        // Ajouter le nouveau profil au fichier
                        ajouterprofil(nouveauprofil);
                        // Mettre à jour la liste des profils
                        chargerprofils();
                        // Ajouter le nouveau profil au JComboBox
                        profilBox.addItem(nouveauprofil);
                        // Sélectionner le nouveau profil ajouté
                        profilBox.setSelectedItem(nouveauprofil);
                        // Afficher un message de confirmation pour le nouveau profil créé
                        pageManager.changerPage(new MenuGeneral2(pageManager));
                    }
                }
            });
            panel2.add(validerNouveauprofil, createGbc(1, 1));

            JButton annuler = new JButton("Annuler");
            annuler.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Retour à la page précédente (panel1)
                    pageManager.changerPage(panel1);
                }
            });
            panel2.add(annuler, createGbc(0, 1));

            // Changer de page vers panel2
            pageManager.changerPage(panel2);
        } else {
            // La page n'est pas vide, simplement changer de page vers panel2
            pageManager.changerPage(panel2);
        }

    }

    public boolean profilExisteDeja(String nouveauprofil) {
        return profils.contains(nouveauprofil);
    }

    // Charger les profils depuis le fichier
    private void chargerprofils() {
        profils = new ArrayList<>();
        try (BufferedReader lire = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("../profils.txt")))) {
            String line;
            while ((line = lire.readLine()) != null) {
                profils.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ajouter un profil au fichier
    private void ajouterprofil(String profil) {
        try (BufferedWriter ecrire = new BufferedWriter(
                new FileWriter(getClass().getResource("../profils.txt").getPath(), true))) {
            ecrire.write(profil);
            ecrire.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
