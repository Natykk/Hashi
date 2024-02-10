import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomPanel2 extends JPanel {
    private ArrayList<Ile2> iles;
    private ArrayList<Pont2> ponts;
    private Ile2 ileSelectionnee;
    private Point pointeurSouris;

    public CustomPanel2(ArrayList<Ile2> iles, ArrayList<Pont2> ponts) {
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
        for (Ile2 ile : iles) {
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
        for (Pont2 pont : new ArrayList<>(ponts)) {
            if (pont.getBounds().contains(e.getPoint())) {
                supprimerPont(pont);
                ponts.remove(pont);
                repaint();
                break;
            }
        }
    }
    

   
    private void supprimerPont(Pont2 pont) {
        if (pont != null) {
          pont.nb_pont--;
          if (pont.isEffacable()) {
            ponts.remove(pont);
        }
            
        }
    }

    private void creerOuDoublerPont(Ile2 ile1, Ile2 ile2) {
        if (ile1.abs() == ile2.abs || ile1.ordo == ile2.ordo) {
            Pont2 pont = trouverPont(ile1, ile2);
            if (pont != null && pont.nb_pont<2 ) {
                pont.nb_pont ++;
            } else {
                // Ajout de la condition pour ne pas créer un nouveau pont si un pont existe déjà
                if (!pontExisteEntreIles(ile1, ile2)) {
                    ponts.add(new Pont2(ile1, ile2));
                }
            }
        }
    }
    
    // Nouvelle méthode pour vérifier si un pont existe déjà entre les mêmes îles
    private boolean pontExisteEntreIles(Ile2 ile1, Ile2 ile2) {
        for (Pont2 pont : ponts) {
            if ((pont.getIleDep() == ile1 && pont.getIleArr() == ile2) ||
                (pont.getIleDep() == ile2 && pont.getIleArr() == ile1)) {
                return true;
            }
        }
        return false;
    }
    
    

    private Pont2 trouverPont(Ile2 ile1, Ile2 ile2) {
        for (Pont2 pont : ponts) {
            if ((pont.getIleDep() == ile1 && pont.getIleArr() == ile2) ||
                (pont.getIleDep() == ile2 && pont.getIleArr() == ile1)) {
                return pont;
            } else if (ile1.liste_pont.contains(pont) && ile2.liste_pont.contains(pont)) {
                // Si les deux îles sont déjà connectées par un autre pont
                return pont;
            }
        }
        return null;
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ile2 ile : iles) {
            ile.draw(g);

        }
        if (ileSelectionnee != null && pointeurSouris != null) {
            g.setColor(Color.BLACK);
            g.drawLine(ileSelectionnee.abs, ileSelectionnee.ordo, pointeurSouris.x, pointeurSouris.y);
            repaint();

        }
        for (Pont2 pont : ponts) {
            pont.draw(g);
        }
    }
}
