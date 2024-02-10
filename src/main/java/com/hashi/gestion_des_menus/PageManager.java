package com.hashi.gestion_des_menus;

import javax.swing.*;

public class PageManager {

    public static void changerPage(JFrame frame, JPanel nouvellePage) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(nouvellePage);
        frame.revalidate();
        frame.repaint();
    }

    public static void afficherMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public static void MessageErreur(JFrame frame, String m1, String m2) {
        JOptionPane.showMessageDialog(frame, m1, m2, JOptionPane.ERROR_MESSAGE);
    }
}
