package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.*;

/**
 * Panneau stylisé pouvant contenir une image.<br>
 * <br>
 * 
 * Pour changer le style il faut passé par le
 * {@link com.hashi.style.StyleManager} et lui donner un
 * {@link com.hashi.style.Style}.<br>
 * L'image peut être géré par l'interface
 * {@link com.hashi.style.ImageComponent}.<br>
 * <br>
 * 
 * L'internationalisation du est gérer par le
 * {@link com.hashi.LanguageManager}.
 */
public class Panel extends JPanel implements ImageComponent<Panel> {
    private Image image;

    /*
     * Créer un panneau sans image.
     */
    public Panel() {
        super();
        init();
    }

    /**
     * Créer un panneau sans image avec un layout.
     * 
     * @param layout
     */
    public Panel(LayoutManager layout) {
        super(layout);
        init();
    }

    /**
     * Créer un panneau avec une image et un layout.
     * 
     * @param layout
     * @param image_res
     */
    public Panel(LayoutManager layout, String image_res) {
        super(layout);
        init();
        setImage(image_res);
    }

    private void init() {
        setOpaque(false);

        this.image = new Image(this);

        StyleManager.getInstance().initPanel(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @return Retourne un {@link com.hashi.style.Panel} afin de pouvoir chainer
     *         les appels de fonctions.
     */
    @Override
    public Panel setImage(String image_res) {
        image.setImage(image_res);

        return this;
    }

    @Override
    public java.awt.Image getImage() {
        return image.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleManager.getInstance().paintPanel(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleManager.getInstance().paintPanelBorder(this, (Graphics2D) g);
    }
}