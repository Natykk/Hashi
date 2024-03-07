package com.hashi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.grid.Grille;
import com.hashi.grid.Ile;

public class AideTest {
    static Grille g = new Grille(5);

    @BeforeAll
    public static void initGrille() {
        g.initialiserTable();
    }

    @BeforeEach
    public void beforeMessage(TestInfo testInfo) {
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
}
