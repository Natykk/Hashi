package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class Label extends JLabel implements FontSize<Label> {
    private StyleWrapper style;
    private int font_size = 20;

    public Label(StyleWrapper style) {
        super();

        this.style = style;

        style.initLabel(this);
    }

    public Label(StyleWrapper style, String text) {
        super(text);

        this.style = style;

        style.initLabel(this);
    }

    public Label(StyleWrapper style, String text, int horizontal_alignment) {
        super(text, horizontal_alignment);

        this.style = style;

        style.initLabel(this);
    }

    public Label setFontSize(int size) {
        font_size = size;

        style.initLabel(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    @Override
    public String getText() {
        if (style == null)
            return "";

        return style.getLanguage().getString(super.getText());
    }

    protected void paintComponent(Graphics g) {
        style.paintLabel(this, (Graphics2D) g);
    }
}