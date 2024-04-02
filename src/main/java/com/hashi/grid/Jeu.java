package com.hashi.grid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.hashi.Hashi;
import com.hashi.grid.action.PontAction;

public class Jeu {

    /**
     * Charge des grilles prédéfini dans un fichier
     * 
     * @param cheminFichier
     * @return la liste de grille
     */
    public static List<Grille> genererGrilleDepuisFichier(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Hashi.class.getResourceAsStream(cheminFichier)))) {
            List<Grille> listeGrille = new ArrayList<>();
            Grille grilleTemp = null;
            String line = "";
            int row = 0;

            // on parcourt toutes les lignes du fichier
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    // si la ligne est vide, on passe
                    continue;
                }

                if (line.trim().equals("-")) {
                    listeGrille.add(grilleTemp);
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
            listeGrille.add(grilleTemp);

            return listeGrille;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Charge des solutions prédéfini dans un fichier
     * 
     * @param cheminFichier
     * @return la liste de solution
     */
    public static List<List<PontAction>> genererSolutionDepuisFichier(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Hashi.class.getResourceAsStream(cheminFichier)))) {
            List<List<PontAction>> listeSolution = new ArrayList<>();
            List<PontAction> solutionTemp = null;
            String line = "";
            int row = 0;

            // on parcourt toutes les lignes du fichier
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    // si la ligne est vide, on passe
                    continue;
                }

                if (line.trim().equals("-")) {
                    listeSolution.add(solutionTemp);
                    row = 0; // Reset row for the new grid
                    continue;
                }

                String[] values = line.split(" ");

                if (row == 0)
                    solutionTemp = new ArrayList<>();

                solutionTemp
                        .add(new PontAction(Integer.parseInt(values[1]), Integer.parseInt(values[0]),
                                Integer.parseInt(values[3]), Integer.parseInt(values[2]),
                                Integer.parseInt(values[4]) == 1));
                // on passe à a ligne suivante
                row++;
            }

            listeSolution.add(solutionTemp);

            return listeSolution;
        } catch (IOException |

                NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

}