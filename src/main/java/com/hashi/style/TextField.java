package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class TextField extends JTextField implements FontSize<TextField> {
    private StyleWrapper style;
    private int font_size = 20;

    public TextField(StyleWrapper style) {
        super();
        init(style);
    }

    public TextField(StyleWrapper style, String text) {
        super(style.getLanguage().getString(text));
        init(style);
    }

    public TextField(StyleWrapper style, int columns) {
        super(columns);
        init(style);
    }

    public TextField(StyleWrapper style, String text, int columns) {
        super(style.getLanguage().getString(text), columns);
        init(style);
    }

    private void init(StyleWrapper style) {
        this.style = style;

        style.initTextField(this);
    }

    public TextField setFontSize(int size) {
        font_size = size;

        style.initTextField(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    protected void paintComponent(Graphics g) {
        style.paintTextField(this, (Graphics2D) g);
    }
}