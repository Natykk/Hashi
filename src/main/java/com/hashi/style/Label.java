package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class Label extends JLabel implements FontSize<Label> {
    private StyleWrapper style;
    private int font_size = 20;
    private boolean is_raw_text = false;

    public Label(StyleWrapper style) {
        super();
        init(style);
    }

    public Label(StyleWrapper style, String text) {
        super(text);
        init(style);
    }

    private void init(StyleWrapper style) {
        this.style = style;

        style.initLabel(this);
    }

    public Label setAsRawText() {
        is_raw_text = true;

        return this;
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

        if (is_raw_text)
            return super.getText();

        try {
            return style.getLanguage().getString(super.getText());
        } catch (Exception e) {
            return "";
        }
    }

    protected void paintComponent(Graphics g) {
        style.paintLabel(this, (Graphics2D) g);
    }
}