import java.util.List;

import javax.management.InvalidAttributeValueException;

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

    @Override
    public int getValeur() {
        return this.valeur;
    }



    /**
     * retourne le nombre de ponts reliés à cette île EN PRENANT EN COMPTE LES PONTS DOUBLES
     * @return le nombre de ponts en comptant les ponts doubles pour 2 reliés à cette île
     */
    public int nbConnexions() {
        int sum=0;

        for (Pont p : listePont) {
            // si le pont ests double, il compte pour 2
            sum += ( p.EstDouble() ? 2 : 1 );
        }

        return sum;
    }



    /**
     * vérifie si le nombre de ponts de l'île est égal à sa valeur
     * @return vrai si le nombre de ponts de l'île est égal à sa valeur, faux sinon
     */
    public boolean isComplete() {
        return this.nbConnexions() == valeur;
    }
    
    
    public String afficher() {
        return this.valeur + "";
    }
    
    public int getNbPonts() {
        return this.listePont.size();
    }


    /**
     * vérifie si le nombre de ponts de l'île est inférieur à sa valeur
     * @return vrai si le nombre de ponts de l'île est inférieur à sa valeur, faux sinon
     */
    public boolean estLibre() {
        return this.nbConnexions() < valeur;
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


        // la méthode getVoisin ne permet pas de récupérer les îles voisines qui sont déjà reliées par un pont, donc
        List<Pont> sesPonts = this.listePont;

        if( !sesPonts.isEmpty() ) {
            
            for (Pont unPont : sesPonts) {
                // pour chaque pont, on cherche l'île qui n'est pas uneIle
                if( unPont.getIle1() == this ) {
                    // si cette île est dans l'attribut -ile1, c'est qu'elle a une voisine dans -île2
                    lesVoisins.add( unPont.getIle2() );
                }
                else {
                    // et, logiquement, cette île est donc dans l'attribut -ile2, elle a une voisine dans -île1
                    lesVoisins.add( unPont.getIle1() );
                }
            }
        }

        // enlever les valeurs null, s'il y en a
        while (lesVoisins.remove(null));


        return lesVoisins;
    }



	/**
	 * compte le nombre d'île voisines de cette île qui n'ont pas encore tous leurs ponts de placés
	 * @return le nombre d'îles voisines qui n'ont pas encore tous leurs ponts de placés
	 */
	public int nbVoisinsLibres() {
        // récupérer la liste de voisins de l'île
        ArrayList<Ile> lesVoisins = this.getVoisins();

        // pas besoin de faire toutes les étapes en-dessous si la liste est vide
        if( lesVoisins.isEmpty() ) {
            return 0;
        }

        // à partir de la liste de ses voisins, 
        // compte le nombre d'îles qui satisfont la méthode estLibre()
        return (int) getVoisins().stream().filter( e -> e.estLibre() ).count();
	}



    /**
     * vérifie les techniques pour quand une île a besoin de beaucoup de ponts, mais qu'elle a peu de voisins
     * la technique spécifique sera décidée par l'attribut -valeur de l'île dans une autre fonction
     * @return vrai si une de ces techniques s'applique
     * @throws InvalidAttributeValueException si l'attribut -valeur de l'île n'est pas compris dans [1,8]
     */
    public boolean techniquePontsForces() throws InvalidAttributeValueException {

        switch( this.valeur ) {
            case 1:
                // une île qui a besoin d'un pont, 
                // n'en a actuellement aucun
                // et qui n'a qu'un seul voisin libre
            case 2:
                // une île qui a besoin de 2 ponts, 
                // en a actuellement moins de 2
                // et qui n'a qu'un seul voisin libre
                if( this.nbVoisinsLibres() == 1
                 && this.nbConnexions() < this.valeur ) {
                    // la même condition peut s'appliquer pour le cas 1 et 2
                    return true;
                }
            case 3:
                // une île qui a besoin de 3 ponts, 
                // en a actuellement moins de 2 dans des sens différents
                // et qui n'a que 2 voisins libres
                if( this.nbVoisinsLibres() == 2
                 && this.listePont.size() < 2 ) {
                    return true;
                }
            case 4:
                // une île qui a besoin de 4 ponts, 
                // en a actuellement moins de 4
                // et qui n'a que 2 voisins libres
                if( this.nbVoisinsLibres() == 2
                 && this.nbConnexions() < this.valeur ) {
                    return true;
                }
            case 5:
                // une île qui a besoin de 5 ponts, 
                // en a actuellement moins de 3 dans des sens différents
                // et qui n'a que 3 voisins libres
                if( this.nbVoisinsLibres() == 3 
                 && this.listePont.size() < 3) {
                    return true;
                }
            case 6:
                // une île qui a besoin de 6 ponts, 
                // en a actuellement moins de 6
                // et qui n'a que 3 voisins libres
                if( this.nbVoisinsLibres() == 3 
                 && this.nbConnexions() < this.valeur ) {
                    return true;
                }
            case 7:
                // une île qui a besoin de 7 ponts 
                // et en a actuellement moins de 4 dans des sens différents
                if( this.listePont.size() < 4 ) {
                    return true;
                }
            case 8:
                // une île qui a besoin de 8 ponts 
                // et en a actuellement moins de 8
                if( this.nbConnexions() < 8 ) {
                    return true;
                }
            default:
                throw new InvalidAttributeValueException("erreur techniquePontsForces(): l'attribut -valeur de "+this+" n'est pas compris dans [1,8]");
        }
    }



    /**
     * vérifie les techniques pour quand une île a un voisin qui lui fournit 1/2 ponts et qui est complété
     * @return vrai si une de ces techniques s'applique
     * @throws InvalidAttributeValueException si l'attribut -valeur de l'île n'est pas compris dans [1,8]
     */
    public boolean techniquePontsBloques() throws InvalidAttributeValueException {

        switch ( this.valeur ) {
            case 3:
                // une île qui a besoin de 3 ponts, 
                // en a actuellement moins de 3
                // et qui n'a qu'un voisin libre dont il partage 1 pont
                if( this.nbVoisinsLibres() == 1
                 && this.nbConnexions() < this.valeur ) {
                    // todo: vérifier que un pont mène à une île complète?
                    
                    return true;
                }
            case 4:
                
            case 5:
                
            case 6:
                
            case 7:
                
        
            default:
                throw new InvalidAttributeValueException("erreur techniquePontsForces(): l'attribut -valeur de "+this+" n'est pas compris dans [1,8]");
        }
    }



    /**
     * appelle toutes les méthodes d'aide pour cette île, si nécessaire
     */
    public void chercherAide() {

        // déjà, on fait rien sur les îles complètes
        if( !isComplete()) {

            // todo: appeler les méthodes d'aide
            //this.techniquePontsForces();
        }
    }
}
