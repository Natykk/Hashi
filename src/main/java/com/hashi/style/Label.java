package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import com.hashi.Language;

public class Label extends JLabel implements FontSize<Label> {
    private int font_size = 20;
    private boolean is_raw_text = false;

    public Label() {
        super();
        init();
    }

    public Label(String text) {
        super(text);
        init();
    }

    private void init() {
        setOpaque(false);

        StyleManager.getInstance().initLabel(this);
    }

    public Label setAsRawText() {
        is_raw_text = true;

        return this;
    }

    public Label setFontSize(int size) {
        font_size = size;

        StyleManager.getInstance().initLabel(this);

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
            return Language.getString(super.getText());
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleManager.getInstance().paintLabel(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleManager.getInstance().paintLabelBorder(this, (Graphics2D) g);
    }
}