package com.hashi.grid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerManager implements ActionListener {
    private JLabel timerLabel;
    private long startTime;
    private long elapsedTime;
    private Timer timer;

    public TimerManager(JLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = 0;

        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateTimer();
    }

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

    public long tempsEcoule() {
        return elapsedTime;
    }
}