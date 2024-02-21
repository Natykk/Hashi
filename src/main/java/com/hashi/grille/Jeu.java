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

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                if (line.trim().equals("-")) {

                    this.listeGrille.add(grilleTemp);
                    grilleTemp = new Grille(7);
                    this.numGrille++;
                    row = 0; // Reset row for the new grid
                    continue;
                }

                String[] values = line.split(" ");

                for (int col = 0; col < values.length; col++) {
                    if (!values[col].isEmpty()) {
                        int value = Integer.parseInt(values[col]);

                        if (value > 0) {
                            Ile ile = new Ile(value, row, col, grilleTemp);
                            grilleTemp.ajouterIle(ile);

                            grilleTemp.setCase(row, col, ile);
                        }
                    }
                }

                row++;
            }

            // Add the last grid to the list
            this.listeGrille.add(grilleTemp);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}