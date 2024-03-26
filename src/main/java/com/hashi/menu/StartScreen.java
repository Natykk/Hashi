package com.hashi.menu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.*;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.hashi.Hashi;
import com.hashi.LanguageManager;
import com.hashi.style.*;

/**
 * La classe `StartScreen` représente l'écran de démarrage du jeu.
 * Elle permet aux utilisateurs de sélectionner un profil existant ou de créer un nouveau profil.
 * Elle étend la classe `Panel`.
 */
public class StartScreen extends Panel {

    private ComboBox<String> profilBox;
    private ArrayList<String> profils;
    private Panel panel1, panel2;

    public StartScreen() {
        super(new GridBagLayout(), "bg-start-screen.png");
        PageManager.getInstance().setTitle("title_start_screen");

        // Charger les profils depuis le fichier "profils.txt"
        chargerprofils();

        PageProfil();
        PageNouveauProfil();

        SwingUtilities.invokeLater(() -> {
            PageManager.changerPage(panel1);
        });
    }

    /**
     * Méthode utilitaire pour créer les contraintes de la grille.
     * @param x La position en x dans la grille.
     * @param y La position en y dans la grille.
     * @return Les contraintes de la grille.
     */
    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5); // Marge
        return gbc;
    }

    /**
     * Méthode pour créer la première page : sélection de profil.
     */
    private void PageProfil() {
        Button bouton = new Button("validate").setFontSize(50);

        // Ajout d'une action lorsque le bouton valider (dans la première page) est
        // cliqué
        bouton.addActionListener(e -> {
            String profilChoisi = profilBox.getItemAt(profilBox.getSelectedIndex());
            // Vérifier si "Nouveau profil" est sélectionné
            if (profilChoisi.equals(LanguageManager.getString("new_profile"))) {
                PageManager.changerPage(panel2);
            } else {
                System.out.println("Vous avez choisi : " + profilChoisi);
                // Changement du page => Menu
                PageManager.changerPage(new HomeMenu());
            }
        });

        // Convertir l'ArrayList en tableau de chaînes
        String[] profilsArray = profils.toArray(new String[profils.size() + 1]);
        // Ajouter "Nouveau profil" à la fin du tableau
        profilsArray[profils.size()] = LanguageManager.getString("new_profile");

        profilBox = new ComboBox<>(profilsArray).setFontSize(50);
        panel1 = new Panel(new GridBagLayout(), "bg-start-screen.png");
        panel1.add(new Label("select_profile").setFontSize(50), createGbc(0, 0));
        panel1.add(profilBox, createGbc(1, 0));
        panel1.add(bouton, createGbc(1, 1));
    }

    /**
     * Méthode pour créer la deuxième page : création de profil.
     */
    private void PageNouveauProfil() {
        panel2 = new Panel(new GridBagLayout(), "bg-start-screen.png");

        TextField nouveauprofilField = new TextField(8).setFontSize(50);
        panel2.add(new Label("create_profile").setFontSize(50), createGbc(0, 0));
        panel2.add(nouveauprofilField, createGbc(1, 0));

        Button validerNouveauprofil = new Button("validate").setFontSize(50);
        validerNouveauprofil.addActionListener(e -> {
            String nouveauprofil = nouveauprofilField.getText();
            // si on saisie rien il affiche un message d'erreur
            if (nouveauprofil.trim().isEmpty()) {
                PageManager.afficherMessageErreur("Veuillez entrer un nom du profil valide.",
                        "Erreur");
                // si on saisie un message déjà exsistant on affiche un message d'erreur
            } else if (profilExisteDeja(nouveauprofil)) {
                PageManager.afficherMessageErreur("Ce profil existe déja",
                        "Erreur");
            } else {
                System.out.println("Nouveau profil: " + nouveauprofil);
                // Ajouter le nouveau profil au fichier
                ajouterprofil(nouveauprofil);
                // Mettre à jour la liste des profils
                chargerprofils();
                // Ajouter le nouveau profil au ComboBox
                profilBox.addItem(nouveauprofil);
                // Sélectionner le nouveau profil ajouté
                profilBox.setSelectedItem(nouveauprofil);
                // Afficher un message de confirmation pour le nouveau profil créé
                PageManager.changerPage(new HomeMenu());
            }
        });
        panel2.add(validerNouveauprofil, createGbc(1, 1));

        Button annuler = new Button("cancel").setFontSize(50);
        annuler.addActionListener(e -> {
            // Retour à la page précédente (panel1)
            PageManager.changerPage(panel1);
        });
        panel2.add(annuler, createGbc(0, 1));
    }

    /**
     * Méthode pour vérifier si un profil existe déjà.
     * @param nouveauprofil Le nom du profil à vérifier.
     * @return true si le profil existe déjà, sinon false.
     */
    public boolean profilExisteDeja(String nouveauprofil) {
        return profils.contains(nouveauprofil);
    }

    /**
     * Méthode pour charger les profils depuis le fichier.
     */
    private void chargerprofils() {
        profils = new ArrayList<>();
        try (BufferedReader lire = new BufferedReader(
                new InputStreamReader(Hashi.class.getResourceAsStream("profils.txt")))) {
            String line;
            while ((line = lire.readLine()) != null) {
                profils.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Méthode pour ajouter un profil au fichier.
     * @param profil Le nom du profil à ajouter.
     */
    private void ajouterprofil(String profil) {
        try (BufferedWriter ecrire = new BufferedWriter(
                new FileWriter(Hashi.class.getResource("profils.txt").getPath(), true))) {
            ecrire.write(profil);
            ecrire.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
