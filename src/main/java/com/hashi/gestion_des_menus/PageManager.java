package com.hashi.gestion_des_menus;

import javax.swing.*;

import com.hashi.Language;
import com.hashi.style.Panel;

public class PageManager extends JFrame {
    private static PageManager instance;

    private PageManager() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);

        instance = this;

        getContentPane().add(new EcranLancement());
        setVisible(true);
    }

    @Override
    public void setTitle(String title_key) {
        super.setTitle(Language.getString(title_key));
    }

    public static PageManager getInstance() {
        if (instance == null)
            new PageManager();

        return instance;
    }

    public static void changerPage(Panel nouvellePage) {
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
