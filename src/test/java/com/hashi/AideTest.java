package com.hashi;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.grille.Grille;
import com.hashi.grille.Ile;

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
        Ile i1 = new Ile(1, 0, 2, g);
        Ile i2 = new Ile(1, 2, 0, g);
        Ile i3 = new Ile(3, 0, 0, g);

        ArrayList<Ile> uneListeDIle = new ArrayList<>();

        g.ajouterIle(i3);
        g.ajouterIle(i1);
        g.ajouterIle(i2);

        uneListeDIle = i3.getVoisins();

        // ==== affichage résultats

        System.out.println("la grille:");
        System.out.println(g.afficher());

        System.out.println("liste des voisins de i3:");
        for (Ile ile : uneListeDIle) {
            System.out.print(ile.afficher() + ",");
        }
        System.out.println();
    }

    @Test
    public void ile3A2Voisins() {

        Ile i1 = new Ile(1, 0, 2, g);
        Ile i2 = new Ile(2, 2, 0, g);
        Ile i3 = new Ile(3, 0, 0, g);

        // ArrayList<Ile> uneListeDIle = new ArrayList<>();

        g.ajouterIle(i3);
        g.ajouterIle(i1);
        g.ajouterIle(i2);

        // uneListeDIle = i3.getVoisins();

        // ==== affichage résultats

        System.out.println("la grille:");
        System.out.println(g.afficher());

        System.out.println("i3.techniquePontsForces()");
        System.out.println(i3.techniquePontsForces());
    }
}
