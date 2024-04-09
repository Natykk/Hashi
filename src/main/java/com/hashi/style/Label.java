package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import com.hashi.LanguageManager;

/**
 * Étiquette stylisé pouvant du texte internationalisé ou non.<br>
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
public class Label extends JLabel implements FontSize<Label>, AsRawText<Label> {
    private int font_size = 20;
    private boolean is_raw_text = false;

    /*
     * Créer une étiquette sans texte.
     */
    public Label() {
        super();
        init();
    }

    /**
     * Créer une étiquette avec texte.
     * 
     * @param text
     */
    public Label(String text) {
        super(text);
        init();
    }

    private void init() {
        setOpaque(false);

        StyleManager.getInstance().initLabel(this);
    }

    /**
     * Désactive l'internationalisation du texte, pour permettre de changer le texte
     * par ce que l'on veut.
     * 
     * @return Retourne un {@link com.hashi.style.Label} afin de pouvoir chainer
     *         les appels de fonctions.
     */
    @Override
    public Label setAsRawText() {
        is_raw_text = true;

        return this;
    }

    /**
     * {@inheritDoc}
     * 
     * @return Retourne un {@link com.hashi.style.Label} afin de pouvoir chainer
     *         les appels de fonctions.
     */
    @Override
    public Label setFontSize(int size) {
        font_size = size;

        StyleManager.getInstance().initLabel(this);

        return this;
    }

    @Override
    public int getFontSize() {
        return font_size;
    }

    @Override
    public String getText() {
        if (super.getText().isEmpty())
            return "";

        if (is_raw_text)
            return super.getText();

        try {
            return LanguageManager.getString(super.getText());
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleManager.getInstance().paintLabel(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleManager.getInstance().paintLabelBorder(this, (Graphics2D) g);
    }
}