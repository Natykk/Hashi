import java.util.List;
import java.util.ArrayList;

public class Ile extends Case{
    // int taille; (px)
    private int valeur;
    private List<Pont> listePont;


    protected Ile(int valeur,int x,int y,Grille lagrille) {
        super(x,y);
        this.valeur = valeur;
        this.listePont = new ArrayList<>();

        grille = lagrille;
    }

    public Case getPosition() {
        return new Case(this.x, this.y);
    }

    @Override
    public int getValeur() {
        return this.valeur;
    }
    
    
    public String afficher() {
        return this.valeur + "";
    }
    
    public int getNbPonts() {
        return this.listePont.size();
    }

    public int getNbPontDroite() {
        int nbPontDroite = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle1().equals(this)) {
                nbPontDroite++;
            }
        }
        return nbPontDroite;
    }

    public int getNbPontGauche() {
        int nbPontGauche = 0;
        for (Pont pont : this.listePont) {
            if (pont.getIle2().equals(this)) {
                nbPontGauche++;
            }
        }
        return nbPontGauche;
    }

    public void ajouterPont(Pont pont) {
        this.listePont.add(pont);
    }

    public void retirerPont(Pont pont) {
        this.listePont.remove(pont);
    }

    public List<Pont> getListePonts() {
        return this.listePont;
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
  





 



}
