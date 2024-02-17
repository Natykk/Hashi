package com.hashi.grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomPanel extends JPanel {
    private ArrayList<Ile> iles;
    private ArrayList<Pont> ponts;
    private Ile ileSelectionne;
    private Point pointeurSouris;

    public CustomPanel(ArrayList<Ile> iles, ArrayList<Pont> ponts) {
        this.iles = iles;
        this.ponts = ponts;
        this.ileSelectionne = null;
        this.pointeurSouris = null;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    handleLeftClick(e);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    handleRightClick(e);
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                pointeurSouris = e.getPoint();
                revalidate();
                repaint();
            }
        });
    }

    private void handleLeftClick(MouseEvent e) {
        for (Ile ile : iles) {
            if (ile.getBounds().contains(e.getPoint())) {
                if (ileSelectionne == null) {
                    ileSelectionne = ile;
                } else if (ileSelectionne != ile) {
                    createOrDuplicatePont(ileSelectionne, ile);
                    ileSelectionne = null;
                }
                repaint();
                break;
            }
        }
    }

    private void handleRightClick(MouseEvent e) {
        for (Pont pont : new ArrayList<>(ponts)) {
            if (pont.getBounds().contains(e.getPoint())) {
                deletePont(pont);
                ponts.remove(pont);
                repaint();
                break;
            }
        }
    }

    private void deletePont(Pont pont) {
        if (pont != null) {
            pont.retirerPont();
            if (pont.isEffacable()) {
                ponts.remove(pont);
            }
        }
    }

    private void createOrDuplicatePont(Ile ile1, Ile ile2) {
        if (ile1.x == ile2.x || ile1.y == ile2.y) {
            Pont pont = findPont(ile1, ile2);
            if (pont != null && pont.getNbPont() < 2) {
                pont.ajouterPont();
            } else {
                if (!pontExistsBetweenIslands(ile1, ile2)) {
                    ponts.add(new Pont(ile1, ile2));
                }
            }
        }
    }

    private boolean pontExistsBetweenIslands(Ile ile1, Ile ile2) {
        for (Pont pont : ponts) {
            if ((pont.getIleDep() == ile1 && pont.getIleArr() == ile2) ||
                    (pont.getIleDep() == ile2 && pont.getIleArr() == ile1)) {
                return true;
            }
        }
        return false;
    }

    private Pont findPont(Ile ile1, Ile ile2) {
        for (Pont pont : ponts) {
            if ((pont.getIleDep() == ile1 && pont.getIleArr() == ile2) ||
                    (pont.getIleDep() == ile2 && pont.getIleArr() == ile1)) {
                return pont;
            } else if (ile1.listePont.contains(pont) && ile2.listePont.contains(pont)) {
                return pont;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ile ile : iles) {
            ile.draw(g);
        }
        if (ileSelectionne != null && pointeurSouris != null) {
            g.setColor(Color.BLACK);
            g.drawLine(ileSelectionne.x, ileSelectionne.y, pointeurSouris.x, pointeurSouris.y);
        }
        for (Pont pont : ponts) {
            pont.draw(g);
        }
    }
}