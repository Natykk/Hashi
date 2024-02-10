import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ile extends Case {
    private int valeur;
    public ArrayList<Pont> listePont;
    protected int x;
    protected int y;
    private int tailleIle;
    private Color color;
    private ArrayList<Ile> listeVoisin;

    public Ile(int valeur, int x, int y, Grille lagrille) {
        super(x, y);
        this.valeur = valeur;
        this.listePont = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.tailleIle = 20;
        this.color = Color.CYAN;
        this.listeVoisin = new ArrayList<>();
        grille = lagrille;
    }

    public Rectangle getBounds() {
        return new Rectangle(x - tailleIle / 2, y - tailleIle / 2, tailleIle, tailleIle);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(x - 10, y - 10, 20, 20);
        g.fillOval(x - 10, y - 10, 20, 20);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(valeur), x - 5, y + 5);
    }

    public void ajouterPont(Pont pont) {
        this.listePont.add(pont);
    }

    public void ajouterVoisin(Ile voisin) {
        this.listeVoisin.add(voisin);
    }

    public int getNbConnexion() {
        int tot = 0;
        for (Pont pont : listePont) {
            tot += pont.getNbPont();
        }
        return tot;
    }

    public boolean supprimerPont(Pont pont) {
        if (this.listePont.contains(pont)) {
            int k = listePont.indexOf(pont);
            if (listePont.get(k).equals(pont)) {
                this.listePont.remove(pont);
                return false;
            } else {
                Pont p1 = listePont.get(k);
                p1.setNbPont(p1.getNbPont() - 1);
                this.listePont.remove(pont);
                this.listePont.add(p1);
            }
        }
        return true;
    }

    public boolean ileComplete() {
        return listePont.size() == this.valeur;
    }

    public int getValeur() {
        return this.valeur;
    }

    public String afficher() {
        return this.valeur + "";
    }

    public int getNbPonts() {
        return this.listePont.size();
    }

    public void retirerPont(Pont pont) {
        this.listePont.remove(pont);
    }

    public ArrayList<Ile> getListeVoisin() {
        return this.listeVoisin;
    }

    public boolean estComplet() {
        return this.valeur == this.getNbPonts();
    }

    public boolean estValide() {
        return this.valeur >= this.getNbPonts();
    }

    public int getNbPontBas() {
        int nbPontBas = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle1().equals(this)) {
                nbPontBas++;
            }
        }
        return nbPontBas;
    }

    public int getNbPontHaut() {
        int nbPontHaut = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle2().equals(this)) {
                nbPontHaut++;
            }
        }
        return nbPontHaut;
    }

    public boolean estDouble() {
        return this.getNbPontBas() > 1 || this.getNbPontHaut() > 1;
    }

    public int nb_connexion() {
        int nb = 0;
        for (Pont pont : this.listePont) {
            nb += pont.getNbPont();
        }
        return nb;
    }

    public int getPosition() {
        return this.x;
    }


}