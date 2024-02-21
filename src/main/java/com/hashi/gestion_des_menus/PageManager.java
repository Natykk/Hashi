package com.hashi.gestion_des_menus;

import javax.swing.*;

public class PageManager extends JFrame{
    public PageManager(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1280, 720);

        changerPage(new EcranLancement(this));

        setVisible(true);
    }

    public void changerPage(JPanel nouvellePage) {
        this.getContentPane().removeAll();
        this.getContentPane().add(nouvellePage);
        this.revalidate();
        this.repaint();
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void MessageErreur(String m1, String m2) {
        JOptionPane.showMessageDialog(this, m1, m2, JOptionPane.ERROR_MESSAGE);
    }
}
