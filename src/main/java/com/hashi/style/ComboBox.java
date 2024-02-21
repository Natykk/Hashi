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
        init(style);
    }

    public ComboBox(StyleWrapper style, E[] items) {
        super(items);
        init(style);
    }

    public ComboBox(StyleWrapper style, Vector<E> items) {
        super(items);
        init(style);
    }

    private void init(StyleWrapper style) {
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

    @Override
    protected void paintComponent(Graphics g) {
        style.paintComboBox(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        style.paintComboBoxBorder(this, (Graphics2D) g);
    }
}