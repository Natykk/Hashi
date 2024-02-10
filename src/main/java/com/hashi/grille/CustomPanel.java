package com.hashi.grille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomPanel extends JPanel {
    private ArrayList<Ile> iles;
    private ArrayList<Pont> ponts;
    private Ile ileSelectionnee;
    private Point pointeurSouris;

    public CustomPanel(ArrayList<Ile> iles, ArrayList<Pont> ponts) {
        this.iles = iles;
        this.ponts = ponts;
        this.ileSelectionnee = null;
        this.pointeurSouris = null;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    gestionClicGauche(e);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    gestionClicDroit(e);
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

    private void gestionClicGauche(MouseEvent e) {
        for (Ile ile : iles) {
            if (ile.getBounds().contains(e.getPoint())) {
                if (ileSelectionnee == null) {
                    ileSelectionnee = ile;
                } else if (ileSelectionnee != ile) {
                    creerOuDoublerPont(ileSelectionnee, ile);
                    ileSelectionnee = null;
                }
                repaint();
                break;
            }
        }
    }

    private void gestionClicDroit(MouseEvent e) {
        for (Pont pont : new ArrayList<>(ponts)) {
            if (pont.getBounds().contains(e.getPoint())) {
                supprimerPont(pont);
                ponts.remove(pont);
                repaint();
                break;
            }
        }
    }

    private void supprimerPont(Pont pont) {
        if (pont != null) {
            pont.nb_pont--;
            if (pont.isEffacable()) {
                ponts.remove(pont);
            }

        }
    }

    private void creerOuDoublerPont(Ile ile1, Ile ile2) {
        if (ile1.x == ile2.x || ile1.y == ile2.y) {
            Pont pont = trouverPont(ile1, ile2);
            if (pont != null && pont.nb_pont < 2) {
                pont.nb_pont++;
            } else {
                // Ajout de la condition pour ne pas créer un nouveau pont si un pont existe
                // déjà
                if (!pontExisteEntreIles(ile1, ile2)) {
                    ponts.add(new Pont(ile1, ile2));
                }
            }
        }
    }

    // Nouvelle méthode pour vérifier si un pont existe déjà entre les mêmes îles
    private boolean pontExisteEntreIles(Ile ile1, Ile ile2) {
        for (Pont pont : ponts) {
            if ((pont.getIleDep() == ile1 && pont.getIleArr() == ile2) ||
                    (pont.getIleDep() == ile2 && pont.getIleArr() == ile1)) {
                return true;
            }
        }
        return false;
    }

    private Pont trouverPont(Ile ile1, Ile ile2) {
        for (Pont pont : ponts) {
            if ((pont.getIleDep() == ile1 && pont.getIleArr() == ile2) ||
                    (pont.getIleDep() == ile2 && pont.getIleArr() == ile1)) {
                return pont;
            } else if (ile1.listePont.contains(pont) && ile2.listePont.contains(pont)) {
                // Si les deux îles sont déjà connectées par un autre pont
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
        if (ileSelectionnee != null && pointeurSouris != null) {
            g.setColor(Color.BLACK);
            g.drawLine(ileSelectionnee.x, ileSelectionnee.y, pointeurSouris.x, pointeurSouris.y);
            repaint();

        }
        for (Pont pont : ponts) {
            pont.draw(g);
        }
    }
}
