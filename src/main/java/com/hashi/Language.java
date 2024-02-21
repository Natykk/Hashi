package com.hashi;

import java.util.ResourceBundle;

/**
 * La classe {@link com.hashi.Language} gère l'internationalisation du jeu.
 */
public class Language {
    private ResourceBundle bundle;

    /**
     * Initialisation de la classe {@link com.hashi.Language}.
     */
    public Language() {
        bundle = ResourceBundle.getBundle("com.hashi.language.fr");
    }

    /**
     * Change la langue cible du jeu, pour celle passer en paramètre.
     * 
     * @param language la nouvelle langue cible.
     */
    public void switchLanguage(String language) {
        bundle = ResourceBundle.getBundle("com.hashi.language." + language);
    }

    /**
     * Récupère le texte correspondant à la a langue cible.
     * 
     * @param key la clé correspondant au texte devant être afficher.
     * @return le texte demandé sous forme de {@link java.lang.String}
     */
    public String getString(String key) {
        return bundle.getString(key);
    }
}
