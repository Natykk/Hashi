import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Ile {
    int valeur;
    int nb_connexion;
    int id;
    int abs;
    int ordo;
    int tailleIle;
    Color color;
    ArrayList<Pont> liste_pont;
    ArrayList<Ile> liste_voisin;
    public Ile (int valeur, int abs, int ordo, int taille, Color color){
        this.valeur= valeur;
        this.abs=abs;
        this.ordo=ordo;
        this.nb_connexion= 0;
        this.tailleIle=taille;
        this.color=color;
        this.liste_pont= new ArrayList<>();
        this.liste_voisin=  new ArrayList<>();
                
    }
    public Rectangle getBounds() {
       
        return new Rectangle(abs - tailleIle / 2, ordo - tailleIle / 2, tailleIle, tailleIle);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval(abs - 10, ordo - 10, 20, 20);
        g.drawString(Integer.toString(valeur), abs - 5, ordo + 5);
    }


    public int abs(){
        return this.abs;
    }
    public int ordo(){
        return this.ordo;
    }

    public void ajouterPont(Pont p){
        if(this.liste_pont.contains(p) && p.nb_pont<2){
            p.nb_pont+=1;
            this.liste_pont.add(p);
        }
        else{
            p.nb_pont+=1;
            this.liste_pont.add(p);
        }
    }
    public void ajouterVoisin(Ile voisin){
        this.liste_voisin.add(voisin);
    }
    public int  nb_connexion(){
        int tot=0;
            for( Pont n : liste_pont){
                tot+=n.nb_pont;
            }
            return tot;

        }
    

    public Boolean supprimerPont (Pont p){
       
        if(this.liste_pont.contains(p)){
            int k=liste_pont.indexOf(p);
            if(liste_pont.get(k).equals(p)){
                this.liste_pont.remove(p);
                return false;
            }
            else{
                Pont p1 =liste_pont.get(k);
                p1.nb_pont-=1;
                this.liste_pont.remove(p);
                this.liste_pont.add(p1);
            
            }
        }                
        return true;
    }
     public Boolean ileComplete (){
        if(liste_pont.size()==this.valeur)
            return true;
        return false;
     }



}
