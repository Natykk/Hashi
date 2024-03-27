package com.hashi;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.game.mode.ModeEntrainement;

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
        save.setScoreEntrainement(1, 150);
        save.setPartieEntrainement(1, new Hashi(new ModeEntrainement(0, 2, 5, false)).getActions());
        save.setScoreArcade(160);
        save.setScoreArcade(900);
        save.setScoreArcade(170);
        save.setScoreArcade(950);
        save.setScoreArcade(600);
        save.setScoreArcade(910);
        save.setScoreHistoire(5, 1);
        save.setPartieHistoire(1, new Hashi(new ModeEntrainement(0, 2, 5, false)).getActions());
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
