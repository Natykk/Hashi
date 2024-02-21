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
        StyleWrapper.getInstance().initLabel(this);
    }

    public Label setAsRawText() {
        is_raw_text = true;

        return this;
    }

    public Label setFontSize(int size) {
        font_size = size;

        StyleWrapper.getInstance().initLabel(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    @Override
    public String getText() {
        if (is_raw_text)
            return super.getText();

        try {
            return Language.getInstance().getString(super.getText());
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleWrapper.getInstance().paintLabel(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleWrapper.getInstance().paintLabelBorder(this, (Graphics2D) g);
    }
}