package com.hashi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.grille.Grille;
import com.hashi.grille.Ile;
import com.hashi.grille.Pont;

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
	public void isInBound() {
		
		assertEquals(true, g.isInBound(0,0));
		assertEquals(true, g.isInBound(4,4));

		assertEquals(false, g.isInBound(-1,0));
		assertEquals(false, g.isInBound(0,-1));

		assertEquals(false, g.isInBound(5,0));
		assertEquals(false, g.isInBound(0,5));
	}
    
}
