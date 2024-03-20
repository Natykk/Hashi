package com.hashi.menu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.hashi.Hashi;
import java.util.*;

public class Profil implements Serializable {
    public String nomProfil ;

    public ArrayList<Integer> listeDesScores ; //Liste des scores mode Entrainement
    public ArrayList<Hashi> listePartieEntrainement ; //Liste des parties en cours mode Entrainement

    public ArrayList<Integer> listeScoreArcade ;//Liste des records mode arcade

    public ArrayList<Integer> listeDesScoresHistoire ; //Liste des scores mode Histoire
    public ArrayList<Hashi> listePartieHistoire; //Liste des parties en cours mode Histoire

    public Profil (String nom){
        nomProfil = nom ;

        listeDesScores = new ArrayList<Integer>();
        listePartieEntrainement = new ArrayList<Hashi>();

        listeScoreArcade = new ArrayList<Integer>();
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);

        listeDesScoresHistoire = new ArrayList<Integer>();
        listePartieHistoire = new ArrayList<Hashi>();
    }

    /**
     * Ajouter un score en mode Entrainement.
     * @param num
     * @param score
     */
    public void addNewScoreEntrainement (int num, int score){
        if (listeDesScores.get(num) == null){
            listeDesScores.add(num, score) ;
        }
        else if (listeDesScores.get(num) < score){
            listeDesScores.add(num, score) ;
        }
    }

    /**
     * Ajouter une sauvegarde d'une partie en mode Entrainement
     * @param num
     * @param partie
     */
    public void addNewPartieEntrainement (int num, Hashi partie){
        listePartieEntrainement.add(num, partie);
    }

    /**
     * Ajouter un score pour le mode Arcade
     * @param score
     */
    public void addNewScoreArcade (int score){
        //Si le score est supérieur au score enregistré le plus faible
        if (score > listeScoreArcade.get(4)){
            //On le remplace par ce score plus élevé.
            listeScoreArcade.add(4, score);
            //On trie la liste dans l'ordre décroissant.
            Collections.sort(listeScoreArcade);
            Collections.reverse(listeScoreArcade);
        }
    }

    /**
     * Ajouter un score en mode Histoire.
     * @param num
     * @param score
     */
    public void addNewScoreHistoire (int num, int score){
        listeDesScoresHistoire.add(num, score) ;
    }

    /**
     * Ajouter une sauvegarde d'une partie en mode Histoire
     * @param num
     * @param partie
     */
    public void addNewPartieHistoire (int num, Hashi partie){
        listePartieEntrainement.add(num, partie);
    }

    /**
     * Lorsqu'un profil est fermé, on enregistre les changements.
     */
    protected void finalize() throws Throwable{
        File fichier =  new File("../resources/save/" + nomProfil.toString()+ ".ser") ;

        ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;

        oos.writeObject(this) ;
        oos.close();
    }

    /*Chargement d'un fichier existant.*/
    /**
     * @param nom
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected Profil charger (String nom) throws IOException, ClassNotFoundException{
        Profil courant = null ;
        try {
            File fichier =  new File("../resources/save/" + nom + ".ser") ;

            // ouverture d'un flux sur un fichier
            ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;
                    
            // désérialization de l'objet
            courant = (Profil)ois.readObject() ;
            ois.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return courant ; 
    }

}