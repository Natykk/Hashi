package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.*;

public class ComboBox<E> extends JComboBox<E> implements FontSize<ComboBox<E>> {
    private int font_size = 20;

    public ComboBox() {
        super();
        init();
    }

    public ComboBox(E[] items) {
        super(items);
        init();
    }

    public ComboBox(Vector<E> items) {
        super(items);
        init();
    }

    private void init() {
        StyleWrapper.getInstance().initComboBox(this);
    }

    public ComboBox<E> setFontSize(int size) {
        font_size = size;

        StyleWrapper.getInstance().initComboBox(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleWrapper.getInstance().paintComboBox(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleWrapper.getInstance().paintComboBoxBorder(this, (Graphics2D) g);
    }
}