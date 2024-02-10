import java.awt.*;

public class Pont2 {
    Ile2 depart;
    Ile2 arrive;
    int nb_pont;

    public Pont2(Ile2 depart , Ile2 arrIle){
        this.depart=depart;
        this.arrive=arrIle;
        this.depart.ajouterPont(this);
        this.arrive.ajouterPont(this);
        this.nb_pont=1;
        
        

    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        
        if (this.nb_pont==2){
            g.drawLine(depart.abs, depart.ordo, arrive.abs, arrive.ordo);
            g.drawLine(depart.abs+3, depart.ordo+3, arrive.abs+3, arrive.ordo+3);
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
    public boolean isEffacable() {
        return nb_pont == 0;
    }

    public Rectangle getBounds() {
        int x = (depart.abs + arrive.abs) / 2 - 5; // Ajustez selon les besoins
        int y = (depart.ordo + arrive.ordo) / 2 - 5; // Ajustez selon les besoins
        return new Rectangle(x, y, 20, 20); // Ajustez selon les besoins
    }

    public  Ile2 getIleDep (){
        return this.depart;
    }
    public  Ile2 getIleArr(){
        return this.arrive;
    }


}
