package com.hashi;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.hashi.gestion_des_menus.EcranLancement;
import com.hashi.gestion_des_menus.MenuGeneral2;

public class MenuTest {
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
    public void ecranLancement() throws InvocationTargetException,
            InterruptedException {
        EcranLancement ecranLancement = new EcranLancement();

        while (ecranLancement.isVisible()) {
            Thread.sleep(1000);
        }
    }

    @Test
    public void menuGenerale() throws InvocationTargetException, InterruptedException {
        MenuGeneral2 menuGenerale = new MenuGeneral2();

        while (menuGenerale.isVisible()) {
            Thread.sleep(1000);
        }
    }
}
