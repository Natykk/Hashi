package com.hashi;

import com.hashi.style.Label;
import com.hashi.style.Panel;
import com.hashi.grid.Action;
import com.hashi.grid.Grille;
import com.hashi.grid.Ile;
import com.hashi.grid.Jeu;
import com.hashi.grid.Pont;
import com.hashi.grid.TimerManager;
import com.hashi.style.Button;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale du jeu.
 */
public class Hashi extends JFrame {
    private Grille grille;

    /**
     * Taille des cellules.
     */
    static protected int cellSize = 40;

    private Button undoButton;
    private Button redoButton;
    public List<Action> actions;
    private int currentIndex;

    private static final int default_width = 1280;
    private static final int default_height = 720;

    /**
     * Créer la {@link javax.swing.JFrame} du jeu.
     * 
     * @param grille la grille contenant la logique du jeu.
     */
    public Hashi(Grille grille) {
        super("Hashi Puzzle");

        this.grille = grille;

        setSize(default_width, default_height);
        setMinimumSize(new Dimension(default_width, default_height));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Panel mainPanel = new Panel(new BorderLayout(), "bg-game.png");

        Panel buttonPanel = new Panel(new GridLayout(8, 1));
        for (int i = 0; i < 6; i++) {
            JButton button = new JButton("Bouton " + (i + 1));
            buttonPanel.add(button);
        }

        undoButton = new Button().setImage("btn-backward.png");
        redoButton = new Button().setImage("btn-forward.png");
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);

        Panel timerPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Label timerLabel = new Label("00:00").setAsRawText().setFontSize(100);
        timerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 150));
        timerPanel.add(timerLabel);
        mainPanel.add(timerPanel, BorderLayout.NORTH);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 0));
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(new PuzzlePanel(new TimerManager(timerLabel)), BorderLayout.CENTER);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        actions = new ArrayList<>();
        currentIndex = -1;

        undoButton.addActionListener(e -> undoAction());
        redoButton.addActionListener(e -> redoAction());
    }

    class PuzzlePanel extends Panel {
        private final TimerManager timerManager;

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            drawGrid(g2d);
            try {
                drawBridges(g2d);
            } catch (InvalidAttributeValueException e) {
                e.printStackTrace();
            }
            drawIslands(g2d);
        }

        private void drawGrid(Graphics2D g2d) {
            int gridSize = grille.getTaille() * cellSize;

            int xOffset = (getWidth() - gridSize) / 2;
            int yOffset = (getHeight() - gridSize) / 2;

            for (int i = 0; i < grille.getTaille(); i++) {
                for (int j = 0; j < grille.getTaille(); j++) {
                    if (grille.getCase(i, j) instanceof Ile) {
                        Ile ile = (Ile) grille.getCase(i, j);
                        ile.setxAffichage(xOffset + i * cellSize + cellSize / 2);
                        ile.setyAffichage(yOffset + j * cellSize + cellSize / 2);
                    }
                }
            }
        }

        private void drawIslands(Graphics2D g2d) {
            for (Ile ile : grille.getIles()) {
                ile.draw(g2d);
            }
        }

        private void drawBridges(Graphics2D g2d) throws InvalidAttributeValueException {
            for (Pont pont : grille.getPonts()) {
                pont.draw(g2d);
            }
        }

        public PuzzlePanel(TimerManager timerManager) {
            this.timerManager = timerManager;

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleClick(e);
                }
            });
        }

        private void handleClick(MouseEvent e) {
            int x = (e.getX() - (getWidth() - grille.getTaille() * cellSize) / 2) / cellSize;
            int y = (e.getY() - (getHeight() - grille.getTaille() * cellSize) / 2) / cellSize;

            Ile clickedIle = grille.getIleAt(x, y);
            Pont pont = grille.getPontAt(e.getX(), e.getY());

            if (clickedIle != null) {
                handleIslandClick(clickedIle);
            } else if (pont != null) {
                handlePontClick(pont);
            } else {
                grille.setSelectedCase(null);
            }

            if (grille.getIsGridFinished()) {
                timerManager.stopTimer();

                System.out.println("Jeu finie, score : " + timerManager.tempsEcoule());
            }

            repaint();
        }

        private void handleIslandClick(Ile clickedIle) {
            if (grille.getSelectedCase() == null) {
                grille.setSelectedCase(clickedIle);
            } else {
                handleIslandSelection(clickedIle);
            }
        }

        private void handleIslandSelection(Ile clickedIle) {
            Ile selectedIle = (Ile) grille.getSelectedCase();

            if (selectedIle != clickedIle
                    && (selectedIle.getX() == clickedIle.getX() || selectedIle.getY() == clickedIle.getY())) {
                if (selectedIle.getNbConnexion() < selectedIle.getValeur()
                        && clickedIle.getNbConnexion() < clickedIle.getValeur()) {
                    // empêche de placer un pont si une ile se trouve entre les deux iles
                    if (selectedIle.getX() == clickedIle.getX()) {
                        int yEnd = Math.max(selectedIle.getY(), clickedIle.getY());

                        for (int y = Math.min(selectedIle.getY(), clickedIle.getY()) + 1; y < yEnd; y++) {
                            if (grille.getIleAt(selectedIle.getX(), y) != null)
                                return;
                        }
                    } else {
                        int xEnd = Math.max(selectedIle.getX(), clickedIle.getX());

                        for (int x = Math.min(selectedIle.getX(), clickedIle.getX()) + 1; x < xEnd; x++) {
                            if (grille.getIleAt(x, selectedIle.getY()) != null)
                                return;
                        }
                    }

                    // si un pont simple est deja présent alors on le transforme en pont double
                    Pont pontAller = grille.getPont(selectedIle, clickedIle);
                    Pont pontRetour = grille.getPont(clickedIle, selectedIle);
                    if (pontAller != null) {
                        pontAller.setEstDouble(true);
                        addAction(new AddPontAction(pontAller));
                    } else if (pontRetour != null) {
                        pontRetour.setEstDouble(true);
                        addAction(new AddPontAction(pontRetour));
                    } else {
                        Pont newPont = new Pont(selectedIle, clickedIle, grille);
                        grille.ajouterPont(newPont);
                        addAction(new AddPontAction(newPont));
                    }
                }
            }
            grille.setSelectedCase(null);
        }

        private void handlePontClick(Pont pont) {
            grille.retirerPont(pont);
            grille.getPonts().remove(pont);
            pont.getIleDep().retirerPont(pont);
            pont.getIleArr().retirerPont(pont);
            addAction(new RemovePontAction(pont));
            repaint();
        }
    }

    private void addAction(Action action) {
        actions.subList(currentIndex + 1, actions.size()).clear();
        actions.add(action);
        currentIndex = actions.size() - 1;
        updateUndoRedoButtons();
    }

    private void undoAction() {
        if (currentIndex >= 0) {
            actions.get(currentIndex).undo();
            currentIndex--;
            updateUndoRedoButtons();
            repaint();
        }
    }

    private void redoAction() {
        if (currentIndex < actions.size() - 1) {
            currentIndex++;
            actions.get(currentIndex).redo();
            updateUndoRedoButtons();
            repaint();
        }
    }

    private void updateUndoRedoButtons() {
        undoButton.setEnabled(currentIndex >= 0);
        redoButton.setEnabled(currentIndex < actions.size() - 1);
    }

    private class AddPontAction implements Action {
        private Pont pont;

        AddPontAction(Pont pont) {
            this.pont = pont;
        }

        @Override
        public void undo() {
            grille.retirerPont(pont);
            grille.getPonts().remove(pont);
            pont.getIleDep().retirerPont(pont);
            pont.getIleArr().retirerPont(pont);
        }

        @Override
        public void redo() {
            grille.ajouterPont(pont);
        }
    }

    private class RemovePontAction implements Action {
        private Pont pont;

        RemovePontAction(Pont pont) {
            this.pont = pont;
        }

        @Override
        public void undo() {
            grille.ajouterPont(pont);
        }

        @Override
        public void redo() {
            grille.retirerPont(pont);
            grille.getPonts().remove(pont);
            pont.getIleDep().retirerPont(pont);
            pont.getIleArr().retirerPont(pont);
        }
    }

    /**
     * Point d'entrée du jeu.
     * 
     * @param args arguments de la ligne de commande du jeu.
     */
    public static void main(String[] args) {
        Jeu j = new Jeu();
        j.genererGrilleDepuisFichier("grille.txt");
        Grille grille = j.listeGrille.get(0);

        SwingUtilities.invokeLater(() -> new Hashi(grille));
    }
}