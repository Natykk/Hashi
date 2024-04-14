package com.hashi.menu;

import javax.swing.*;

import com.hashi.LanguageManager;
import com.hashi.Profil;
import com.hashi.style.Panel;

/**
 * La classe {@link com.hashi.menu.PageManager} est un singleton qui gère quel
 * menu ou écran afficher sur la fenêtre, ansi que le titre de la fenêtre.
 * 
 * Cette classe permet aussi d'afficher des popup.
 */
public class PageManager extends JFrame {
    /**
     * L'instance de {@link com.hashi.menu.PageManager}.
     */
    private static PageManager instance;

    /**
     * Profil courant.
     */
    private Profil profil;

    private PageManager() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);

        instance = this;
        profil = null;

        getContentPane().add(new StartScreen());
        setVisible(true);
    }

    /**
     * Définit le titre de la fenêtre.
     * 
     * @param title_key clé pour le titre internationaliser.
     */
    @Override
    public void setTitle(String title_key) {
        super.setTitle(LanguageManager.getString(title_key));
    }

    /**
     * Récupère l'instance de la classe {@link com.hashi.menu.PageManager} et la
     * créer si elle n'existe pas.
     * 
     * @return Retourne l'instance de {@link com.hashi.menu.PageManager}.
     */
    public static PageManager getInstance() {
        if (instance == null)
            new PageManager();

        return instance;
    }

    /**
     * Récupère le {@link com.hashi.Profil} à utiliser.
     * 
     * @return Retourne le {@link com.hashi.Profil} à utiliser.
     */
    public static Profil getProfil() {
        return getInstance().profil;
    }

    /**
     * Définit le {@link com.hashi.Profil} à utiliser.
     * 
     * @param profil le {@link com.hashi.Profil}.
     */
    public static void setProfil(Profil profil) {
        getInstance().profil = profil;
    }

    /**
     * Change le menu ou écran courament afficher sur la fenêtre.
     * 
     * @param nouvellePage le {@link com.hashi.style.Panel} à remplacer.
     */
    public static void changerPage(Panel nouvellePage) {
        getInstance().getContentPane().removeAll();
        getInstance().getContentPane().add(nouvellePage);
        getInstance().revalidate();
        getInstance().repaint();
    }

    /**
     * Affiche une popup d'erreur avec un message et un titre.
     * 
     * @param titre   le titre de la popup.
     * @param message le message de la popup.
     */
    public static void afficherMessageErreur(String titre, String message) {
        JOptionPane.showMessageDialog(getInstance(), message, titre, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Point d'entrée du jeu.
     * 
     * @param args arguments de la ligne de commande du jeu.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> PageManager.getInstance());
    }
}
