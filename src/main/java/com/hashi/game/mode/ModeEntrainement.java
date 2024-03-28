package com.hashi.game.mode;

import java.util.ArrayList;
import java.util.List;

import com.hashi.grid.action.Action;
import com.hashi.grid.Jeu;
import com.hashi.menu.PageManager;
import com.hashi.style.Panel;

public class ModeEntrainement extends Mode {

    public ModeEntrainement(Panel returnPanel, int typeTaille, int row, int column, boolean charger) {
        super(returnPanel, null, 0, charger);

        // Fais 3 switch imbriqués pour déterminer la taille de la grille / la
        // difficulté / le niveau
        // puis appelle la méthode de Hashi pour générer la grille
        // et enfin appelle la méthode de PageManager pour changer de page
        StringBuilder sb = new StringBuilder();
        switch (typeTaille) {
            case 0:
                sb.append("7x7/");
                break;
            case 1:
                sb.append("10x10/");
                break;
            case 2:
                sb.append("25x25/");
                break;
            default:
                sb.append("7x7/");
                break;
        }

        switch (row) {
            case 0:
                sb.append("Facile/GF.txt");
                break;
            case 1:
                sb.append("Moyen/GM.txt");
                break;
            case 2:
                sb.append("Difficile/GD.txt");
                break;
            default:
                sb.append("Facile/GF.txt");
                break;
        }

        Jeu j = new Jeu();

        j.genererGrilleDepuisFichier(sb.toString());

        grille = j.listeGrille.get(column);
        numGrille = typeTaille * 18 + row * 6 + column;
    }

    @Override
    public void sauvegarder(List<Action> actions) {
        PageManager.getProfil().setPartieEntrainement(numGrille, actions);
    }

    @Override
    public List<Action> getActions() {
        if (charger) {
            return PageManager.getProfil().getPartieEntrainement(numGrille);
        }

        PageManager.getProfil().setPartieEntrainement(numGrille, new ArrayList<>());

        return new ArrayList<>();
    }

    @Override
    public void setScore(int temps) {
        PageManager.getProfil().setScoreEntrainement(numGrille, temps);
    }

}
