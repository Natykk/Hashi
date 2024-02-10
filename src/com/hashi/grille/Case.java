public class Case{
    protected int x;
    protected int y;
    // référence vers la grille où cette île se situe
    static Grille grille;

    public Case(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setGrille(Grille new_grille){
        grille = new_grille;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean estVide(){
        return !(grille.getCase(x, y) instanceof Ile) 
            && !(grille.getCase(x, y) instanceof Pont);
    }

    public boolean estIle(){
        return grille.getCase(x, y) instanceof Ile;
    }

    public boolean estPont(){
        return grille.getCase(x, y) instanceof Pont;
    }

    public String afficher(){
        if (this.estVide()){
            return " ";
        }
        else{
            return grille.getCase(x, y).afficher();
        }
    }

    public int getValeur() {
        if (this.estIle()) {
            return ((Ile) grille.getCase(x, y)).getValeur();
        } else {
            return -1;
        }
    }

    

}