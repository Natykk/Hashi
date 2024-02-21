package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import com.hashi.Language;

public class TextField extends JTextField implements FontSize<TextField> {
    private int font_size = 20;

    public TextField() {
        super();
        init();
    }

    public TextField(String text) {
        super(Language.getInstance().getString(text));
        init();
    }

    public TextField(int columns) {
        super(columns);
        init();
    }

    public TextField(String text, int columns) {
        super(Language.getInstance().getString(text), columns);
        init();
    }

    private void init() {
        StyleWrapper.getInstance().initTextField(this);
    }

    public TextField setFontSize(int size) {
        font_size = size;

        StyleWrapper.getInstance().initTextField(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleWrapper.getInstance().paintTextField(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleWrapper.getInstance().paintTextFieldBorder(this, (Graphics2D) g);
    }
}