import java.util.ArrayList;




public class Pont {
    private Ile ile1; // Ile d'où part l'pont
    private Ile ile2; // Ile où arrive l'pont
    private boolean estDouble; // Booléen définissant si une pont est double ou non
    private ArrayList<Case> listeCase; // Liste des cases par lesquelles passe l'Pont

    protected Pont(Ile ile1, Ile ile2, boolean estDouble){
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

    public boolean EstDouble() {
        return this.estDouble;
    }


    
    public void supprimer() {
        while (!this.listeCase.isEmpty()) {
            Case laCase = this.listeCase.remove(0);
            laCase.setContenu(null);
        }
        /* 
        this.ile1.getPosition().getGrille().retirerPont(this);
        this.ile1.retirerPont(this);
        this.ile2.retirerPont(this); */
    }


    public void afficher() {
        if (this.ile1.getPosition().getX() == this.ile2.getPosition().getX()) {
            if (this.estDouble) {
                System.out.print("=");
            } else {
                System.out.print("-");
            }
        } else {
            if (this.estDouble) {
                System.out.print("‖");
            } else {
                System.out.print("|");
            }
        }
    }
    
}
