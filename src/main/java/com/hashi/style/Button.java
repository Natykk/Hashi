package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class Button extends JButton implements FontSize<Button>, ImageComponent<Button> {
    private StyleWrapper style;
    private int font_size = 20;
    private Image image;

    public Button(StyleWrapper style) {
        super();
        init(style);
    }

    public Button(StyleWrapper style, Action a) {
        super(a);
        init(style);
    }

    public Button(StyleWrapper style, String text) {
        super(text);
        init(style);
    }

    private void init(StyleWrapper style) {
        this.style = style;
        this.image = new Image(style, this);

        style.initButton(this);
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

        style.initButton(this);

        return this;
    }

    public int getFontSize() {
        return font_size;
    }

    @Override
    public String getText() {
        if (style == null || super.getText().isEmpty())
            return "";

        return style.getLanguage().getString(super.getText());
    }

    protected void paintComponent(Graphics g) {
        style.paintButton(this, (Graphics2D) g);
    }
}