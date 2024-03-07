package com.hashi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.grid.Grille;
import com.hashi.grid.Ile;

public class GrilleTest {
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
	public void getTailleAndInitialiserTable() {
		Grille z = new Grille(2);
		z.initialiserTable();

		assertEquals(2, z.getTaille());

		for (int i = 0; i < z.getTaille(); i++) {
			for (int j = 0; j < z.getTaille(); j++) {

				assertTrue(z.getCase(i, j).estVide());
			}
		}
	}

	@Test
	public void ajouterIle() {
		Ile ile1 = new Ile(1, 0, 1, g);

		g.ajouterIle(ile1);

		assertEquals(ile1, g.getCase(0, 1));
		assertEquals(ile1, g.getIleAt(0, 1));
	}

	@Test
	public void isInBound() {

		assertTrue(g.isInBound(0, 0));
		assertTrue(g.isInBound(4, 4));

		assertFalse(g.isInBound(-1, 0));
		assertFalse(g.isInBound(0, -1));

		assertFalse(g.isInBound(5, 0));
		assertFalse(g.isInBound(0, 5));
	}

}
