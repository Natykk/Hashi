import java.util.List;
import java.util.ArrayList;

public class Ile {
    // int taille; (px)
    private int valeur;
    private List<Pont> listePont;


    protected Ile(int valeur,int x,int y,Grille lagrille) {
        this.valeur = valeur;
        this.listePont = new ArrayList<>();
        this.x = x;
        this.y = y;
        grille = lagrille;
    }

    public getPosition() {
        return new Case(x,y);
    }

}
