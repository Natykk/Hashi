package com.hashi.grille;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.hashi.Hashi;

public class Jeu {

    public List<Grille> listeGrille;
    int numGrille;

    public Jeu() {
        this.listeGrille = new ArrayList<>();
        this.numGrille = 0;
    }

    public void genererGrilleDepuisFichier(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Hashi.class.getResourceAsStream(cheminFichier)))) {
            String line = "";
            int row = 0;

            Grille grilleTemp = new Grille(7);

            // on parcourt toutes les lignes du fichier
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    // si la ligne est vide, on passe
                    continue;
                }

                if (line.trim().equals("-")) {
                    // si la ligne contient juste un "-", la grille créée dans grilleTemp est finie
                    this.listeGrille.add(grilleTemp); // ajouter la grille à la liste de Grilles du Jeu
                    grilleTemp = new Grille(7); // créer une nouvelle grille
                    this.numGrille++;
                    row = 0; // Reset row for the new grid
                    continue;
                }

                String[] values = line.split(" ");

                for (int col = 0; col < values.length; col++) {
                    if (!values[col].isEmpty()) {
                        int value = Integer.parseInt(values[col]);

                        if (value > 0) {
                            // il y a une Ile
                            Ile ile = new Ile(value, row, col, grilleTemp);
                            grilleTemp.ajouterIle(ile);
                        }
                    }
                }
                // on passe à a ligne suivante
                row++;
            }

            // Add the last grid to the list
            this.listeGrille.add(grilleTemp);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}