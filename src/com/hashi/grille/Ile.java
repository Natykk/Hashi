import java.util.List;
import java.util.ArrayList;

public class Ile extends Case{
    // attributs hérités
        // int x, y; // coordonnées de la position dans la grille
        // static Grille grille; // référence vers la grille où cette île se situe
    private int valeur;
    
    private List<Pont> listePont;


    protected Ile(int valeur,int x,int y,Grille lagrille) {
        super(x,y);
        setGrille(lagrille);

        this.valeur = valeur;
        this.listePont = new ArrayList<>();
    }

    public Case getPosition() {
        return new Case(this.x, this.y);
    }
    
    
    public void afficher() {
        System.out.print(this.valeur);
    }
    

}
