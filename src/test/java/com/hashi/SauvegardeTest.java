package com.hashi;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.grid.Grille;

public class SauvegardeTest {
    Profil save = new Profil("Toto");

    @BeforeAll
    public static void initGrille() {

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
    public void sauvegardeProfil() throws Throwable {
        save.addScoreEntrainement(1, 150);
        save.addPartieEntrainement(1, new Hashi(new Grille(15)).getActions());
        save.addScoreArcade(160);
        save.addScoreArcade(900);
        save.addScoreArcade(170);
        save.addScoreArcade(950);
        save.addScoreArcade(600);
        save.addScoreArcade(910);
        save.addScoreHistoire(5, 1);
        save.addPartieHistoire(1, new Hashi(new Grille(15)).getActions());
        System.out.println(save);
        save.sauvegarde();
    }

    @Test
    public void getListeNomProlil() throws ClassNotFoundException, IOException {
        System.out.println(Profil.getListeNomProlil());
    }

    @Test
    public void chargerProfil() throws ClassNotFoundException, IOException {
        Profil charge = Profil.charger("Toto");
        System.out.println(charge);
    }

}
