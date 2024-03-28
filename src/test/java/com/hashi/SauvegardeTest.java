package com.hashi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.grid.action.Action;
import com.hashi.grid.action.ResetGrilleAction;

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
        List<Action> actions = new ArrayList<>();

        actions.add(new ResetGrilleAction(new ArrayList<>()));

        save.setScoreEntrainement(1, 150);
        save.setPartieEntrainement(1, actions);
        save.setScoreArcade(160);
        save.setScoreArcade(900);
        save.setScoreArcade(170);
        save.setScoreArcade(950);
        save.setScoreArcade(600);
        save.setScoreArcade(910);
        save.setScoreHistoire(5, 1);
        save.setPartieHistoire(1, actions);
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
