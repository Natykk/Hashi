package com.hashi;

import com.hashi.style.Label;
import com.hashi.style.Panel;
import com.hashi.grid.Action;
import com.hashi.grid.Grille;
import com.hashi.grid.Ile;
import com.hashi.grid.Pont;
import com.hashi.grid.TimerManager;
import com.hashi.style.Button;

import javax.management.InvalidAttributeValueException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import com.hashi.menu.Help;
import com.hashi.menu.PageManager;
import com.hashi.menu.Parameter;
import com.hashi.menu.Rule;
import com.hashi.menu.TrainingGridSelection;
import com.hashi.menu.Victory;

/**
 * Classe principale du jeu.
 */
public class Hashi extends Panel {
    private Grille grille;

    /**
     * Taille des cellules.
     */
    static protected int cellSize = 40;

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
     */
    public Hashi(Grille grille) {
        super(new BorderLayout(), "bg-game.png");
        PageManager.getInstance().setTitle("title");
        this.grille = grille;

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

        hintButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new Help(grille));
        });

        helpButton.addActionListener(e -> {
            PageManager.changerPage(new Rule(this, "title"));
        });

        returnButton.addActionListener(e -> {
            PageManager.changerPage(new TrainingGridSelection(0));
        });

        Panel timerPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Label timerLabel = new Label("00:00").setAsRawText().setFontSize(90);
        timerLabel.setPreferredSize(new Dimension(400, 120));
        timerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 200));
        timerPanel.add(timerLabel);
        add(timerPanel, BorderLayout.NORTH);

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 10, 0));
        add(buttonPanel, BorderLayout.WEST);
        add(new PuzzlePanel(new TimerManager(timerLabel)), BorderLayout.CENTER);

        setVisible(true);

        actions = new ArrayList<>();
        currentIndex = -1;

        undoButton.addActionListener(e -> undoAction());
        redoButton.addActionListener(e -> redoAction());

        resetButton.addActionListener(e -> {
            grille.reset();
            repaint();
            // vide les actions
            actions.clear();
        });

        optionButton.addActionListener(e -> {
            PageManager.changerPage(new Parameter(this, "title"));
        });

        checkbutton.addActionListener(e -> {
            if (grille.getIsGridFinished()) {
                // recupere le temps
                String temps = timerLabel.getText();
                // change la page vers la page victory
                PageManager.changerPage(new Victory(temps));
            }
        });

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
            System.out.println("Nombre de ponts : " + grille.getNbPonts());
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
            Pont pont = grille.getPontAtOnScreen(e.getX(), e.getY());

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

        /**
         * Gère le clic sur un pont.
         * 
         * @param pont
         */
        private void handlePontClick(Pont pont) {
            grille.retirerPont(pont);
            grille.getPonts().remove(pont);
            pont.getIle1().retirerPont(pont);
            pont.getIle2().retirerPont(pont);
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
        actions.subList(currentIndex + 1, actions.size()).clear();
        actions.add(action);
        currentIndex = actions.size() - 1;
        updateUndoRedoButtons();
    }

    /**
     * Annule l'action précédente.
     */
    private void undoAction() {
        if (currentIndex >= 0) {
            actions.get(currentIndex).undo();
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
            actions.get(currentIndex).redo();
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

    /**
     * Action d'ajout d'un pont.
     */
    private class AddPontAction implements Action {
        private Pont pont;

        AddPontAction(Pont pont) {
            this.pont = pont;
        }

        @Override
        public void undo() {
            // si les pont est double alors on le transforme en pont simple
            if (pont.estDouble()) {
                System.out.println("AddPontAction undo double to simple");
                pont.setEstDouble(false);
            } else {
                grille.retirerPont(pont);
                grille.getPonts().remove(pont);
                pont.getIle1().retirerPont(pont);
                pont.getIle2().retirerPont(pont);
            }
        }

        @Override
        public void redo() {
            // si le pont est simple alors on le transforme en pont double
            if (!pont.estDouble()) {
                if (grille.getPonts().contains(pont)) {
                    System.out.println("AddPontAction redo simple to double");
                    // si les iles ne sont pas au max de leur valeur alors on transforme le pont en
                    // pont double
                    if (pont.getIle1().nbConnexions() < pont.getIle1().getValeur()
                            && pont.getIle2().nbConnexions() < pont.getIle2().getValeur()) {
                        pont.setEstDouble(true);
                    }
                } else {
                    System.out.println("AddPontAction redo simple ");
                    grille.ajouterPont(pont);
                    pont.getIle1().ajouterPont(pont);
                    pont.getIle2().ajouterPont(pont);
                }
            } else {
                grille.ajouterPont(pont);
            }
        }
    }

    /**
     * Action de suppression d'un pont.
     */
    private class RemovePontAction implements Action {
        private Pont pont;

        RemovePontAction(Pont pont) {
            this.pont = pont;
        }

        @Override
        public void undo() {
            System.out.println("RemovePontAction undo " + (pont.estDouble() ? "double" : "simple"));

            grille.ajouterPont(pont);
            pont.getIle1().ajouterPont(pont);
            pont.getIle2().ajouterPont(pont);
        }

        @Override
        public void redo() {
            System.out.println("RemovePontAction redo");
            grille.retirerPont(pont);
            grille.getPonts().remove(pont);
            pont.getIle1().retirerPont(pont);
            pont.getIle2().retirerPont(pont);
        }
    }
}