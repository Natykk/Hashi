package com.hashi;

import com.hashi.menu.*;
import com.hashi.style.Label;
import com.hashi.style.Panel;
import com.hashi.game.mode.Mode;
import com.hashi.grid.Case;
import com.hashi.grid.Grille;
import com.hashi.grid.Ile;
import com.hashi.grid.Pont;
import com.hashi.grid.action.Action;
import com.hashi.grid.action.AddPontAction;
import com.hashi.grid.action.RemovePontAction;
import com.hashi.grid.action.ResetGrilleAction;
import com.hashi.style.Button;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Classe principale du jeu.
 */
public class Hashi extends Panel {
    private Mode mode;
    private Grille grille;

    /**
     * Taille des cellules.
     */
    private int cellSize = 40;

    private Button undoButton;
    private Button redoButton;
    private Button resetButton;
    private Button optionButton;
    private Button checkbutton;
    private Button returnButton;
    private Button helpButton;
    private Button hintButton;

    private List<Action> actions;
    private int currentIndex;

    /**
     * Créer la {@link javax.swing.JFrame} du jeu.
     * 
     * @param grille la grille contenant la logique du jeu.
     * @param mode
     */
    public Hashi(Mode mode) {
        super(new BorderLayout(), "bg-game.png");
        PageManager.getInstance().setTitle("title");
        this.mode = mode;
        this.grille = mode.getGrille();
        actions = mode.getActions();
        currentIndex = actions.size() - 1;

        Panel buttonPanel = new Panel(new GridLayout(8, 1));

        returnButton = new Button().setImage("btn-return.png");
        returnButton.setPreferredSize(new Dimension(80, 80));
        buttonPanel.add(returnButton);

        optionButton = new Button().setImage("btn-option.png");
        buttonPanel.add(optionButton);

        helpButton = new Button().setImage("btn-rule.png");
        buttonPanel.add(helpButton);

        hintButton = new Button().setImage("btn-help.png");
        buttonPanel.add(hintButton);

        checkbutton = new Button().setImage("btn-check.png");
        buttonPanel.add(checkbutton);

        undoButton = new Button().setImage("btn-backward.png");
        buttonPanel.add(undoButton);

        redoButton = new Button().setImage("btn-forward.png");
        buttonPanel.add(redoButton);

        resetButton = new Button().setImage("btn-restart.png");
        buttonPanel.add(resetButton);

        Panel timerPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Label timerLabel = new Label("00:00").setAsRawText().setFontSize(90);
        timerLabel.setPreferredSize(new Dimension(400, 120));
        timerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 200));
        timerPanel.add(timerLabel);
        add(timerPanel, BorderLayout.NORTH);
        mode.startTimer(timerLabel);

        hintButton.addActionListener(e -> {
            this.mode.callAide();
            SwingUtilities.invokeLater(() -> new Help(grille));
        });

        helpButton.addActionListener(e -> {
            PageManager.changerPage(new Rule(this, "title"));
        });

        returnButton.addActionListener(e -> {
            PageManager.changerPage(mode.getReturnPanel());
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 0));
        add(buttonPanel, BorderLayout.WEST);
        add(new PuzzlePanel(), BorderLayout.CENTER);

        undoButton.addActionListener(e -> undoAction());
        redoButton.addActionListener(e -> redoAction());

        resetButton.addActionListener(e -> {
            addAction(new ResetGrilleAction(grille.getPonts()));
            repaint();
        });

        optionButton.addActionListener(e -> {
            PageManager.changerPage(new Parameter(this, "title"));
        });

        checkbutton.addActionListener(e -> {
            if (grille.isGridFinished()) {
                PageManager.changerPage(mode.gameFinishedGetVictoryPanel());
            }
        });

        for (Action action : actions) {
            action.redo(grille);
        }

        setVisible(true);
    }

    class PuzzlePanel extends Panel {

        /**
         * Redéfinition de la méthode paintComponent pour dessiner la grille
         * 
         * @param g
         * 
         */
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

        // Méthode pour dessiner la grille
        private void drawGrid(Graphics2D g2d) {
            int gridSize = getHeight() - 50;

            cellSize = gridSize / grille.getTaille();

            int xOffset = (getWidth() - gridSize) / 2;
            int yOffset = (getHeight() - gridSize) / 2;

            for (int i = 0; i < grille.getTaille(); i++) {
                for (int j = 0; j < grille.getTaille(); j++) {
                    if (grille.getCase(i, j) instanceof Ile) {
                        Ile ile = (Ile) grille.getCase(i, j);
                        ile.setXAffichage(xOffset + i * cellSize + cellSize / 2);
                        ile.setYAffichage(yOffset + j * cellSize + cellSize / 2);
                        ile.setTailleAffichage(cellSize);
                    }
                }
            }
        }

        /**
         * Méthode pour dessiner les îles
         * 
         * @param g2d
         */
        private void drawIslands(Graphics2D g2d) {
            for (Ile ile : grille.getIles()) {
                ile.draw(g2d);
            }
        }

        /**
         * Méthode pour dessiner les ponts
         * 
         * @param g2d
         * @throws InvalidAttributeValueException
         */

        private void drawBridges(Graphics2D g2d) throws InvalidAttributeValueException {
            for (Pont pont : grille.getPonts()) {
                pont.draw(g2d);
            }
        }

        /**
         * Constructeur de PuzzlePanel
         * 
         */
        public PuzzlePanel() {

            /**
             * Evenement pour voir si l'utilisateur clique sur sa souris
             */
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleClick(e);
                }
            });
        }

        /**
         * Méthode pour gérer les clics de souris sur la grille
         * 
         * @param e
         */
        private void handleClick(MouseEvent e) {
            int x = (e.getX() - (getWidth() - grille.getTaille() * cellSize) / 2) / cellSize;
            int y = (e.getY() - (getHeight() - grille.getTaille() * cellSize) / 2) / cellSize;

            Ile clickedIle = grille.getIleAt(x, y);
            Pont pont = grille.getPontAtOnScreen(e.getX(), e.getY());

            if (clickedIle != null) {
                handleIslandClick(clickedIle);
            } else if (pont != null) {
                handlePontClick(pont);
            } else {
                grille.setSelectedCase(null);
            }

            repaint();
        }

        /**
         * Gère le clic sur une île.
         * 
         * @param clickedIle
         * 
         */
        private void handleIslandClick(Ile clickedIle) {
            if (grille.getSelectedCase() == null) {
                grille.setSelectedCase(clickedIle);
            } else {
                handleIslandSelection(clickedIle);
            }
        }

        /**
         * Gère la sélection d'une île.
         * 
         * @param clickedIle
         */
        private void handleIslandSelection(Ile clickedIle) {
            Ile selectedIle = (Ile) grille.getSelectedCase();

            if (selectedIle != clickedIle
                    && (selectedIle.getX() == clickedIle.getX() || selectedIle.getY() == clickedIle.getY())) {
                if (selectedIle.getNbConnexion() < selectedIle.getValeur()
                        && clickedIle.getNbConnexion() < clickedIle.getValeur()) {
                    Pont pont = grille.getPont(selectedIle, clickedIle);

                    // empêche de placer un pont si une ile ou un pont se trouve entre les deux iles
                    if (selectedIle.getX() == clickedIle.getX()) {
                        int yEnd = Math.max(selectedIle.getY(), clickedIle.getY());

                        for (int y = Math.min(selectedIle.getY(), clickedIle.getY()) + 1; y < yEnd; y++) {
                            Case case_ = grille.getCase(selectedIle.getX(), y);

                            if (case_.estIle() || (case_.estPont() && case_ != pont))
                                return;
                        }
                    } else {
                        int xEnd = Math.max(selectedIle.getX(), clickedIle.getX());

                        for (int x = Math.min(selectedIle.getX(), clickedIle.getX()) + 1; x < xEnd; x++) {
                            Case case_ = grille.getCase(x, selectedIle.getY());

                            if (case_.estIle() || (case_.estPont() && case_ != pont))
                                return;
                        }
                    }

                    addAction(new AddPontAction(selectedIle, clickedIle));
                }
            }
            grille.setSelectedCase(null);
        }

        /**
         * Gère le clic sur un pont.
         * 
         * @param pont
         */
        private void handlePontClick(Pont pont) {
            addAction(new RemovePontAction(pont));
            repaint();
        }
    }

    /**
     * Ajoute une action à la liste des actions.
     * 
     * @param action
     */
    private void addAction(Action action) {
        if (currentIndex < actions.size() - 1)
            actions.subList(currentIndex + 1, actions.size()).clear();

        actions.add(action);
        currentIndex = actions.size() - 1;
        action.redo(grille);
        mode.sauvegarder(actions);
        updateUndoRedoButtons();
    }

    /**
     * Annule l'action précédente.
     */
    private void undoAction() {
        if (currentIndex >= 0) {
            actions.get(currentIndex).undo(grille);
            currentIndex--;
            updateUndoRedoButtons();
            repaint();
        }
    }

    /**
     * Refait l'action précédente.
     */
    private void redoAction() {
        if (currentIndex < actions.size() - 1) {
            currentIndex++;
            actions.get(currentIndex).redo(grille);
            updateUndoRedoButtons();
            repaint();
        }
    }

    /**
     * 
     * @return Retourne la liste d'action.
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * Met à jour l'état des boutons d'annulation et de refaire.
     */
    private void updateUndoRedoButtons() {
        undoButton.setEnabled(currentIndex >= 0);
        redoButton.setEnabled(currentIndex < actions.size() - 1);
    }
}