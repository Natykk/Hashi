import java.lang.*;
import java.awt.Rectangle;
import java.awt.*;
public class Pont {
    Ile depart;
    Ile arrive;
    int nb_pont;
    // 1 horizontale , 2 verticale
    int orientation;

    public Pont(Ile depart , Ile arrIle, int orientation){
        this.depart=depart;
        this.arrive=arrIle;
        this.depart.ajouterPont(this);
        this.arrive.ajouterPont(this);
        this.nb_pont=0;
        this.orientation=orientation;
        

    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(depart.abs, depart.ordo, arrive.abs, arrive.ordo);
    }

    public void effacer (){
        if (!this.depart.supprimerPont(this)){
            this.nb_pont=0;
        }
        else{
            this.nb_pont-=1;
        }

    }
    // Ajouter cette méthode pour obtenir les coordonnées du pont
    public Rectangle getBounds() {
        int x = (depart.abs + arrive.abs) / 2 - 5; // Ajustez selon les besoins
        int y = (depart.ordo + arrive.ordo) / 2 - 5; // Ajustez selon les besoins
        return new Rectangle(x, y, 10, 10); // Ajustez selon les besoins
    }

    public  Ile getIleDep (){
        return this.depart;
    }
    public  Ile getIleArr(){
        return this.arrive;
    }


}
