package com.hashi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import com.hashi.grid.Action;

/**
 * La classe Profil représente le profil d'un utilisateur. Elle peut être
 * sérialisée et sauvegardée/rechargée.
 */
public class Profil implements Serializable {
    public String nomProfil;

    public ArrayList<Integer> listeDesScores; // Liste des scores mode Entrainement
    public ArrayList<List<Action>> listePartieEntrainement; // Liste des parties en cours mode Entrainement

    public ArrayList<Integer> listeScoreArcade;// Liste des records mode arcade

    public ArrayList<Integer> listeDesScoresHistoire; // Liste des scores mode Histoire
    public ArrayList<List<Action>> listePartieHistoire; // Liste des parties en cours mode Histoire

    public Profil(String nom) {
        nomProfil = nom;
        List<Action> la = null;

        listeDesScores = new ArrayList<Integer>(54);
        listePartieEntrainement = new ArrayList<List<Action>>();
        for (int i = 0; i < 54; i++) {
            listeDesScores.add(0);
        }
        for (int i = 0; i < 54; i++) {
            listePartieEntrainement.add(la);
        }

        listeScoreArcade = new ArrayList<Integer>(5);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);

        listeDesScoresHistoire = new ArrayList<Integer>(12);
        for (int i = 0; i < 12; i++) {
            listeDesScoresHistoire.add(0);
        }
        listePartieHistoire = new ArrayList<List<Action>>();
        for (int i = 0; i < 12; i++) {
            listePartieHistoire.add(la);
        }
    }

    /**
     * Ajouter un score en mode Entrainement.
     * 
     * @param num
     * @param score
     */
    public void addNewScoreEntrainement(int num, int score) {
        if (listeDesScores.get(num) == null) {
            listeDesScores.add(num, score);
        } else if (listeDesScores.get(num) < score) {
            listeDesScores.add(num, score);
        }
    }

    /**
     * Ajouter une sauvegarde d'une partie en mode Entrainement
     * 
     * @param num
     * @param partie
     */
    public void addNewPartieEntrainement(int num, List<Action> partie) {
        listePartieEntrainement.add(num, partie);
    }

    /**
     * Ajouter un score pour le mode Arcade
     * 
     * @param score
     */
    public void addNewScoreArcade(int score) {
        // Si le score est supérieur au score enregistré le plus faible
        if (score > listeScoreArcade.get(4)) {
            // On le remplace par ce score plus élevé.
            listeScoreArcade.add(4, score);
            // On trie la liste dans l'ordre décroissant.
            Collections.sort(listeScoreArcade);
            Collections.reverse(listeScoreArcade);
            listeScoreArcade.remove(5);
        }
    }

    /**
     * Ajouter un score en mode Histoire.
     * 
     * @param num
     * @param score
     */
    public void addNewScoreHistoire(int num, int score) {
        listeDesScoresHistoire.add(num, score);
    }

    /**
     * Ajouter une sauvegarde d'une partie en mode Histoire
     * 
     * @param num
     * @param partie
     */
    public void addNewPartieHistoire(int num, List<Action> partie) {
        listePartieHistoire.add(num, partie);
    }

    protected static void createSaveDir() {
        try {
            File directory = new File("save");

            if (!directory.exists())
                directory.mkdir();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<String> getListeNomProlil() {
        createSaveDir();

        List<String> listeNom = new ArrayList<>();

        try {
            for (String filename : new File("save").list()) {
                listeNom.add(filename.substring(0, filename.length() - 4));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listeNom;
    }

    /**
     * Lorsqu'un profil est fermé, on enregistre les changements.
     */
    public void sauvegarde() throws Throwable {
        createSaveDir();

        try {
            File fichier = new File("save/" + nomProfil.toString() + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));

            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    protected void finalize() throws Throwable {
        this.sauvegarde();
    }

    /* Chargement d'un fichier existant. */
    /**
     * @param nom
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Profil charger(String nom) throws IOException, ClassNotFoundException {
        createSaveDir();

        Profil courant = null;
        try {
            File fichier = new File("save/" + nom + ".ser");

            // ouverture d'un flux sur un fichier
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));

            // désérialization de l'objet
            courant = (Profil) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return courant;
    }

    /**
     * Affiche le nom de la partie + les listes de scores.
     */
    public String toString() {
        return this.nomProfil + "\n" + this.listeDesScores + "\n" + this.listeScoreArcade + "\n"
                + this.listeDesScoresHistoire;
    }

}