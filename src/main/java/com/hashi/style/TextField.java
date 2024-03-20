package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import com.hashi.LanguageManager;

/**
 * Boite de texte stylisé pouvant contenir du texte internationalisé ou non.<br>
 * <br>
 * 
 * Pour changer le style il faut passé par le
 * {@link com.hashi.style.StyleManager} et lui donner un
 * {@link com.hashi.style.Style}.<br>
 * La taille de police peut être géré via l'interface
 * {@link com.hashi.style.FontSize}.<br>
 * <br>
 * 
 * L'internationalisation du est gérer par le
 * {@link com.hashi.LanguageManager}.
 */
public class TextField extends JTextField implements FontSize<TextField> {
    private int font_size = 20;

    /**
     * Créer une boite de texte sans texte.
     */
    public TextField() {
        super();
        init();
    }

    /**
     * Créer une boite de texte avec texte.
     * 
     * @param text
     */
    public TextField(String text) {
        super(LanguageManager.getString(text));
        init();
    }

    /**
     * Créer une boite de texte avec un nombre de colonnes fixe.
     * 
     * @param columns
     */
    public TextField(int columns) {
        super(columns);
        init();
    }

    /**
     * Créer une boite de texte avec du texte et un nombre de colonnes fixe.
     * 
     * @param text
     * @param columns
     */
    public TextField(String text, int columns) {
        super(LanguageManager.getString(text), columns);
        init();
    }

    private void init() {
        setOpaque(false);

        StyleManager.getInstance().initTextField(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @return Retourne un {@link com.hashi.style.TextField} afin de pouvoir chainer
     *         les appels de fonctions.
     */
    public TextField setFontSize(int size) {
        font_size = size;

        StyleManager.getInstance().initTextField(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleManager.getInstance().paintTextField(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleManager.getInstance().paintTextFieldBorder(this, (Graphics2D) g);
    }
}