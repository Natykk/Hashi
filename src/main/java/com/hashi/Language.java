package com.hashi;

import java.util.ResourceBundle;

public class Language {
    protected ResourceBundle bundle;

    public Language() {
        bundle = ResourceBundle.getBundle("com.hashi.language.fr");
    }

    public void switchLanguage(String language) {
        bundle = ResourceBundle.getBundle("com.hashi.language." + language);
    }

    public String getString(String key) {
        return bundle.getString(key);
    }
}
