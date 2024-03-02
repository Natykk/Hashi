package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

import com.hashi.Language;

public class Button extends JButton implements FontSize<Button>, ImageComponent<Button> {
    private int font_size = 20;
    private boolean is_raw_text = false;
    private Image image;

    public Button() {
        super();
        init();
    }

    public Button(String text) {
        super(text);
        init();
    }

    private void init() {
        setOpaque(false);

        this.image = new Image(this);

        StyleManager.getInstance().initButton(this);
    }

    public Button setAsRawText() {
        is_raw_text = true;

        return this;
    }

    public Button setImage(String image_res) {
        image.setImage(image_res);

        return this;
    }

    public java.awt.Image getImage() {
        return image.getImage();
    }

    public Button setFontSize(int size) {
        font_size = size;

        StyleManager.getInstance().initButton(this);

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
        StyleManager.getInstance().paintButton(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleManager.getInstance().paintButtonBorder(this, (Graphics2D) g);
    }
}