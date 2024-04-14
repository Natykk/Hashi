package com.hashi.game.mode;

import java.util.ArrayList;
import java.util.List;

import com.hashi.grid.action.Action;
import com.hashi.grid.action.PontAction;
import com.hashi.menu.ArcadeVictory;
import com.hashi.menu.HomeMenu;
import com.hashi.menu.PageManager;
import com.hashi.style.Label;
import com.hashi.style.Panel;
import com.hashi.Hashi;
import com.hashi.game.Score;
import com.hashi.grid.Grille;
import com.hashi.grid.Jeu;
import com.hashi.grid.TimerManager;

/**
 * Classe gérant le mode arcade.
 */
public class ModeArcade extends Mode {
    /**
     * Colonne de la grille au niveau de l'affichage dans le menu.
     */
    protected int column;

    /**
     * Le fichier de la grille sélectionner.
     */
    protected String fichierGrille;

    /**
     * Liste de score accumuler.
     */
    protected List<Integer> scores;

    /**
     * Temps au début de chaque partie.
     */
    protected int startTime;

    /**
     * Créer une instance du mode arcade.
     */
    public ModeArcade() {
        super(new HomeMenu(), false);

        scores = new ArrayList<>();
        startTime = -1;
    }

    @Override
    public Grille getGrille() {
        int typeTaille = (int) (Math.random() * 3);
        int row = (int) (Math.random() * 3);
        column = (int) (Math.random() * 6);

        if (startTime == -1) {
            // temps au début de la première partie
            startTime = (int) (60 * (typeTaille + 1) * 1.5);
        } else {
            // temps ajouté entre 2 partie
            startTime += 30 * (typeTaille + 1);
        }

        fichierGrille = Mode.getGrilleToPlay(typeTaille, row, column);

        return Jeu.genererGrilleDepuisFichier(fichierGrille).get(column);
    }

    @Override
    public List<PontAction> getSolution() {
        return Jeu.genererSolutionDepuisFichier(fichierGrille.replace("G", "SG")).get(column);
    }

    @Override
    public Panel gameFinishedGetVictoryPanel() {
        timer.stopTimer();

        int time = (int) timer.tempsEcoule() / 1000;

        if (time > 0) {
            scores.add(startTime - time);
            startTime = time;

            return new Hashi(this);
        } else {
            PageManager.getProfil().setScoreArcade(Score.calculScoreArcade(scores));

            return new ArcadeVictory();
        }
    }

    @Override
    public void startTimer(Label label) {
        timer = new TimerManager(label, startTime, true);
        timer.addActionListener(e -> {
            if (timer.tempsEcoule() <= 0)
                PageManager.changerPage(gameFinishedGetVictoryPanel());
        });
    }

    @Override
    public void sauvegarder(List<Action> actions) {
    }

    @Override
    public List<Action> getActions() {
        return new ArrayList<>();
    }

    /**
     * A chaque appel de l'aide enlève 10 secondes du timer
     */
    @Override
    public void callAide() {
        timer.addTemps(-10);
    }

}
