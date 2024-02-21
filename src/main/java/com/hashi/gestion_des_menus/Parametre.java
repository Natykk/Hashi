package com.hashi.gestion_des_menus;

import javax.swing.*;
import java.awt.*;

public class Parametre extends JPanel {
    /*
     * private JComboBox<String> themeBox;
     * private ArrayList<String> themes;
     */
    private PageManager pageManager;
    public Parametre(PageManager pageManager) {
        this.pageManager = pageManager;
        pageManager.setTitle("Paramètre");

        this.add(new JLabel("Thèmes: "));
    }
}
