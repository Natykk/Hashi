import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;




public class Pont extends Case{
    private Ile ile1; // Ile d'où part l'pont
    private Ile ile2; // Ile où arrive l'pont
    private boolean estDouble; // Booléen définissant si une pont est double ou non

    private ArrayList<Case> listeCase; // Liste des cases par lesquelles passe l'Pont
    public boolean estClique;


    protected Pont(Ile ile1, Ile ile2, boolean estDouble,int x,int y){
        super(x,y);
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.estDouble = estDouble;
        this.listeCase = new ArrayList<>();


    }



    public Ile getIle1() {
        return this.ile1;
    }

    public Ile getIle2() {
        return this.ile2;
    }

    public ArrayList<Case> getListeCase() {
        return this.listeCase;
    }

    public void ajoutCase( Case c ) {
        this.listeCase.add(c);
    }

    public boolean EstDouble() {
        return this.estDouble;
    }



    /**
     * est-ce que le Pont est horizontal ou vertical
     * @return vrai si le Pont est horizontal, faux si il est vertical
     * @throws InvalidAttributeValueException si les 2 îles que le pont relie ne sont pas alignées horizontalement ni verticalement
     */
    public boolean estHorizontal() throws InvalidAttributeValueException {

        if( this.ile1.getX() == this.ile2.getX() ) {
            // si les 2 îles que le pont relie sont sur le même axe X (horizontal)
            // c'est un pont horizontal
            return true;
        }
        else if( this.ile1.getY() == this.ile2.getY() ) {
            // si les 2 îles que le pont relie sont sur le même axe Y (vertical)
            // c'est un pont vertical
            return false;
        }
        else {
            throw new InvalidAttributeValueException();
        }
    }


    
    public void supprimer() {
        while (!this.listeCase.isEmpty()) {
            Case laCase = this.listeCase.remove(0);
        }
        
        //this.ile1.getPosition().getGrille().retirerPont(this);
        this.ile1.retirerPont(this);
        this.ile2.retirerPont(this);
    }
  
  
  
    public boolean estValide() {
        if (ile1 == null || ile2 == null) {
            return false;
        }
        if (this.ile1.equals(this.ile2)) {
            return false;
        }
        if (this.ile1.getPosition().equals(this.ile2.getPosition())) {
            return false;
        }
        if (this.ile1.getPosition().getX() == this.ile2.getPosition().getX()) {
            if (this.ile1.getPosition().getY() == this.ile2.getPosition().getY() - 1) {
                if (this.ile1.getNbPontDroite() == 2 || this.ile2.getNbPontGauche() == 2) {
                    return false;
                }
            } else if (this.ile1.getPosition().getY() == this.ile2.getPosition().getY() + 1) {
                if (this.ile1.getNbPontGauche() == 2 || this.ile2.getNbPontDroite() == 2) {
                    return false;
                }
            } else {
                return false;
            }
        } else if (this.ile1.getPosition().getY() == this.ile2.getPosition().getY()) {
            if (this.ile1.getPosition().getX() == this.ile2.getPosition().getX() - 1) {
                if (this.ile1.getNbPontBas() == 2 || this.ile2.getNbPontHaut() == 2) {
                    return false;
                }
            } else if (this.ile1.getPosition().getX() == this.ile2.getPosition().getX() + 1) {
                if (this.ile1.getNbPontHaut() == 2 || this.ile2.getNbPontBas() == 2) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public Ile getCaseDepart() {
        return this.ile1;
    }

    public Ile getCaseArrivee() {
        return this.ile2;
    }


    public String afficher() {
        if (this.estDouble) {
            return "=";
        } else {
            return "-";
        }
    }
    
}
