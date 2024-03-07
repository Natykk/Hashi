package com.hashi;

import java.util.ResourceBundle;

/**
 * La classe {@link com.hashi.LanguageManager} gère l'internationalisation du
 * jeu.
 */
public class LanguageManager {
    private static LanguageManager instance;
    private ResourceBundle bundle;
    private String language;

    private LanguageManager() {
        language = "fr";
        bundle = ResourceBundle.getBundle("com.hashi.language.fr");
    }

    /**
     * Récupère une instance de la classe {@link com.hashi.LanguageManager}.
     */
    public static LanguageManager getInstance() {
        if (instance == null)
            instance = new LanguageManager();

        return instance;
    }

    /**
     * Change la langue cible du jeu, pour celle passer en paramètre.
     * 
     * @param language la nouvelle langue cible.
     */
    public static void setLanguage(String language) {
        getInstance().language = language;
        getInstance().bundle = ResourceBundle.getBundle("com.hashi.language." + language);
    }

    /**
     * Récupère la langue utiliser.
     * 
     * @return la langue utililer.
     */
    public static String getLanguage() {
        return getInstance().language;
    }

    /**
     * Récupère le texte correspondant à la a langue cible.
     * 
     * @param key la clé correspondant au texte devant être afficher.
     * @return le texte demandé sous forme de {@link java.lang.String}.
     */
    public static String getString(String key) {
        return getInstance().bundle.getString(key);
    }
}
