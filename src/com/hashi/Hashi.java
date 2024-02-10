package com.hashi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.hashi.grille.*;

public class Hashi extends JFrame {
    private Grille grille;
    static protected int Offset = 40;

    public Hashi(Grille grille) {
        this.grille = grille;
        setTitle("Hashi Puzzle");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new PuzzlePanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PuzzlePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            drawGrid(g2d);
            drawIslands(g2d);
            drawBridges(g2d);
        }

        private void drawGrid(Graphics2D g2d) {
            int cellSize = Offset;
            for (int i = 0; i < grille.getTaille(); i++) {
                for (int j = 0; j < grille.getTaille(); j++) {
                    g2d.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);

                    if (grille.getCase(i, j) instanceof Ile) {
                        Ile ile = (Ile) grille.getCase(i, j);
                        ile.x = i * cellSize + cellSize / 2;
                        ile.y = j * cellSize + cellSize / 2;
                    }
                }
            }
        }

        private void drawIslands(Graphics2D g2d) {
            for (Ile ile : grille.getIles()) {
                ile.draw(g2d);
            }
        }

        private void drawBridges(Graphics2D g2d) {
            for (Pont pont : grille.getPonts()) {
                pont.draw(g2d);
            }
            System.out.println("Ponts : " + grille.getPonts());
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
            int cellSize = Offset;
            int x = e.getX() / cellSize;
            int y = e.getY() / cellSize;

            Ile clickedIle = grille.getIleAt(x, y);
            Pont pont = grille.getPontAt(e.getX(), e.getY());

            if (clickedIle != null) {
                if (grille.getSelectedCase() == null) {
                    grille.setSelectedCase(clickedIle);
                } else {
                    if (grille.getSelectedCase() instanceof Ile) {
                        Ile selectedIle = (Ile) grille.getSelectedCase();
                        if (selectedIle != clickedIle && (selectedIle.getX() == clickedIle.getX()
                                || selectedIle.getY() == clickedIle.getY())) {
                            if (selectedIle.getNbConnexion() < selectedIle.getValeur()
                                    && clickedIle.getNbConnexion() < clickedIle.getValeur()) {
                                grille.ajouterPont(new Pont(selectedIle, clickedIle));
                            }
                        }

                    }
                    grille.setSelectedCase(null);
                }
            } else if ((grille.getPonts().size()) > 0 && pont != null) {
                // si la position de la souris est dans le rectangle du pont

                if (pont != null) {
                    // supprimer le pont
                    grille.retirerPont(pont);

                    // retirer le pont de la liste des ponts
                    grille.getPonts().remove(pont);

                    // retirer le pont de la liste des ponts de l'ile
                    pont.getIleDep().retirerPont(pont);

                    // retirer le pont de la liste des ponts de l'ile

                    pont.getIleArr().retirerPont(pont);

                }

            } else {
                grille.setSelectedCase(null);
            }

            repaint();
        }
    }

    public static void main(String[] args) {
        Jeu j = new Jeu();
        j.genererGrilleDepuisFichier("grille.txt");
        Grille grille = j.listeGrille.get(0);

        @SuppressWarnings("unused")
        Hashi hashiInterface = new Hashi(grille);
    }
}