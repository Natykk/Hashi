package com.hashi.grid;

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

    /**
     * Genere une grille prédéfini deja dans un fichier
     * @param cheminFichier
     */
    public void genererGrilleDepuisFichier(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Hashi.class.getResourceAsStream(cheminFichier)))) {
            String line = "";
            int row = 0;

            Grille grilleTemp = null;

            // on parcourt toutes les lignes du fichier
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    // si la ligne est vide, on passe
                    continue;
                }

                if (line.trim().equals("-")) {
                    this.listeGrille.add(grilleTemp);
                    this.numGrille++;
                    row = 0; // Reset row for the new grid
                    continue;
                }

                String[] values = line.split(" ");

                if (row == 0)
                    grilleTemp = new Grille(values.length);

                for (int col = 0; col < values.length; col++) {
                    if (!values[col].isEmpty()) {
                        int value = Integer.parseInt(values[col]);

                        if (value > 0) {
                            Ile ile = new Ile(value, col, row, grilleTemp);

                            grilleTemp.ajouterIle(ile);
                            grilleTemp.setCase(col, row, ile);
                        }
                    }
                }
                // on passe à a ligne suivante
                row++;
            }

            // Add the last grid to the list
            this.listeGrille.add(grilleTemp);
            this.numGrille++;

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}