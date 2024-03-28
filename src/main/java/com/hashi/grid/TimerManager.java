package com.hashi.grid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerManager implements ActionListener {
    private JLabel timerLabel;
    private long startTime;
    private long elapsedTime;
    private Timer timer;

    /**
     * Constructeur prenant un JLabel en paramètre pour afficher le temps écoulé
     * @param timerLabel
     */
    public TimerManager(JLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = 0;

        timer = new Timer(1000, this);
        timer.start();
    }

    /**
     * Méthode appelée à chaque fois que le Timer déclenche un événement d'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        updateTimer();
    }

    /**
     * Méthode privée pour mettre à jour le minuteur
     */
    private void updateTimer() {
        elapsedTime = System.currentTimeMillis() - startTime;

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
     * // Méthode pour obtenir le temps écoulé en millisecondes depuis le démarrage du minuteur
     * @return
     */
    public long tempsEcoule() {
        return elapsedTime;
    }
}