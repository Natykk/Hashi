package com.hashi;

import com.hashi.grille.Grille;
import com.hashi.grille.Ile;
import com.hashi.grille.Jeu;
import com.hashi.grille.Pont;
import com.hashi.grille.TimerManager;
import com.hashi.grille.Action;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Hashi extends JFrame {
    private Grille grille;
    static protected int cellSize = 40;

    private JButton undoButton;
    private JButton redoButton;
    private List<Action> actions;
    private int currentIndex;
    private TimerManager timerManager;

    public Hashi(Grille grille) {
        this.grille = grille;
        setTitle("Hashi Puzzle");
        setSize(1000, 1000);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // met l'image de fond
        //setContentPane(new JLabel(new ImageIcon("../../../resources/com/hashi/theme/school/bg-1.png")));



        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 1));
        for (int i = 0; i < 6; i++) {
            JButton button = new JButton("Bouton " + (i + 1));
            buttonPanel.add(button);
        }

        undoButton = new JButton("Revenir en arrière");
        redoButton = new JButton("Revenir en avant");
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);

        JLabel timerLabel = new JLabel("Temps : 00:00");
        mainPanel.add(timerLabel, BorderLayout.NORTH);
        this.timerManager = new TimerManager(timerLabel);

        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(new PuzzlePanel(), BorderLayout.CENTER);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        actions = new ArrayList<>();
        currentIndex = -1;

        undoButton.addActionListener(e -> undoAction());
        redoButton.addActionListener(e -> redoAction());
    }

    class PuzzlePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            drawGrid(g2d);
            drawIslands(g2d);
            try {
                drawBridges(g2d);
            } catch (InvalidAttributeValueException e) {
                e.printStackTrace();
            }
        }

        private void drawGrid(Graphics2D g2d) {
            int gridSize = grille.getTaille() * cellSize;

            int xOffset = (getWidth() - gridSize) / 2;
            int yOffset = (getHeight() - gridSize) / 2;

            for (int i = 0; i < grille.getTaille(); i++) {
                for (int j = 0; j < grille.getTaille(); j++) {
                    if (grille.getCase(i, j) instanceof Ile) {
                        Ile ile = (Ile) grille.getCase(i, j);
                        ile.x = xOffset + i * cellSize + cellSize / 2;
                        ile.y = yOffset + j * cellSize + cellSize / 2;
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

        public PuzzlePanel() {
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
            if (selectedIle != clickedIle && (selectedIle.getX() == clickedIle.getX() || selectedIle.getY() == clickedIle.getY())) {
                if (selectedIle.getNbConnexion() < selectedIle.getValeur() && clickedIle.getNbConnexion() < clickedIle.getValeur()) {
                    Pont newPont = new Pont(selectedIle, clickedIle);
                    grille.ajouterPont(newPont);
                    addAction(new AddPontAction(newPont));
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
        }
    }

    public static void main(String[] args) {
        Jeu j = new Jeu();
        j.genererGrilleDepuisFichier("grille.txt");
        Grille grille = j.listeGrille.get(0);

        SwingUtilities.invokeLater(() -> new Hashi(grille));
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

}