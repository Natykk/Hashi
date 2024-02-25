package com.hashi.gestion_des_menus;

import javax.swing.*;

public class PageManager extends JFrame {
    private static PageManager instance;

    private PageManager() {
        instance = this;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1280, 720);

        getContentPane().add(new EcranLancement());
        setVisible(true);
    }

    public static PageManager getInstance() {
        if (instance == null)
            new PageManager();

        return instance;
    }

    public static void setPageTitle(String title) {
        getInstance().setTitle(title);
    }

    public static void changerPage(JPanel nouvellePage) {
        getInstance().getContentPane().removeAll();
        getInstance().getContentPane().add(nouvellePage);
        getInstance().revalidate();
        getInstance().repaint();
    }

    public static void afficherMessage(String message) {
        JOptionPane.showMessageDialog(getInstance(), message);
    }

    public static void MessageErreur(String m1, String m2) {
        JOptionPane.showMessageDialog(getInstance(), m1, m2, JOptionPane.ERROR_MESSAGE);
    }
}
