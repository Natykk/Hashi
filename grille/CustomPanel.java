import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomPanel extends JPanel {
    private ArrayList<Ile> iles;
    private ArrayList<Pont> ponts;
    private Ile ileSelectionnee;

    public CustomPanel(ArrayList<Ile> iles, ArrayList<Pont> ponts) {
        this.iles = iles;
        this.ponts = ponts;
        this.ileSelectionnee = null;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    gestionClicGauche(e);
                } else if (e.getButton() == MouseEvent.BUTTON2) {
                    gestionClicDroit(e);
                }
            }
        });
    }

    private void gestionClicGauche(MouseEvent e) {
        for (Ile ile : iles) {
            if (ile.getBounds().contains(e.getPoint())) {
                if (ileSelectionnee == null) {
                    ileSelectionnee = ile;
                } else {
                    creerPont(ileSelectionnee, ile);
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
                pont.effacer();
                supprimerPont(pont );
                ponts.remove(pont);

                repaint();
                break;
            }
        }
    }
    private void supprimerPont(Pont pont) {
        if (pont != null) {
            if(pont.nb_pont ==2){
                pont.effacer();
                ponts.remove(pont);
                pont.nb_pont-=1;
                ponts.add(pont);
            }
           else{
                pont.effacer();
                ponts.remove(pont);
           }
        }
    }
    
    private void creerPont(Ile ile1, Ile ile2) {
        Pont pont = trouverPont(ile1, ile2);
        if (pont != null) {
            if(pont.nb_pont < 2){
                pont.effacer();
                ponts.remove(pont);
                pont.nb_pont+=1;
                ponts.add(pont);
            }
            
        } else {
            pont = new Pont(ile1, ile2, 1); 
            ile1.ajouterPont(pont);
            ile2.ajouterPont(pont);
            ponts.add(pont);    
        }
    }

    private Pont trouverPont(Ile ile1, Ile ile2) {
        for (Pont pont : ponts) {
            if ((pont.getIleDep() == ile1 && pont.getIleArr() == ile2) ||
                (pont.getIleDep() == ile2 && pont.getIleArr() == ile1)) {
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
        for (Pont pont : ponts) {
            pont.draw(g);
        }
    }
}
