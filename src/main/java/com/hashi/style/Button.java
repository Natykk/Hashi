package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import com.hashi.LanguageManager;

/**
 * Bouton stylisé pouvant contenir une image ou du texte internationalisé ou
 * non.<br>
 * <br>
 * 
 * Pour changer le style il faut passé par le
 * {@link com.hashi.style.StyleManager} et lui donner un
 * {@link com.hashi.style.Style}.<br>
 * La taille de police peut être géré via l'interface
 * {@link com.hashi.style.FontSize} et l'image par l'interface
 * {@link com.hashi.style.ImageComponent}.<br>
 * <br>
 * 
 * L'internationalisation du est gérer par le
 * {@link com.hashi.LanguageManager}.
 */
public class Button extends JButton implements FontSize<Button>, ImageComponent<Button>, AsRawText<Button> {
    private int font_size = 20;
    private boolean is_raw_text = false;
    private Image image;

    /**
     * Créer un bouton sans texte ni image.
     */
    public Button() {
        super();
        init();
    }

    /**
     * Créer un bouton avec texte.
     * 
     * @param text
     */
    public Button(String text) {
        super(text);
        init();
    }

    private void init() {
        setOpaque(false);

        this.image = new Image(this);

        StyleManager.getInstance().initButton(this);
    }

    /**
     * Désactive l'internationalisation du texte, pour permettre de changer le texte
     * par ce que l'on veut.
     * 
     * @return Retourne un {@link com.hashi.style.Button} afin de pouvoir chainer
     *         les appels de fonctions.
     */
    public Button setAsRawText() {
        is_raw_text = true;

        return this;
    }

    /**
     * {@inheritDoc}
     * 
     * @return Retourne un {@link com.hashi.style.Button} afin de pouvoir chainer
     *         les appels de fonctions.
     */
    public Button setImage(String image_res) {
        image.setImage(image_res);

        return this;
    }

    public java.awt.Image getImage() {
        return image.getImage();
    }

    /**
     * {@inheritDoc}
     * 
     * @return Retourne un {@link com.hashi.style.Button} afin de pouvoir chainer
     *         les appels de fonctions.
     */
    public Button setFontSize(int size) {
        font_size = size;

        StyleManager.getInstance().initButton(this);

        return this;
    }

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
        StyleManager.getInstance().paintButton(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleManager.getInstance().paintButtonBorder(this, (Graphics2D) g);
    }
}