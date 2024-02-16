package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.*;

public class ComboBox<E> extends JComboBox<E> implements FontSize<ComboBox<E>> {
    private StyleWrapper style;
    private int font_size = 20;

    public ComboBox(StyleWrapper style) {
        super();

        this.style = style;

        style.initComboBox(this);
    }

    public ComboBox(StyleWrapper style, E[] items) {
        super(items);

        this.style = style;

        style.initComboBox(this);
    }

    public ComboBox(StyleWrapper style, Vector<E> items) {
        super(items);

        this.style = style;

        style.initComboBox(this);
    }

    public ComboBox<E> setFontSize(int size) {
        font_size = size;

        style.initComboBox(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    protected void paintComponent(Graphics g) {
        style.paintComboBox(this, (Graphics2D) g);
    }
}