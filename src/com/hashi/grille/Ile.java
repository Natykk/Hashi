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



    /**
     * vérifie si le nombre de ponts de l'île est égal à sa valeur
     * @return vrai si le nombre de ponts de l'île est égal à sa valeur, faux sinon
     */
    public boolean isComplete() {
        return listePont.size() == valeur;
    }
    


    /**
     * vérifie si le nombre de ponts de l'île est inférieur à sa valeur
     * @return vrai si le nombre de ponts de l'île est inférieur à sa valeur, faux sinon
     */
    public boolean estLibre() {
        return listePont.size() < valeur;
    }
    


    /**
     * récupère les îles voisines (les îles sur le même axe cardinal que cette île, sans être bloqué par un pont)
     * @return une liste d'îles qui sont les îles voisines, ou null si l'île n'a aucune voisine
     */
    public ArrayList<Ile> getVoisins() {

        ArrayList<Ile> lesVoisins = new ArrayList<>();

        // récupération des îles voisines dans les quatre sens
        lesVoisins.add( grille.getVoisin(this, "haut") );
        lesVoisins.add( grille.getVoisin(this, "bas") );
        lesVoisins.add( grille.getVoisin(this, "gauche") );
        lesVoisins.add( grille.getVoisin(this, "droite") );

        // enlever les valeurs null, s'il y en a
        while (lesVoisins.remove(null));


        return lesVoisins;
    }



	/**
	 * compte le nombre d'île voisines de cette île qui n'ont pas encore tous leurs ponts de placés
	 * @return le nombre d'îles voisines qui n'ont pas encore tous leurs ponts de placés
	 */
	public int nbVoisinsLibres() {
        // à partir de la liste de ses voisins, 
        // compte le nombre d'îles qui satisfont la méthode estLibre()
        return (int) getVoisins().stream().filter( e -> e.estLibre() ).count();
	}



    /**
     * appelle toutes les méthodes d'aide pour cette île, si nécessaire
     */
    public void chercherAide() {

        // déjà, on fait rien sur les îles complètes
        if( !isComplete()) {

            // todo: appeler les méthodes d'aide
        }
    }
}
