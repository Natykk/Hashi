package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import com.hashi.LanguageManager;

public class TextField extends JTextField implements FontSize<TextField> {
    private int font_size = 20;

    public TextField() {
        super();
        init();
    }

    public TextField(String text) {
        super(LanguageManager.getString(text));
        init();
    }

    public TextField(int columns) {
        super(columns);
        init();
    }

    public TextField(String text, int columns) {
        super(LanguageManager.getString(text), columns);
        init();
    }

    private void init() {
        setOpaque(false);

        StyleManager.getInstance().initTextField(this);
    }

    public TextField setFontSize(int size) {
        font_size = size;

        StyleManager.getInstance().initTextField(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleManager.getInstance().paintTextField(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleManager.getInstance().paintTextFieldBorder(this, (Graphics2D) g);
    }
}