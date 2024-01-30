public class Case{
    protected int x;
    protected int y;
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
        return grille.getCase(x, y) == null;
    }

    public boolean estIle(){
        return grille.getCase(x, y) instanceof Ile;
    }

    public boolean estPont(){
        return grille.getCase(x, y) instanceof Pont;
    }

    

}