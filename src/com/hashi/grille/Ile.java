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
    
    
    public void afficher() {
        System.out.print(this.valeur);
    }
    

}
