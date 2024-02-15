package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.*;

public class ComboBox<E> extends JComboBox<E> {
    private StyleWrapper style;

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

    protected void paintComponent(Graphics g) {
        style.paintComboBox(this, (Graphics2D) g);
    }
}