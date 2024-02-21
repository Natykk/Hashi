package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.*;

public class Panel extends JPanel implements ImageComponent<Panel> {
    private Image image;
    private StyleWrapper style;

    public Panel(StyleWrapper style) {
        super();
        init(style);
    }

    public Panel(StyleWrapper style, LayoutManager layout) {
        super(layout);
        init(style);
    }

    public Panel(StyleWrapper style, LayoutManager layout, String image_res) {
        super(layout);
        init(style);
        setImage(image_res);
    }

    private void init(StyleWrapper style) {
        this.style = style;
        this.image = new Image(style, this);

        style.initPanel(this);
    }

    public Panel setImage(String image_res) {
        image.setImage(image_res);

        return this;
    }

    public java.awt.Image getImage() {
        return image.getImage();
    }

    protected void paintComponent(Graphics g) {
        style.paintPanel(this, (Graphics2D) g);
    }

    protected void paintBorder(Graphics g) {
        style.paintPanelBorder(this, (Graphics2D) g);
    }
}