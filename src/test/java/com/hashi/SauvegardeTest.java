package com.hashi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.grid.Grille;
import com.hashi.grid.Ile;

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
        save.addNewScoreEntrainement(1, 150);
        save.addNewPartieEntrainement(1, new Hashi(new Grille(15)).actions);
        save.addNewScoreArcade(160);
        save.addNewScoreArcade(900);
        save.addNewScoreArcade(170);
        save.addNewScoreArcade(950);
        save.addNewScoreArcade(600);
        save.addNewScoreArcade(910);
        save.addNewScoreHistoire(5, 1);
        save.addNewPartieHistoire(1, new Hashi(new Grille(15)).actions);
        System.out.println(save);
        save.sauvegarde();
	}

    @Test
    public void chargerProfil() throws ClassNotFoundException, IOException {
        Profil charge = Profil.charger("Toto");
        System.out.println(charge);
    }

}
