package com.hashi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.grid.Grille;
import com.hashi.grid.Ile;
import com.hashi.grid.Jeu;

public class AideTest {
    static Jeu j = new Jeu();
    static Grille g = new Grille(5);

    @BeforeEach
    public void beforeMessage(TestInfo testInfo) {
        g.initialiserTable();

        System.out.println(
                "===================== Start testing : " + testInfo.getDisplayName() + " =====================");
    }

    @AfterEach
    public void afterMessage(TestInfo testInfo) {
        System.out.println(
                "===================== Test ended OK : " + testInfo.getDisplayName() + " =====================");
    }

    @Test
    public void getVoisins() {
        Ile i1 = new Ile(1, 0, 2, g);// en dessous de i3
        Ile i2 = new Ile(2, 2, 0, g);// à droite de i3
        Ile i3 = new Ile(3, 0, 0, g);// coin haut gauche

        List<Ile> listeGetVoisins = new ArrayList<>();
        List<Ile> uneListeDIle = new ArrayList<>();

        g.ajouterIle(i3);
        g.ajouterIle(i1);
        g.ajouterIle(i2);
        g.fillListVoisins();

        listeGetVoisins = i3.getVoisins();
        uneListeDIle.add(i1); // bas
        uneListeDIle.add(i2); // droite

        // getVoisins() doit donner une liste contenant i1 et i2
        assertEquals(uneListeDIle, listeGetVoisins);

        /*
         * // ==== affichage résultats
         * 
         * System.out.println("la grille:");
         * System.out.println(g.afficher());
         * 
         * System.out.println("liste des voisins de i3:");
         * for (Ile ile : uneListeDIle) {
         * System.out.print(ile.afficher() + ",");
         * }
         * System.out.println();
         */
    }

    @Test
    public void ile3A2Voisins() {

        Ile i1 = new Ile(1, 0, 2, g);
        Ile i2 = new Ile(2, 2, 0, g);
        Ile i3 = new Ile(3, 0, 0, g);

        List<Ile> uneListeDIle = new ArrayList<>();

        g.ajouterIle(i3);
        g.ajouterIle(i1);
        g.ajouterIle(i2);
        g.fillListVoisins();

        uneListeDIle = i3.getVoisins();

        // i3 a 2 voisins
        assertEquals(2, uneListeDIle.size());

        // l'ordre de la recherche est : haut,bas,gauche,droite
        // voisin du bas
        assertEquals(i1, uneListeDIle.get(0));
        // voisin de droite
        assertEquals(i2, uneListeDIle.get(1));

        /*
         * // ==== affichage résultats
         * 
         * System.out.println("la grille:");
         * System.out.println(g.afficher());
         * 
         * System.out.println("i3.techniquePontsForces()");
         * System.out.println(i3.techniquePontsForces());
         */
    }

    @Test
    public void techniquePontsForces1() {
        // charger des grilles
        j.genererGrilleDepuisFichier("grille.txt");
        g = j.listeGrille.get(0);

        // la grille #0 ressemble à (?)
        // _ _ 3 _ 2 _ 1
        // 1 _ _ _ _ _ _
        // _ _ _ _ _ _ _
        // 3 _ _ _ 2 _ _
        // _ _ _ _ _ _ _
        // _ _ _ _ _ _ _
        // 3 _ 5 _ 4 _ 2

        System.out.println(g.toString());

        Ile i1 = g.getIleAt(0, 2);

        if (i1 != null) {
            System.out.println("valeur : " + i1.getValeur());

            System.out.println("voisins: ");
            for (Ile i : i1.getVoisins()) {
                System.out.println(i.getValeur());
            }

            System.out.println("nb voisins libres : " + i1.getVoisinsLibres().size());

            // assertEquals( Aide.FORCE1, i1.techniquePontsForces() );
        } else {
            System.out.println("i1 est null");
        }

    }

}
