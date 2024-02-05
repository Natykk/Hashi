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

}
