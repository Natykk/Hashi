package com.hashi.style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.*;

public class Panel extends JPanel implements ImageComponent<Panel> {
    private Image image;

    public Panel() {
        super();
        init();
    }

    public Panel(LayoutManager layout) {
        super(layout);
        init();
    }

    public Panel(LayoutManager layout, String image_res) {
        super(layout);
        init();
        setImage(image_res);
    }

    private void init() {
        this.image = new Image(this);

        StyleWrapper.getInstance().initPanel(this);
    }

    public Panel setImage(String image_res) {
        image.setImage(image_res);

        return this;
    }

    public java.awt.Image getImage() {
        return image.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        StyleWrapper.getInstance().paintPanel(this, (Graphics2D) g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        StyleWrapper.getInstance().paintPanelBorder(this, (Graphics2D) g);
    }
}