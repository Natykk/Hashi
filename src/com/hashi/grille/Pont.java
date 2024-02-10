import java.awt.*;
import java.util.ArrayList;

public class Pont extends Case {
    private Ile ile1;
    private Ile ile2;
    private int nbPont;
    private boolean estDouble;
    private boolean estClique;
    public int nb_pont;

    public Pont(Ile ile1, Ile ile2) {
        super(0, 0);
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.nbPont = 1;
        this.estDouble = false;
        this.estClique = false;

        this.ile1.ajouterPont(this);
        this.ile2.ajouterPont(this);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        // efface tout les ponts

        // Dessine un pont simple si le nombre de ponts est 0
        System.out.println("nbPont : " + this.nbPont);
        if (this.nbPont == 0) {
            g.drawLine(ile1.x - 5, ile1.y - 5, ile2.x - 5, ile2.y - 5);
            g.drawLine(ile1.x + 5, ile1.y + 5, ile2.x + 5, ile2.y + 5);
            
        } else {
            // Dessine 2 ponts l'un a coté de l'autre si le nombre de ponts est 2
            g.drawLine(ile1.x, ile1.y, ile2.x, ile2.y);


        }
        

    }

    public Rectangle getBounds() {
        int x = (ile1.x + ile2.x) / 2 - 5; 
        int y = (ile1.y + ile2.y) / 2 - 5; 
        return new Rectangle(x, y, 20, 20);
    }

    public void effacer() {
        if (this.nbPont > 0) {
            this.nbPont = 0;
            this.ile1.retirerPont(this);
            this.ile2.retirerPont(this);
        }
    }

    public boolean isEffacable() {
        return nbPont == 0;
    }

    public Ile getIleDep() {
        return this.ile1;
    }

    public Ile getIleArr() {
        return this.ile2;
    }

    public int getNbPont() {
        return this.nbPont;
    }

    public boolean estValide() {
        if (this.ile1.equals(this.ile2) ) {
            return false;
        }

        int deltaX = Math.abs(ile1.x - ile2.x);
        int deltaY = Math.abs(ile1.y - ile2.y);

        if (deltaX > 1 || deltaY > 1) {
            return false;
        }

        if (this.nbPont == 2 || this.ile1.nb_connexion() >= this.ile1.getValeur() ||
                this.ile2.nb_connexion() >= this.ile2.getValeur()) {
            return false;
        }

        return true;
    }

    public void ajouterPont() {
        this.nbPont++;
    }

    public void retirerPont() {
        this.nbPont--;
    }

    public boolean estDouble() {
        return estDouble;
    }

    public void setEstDouble(boolean estDouble) {
        this.estDouble = estDouble;
    }

    public boolean estClique() {
        return estClique;
    }

    public void setEstClique(boolean estClique) {
        this.estClique = estClique;
    }

    public Ile getIle1() {
        return ile1;
    }

    public Ile getIle2() {
        return ile2;
    }

    public void setNbPont(int nbPont) {
        this.nbPont = nbPont;
    }
}