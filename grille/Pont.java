import java.lang.*;
import java.awt.*;
import java.io.FilenameFilter;
public class Pont {
    Ile depart;
    Ile arrive;
    int nb_pont;

    public Pont(Ile depart , Ile arrIle){
        this.depart=depart;
        this.arrive=arrIle;
        this.depart.ajouterPont(this);
        this.arrive.ajouterPont(this);
        this.nb_pont=0;
        
        

    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        if (nb_pont==2){
        g.drawLine(depart.abs, depart.ordo+1, arrive.abs, arrive.ordo+1);
        g.drawLine(depart.abs, depart.ordo+1, arrive.abs, arrive.ordo+1);
        }
        else{
            g.drawLine(depart.abs, depart.ordo, arrive.abs, arrive.ordo);

        }
    }

    public void effacer (){
        if (!this.depart.supprimerPont(this)){
            this.nb_pont=0;
        }
        else{
            this.nb_pont-=1;
        }

    }
    public Rectangle getBounds() {
        int x = (depart.abs + arrive.abs) / 2 - 5; // Ajustez selon les besoins
        int y = (depart.ordo + arrive.ordo) / 2 - 5; // Ajustez selon les besoins
        return new Rectangle(x, y, 20, 20); // Ajustez selon les besoins
    }

    public  Ile getIleDep (){
        return this.depart;
    }
    public  Ile getIleArr(){
        return this.arrive;
    }


}
