
import java.awt.event.MouseAdapter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
//package com.hashi.grille;

import org.w3c.dom.events.MouseEvent;



public class Grille extends MouseAdapter {
    private int taille; // coté de la grille² 
    private Case[][] table; // La matrice des Case de la grille
    private List<Ile> Iles; // Le Tableau des Iles de la grille
    private List<Pont> Ponts; // Le Tableau des ponts de la grille
    private Case selectedCase; // Case selectionnée par l'utilisateur

    protected Grille(int taille){
        this.table = new Case[taille][taille];
        this.taille = taille;
        this.Iles = new ArrayList<Ile>();
        this.Ponts = new ArrayList<Pont>();
        
    }


    

    public Case getCase(int x, int y) {
        return this.table[x][y];
    }

    public void setCase(int x, int y,Case new_case) {
        this.table[x][y] = new_case;
    }

    public void ajouterIle(Ile ile) {
        this.Iles.add(ile);
    }

    public void ajouterPont(Pont pont) {
        this.Ponts.add(pont);
    }

    public void retirerPont(Pont pont) {
        this.Ponts.remove(pont);
    }

    public ArrayList<Pont> getListePonts() {
        return (ArrayList<Pont>) this.Ponts;
    }
    

    public String afficher() {
        String res = "";
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.table[i][j] == null) {
                    res += " ";
                } else {
                    res += this.table[i][j].afficher();
                }
            }
            res += "\n";
        }
        return res;
    }

    public int getTaille() {
        return this.taille;
    }

    public String afficherIles() {
        String res = "";
        for (Ile ile : this.Iles) {
            if(ile!=null){
                res += ile.afficher();
            }else{
                res += "*";
            }
        }
        return res;
    }

    public String afficherPonts() {
        String res = "";
        for (Pont pont : this.Ponts) {
            res += pont.afficher();
        }
        return res;
    }

    public void afficherGrille() {
        this.afficher();
    }


    public int[][] getGridAsArray(){
        int[][] res = new int[this.taille][this.taille];
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.table[i][j] == null) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = this.table[i][j].getValeur();
                }
            }
        }
        return res;
    }

    // Methode qui verifie si le pont a créer est valide ( les ponts ne se croisent pas, les ponts ne sont pas en diagonale, les ponts ne sont pas superposés)
    public boolean pontValide(Pont pont) {
        if (pont.getIle1().getX() == pont.getIle2().getX()) {
            int x = pont.getIle1().getX();
            int y1 = pont.getIle1().getY();
            int y2 = pont.getIle2().getY();
            if (y1 < y2) {
                for (int i = y1 + 1; i < y2; i++) {
                    if (this.table[x][i] != null) {
                        return false;
                    }
                }
            } else {
                for (int i = y2 + 1; i < y1; i++) {
                    if (this.table[x][i] != null) {
                        return false;
                    }
                }
            }
        } else if (pont.getIle1().getY() == pont.getIle2().getY()) {
            int y = pont.getIle1().getY();
            int x1 = pont.getIle1().getX();
            int x2 = pont.getIle2().getX();
            if (x1 < x2) {
                for (int i = x1 + 1; i < x2; i++) {
                    if (this.table[i][y] != null) {
                        return false;
                    }
                }
            } else {
                for (int i = x2 + 1; i < x1; i++) {
                    if (this.table[i][y] != null) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }


    

        
} 
