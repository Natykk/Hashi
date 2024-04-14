package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.*;

/**
 * Menu déroulant stylisé.<br>
 * <br>
 * 
 * Pour changer le style il faut passé par le
 * {@link com.hashi.style.StyleManager} et lui donner un
 * {@link com.hashi.style.Style}.<br>
 * 
 * @param <E> le type des éléments contenu par le menu déroulant.
 */
public class ComboBox<E> extends JComboBox<E> implements FontSize<ComboBox<E>> {
    /**
     * Taille de police.
     */
    private int font_size = 20;

    /**
     * Créer un menu déroulant vide.
     */
    public ComboBox() {
        super();
        init();
    }

    /**
     * Créer un menu déroulant à partir d'une liste d'éléments.
     * 
     * @param items la liste des éléments du menu déroulant sous forme de tableau.
     */
    public ComboBox(E[] items) {
        super(items);
        init();
    }

    /**
     * Créer un menu déroulant à partir d'une liste d'éléments.
     * 
     * @param items la liste des éléments du menu déroulant sous forme de
     *              {@link java.util.Vector}.
     */
    public ComboBox(Vector<E> items) {
        super(items);
        init();
    }

    private void init() {
        setOpaque(false);

        StyleManager.getInstance().initComboBox(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @return Retourne une {@link com.hashi.style.ComboBox} afin de
     *         pouvoir chainer les appels de fonctions.
     */
    @Override
    public ComboBox<E> setFontSize(int size) {
        font_size = size;

        StyleManager.getInstance().initComboBox(this);

        return this;
    }

    @Override
    public int getFontSize() {
        return font_size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleManager.getInstance().paintComboBox(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleManager.getInstance().paintComboBoxBorder(this, (Graphics2D) g);
    }
}