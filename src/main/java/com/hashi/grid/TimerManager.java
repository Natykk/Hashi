package com.hashi.grid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Timer capable de compter ou décompter à partir d'un certain temps.
 */
public class TimerManager implements ActionListener {
    private JLabel timerLabel;
    private long startTime;
    private long elapsedTime;
    private Timer timer;
    private boolean isInverted;

    /**
     * Créer un timer qui compte les secondes.
     * 
     * @param timerLabel Label pour afficher le temps écoulé.
     * @param startTime  le temps en secondes au démarrage du timer.
     * @param isInverted vrai si le timer décompte faux sinon.
     */
    public TimerManager(JLabel timerLabel, int startTime, boolean isInverted) {
        this.timerLabel = timerLabel;
        this.isInverted = isInverted;

        if (isInverted) {
            this.startTime = System.currentTimeMillis() + startTime * 1000;
            this.elapsedTime = startTime * 1000;
        } else {
            this.startTime = System.currentTimeMillis() - startTime * 1000;
            this.elapsedTime = 0;
        }

        timer = new Timer(1000, this);
        timer.start();

        updateTimer();
    }

    /**
     * Méthode appelée à chaque fois que le Timer déclenche un événement d'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        updateTimer();
    }

    /**
     * Ajout un écouteur d'évènement sur le timer.
     * 
     * @param al l'action listener à ajouter.
     */
    public void addActionListener(ActionListener al) {
        timer.addActionListener(al);
    }

    /**
     * Méthode privée pour mettre à jour le minuteur
     */
    private void updateTimer() {
        if (isInverted) {
            elapsedTime = startTime - System.currentTimeMillis();
        } else {
            elapsedTime = System.currentTimeMillis() - startTime;
        }

        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;

        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Arrête le timer.
     */
    public void stopTimer() {
        timer.stop();
    }

    /**
     * Méthode pour obtenir le temps écoulé en millisecondes depuis le démarrage du
     * minuteur.
     * 
     * @return retourne le temps écoulé en millisecondes.
     */
    public long tempsEcoule() {
        return elapsedTime;
    }

    /**
     * Ajoute du temps au timer.
     * 
     * @param temps le temps à ajouter.
     */
    public void addTemps(int temps) {
        startTime += (isInverted ? temps : -temps) * 1000;
    }
}