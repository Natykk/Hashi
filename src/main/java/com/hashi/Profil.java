package com.hashi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import com.hashi.grid.action.Action;

/**
 * La classe Profil représente le profil d'un utilisateur. Elle peut être
 * sérialisée et sauvegardée/rechargée.
 */
public class Profil implements Serializable {
    private String nomProfil;

    private int AvancementHistoire = 1;

    private ArrayList<Integer> listeScoreEntrainement; // Liste des scores mode Entrainement
    private ArrayList<Integer> listeTempsEntrainement; // Liste des temps de parties en cours mode Entrainement
    private ArrayList<List<Action>> listePartieEntrainement; // Liste des parties en cours mode Entrainement

    private ArrayList<Integer> listeScoreArcade;// Liste des records mode arcade

    private ArrayList<Integer> listeScoreHistoire; // Liste des scores mode Histoire
    private ArrayList<Integer> listeTempsHistoire; // Liste des temps de parties en cours mode Histoire
    private ArrayList<List<Action>> listePartieHistoire; // Liste des parties en cours mode Histoire

    public Profil(String nom) {
        nomProfil = nom;

        listeScoreEntrainement = new ArrayList<Integer>(54);
        listePartieEntrainement = new ArrayList<List<Action>>(54);
        listeTempsEntrainement = new ArrayList<Integer>(54);

        for (int i = 0; i < 54; i++) {
            listeScoreEntrainement.add(-1);
            listePartieEntrainement.add(new ArrayList<Action>());
            listeTempsEntrainement.add(0);
        }

        // La capacité de la liste a été réglé à 5 aussi dans ArcadeVictory.java
        listeScoreArcade = new ArrayList<Integer>(5);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);
        listeScoreArcade.add(0);

        listeScoreHistoire = new ArrayList<Integer>(12);
        listePartieHistoire = new ArrayList<List<Action>>(12);
        listeTempsHistoire = new ArrayList<Integer>(12);

        for (int i = 0; i < 12; i++) {
            listeScoreHistoire.add(0);
            listePartieHistoire.add(new ArrayList<Action>());
            listeTempsHistoire.add(0);
        }
    }

    /**
     * Récupère le nom du profil.
     * 
     * @return le nom du profil.
     */
    public String getNomProfil() {
        return nomProfil;
    }

    /**
     * Ajouter un score en mode Entrainement.
     * 
     * @param num
     * @param score
     */
    public void setScoreEntrainement(int num, int score) {
        if (listeScoreEntrainement.get(num) < score) {
            listeScoreEntrainement.set(num, score);
            setPartieEntrainement(num, new ArrayList<>());
            setTempsEntrainement(num, 0);
            sauvegarde();
        }
    }

    /**
     * Récupère le score en mode Entrainement.
     * 
     * @param num
     * @return le score.
     */
    public int getScoreEntrainement(int num) {
        return listeScoreEntrainement.get(num);
    }

    /**
     * Ajouter une sauvegarde d'un temps en mode Entrainement
     * 
     * @param num
     * @param temps
     */
    public void setTempsEntrainement(int num, int temps) {
        listeTempsEntrainement.set(num, temps);
        sauvegarde();
    }

    /**
     * Récupère le temps en mode Entrainement.
     * 
     * @param num
     * @return le temps.
     */
    public int getTempsEntrainement(int num) {
        return listeTempsEntrainement.get(num);
    }

    /**
     * Ajouter une sauvegarde d'une partie en mode Entrainement
     * 
     * @param num
     * @param partie
     */
    public void setPartieEntrainement(int num, List<Action> partie) {
        listePartieEntrainement.set(num, partie);
        sauvegarde();
    }

    /**
     * Récupère le partie en mode Entrainement.
     * 
     * @param num
     * @return le partie.
     */
    public List<Action> getPartieEntrainement(int num) {
        return listePartieEntrainement.get(num);
    }

    /**
     * Ajouter un score pour le mode Arcade
     * 
     * @param score
     */
    public void setScoreArcade(int score) {
        // Si le score est supérieur au score enregistré le plus faible
        if (score > listeScoreArcade.get(4)) {
            // On le remplace par ce score plus élevé.
            listeScoreArcade.add(4, score);
            // On trie la liste dans l'ordre décroissant.
            Collections.sort(listeScoreArcade);
            Collections.reverse(listeScoreArcade);
            listeScoreArcade.remove(5);
            sauvegarde();
        }
    }

    /**
     * Récupère les scores en mode Arcade.
     * 
     * @return les scores.
     */
    public List<Integer> getScoresArcade() {
        return listeScoreArcade;
    }

    /**
     * Ajouter un score en mode Histoire.
     * 
     * @param num
     * @param score
     */
    public void setScoreHistoire(int num, int score) {
        listeScoreHistoire.add(num, score);
        setPartieHistoire(num, new ArrayList<>());
        setTempsHistoire(num, 0);
        sauvegarde();
    }

    /**
     * Récupère le score en mode Histoire.
     * 
     * @param num
     * @return le score.
     */
    public int getScoreHistoire(int num) {
        return listeScoreHistoire.get(num);
    }

    /**
     * Ajouter une sauvegarde d'un temps en mode Histoire
     * 
     * @param num
     * @param temps
     */
    public void setTempsHistoire(int num, int temps) {
        listeTempsHistoire.set(num, temps);
        sauvegarde();
    }

    /**
     * Récupère le temps en mode Histoire.
     * 
     * @param num
     * @return le temps.
     */
    public int getTempsHistoire(int num) {
        return listeTempsHistoire.get(num);
    }

    /**
     * Ajouter une sauvegarde d'une partie en mode Histoire
     * 
     * @param num
     * @param partie
     */
    public void setPartieHistoire(int num, List<Action> partie) {
        listePartieHistoire.set(num, partie);
    }

    /**
     * Récupère le partie en mode Histoire.
     * 
     * @param num
     * @return le partie.
     */
    public List<Action> getPartieHistoire(int num) {
        return listePartieHistoire.get(num);
    }

    /**
     * Reset le mode Histoire.
     */
    public void resetHistoire() {
        setAvancementHistoire(1);

        listeScoreHistoire.clear();
        listePartieHistoire.clear();
        listeTempsHistoire.clear();

        for (int i = 0; i < 12; i++) {
            listeScoreHistoire.add(0);
            listePartieHistoire.add(new ArrayList<Action>());
            listeTempsHistoire.add(0);
        }

        sauvegarde();
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

    /**
     * Récupère la liste des noms de profils.
     * 
     * @return la liste des noms de profils.
     */
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
    public void sauvegarde() {
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

    protected void finalize() {
        this.sauvegarde();
    }

    /* Chargement d'un fichier existant. */
    /**
     * @param nom
     * @return
     */
    public static Profil charger(String nom) {
        createSaveDir();

        try {
            File fichier = new File("save/" + nom + ".ser");

            // ouverture d'un flux sur un fichier
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));

            // désérialization de l'objet
            Profil courant = (Profil) ois.readObject();

            ois.close();

            return courant;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Affiche le nom de la partie + les listes de scores.
     */
    public String toString() {
        return this.nomProfil + "\n" + this.listeScoreEntrainement + "\n" + this.listeScoreArcade + "\n"
                + this.listeScoreHistoire;
    }

    public int getAvancementHistoire() {
        return this.AvancementHistoire;
    }

    public void setAvancementHistoire(int avancement) {
        this.AvancementHistoire = avancement;
    }

}